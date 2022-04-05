package com.ns.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ns.adapter.adapter_holder.CancledTickedMyHolder;
import com.ns.model.CancelBooking.CancelBookingInfo;
import com.ns.stellarjet.R;
import com.ns.utils.ConstantMethod;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CancledTickedAdapter extends RecyclerView.Adapter<CancledTickedMyHolder> {

    private static final String TAG = "CancledTickedAdapter";

    public static int iCountPosition = 0;
    private ArrayList<CancelBookingInfo> mSeatListIds;

    public CancledTickedAdapter(ArrayList<CancelBookingInfo> seatListIds, int setCount) {
        mSeatListIds = seatListIds;
        iCountPosition = setCount;
    }

    public void CheckedUncheckedStatus(boolean status) {

        int count = mSeatListIds.size();
        if (status) {

            for (int i = 0; i < count; i++) {
                CancelBookingInfo info = new CancelBookingInfo(mSeatListIds.get(i).getSeatName(), mSeatListIds.get(i).getGuestName(), mSeatListIds.get(i).getSeatId(), true);
                mSeatListIds.set(i, info);
            }
            iCountPosition = count;
        } else {
            for (int i = 0; i < count; i++) {
                CancelBookingInfo info = new CancelBookingInfo(mSeatListIds.get(i).getSeatName(), mSeatListIds.get(i).getGuestName(), mSeatListIds.get(i).getSeatId(), false);
                mSeatListIds.set(i, info);
            }
            iCountPosition = 0;

        }

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CancledTickedMyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CancledTickedMyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cancled_ticket_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CancledTickedMyHolder holder, int position) {

        holder.checkIcon.setTag(position);
        CancelBookingInfo info = mSeatListIds.get(position);

        Log.d(TAG, "onBindViewHolder: "+iCountPosition);

        holder.checkIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = (int) view.getTag();
                CancelBookingInfo info1 = mSeatListIds.get(id);

                if (info.isCheckStatus() == true) {
                    Log.d(TAG, "onClick: ");


                    iCountPosition--;
                    CancelBookingInfo bean = new CancelBookingInfo(info1.getSeatName(), info1.getGuestName(), info1.getSeatId(), false);
                    mSeatListIds.set(id, bean);

                    holder.checkIcon.setBackgroundResource(R.drawable.ic_tik_check);

                    iCanceledchecked.onRowClick(true, position);


                } else if (info.isCheckStatus() == false) {
                    Log.d(TAG, "onClick: ");

                    iCountPosition++;
                    CancelBookingInfo bean = new CancelBookingInfo(info1.getSeatName(), info1.getGuestName(), info1.getSeatId(), true);
                    mSeatListIds.set(id, bean);

                    holder.checkIcon.setBackgroundResource(R.drawable.ic_tik_uncheck);

                    iCanceledchecked.onRowClick(false, position);
                }
            }
        });


        if (info.isCheckStatus() == true) {
            holder.checkIcon.setBackgroundResource(R.drawable.ic_tik_check);
        } else if (info.isCheckStatus() == false) {
            holder.checkIcon.setBackgroundResource(R.drawable.ic_tik_uncheck);
        }

        holder.SeatName.setText(mSeatListIds.get(position).getSeatName());
        holder.GuestName.setText(mSeatListIds.get(position).getGuestName());
    }

    @Override
    public int getItemCount() {
        return mSeatListIds.size();
    }


    public ICanceledchecked iCanceledchecked;

    public ICanceledchecked setCanceledchecked(ICanceledchecked Canceledchecked) {
        return iCanceledchecked = Canceledchecked;
    }

    public interface ICanceledchecked {
        void onRowClick(boolean statu, int position);
    }
}
