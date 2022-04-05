package com.ns.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ns.activity.MyBookingUserDetailsActivity;
import com.ns.adapter.MyBookingAdapter;
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


public class CompletedFragment extends Fragment implements MyBookingAdapter.BoardingPassRowClick {
    private static final String TAG = "MyBookingCompletedFragm";

    private int offset = 0;
    private int limit = 10;

    private static final int PAGE_START = 0;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 100;
    private int currentPage = PAGE_START;

    private RecyclerView mRecyclerView;

    private BoardingListResponse mBoardingListResponse;
    private MyBookingAdapter mMyBookingAdapter;
    private List<BookingListBean> mBookingListBean = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mybooking_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);


    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.d(TAG, "getUserVisibleHint: ");
            BoardingPassAPICall(0);
        }
        else {
            Log.d(TAG, "getUserVisibleHint: ");
        }
    }


    private void initView(View view) {

        mRecyclerView = view.findViewById(R.id.recyclerView_mybooking);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);


       /* mRecyclerView.addOnScrollListener(new PaginationScrollListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1;

                APICall(mBookingListBean.size());
                Log.d(TAG, "loadMoreItems: "+currentPage);

            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });*/
    }


    private void BoardingPassAPICall(int pageCount) {
//        ConstantMethod.ShowProgressBar(getActivity());

        APICallBack.getBookingHistoryData(new APICallBack.IBookingHistory() {
            @Override
            public void onBookingHistoryData(BoardingListResponse response) {
//                ConstantMethod.HideProgressBar(getActivity());
                if (response.getResultcode() == 1) {
                    Log.d(TAG, "onNext: " + response);
                    ShowBoardingResult(response);
                }
            }

            @Override
            public void onError(Throwable e) {
//                ConstantMethod.HideProgressBar(getActivity());
            }
        }, getContext(), pageCount, limit,"completed");

    }

    private void ShowBoardingResult(BoardingListResponse response) {
        mBoardingListResponse = response;
        ShowResultAdapter(response);


    }

    private void ShowResultAdapter(BoardingListResponse response) {

        if (response.getData() != null) {

            List<BookingListBean> listBeans = response.getData().getBooking_list();

            if (mBookingListBean.size() == 0) {
                mBookingListBean = listBeans;
                isLastPage = false;
            } else {
                mBookingListBean.addAll(listBeans);
                isLastPage = true;
            }

            mMyBookingAdapter = new MyBookingAdapter(getContext(),mBookingListBean, false);
            mMyBookingAdapter.setBoardingRowClick(this);
            mRecyclerView.setAdapter(mMyBookingAdapter);

        } else {
            Toast.makeText(getContext(), "No result", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSelectRowData(BookingListBean bookingListBean, int pos) {
        Intent intent = new Intent(getContext(), MyBookingUserDetailsActivity.class);
        intent.putExtra("bookingListBean", bookingListBean);
        intent.putExtra("status", true);
        intent.putExtra("row_pos", pos);
        startActivityForResult(intent, 102);
    }

   /* @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: " + requestCode);
        if (resultCode == 101) {
            BoardingPassAPICall(0);
            Log.d(TAG, "onActivityResult: ");
        } else {
            ShowResultAdapter(mBoardingListResponse);
            Log.d(TAG, "onActivityResult: ");
        }
    }*/
}
