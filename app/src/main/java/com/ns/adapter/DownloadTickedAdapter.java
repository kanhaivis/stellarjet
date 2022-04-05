package com.ns.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ns.adapter.adapter_holder.DownloadTickedMyHolder;
import com.ns.model.CancelBooking.CancelBookingInfo;
import com.ns.model.Download.DownloadPojo;
import com.ns.stellarjet.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DownloadTickedAdapter extends RecyclerView.Adapter<DownloadTickedMyHolder> {

    private static final String TAG = "DownloadTickedAdapter";


    private ArrayList<DownloadPojo> mSeatListIds;

    public DownloadTickedAdapter(ArrayList<DownloadPojo> seatListIds) {
        mSeatListIds = seatListIds;
    }


    @NonNull
    @Override
    public DownloadTickedMyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DownloadTickedMyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_download_ticket_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DownloadTickedMyHolder holder, int position) {

        holder.checkIcon.setTag(position);
        holder.SeatName.setText(mSeatListIds.get(position).getSeatName());
        holder.GuestName.setText(mSeatListIds.get(position).getUsername());

        holder.checkIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                iRowTicketClick.OnRowClick(mSeatListIds.get(position));
            }
        });


    }

    @Override
    public int getItemCount() {
        return mSeatListIds.size();
    }


    public RowTicketClick iRowTicketClick;

    public RowTicketClick setRowTicketClick(RowTicketClick rowTicketClick) {
        return iRowTicketClick = rowTicketClick;
    }
    public interface RowTicketClick{
        void OnRowClick(DownloadPojo data);
    }
}
