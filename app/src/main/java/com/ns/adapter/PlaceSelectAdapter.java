package com.ns.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ns.model.LoginResponse.CitiesBean;
import com.ns.stellarjet.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlaceSelectAdapter extends RecyclerView.Adapter<PlaceSelectAdapter.PlaceViewHolder> {

    private List<CitiesBean> items;
    private onPlaceSelectClickListener mOnPlaceSelectClickListener;
    private boolean checkTag;

    public PlaceSelectAdapter(onPlaceSelectClickListener onPlaceSelectClickListenerParams, List<CitiesBean> itemsParams, boolean check) {
        mOnPlaceSelectClickListener = onPlaceSelectClickListenerParams;
        this.items = itemsParams;
        this.checkTag = check;
    }

    public interface onPlaceSelectClickListener {
        void onPlaceSelected(String placeName, int placeId);
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v;
        if (checkTag) {
            v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.row_city_layout, viewGroup, false);
        } else {
            v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.row_item_layout, viewGroup, false);
        }

        return new PlaceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder placeViewHolder, int i) {

        placeViewHolder.mPlaceTextView.setText(items.get(i).getName());
        placeViewHolder.mRowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnPlaceSelectClickListener.onPlaceSelected(items.get(i).getName(), items.get(i).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder {
        TextView mPlaceTextView;

        View mRowView;

        PlaceViewHolder(View itemView) {
            super(itemView);
            mRowView = itemView;
            mPlaceTextView = itemView.findViewById(R.id.textView_place_row);
        }
    }
}
