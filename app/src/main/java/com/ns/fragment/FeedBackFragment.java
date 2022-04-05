package com.ns.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ns.adapter.FeedBackAdapter;
import com.ns.database.SharedPref;
import com.ns.model.CommonPojo;
import com.ns.model.FeedBack.FeedBackCategoriesDataBean;
import com.ns.model.FeedBack.FeedBackCategoriesResponse;
import com.ns.model.FeedBack.FeedBackDetailsRequest;
import com.ns.model.FeedBack.FeedBackRequest;
import com.ns.model.ReUseModel.BookingListBean;
import com.ns.retrofit.APICallBack;
import com.ns.stellarjet.R;
import com.ns.utils.ConstantMethod;
import com.ns.utils.UiConstants;


import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FeedBackFragment extends Fragment {
    private static final String TAG = "FeedBackFragment";

    private RecyclerView mRecyclerView;
    private FeedBackAdapter mFeedBackAdapter;
    private ProgressBar mProgressBar;

    // Bundle data pass
    private BookingListBean mBookingListBean;
    private int mPosition;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBookingListBean = (BookingListBean) this.getArguments().getSerializable("bookingListBean");
        mPosition = this.getArguments().getInt("row_pos");
        Log.d(TAG, "onCreate: " + mBookingListBean);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feedback, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mProgressBar = view.findViewById(R.id.progressBar);
        mRecyclerView = view.findViewById(R.id.common_recycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);


        if (ConstantMethod.isConnected(getContext())) {
            FeedBackInfoAPICall();
        } else {
            ConstantMethod.ShowToastMessage(getContext(), UiConstants.NO_NETWORK);
        }

        /**
         * Feedback Confirm event button.
         */
        view.findViewById(R.id.ticket_feedback_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<FeedBackDetailsRequest> requests = mFeedBackAdapter.onSubmit();
                Log.d(TAG, "onClick: " + requests);

                boolean ratingStatus = false;
                for (FeedBackDetailsRequest bean : requests) {
                    if (bean.getRATING() != 0) {
                        ratingStatus = true;
                    }
                }

                if (ratingStatus) {
                    APICallFeedback(requests);
                } else {
                    ConstantMethod.DialogShow(getContext(), " Please select rating!");
                }

            }
        });

    }


    /**
     * First call api get response for Feedback list
     */
    private void FeedBackInfoAPICall() {

        APICallBack.getFeedBackCategories(new APICallBack.IFeedBackCategoriesInterface() {
            @Override
            public void onFeedBackCategories(FeedBackCategoriesResponse feedBackCategoriesResponse) {
                if (feedBackCategoriesResponse.getResultcode() == 1) {
                    onShowResult(feedBackCategoriesResponse.getData());
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        }, getContext());

    }


    /**
     *
     * @param requests
     */
    private void APICallFeedback(List<FeedBackDetailsRequest> requests) {
        String token = SharedPref.getSharedPreferences(getContext()).getTocken();
        FeedBackRequest feedBackRequest = new FeedBackRequest();
        feedBackRequest.setToken(token);
        feedBackRequest.setBooking_id(mBookingListBean.getBooking_id());
        feedBackRequest.setFeedbacks(requests);

        mProgressBar.setVisibility(View.VISIBLE);

        APICallBack.getFeedBack(new APICallBack.ICommonInterface() {
            @Override
            public void onCommonData(CommonPojo commonPojo) {
                mProgressBar.setVisibility(View.GONE);
                if (commonPojo.getResultcode() == 1) {
                    SharedPref.getSharedPreferences(getContext()).setFeedBackDetails(1);
                    ConstantMethod.DialogShowFragmentBackPress(getActivity(), commonPojo.getMessage());
                }

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError:" + e.getMessage());
                mProgressBar.setVisibility(View.GONE);
            }
        }, getContext(), feedBackRequest);
    }


    /**
     *
     * @param data
     */
    private void onShowResult(List<FeedBackCategoriesDataBean> data) {


        List<FeedBackDetailsRequest> feedback = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            FeedBackDetailsRequest bean = new FeedBackDetailsRequest(0, 0, "");
            feedback.add(bean);
        }

        mFeedBackAdapter = new FeedBackAdapter(getActivity(), data, feedback);
        mRecyclerView.setAdapter(mFeedBackAdapter);
    }


    public FeedBackAction iFeedBackAction;

    public FeedBackAction setFeedBackAction(FeedBackAction feedBackAction_) {
        return iFeedBackAction = feedBackAction_;
    }

    public interface FeedBackAction{
        void onActionFeedback(boolean status);
    }

}
