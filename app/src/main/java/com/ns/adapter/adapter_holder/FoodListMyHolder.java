package com.ns.adapter.adapter_holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ns.stellarjet.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FoodListMyHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public TextView heading;
    public LinearLayout llClick;
    public ImageView imgDownArrow;
    public ImageView imgUpArrow;
    public RecyclerView mRecyclerView;
    public RelativeLayout mHeadingIconLayout;


    public FoodListMyHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.textView_row_global_food_name);
        heading = itemView.findViewById(R.id.row_foodlist_heading);
        llClick = itemView.findViewById(R.id.ll);
        imgDownArrow = itemView.findViewById(R.id.row_foodlist_down_icon);
        imgUpArrow = itemView.findViewById(R.id.row_foodlist_up_icon);
        mRecyclerView = itemView.findViewById(R.id.recyclerView_food_days);
        mHeadingIconLayout = itemView.findViewById(R.id.layout_row_global_days);

    }
}
