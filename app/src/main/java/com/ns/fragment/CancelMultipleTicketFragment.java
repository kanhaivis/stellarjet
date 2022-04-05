package com.ns.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ns.adapter.CancledTickedAdapter;
import com.ns.database.SharedPref;
import com.ns.model.CancelBooking.CancelBookingInfo;
import com.ns.model.CancelBooking.CancelBookingRequest;
import com.ns.model.CommonPojo;
import com.ns.retrofit.APICallBack;
import com.ns.stellarjet.R;
import com.ns.utils.ConstantMethod;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.ns.adapter.CancledTickedAdapter.iCountPosition;

public class CancelMultipleTicketFragment extends Fragment implements View.OnClickListener, CancledTickedAdapter.ICanceledchecked {
    private static final String TAG = "CancledMultipleTickerFr";


    private RecyclerView mTicketRecyclerView;
    private TextView mCancledAllSeatTxt;
    private Button selectBtn;

    private CancledTickedAdapter aCancledTickedAdapter;


    private boolean AllSeatChekced = true;

    private ArrayList<Integer> seatListId = new ArrayList<>();
    private int bookingId;


    private ArrayList<CancelBookingInfo> mCancledBookingInfoList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bookingId = this.getArguments().getInt("bookingid");
        mCancledBookingInfoList = (ArrayList<CancelBookingInfo>) this.getArguments().getSerializable("canceledList");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cancled_multiple_ticket, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        ((TextView) view.findViewById(R.id.title)).setText(getContext().getResources().getString(R.string.cancel_heading));
        ((TextView) view.findViewById(R.id.subtitle)).setText(getContext().getResources().getString(R.string.cancel_details));

        mCancledAllSeatTxt = view.findViewById(R.id.cancled_all_seats);
        mCancledAllSeatTxt.setOnClickListener(this);
        selectBtn = view.findViewById(R.id.cancel_ticet_btn);
        selectBtn.setOnClickListener(this);


        mTicketRecyclerView = view.findViewById(R.id.cancled_ticket_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mTicketRecyclerView.setLayoutManager(layoutManager);

        aCancledTickedAdapter = new CancledTickedAdapter(mCancledBookingInfoList,0);
        aCancledTickedAdapter.setCanceledchecked(this);
        mTicketRecyclerView.setAdapter(aCancledTickedAdapter);


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.cancled_all_seats:

                if (AllSeatChekced) {
                    AllSeatChekced = false;
                    aCancledTickedAdapter.CheckedUncheckedStatus(true);
                    mCancledAllSeatTxt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_tik_check, 0);
                    selectBtn.setVisibility(View.VISIBLE);
                    selectBtn.setText("Cancel All Seats");
                } else {
                    AllSeatChekced = true;
                    aCancledTickedAdapter.CheckedUncheckedStatus(false);
                    selectBtn.setVisibility(View.GONE);
                    mCancledAllSeatTxt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_tik_uncheck, 0);
                }
                break;
            case R.id.cancel_ticet_btn:
                Log.d(TAG, "onClick: " + mCancledBookingInfoList);
                seatListId.clear();
                for (CancelBookingInfo info : mCancledBookingInfoList) {
                    if (info.isCheckStatus() == true) {
                        seatListId.add(info.getSeatId());
                    }
                }
                APICall();
                break;
        }
    }


    public void APICall() {


        String token = SharedPref.getSharedPreferences(getContext()).getTocken();
        CancelBookingRequest request = new CancelBookingRequest(token, bookingId, seatListId);
        Log.e(TAG, "onAction: " + request);

        ConstantMethod.ShowProgressBar(getActivity());
        APICallBack.getCancelData(new APICallBack.ICancelTicket() {
            @Override
            public void onCancelData(CommonPojo commonPojo) {

                Log.d(TAG, "onCancelData: " + commonPojo);
                ConstantMethod.HideProgressBar(getActivity());
                if (commonPojo.getResultcode() == 1) {
                    ConstantMethod.ShowToastMessage(getContext(), commonPojo.getMessage());
                    mCancledBookingInfoList.clear();
                    mITicketCanceled.onAction(true);
                }
            }

            @Override
            public void onError(Throwable e) {
                ConstantMethod.HideProgressBar(getActivity());
            }
        }, getActivity(), request);

    }

    @Override
    public void onRowClick(boolean status, int position) {


        int arryCount = mCancledBookingInfoList.size();


        if (iCountPosition == arryCount) {
            selectBtn.setVisibility(View.VISIBLE);
            selectBtn.setText("Cancel All Seats");
            AllSeatChekced = false;
            mCancledAllSeatTxt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_tik_check, 0);

        } else if (iCountPosition == 0) {
            selectBtn.setVisibility(View.GONE);
        } else if (iCountPosition > 0) {

            selectBtn.setVisibility(View.VISIBLE);
            if (iCountPosition == 1) {
                selectBtn.setText("Cancel "+iCountPosition+" Seat");
            } else {
                selectBtn.setText("Cancel "+iCountPosition+" Seats");
            }
            AllSeatChekced = true;
            mCancledAllSeatTxt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_tik_uncheck, 0);

        }


        aCancledTickedAdapter.notifyDataSetChanged();
    }


    public ITicketCanceled mITicketCanceled;

    public ITicketCanceled setTicketAction(ITicketCanceled iTicketCanceled) {
        return mITicketCanceled = iTicketCanceled;
    }

    public interface ITicketCanceled {
        void onAction(boolean status);
    }
}





