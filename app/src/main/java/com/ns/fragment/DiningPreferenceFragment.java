package com.ns.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ns.adapter.FoodListAdapter;
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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.ns.utils.ConstantMethod.AllDayList;
import static com.ns.utils.ConstantMethod.DayHeadingList;

public class DiningPreferenceFragment extends Fragment {
    private static final String TAG = "DiningPreferenceFragmen";

    private CustomerPrefsBean mCustomerPrefsBean;

    private RecyclerView mFoodDayRecyclerView;
    private ImageView downArrowImg;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCustomerPrefsBean = (CustomerPrefsBean) this.getArguments().getSerializable("customer_prefs");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_diningpreference, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((TextView) view.findViewById(R.id.title)).setText(getContext().getResources().getString(R.string.info_global_food_title));
        ((TextView) view.findViewById(R.id.subtitle)).setText(getContext().getResources().getString(R.string.info_global_food_heading));

        mFoodDayRecyclerView = view.findViewById(R.id.foodlist_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mFoodDayRecyclerView.setLayoutManager(layoutManager);

        List<FoodDaySelectPojo> mFoodDaySelectList = CreateNewFoodListData(mCustomerPrefsBean);

        FoodListAdapter foodListAdapter = new FoodListAdapter(getActivity(), mFoodDaySelectList);
        mFoodDayRecyclerView.setAdapter(foodListAdapter);


        view.findViewById(R.id.button_global_food_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ConstantMethod.isConnected(getContext())) {
                    List<FoodDaySelectPojo> mFoodDaySelectList = foodListAdapter.FoodDayConfirmClick();

                    FoodDaySelectedShow(mFoodDaySelectList);

                } else {
                    ConstantMethod.ShowToastMessage(getContext(), UiConstants.NO_NETWORK);
                }
            }
        });


    }

    private void FoodDaySelectedShow(List<FoodDaySelectPojo> mFoodDaySelectList) {

        JSONArray mRelationJsonArray = new JSONArray();
        for (int i = 0; i < mFoodDaySelectList.size(); i++) {
            if (mFoodDaySelectList.get(i).isShow_days()) {

                JSONObject mainObject = new JSONObject();
                try {
                    mainObject.put("food_category", mFoodDaySelectList.get(i).getCat_key());
                    List<DayList> dayList = mFoodDaySelectList.get(i).getDays();
                    if (dayList.size() != 0) {
                        JSONArray dayArray = new JSONArray();

                        for (DayList day : dayList) {
                            if (day.isStatus()) {
                                dayArray.put(day.getDay());
                            }
                        }
                        mainObject.put("days", dayArray);
                    }
                    mRelationJsonArray.put(mainObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }


        Log.d(TAG, "FoodDayConfirmClick: " + mRelationJsonArray.toString());

        FoodPrefsUpdateAPICall(mRelationJsonArray.toString());
    }

    /**
     * Food day list for json formate
     * API Call
     *
     * @param foodJsonData
     */
    private void FoodPrefsUpdateAPICall(String foodJsonData) {

        if (ConstantMethod.isConnected(getContext())) {
            APICallBack.getFoodPrefrenceUpdate(getActivity(), foodJsonData);
        } else {
            ConstantMethod.ShowToastMessage(getContext(), UiConstants.NO_NETWORK);
        }
    }

    private List<FoodDaySelectPojo> CreateNewFoodListData(CustomerPrefsBean mCustomerPrefsBean) {

        List<FoodCategoriesBean> foodCategories = mCustomerPrefsBean.getFood_categories();
        List<String> flightDays = AllDayList();


        List<FoodDaySelectPojo> mFoodDaySelectList = new ArrayList<>();

        FoodDaySelectPojo foodDaySelectPojo;


        for (int i = 0; i < foodCategories.size(); i++) {


            List<DayList> dayList = new ArrayList<>();
            DayList dayPojo;
            for (String day : flightDays) {
                dayPojo = new DayList(day, false);
                dayList.add(dayPojo);
            }


            boolean isDayShow = false;
            List<FoodPrefsBean> foodPrefsBean = mCustomerPrefsBean.getFood_prefs();
            for (FoodPrefsBean bean : foodPrefsBean) {
                if (bean.getFood_category().equals(foodCategories.get(i).getCat_key())) {
                    isDayShow = true;

                    List<String> pDay = bean.getDays();
                    if (pDay == null) {
                        Log.d(TAG, "CreateNewFoodListData: ");
                    } else {
                        Log.d(TAG, "CreateNewFoodListData: " + pDay);

                        for (String pDays : pDay) {

                            int pos = flightDays.indexOf(pDays);
                            if (flightDays.contains(pDays)) {

                                dayPojo = new DayList(pDays, true);

                                dayList.set(pos, dayPojo);

                            } else {
                                dayPojo = new DayList(pDays, false);
                                dayList.set(pos, dayPojo);
                            }
                        }
                    }


                    Log.d(TAG, "CreateNewFoodListData: " + pDay);
                }
            }

            Log.d(TAG, "CreateNewFoodListData: " + isDayShow);


            foodDaySelectPojo = new FoodDaySelectPojo(foodCategories.get(i).isShow_days(),foodCategories.get(i).getCat_text(), foodCategories.get(i).getCat_key(), dayList, isDayShow, DayHeadingList().get(i));

            mFoodDaySelectList.add(foodDaySelectPojo);


        }

        Log.d(TAG, "CreateNewFoodListData: " + mFoodDaySelectList);

        return mFoodDaySelectList;

    }

}


