package com.ns.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ns.adapter.adapter_holder.MyBookingMyHolder;
import com.ns.database.SharedPref;
import com.ns.model.ReUseModel.BookingListBean;
import com.ns.stellarjet.R;
import com.ns.utils.ConstantDateFormate;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

public class MyBookingAdapter extends RecyclerView.Adapter<MyBookingMyHolder> {
    private static final String TAG = "MyBookingAdapter";

    private boolean bCheck;
    private List<BookingListBean> mBoardingPassList;
    private Context mContext;

    public MyBookingAdapter(Context context, List<BookingListBean> boarding_pass, boolean check) {
        this.mBoardingPassList = boarding_pass;
        bCheck = check;
        mContext = context;
    }

    @NonNull
    @Override
    public MyBookingMyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (bCheck) {
            return new MyBookingMyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_mybooking_layout, parent, false));
        } else {
            return new MyBookingMyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_completed_layout, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull MyBookingMyHolder holder, int position) {

        BookingListBean boardingPass = mBoardingPassList.get(position);

        String fromName = boardingPass.getFrom_city_info().getName();
        String toName = boardingPass.getTo_city_info().getName();
        long rowDateTime = boardingPass.getJourney_datetime();

        holder.fromCitiName.setText(fromName);
        holder.fromAirpoartName.setText(boardingPass.getFrom_city_info().getAirport());

        holder.toCitiName.setText(toName);
        holder.toAirpoartName.setText(boardingPass.getTo_city_info().getAirport());

        holder.boardingPassDate.setText(ConstantDateFormate.getFormattedCompeltedDate(rowDateTime));
        holder.departurePassDate.setText(ConstantDateFormate.getFormattedhours(rowDateTime) + " hrs");





        String status = boardingPass.getStatus();
        Log.d(TAG, "onBindViewHolder: " + status);

        // All seat ticket status
        if (status.equals("Cancelled")) {
            holder.iconCaneled.setVisibility(View.VISIBLE);
            holder.showstatus_message.setText(mContext.getResources().getString(R.string.booking_summary_cancel_ticket));
        } else {
            holder.iconCaneled.setVisibility(View.GONE); //Please personalize your preferences
            holder.showstatus_message.setText(mContext.getResources().getString(R.string.booking_summary_need_personalize));
        }



        if (!boardingPass.isFeedback_available()) {
            SharedPref.getSharedPreferences(mContext).setFeedBackDetails(0);
        }

        if (boardingPass.getPick_address_main().isEmpty() && boardingPass.getDrop_address_main().isEmpty()) {
            SharedPref.getSharedPreferences(mContext).setPickUpDropUpkDetails(0);
        }


        holder.showstatus_message.setCompoundDrawablesWithIntrinsicBounds(mContext.getResources().getDrawable(R.drawable.ic_info_personalize), null, null, null);


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBoardingPassRowClick.onSelectRowData(boardingPass, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mBoardingPassList.size();
    }


    public BoardingPassRowClick mBoardingPassRowClick;

    public BoardingPassRowClick setBoardingRowClick(BoardingPassRowClick click) {
        return mBoardingPassRowClick = click;
    }

    public interface BoardingPassRowClick {
        void onSelectRowData(BookingListBean bookingListBean, int pos);
    }
}
