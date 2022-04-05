package com.ns.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ns.adapter.FoodMenuListAdapter;
import com.ns.database.SharedPref;
import com.ns.model.FoodMenuList.FoodMenuDataBean;
import com.ns.model.FoodMenuList.FoodMenuListResponse;
import com.ns.model.ReUseModel.BookingListBean;
import com.ns.retrofit.APICallBack;
import com.ns.stellarjet.R;
import com.ns.utils.ConstantDateFormate;
import com.ns.utils.ConstantMethod;
import com.ns.utils.UiConstants;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FoodCategoriesListFragment extends Fragment implements FoodMenuListAdapter.RowFoodMenuClick {

    private static final String TAG = "FoodCategoriesListFragm";


    private ArrayList<FoodMenuDataBean> fullFoodMenuDataBeans;

    private RecyclerView recyclerView;


    // Bundle data pass
    BookingListBean mBookingListBean;
    private int mPosition;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBookingListBean = (BookingListBean) this.getArguments().getSerializable("bookingListBean");

        mPosition = this.getArguments().getInt("row_pos");
        Log.d(TAG, "onCreate: " + mBookingListBean);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_foodmenulist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((TextView) view.findViewById(R.id.foodmenu_top_title)).setText(getResources().getString(R.string.foodmenulist));

        ((Button) view.findViewById(R.id.foodmenu_bottom_confirm)).setVisibility(View.GONE);

        recyclerView = view.findViewById(R.id.common_recycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        String datesStr = ConstantDateFormate.getPersonalizationHours(mBookingListBean.getJourney_datetime());
        Log.d(TAG, "onViewCreated: " + datesStr);

        if (ConstantMethod.isConnected(getContext())) {
            APICall(mBookingListBean.getSchedule_id(), datesStr);
        } else {
            ConstantMethod.ShowToastMessage(getContext(), UiConstants.NO_NETWORK);
        }
    }


    /**
     * @param id
     * @param journeyDate
     */
    private void APICall(int id, String journeyDate) {

        APICallBack.getFoodMenuList(new APICallBack.IFoodMenuListInterface() {
            @Override
            public void onFoodMenuList(FoodMenuListResponse response) {
                if (response.getResultcode() == 1) {
                    ShowResult(response.getData());
                } else if (response.getResultcode() == 0) {
                    ConstantMethod.DialogShowFragmentBackPress(getActivity(), response.getMessage());
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }
        }, getContext(), id, journeyDate);
    }


    /**
     * @param data
     */
    private void ShowResult(ArrayList<FoodMenuDataBean> data) {

        fullFoodMenuDataBeans = data;

        // Remove the repeated name for Categories list
        Set<FoodMenuDataBean> setOfBlogs = new LinkedHashSet<>(data);

        // new create categories list
        ArrayList<FoodMenuDataBean> catList = new ArrayList<>();
        catList.clear();

        // add all categories name
        catList.addAll(setOfBlogs);


        Log.d(TAG, "onCreate: " + catList + " main list " + data);
        List<String> categoryList = new ArrayList<>();
        for (FoodMenuDataBean bean : catList) {
            categoryList.add(bean.getFood_type_text());
        }


        // Adapter call show only Categories name
        FoodMenuListAdapter foodMenuListAdapter = new FoodMenuListAdapter(categoryList, data);
        foodMenuListAdapter.setFoodMenuClick(this);
        recyclerView.setAdapter(foodMenuListAdapter);


    }


    /**
     * Select the single categories name and show sub-categories list
     * @param filterBeans
     * @param categoryName
     */
    @Override
    public void onClickRow(ArrayList<FoodMenuDataBean> filterBeans, String categoryName) {
        Log.d(TAG, "onClickRow: " + filterBeans);



        Bundle bundle = new Bundle();
        bundle.putString("title", categoryName);
        bundle.putInt("row_pos", mPosition);
        bundle.putSerializable("foodListData", filterBeans);
        bundle.putSerializable("bookingListBean", mBookingListBean);

        FoodSubCategoryListFragment foodMenuCategoryListFragment = new FoodSubCategoryListFragment();
        foodMenuCategoryListFragment.setArguments(bundle);


        new Handler().postDelayed(() -> Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                .replace(R.id.framePane, foodMenuCategoryListFragment)
                .addToBackStack(null)
                .commit(), UiConstants.DELAYMILLISECOND);
    }
}
