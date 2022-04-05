package com.ns.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ns.adapter.adapter_holder.FoodListMyHolder;
import com.ns.model.FoodDay.DayList;
import com.ns.model.FoodDay.FoodDaySelectPojo;
import com.ns.model.LoginResponse.CustomerPrefsBean;
import com.ns.model.LoginResponse.FoodCategoriesBean;
import com.ns.model.LoginResponse.FoodPrefsBean;
import com.ns.retrofit.APICallBack;
import com.ns.stellarjet.R;
import com.ns.utils.ConstantMethod;
import com.ns.utils.UiConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.ns.utils.ConstantMethod.AllDayList;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListMyHolder> {

    private static final String TAG = "FoodListAdapter";

    //    private CustomerPrefsBean mCustomerPrefsBean;
    List<FoodDaySelectPojo> mFoodDaySelectList;
    private Activity mContext;
    private FoodDaysAdapter mDayAdapter;

    public FoodListAdapter(Activity context, List<FoodDaySelectPojo> foodDaySelectList) {
        mContext = context;
        mFoodDaySelectList = foodDaySelectList;
    }

    @NonNull
    @Override
    public FoodListMyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FoodListMyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_foodlist_expandable, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FoodListMyHolder holder, int position) {

        FoodDaySelectPojo foodDaySelectPojo = mFoodDaySelectList.get(position);

        holder.title.setText(foodDaySelectPojo.getFood_category());

        holder.heading.setText(foodDaySelectPojo.getHeading());

        if (position == 0) {
            holder.mHeadingIconLayout.setVisibility(View.GONE);
        } else {
            holder.mHeadingIconLayout.setVisibility(View.VISIBLE);
        }

        if (foodDaySelectPojo.isShow_days()) {
            foodDaySelectPojo.setClickStatus(true);
            holder.title.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_tik_check, 0);
            if (foodDaySelectPojo.getHeading().equals("")) {
                holder.mHeadingIconLayout.setVisibility(View.GONE);
            } else {
                holder.mHeadingIconLayout.setVisibility(View.VISIBLE);
            }
        } else {
            foodDaySelectPojo.setClickStatus(false);
            holder.title.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_tik_uncheck, 0);
            holder.mHeadingIconLayout.setVisibility(View.GONE);
        }

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position == 0) {

                    if (foodDaySelectPojo.isClickStatus()) {
                        holder.title.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_tik_uncheck, 0);
                        foodDaySelectPojo.setClickStatus(false);
                        foodDaySelectPojo.setShow_days(false);

                        List<DayList> dayList = onDayList(false);
                        foodDaySelectPojo.setDays(dayList);
                    } else {
                        holder.title.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_tik_check, 0);
                        foodDaySelectPojo.setClickStatus(true);
                        foodDaySelectPojo.setShow_days(true);

                        List<DayList> dayList = onDayList(true);
                        foodDaySelectPojo.setDays(dayList);
                    }
                } else {

                    if (foodDaySelectPojo.isClickStatus()) {
                        foodDaySelectPojo.setClickStatus(false);
                        foodDaySelectPojo.setShow_days(false);
                        holder.title.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_tik_uncheck, 0);


                        List<DayList> dayList = onDayList(false);
                        foodDaySelectPojo.setDays(dayList);

                        holder.mHeadingIconLayout.setVisibility(View.GONE);
                        holder.llClick.setVisibility(View.GONE);
                        holder.imgUpArrow.setVisibility(View.GONE);
                        holder.imgDownArrow.setVisibility(View.GONE);
                        holder.heading.setVisibility(View.GONE);
                        Log.d(TAG, "onClick: ");

                    } else {
                        foodDaySelectPojo.setClickStatus(true);
                        foodDaySelectPojo.setShow_days(true);
                        holder.title.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_tik_check, 0);

                        holder.mHeadingIconLayout.setVisibility(View.VISIBLE);

                        holder.llClick.setVisibility(View.VISIBLE);
                        holder.imgUpArrow.setVisibility(View.VISIBLE);
                        holder.imgDownArrow.setVisibility(View.GONE);
                        holder.heading.setVisibility(View.VISIBLE);

                        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true);
                        holder.mRecyclerView.setLayoutManager(layoutManager);


                        List<DayList> dayList = onDayList(true);
                        foodDaySelectPojo.setDays(dayList);

                        mDayAdapter = new FoodDaysAdapter(foodDaySelectPojo, position);
                        holder.mRecyclerView.setAdapter(mDayAdapter);

                        Log.d(TAG, "onClick: ");
                    }

                }
            }
        });


        holder.imgUpArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.llClick.setVisibility(View.GONE);
                holder.imgUpArrow.setVisibility(View.GONE);
                holder.imgDownArrow.setVisibility(View.VISIBLE);
                Log.d(TAG, "onClick: ");

            }
        });

        holder.imgDownArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.llClick.setVisibility(View.VISIBLE);
                holder.imgUpArrow.setVisibility(View.VISIBLE);
                holder.imgDownArrow.setVisibility(View.GONE);

                LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true);
                holder.mRecyclerView.setLayoutManager(layoutManager);

                mDayAdapter = new FoodDaysAdapter(foodDaySelectPojo, position);
                holder.mRecyclerView.setAdapter(mDayAdapter);
                Log.d(TAG, "onClick: ");

            }
        });
    }


    private List<DayList> onDayList(boolean status) {
        List<DayList> dayList = new ArrayList<>();
        DayList dayBean;
        for (String d : AllDayList()) {
            dayBean = new DayList(d, status);
            dayList.add(dayBean);
        }

        return dayList;
    }


    @Override
    public int getItemCount() {
        return mFoodDaySelectList.size();
    }

    /**
     * Confirm Button Click
     */
    public List<FoodDaySelectPojo> FoodDayConfirmClick() {
        Log.d(TAG, "FoodDayConfirmClick: " + mFoodDaySelectList);
        return mFoodDaySelectList;

    }


}
