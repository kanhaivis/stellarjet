package com.ns.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.ns.model.Download.DownloadPojo;
import com.ns.stellarjet.R;
import com.ns.utils.ConstantMethod;
import com.ns.utils.UiConstants;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import de.number42.subsampling_pdf_decoder.PDFDecoder;
import de.number42.subsampling_pdf_decoder.PDFRegionDecoder;

public class TicketDownloadPager extends PagerAdapter {
    private static final String TAG = "TicketDownloadPager";

    private Context context;

    ArrayList<DownloadPojo> mDownloadPojos;

    public TicketDownloadPager(Context context, ArrayList<DownloadPojo> DownloadPojos) {
        super();
        this.context = context;
        this.mDownloadPojos = DownloadPojos;

    }


    @Override
    public Object instantiateItem(@NonNull ViewGroup collection, int position) {
        LayoutInflater inflater = LayoutInflater.from(collection.getContext());

        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.row_show_ticket, collection, false);


        ImageView imageView = layout.findViewById(R.id.row_showticket_img);
        String url = mDownloadPojos.get(position).getTicketUrlLinked();
        String last = url.substring(url.lastIndexOf('/') + 1);

        String Path = Environment.getExternalStorageDirectory().getPath().toString() + "/" + UiConstants.FOLDER_NAME + "/" + last;
        System.out.println("Path  : " + Path);

        ArrayList<Bitmap> bitmaps = ConstantMethod.pdfToBitmap(new File(Path), context);
        Log.d(TAG, "instantiateItem: " + bitmaps);
        if (bitmaps.size() != 0) {
            imageView.setImageBitmap(bitmaps.get(0));
        }

        collection.addView(layout);
        return layout;
    }


    public int getCount() {
        return mDownloadPojos.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        container.removeView((View) view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}