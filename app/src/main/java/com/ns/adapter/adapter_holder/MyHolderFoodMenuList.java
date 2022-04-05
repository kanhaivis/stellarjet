package com.ns.adapter.adapter_holder;

import android.view.View;
import android.widget.TextView;

import com.ns.stellarjet.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyHolderFoodMenuList extends RecyclerView.ViewHolder {
   public TextView name;
   public View mIView;
    public MyHolderFoodMenuList(@NonNull View itemView) {
        super(itemView);
        mIView = itemView;
        name = itemView.findViewById(R.id.textView_food_list_row_name);
    }
}
