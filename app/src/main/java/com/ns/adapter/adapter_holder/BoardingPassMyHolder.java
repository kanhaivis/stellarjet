package com.ns.adapter.adapter_holder;

import android.view.View;
import android.widget.TextView;

import com.ns.stellarjet.R;

import androidx.recyclerview.widget.RecyclerView;

public class BoardingPassMyHolder extends BaseViewHolder {

    public TextView fromCitiName;
    public TextView fromAirpoartName;
    public TextView toCitiName;
    public TextView toAirpoartName;
    public TextView boardingPassDate;
    public TextView departurePassDate;
    public TextView boardingPassSeats;
    public TextView flightName;
    public View mView;


    public BoardingPassMyHolder(View itemView) {
        super(itemView);

        mView = itemView;
        fromCitiName = itemView.findViewById(R.id.textView_row_boarding_pass_from_city);
        fromAirpoartName = itemView.findViewById(R.id.textView_row_boarding_pass_from_airport);
        toCitiName = itemView.findViewById(R.id.textView_row_boarding_pass_to_city);
        toAirpoartName = itemView.findViewById(R.id.textView_row_boarding_pass_to_airport);
        boardingPassDate = itemView.findViewById(R.id.textView_row_boarding_pass_date);
        departurePassDate = itemView.findViewById(R.id.textView_row_boarding_pass_departure_time);
        boardingPassSeats = itemView.findViewById(R.id.textView_row_boarding_pass_seats);
        flightName = itemView.findViewById(R.id.textView_row_boarding_pass_flight_name);
    }

    @Override
    protected void clear() {

    }
}