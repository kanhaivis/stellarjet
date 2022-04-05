package com.ns.adapter.adapter_holder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ns.stellarjet.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FeedBackMyHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public RatingBar ratingBar;
    public TextView message;
    public LinearLayout layoutShowHide;

    public FeedBackMyHolder(@NonNull View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.feedback_title);
        message = itemView.findViewById(R.id.feedback_msg);
        ratingBar = itemView.findViewById(R.id.feedback_ratingStars);
        layoutShowHide = itemView.findViewById(R.id.feedback_layout_showhide);
    }
}
