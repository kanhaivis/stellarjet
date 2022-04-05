package com.ns.adapter.adapter_holder;

import android.view.View;
import android.widget.TextView;

import com.ns.stellarjet.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PrimaryUserMyHolder extends RecyclerView.ViewHolder {

    public View mView;
    public TextView mName;

    public PrimaryUserMyHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
        mName = itemView.findViewById(R.id.row_single_txtId);
    }
}
