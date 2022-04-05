package com.ns.adapter.adapter_holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ns.stellarjet.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DownloadTickedMyHolder extends RecyclerView.ViewHolder {
    public TextView SeatName;
    public TextView GuestName;
    public ImageView checkIcon;

    public DownloadTickedMyHolder(@NonNull View itemView) {
        super(itemView);

        SeatName = itemView.findViewById(R.id.row_download_ticket_seatname);
        GuestName = itemView.findViewById(R.id.row_download_ticket_guestname);
        checkIcon = itemView.findViewById(R.id.row_download_ticket_icon);

    }
}
