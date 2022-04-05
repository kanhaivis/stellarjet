package com.ns.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ns.activity.MyBookingUserDetailsActivity;
import com.ns.adapter.MyBookingAdapter;
import com.ns.database.SharedPref;
import com.ns.model.BoardingList.BoardingListResponse;
import com.ns.model.ReUseModel.BookingListBean;
import com.ns.retrofit.APICallBack;
import com.ns.stellarjet.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class UpcomingFragment extends Fragment implements MyBookingAdapter.BoardingPassRowClick {
    private static final String TAG = "MyBookingUpcomingFragme";

    private int offset = 0;
    private int limit = 10;

    private ProgressBar mProgressBar;

    private RecyclerView mRecyclerView;
    private BoardingListResponse mBoardingListResponse;
    private MyBookingAdapter mMyBookingAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPref.getSharedPreferences(getContext()).setDiningkDetails(0,0);
//        SharedPref.getSharedPreferences(getContext()).setDiningkDetailsTest(0);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mybooking_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        BoardingPassAPICall();

    }

    private void initView(View view) {

        mRecyclerView = view.findViewById(R.id.recyclerView_mybooking);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        mProgressBar = view.findViewById(R.id.progressBar);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: " + requestCode);
        if (resultCode == 101) {
            BoardingPassAPICall();
        } else {
            ShowResultAdapter(mBoardingListResponse);
            Log.d(TAG, "onActivityResult: "+mBoardingListResponse);
        }


        /*if (SharedPref.getSharedPreferences(getContext()).getDiningDeatils() == 1) {
            Log.d(TAG, "onActivityResult: ");
            SharedPref.getSharedPreferences(getContext()).setDiningkDetailsTest(0);
            BoardingPassAPICall();
        } else {
            Log.d(TAG, "onActivityResult: ");
        }*/
    }


    /**
     *  User base api call
     *
     * call back response
     */
    private void BoardingPassAPICall() {
        mProgressBar.setVisibility(View.VISIBLE);

        APICallBack.getBookingHistoryData(new APICallBack.IBookingHistory() {
            @Override
            public void onBookingHistoryData(BoardingListResponse response) {
                mProgressBar.setVisibility(View.GONE);
                if (response.getResultcode() == 1) {
                    Log.d(TAG, "onNext: " + response);
                    ShowBoardingResult(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                mProgressBar.setVisibility(View.GONE);
            }
        }, getContext(), offset, limit, "upcoming");

    }

    /**
     * BardingPass response
     *
     * @param response
     */
    private void ShowBoardingResult(BoardingListResponse response) {

        mBoardingListResponse = response;

        ShowResultAdapter(response);

    }

    private void ShowResultAdapter(BoardingListResponse response) {
        if (mBoardingListResponse.getData() != null) {
            mMyBookingAdapter = new MyBookingAdapter(getContext(), response.getData().getBooking_list(), true);
            mMyBookingAdapter.setBoardingRowClick(this);
            mRecyclerView.setAdapter(mMyBookingAdapter);
        } else {
            Toast.makeText(getContext(), "No result", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * Boardingpass single row data to pass
     * Single UI show all data
     *
     * @param bookingListBean
     */
    @Override
    public void onSelectRowData(BookingListBean bookingListBean, int pos) {

        Intent intent = new Intent(getContext(), MyBookingUserDetailsActivity.class);
        intent.putExtra("bookingListBean", bookingListBean);
        intent.putExtra("status", false);
        intent.putExtra("row_pos", pos);

        startActivityForResult(intent, 101);
    }


}

