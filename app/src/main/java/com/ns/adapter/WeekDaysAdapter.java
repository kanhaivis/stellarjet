package com.ns.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ns.stellarjet.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WeekDaysAdapter extends RecyclerView.Adapter<WeekDaysAdapter.PlaceViewHolder> {

    private List<String> items;

    public WeekDaysAdapter(List<String> itemsParams) {
        this.items = itemsParams;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_week_days_layout, viewGroup, false);
        return new PlaceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder placeViewHolder, int i) {
        placeViewHolder.bind(i);
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    class PlaceViewHolder extends RecyclerView.ViewHolder{
        TextView mPlaceTextView;

        PlaceViewHolder(View itemView) {
            super(itemView);
            // binds the UI with adapter
            mPlaceTextView = itemView.findViewById(R.id.textView_row_week_days);
        }

        void bind(final int position){
            mPlaceTextView.setText(items.get(position));
        }
    }
}
