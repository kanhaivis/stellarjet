package com.ns.adapter;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.ns.adapter.adapter_holder.FeedBackMyHolder;
import com.ns.model.FeedBack.FeedBackCategoriesDataBean;
import com.ns.model.FeedBack.FeedBackDetailsRequest;
import com.ns.model.FeedBack.FeedBackRequest;
import com.ns.stellarjet.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FeedBackAdapter extends RecyclerView.Adapter<FeedBackMyHolder> {
    private static final String TAG = "FeedBackAdapter";

    private Activity mActivity;
    private List<FeedBackCategoriesDataBean> mFeedBackCategoriesList;
    private List<FeedBackDetailsRequest> mFeedbackList;

    public FeedBackAdapter(Activity _activity, List<FeedBackCategoriesDataBean> _feedBackCategoriesDataBeans, List<FeedBackDetailsRequest> _feedback) {
        this.mActivity = _activity;
        this.mFeedBackCategoriesList = _feedBackCategoriesDataBeans;
        this.mFeedbackList = _feedback;
    }


    @NonNull
    @Override
    public FeedBackMyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FeedBackMyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_feedback, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FeedBackMyHolder holder, int position) {

        FeedBackDetailsRequest feedBack = mFeedbackList.get(position);
        FeedBackCategoriesDataBean bean = mFeedBackCategoriesList.get(position);
        holder.title.setText(bean.getFeedback_type());

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bean.isStatus()) {

                    bean.setStatus(false);
                    holder.title.setCompoundDrawablesWithIntrinsicBounds(null, null, mActivity.getResources().getDrawable(R.drawable.ic_tik_uncheck), null);
                    holder.layoutShowHide.setVisibility(View.GONE);
                } else {

                    holder.title.setCompoundDrawablesWithIntrinsicBounds(null, null, mActivity.getResources().getDrawable(R.drawable.ic_tik_check), null);
                    bean.setStatus(true);
                    holder.layoutShowHide.setVisibility(View.VISIBLE);
                }

            }
        });

        holder.message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                feedBack.setDESCRIPTION(editable.toString());
            }
        });


        holder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Log.d(TAG, "onRatingChanged: " + v);
                feedBack.setRATING((int) v);
            }
        });
        feedBack.setID(bean.getId());


    }

    @Override
    public int getItemCount() {
        return mFeedBackCategoriesList.size();
    }

    public List<FeedBackDetailsRequest> onSubmit() {

        List<FeedBackDetailsRequest> feedbackList = mFeedbackList;
        Log.d(TAG, "onSubmit: " + feedbackList);

        return feedbackList;
    }
}
