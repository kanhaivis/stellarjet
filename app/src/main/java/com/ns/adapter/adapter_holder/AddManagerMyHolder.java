package com.ns.adapter.adapter_holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ns.stellarjet.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AddManagerMyHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public TextView phone;
    public View onRowCLick;
    public ImageView delete;

    public AddManagerMyHolder(@NonNull View itemView) {
        super(itemView);
        onRowCLick = itemView;
        title = itemView.findViewById(R.id.row_addmanager_title);
        phone = itemView.findViewById(R.id.row_addmanager_phone);
        delete = itemView.findViewById(R.id.row_addmanager_delete);
    }
}
