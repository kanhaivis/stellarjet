package com.ns.adapter.adapter_holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ns.stellarjet.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyBookingMyHolder  extends RecyclerView.ViewHolder {

    public View mView;
    public TextView fromCitiName;
    public TextView fromAirpoartName;
    public TextView toCitiName;
    public TextView toAirpoartName;
    public TextView boardingPassDate;
    public TextView departurePassDate;
    public ImageView iconCaneled;
    public TextView showstatus_message;
    public RelativeLayout mPersonalizeRelativeLayout;

    public MyBookingMyHolder(@NonNull View itemView) {
        super(itemView);

        mView = itemView;
        fromCitiName = itemView.findViewById(R.id.row_mybooking_from_city);
        fromAirpoartName = itemView.findViewById(R.id.row_mybooking_from_airport);
        toCitiName = itemView.findViewById(R.id.row_mybooking_to_city);
        toAirpoartName = itemView.findViewById(R.id.row_mybooking_to_airport);
        boardingPassDate = itemView.findViewById(R.id.row_mybooking_boardingpass_date);
        departurePassDate = itemView.findViewById(R.id.row_mybooking_boardingdeparture_date);
        iconCaneled = itemView.findViewById(R.id.row_mybooking_cancelicon);
        showstatus_message = itemView.findViewById(R.id.row_mybooking_personalize_status);
        mPersonalizeRelativeLayout = itemView.findViewById(R.id.layout_row_boarding_pass_personalize);
    }
}
