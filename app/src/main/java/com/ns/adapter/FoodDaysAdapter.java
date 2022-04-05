package com.ns.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ns.model.FoodDay.DayList;
import com.ns.model.FoodDay.FoodDaySelectPojo;
import com.ns.model.LoginResponse.CustomerPrefsBean;
import com.ns.model.LoginResponse.FoodCategoriesBean;
import com.ns.model.LoginResponse.FoodPrefsBean;
import com.ns.stellarjet.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FoodDaysAdapter extends RecyclerView.Adapter<FoodDaysAdapter.MyHolder> {

    private static final String TAG = "FoodDaysAdapter";


    private FoodDaySelectPojo mFoodDaySelectPojo;

    public FoodDaysAdapter(FoodDaySelectPojo foodDaySelectPojo, int parentPosition) {

        this.mFoodDaySelectPojo = foodDaySelectPojo;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_food_days_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        // Tag for name Textview
        holder.name.setTag(position);

        holder.name.setText(mFoodDaySelectPojo.getDays().get(position).getDay());


        if (mFoodDaySelectPojo.getDays().get(position).isStatus()) {
            holder.name.setBackgroundResource(R.drawable.drawable_unselected_circle);

        } else {
            holder.name.setBackgroundResource(R.drawable.drawable_selected_circle);

        }


        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = (int) view.getTag();

                List<DayList> dayList = mFoodDaySelectPojo.getDays();
                DayList dayPojo;

                if (mFoodDaySelectPojo.getDays().get(position).isStatus()) {
                    holder.name.setBackgroundResource(R.drawable.drawable_selected_circle);
                    dayPojo = new DayList(mFoodDaySelectPojo.getDays().get(position).getDay(), false);
                    dayList.set(id, dayPojo);
                    mFoodDaySelectPojo.setDays(dayList);
                } else {
                    holder.name.setBackgroundResource(R.drawable.drawable_unselected_circle);
                    dayPojo = new DayList(mFoodDaySelectPojo.getDays().get(position).getDay(), true);
                    dayList.set(id, dayPojo);
                    mFoodDaySelectPojo.setDays(dayList);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mFoodDaySelectPojo.getDays().size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView name;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView_row_food_days);
        }
    }


}
