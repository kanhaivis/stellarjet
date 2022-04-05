package com.ns.fragment;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ns.activity.TicketViewActivity;
import com.ns.adapter.DownloadTickedAdapter;
import com.ns.model.Download.DownloadPojo;
import com.ns.stellarjet.R;
import com.ns.utils.ConstantMethod;
import com.ns.utils.DodnloadMyThread;
import com.ns.utils.UiConstants;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.ns.utils.DownloadPdfMethod.downloadFile;

public class BoardingPassDownloadFragment extends Fragment implements DownloadTickedAdapter.RowTicketClick  {
    private static final String TAG = "BoardingPassDownloadFra";

    private ProgressBar mProgressBar;
    //    private TextView showTicketCount;
    private RelativeLayout mProgressLayout;



    // Bundle Data
    ArrayList<DownloadPojo> mDownloadPojos;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDownloadPojos = (ArrayList<DownloadPojo>) this.getArguments().getSerializable("downloadInfoList");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_boarding_pass_download, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((TextView) view.findViewById(R.id.title)).setText(getContext().getResources().getString(R.string.download_boarding_pass_header));
        ((TextView) view.findViewById(R.id.subtitle)).setText(getContext().getResources().getString(R.string.download_boarding_pass_details));


        Log.d(TAG, "onViewCreated: "+mDownloadPojos);

        mProgressBar = view.findViewById(R.id.download_ticket_progressBar);
//        showTicketCount = view.findViewById(R.id.download_ticket_progress_count_ticket);
        mProgressLayout = view.findViewById(R.id.download_ticket_progressbar_layout);

        RecyclerView recyclerView = view.findViewById(R.id.download_ticket_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        DownloadTickedAdapter adapter = new DownloadTickedAdapter(mDownloadPojos);
        adapter.setRowTicketClick(this);
        recyclerView.setAdapter(adapter);



        // Download all boarding pass in single action after that show boarding padd..
        view.findViewById(R.id.download_all_ticket).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean check = false;

                String path  =ConstantMethod.getFolderPath();

                for (DownloadPojo pojo : mDownloadPojos) {

                    String url = pojo.getTicketUrlLinked();
                    String last = url.substring(url.lastIndexOf('/') + 1);



                    Log.d("Files", "Path: " + path);
                    File directory = new File(path);

                    File[] files = directory.listFiles();
                    Log.d("Files", "Size: " + files.length);
                    if (files.length != 0) {
                        for (int i = 0; i < files.length; i++) {
                            if (files[i].getName().equals(last)) {
                                check = true;
                            }
                            Log.d("Files", "FileName:" + files[i].getName());
                        }
                    }
                }

                if (check) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("downloadInfoList", mDownloadPojos);

                    Intent intent = new Intent(getActivity(), TicketViewActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {


                    DownloadTicketPDFFile(mDownloadPojos);
                }

            }
        });


        Log.d(TAG, "onViewCreated: " + mDownloadPojos);
    }


    private void DownloadTicketPDFFile(ArrayList<DownloadPojo> mDownloadPojos) {
        mProgressLayout.setVisibility(View.VISIBLE);
        int count = 1;
        for (DownloadPojo pojo : mDownloadPojos) {
            String url = pojo.getTicketUrlLinked();
//            String fileName = "kanhai.png";
            String fileName = url.substring(url.lastIndexOf('/') + 1);
            Log.d(TAG, "onCreate: " + fileName);

            DodnloadMyThread mythreads = new DodnloadMyThread(mHandler,url, fileName, count);
            Thread thread = new Thread(mythreads);
            thread.start();

            count++;

        }
    }

    @Override
    public void OnRowClick(DownloadPojo data) {

        String ticket_name = data.getTicketUrlLinked().substring(data.getTicketUrlLinked().lastIndexOf('/') + 1);
        boolean check = ConstantMethod.checks(ticket_name);


        if (check) {
            ArrayList<DownloadPojo> mDownloadPojos = new ArrayList<>();
            mDownloadPojos.add(data);
            Bundle bundle = new Bundle();
            bundle.putSerializable("downloadInfoList", mDownloadPojos);

            Intent intent = new Intent(getActivity(), TicketViewActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);


        } else {

            DodnloadMyThread mythreads = new DodnloadMyThread(mHandler, data.getTicketUrlLinked(), ticket_name, 1);
            Thread thread = new Thread(mythreads);
            thread.start();
        }

    }







    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {

            Log.d(TAG, "handleMessage:-->  " + message.arg1);

            Log.d(TAG, "handleMessage: " + mDownloadPojos.size());

            int seatCount = mDownloadPojos.size();
            mProgressBar.setProgress(message.arg1);
//            showTicketCount.setText(message.arg2 + "/" + seatCount);
            if (seatCount == message.arg2) {

                if (message.arg1 == 100) {
                    mProgressLayout.setVisibility(View.GONE);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("downloadInfoList", mDownloadPojos);

                    Intent intent = new Intent(getActivity(), TicketViewActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

            }

            return false;

        }
    });
}
