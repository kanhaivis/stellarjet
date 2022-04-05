package com.ns.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ns.adapter.FoodSubCategoryListAdapter;
import com.ns.database.SharedPref;
import com.ns.model.BoardingList.FoodItems;
import com.ns.model.CommonPojo;
import com.ns.model.FoodMenuList.FoodMenuDataBean;
import com.ns.model.ReUseModel.BookingListBean;
import com.ns.retrofit.APICallBack;
import com.ns.stellarjet.R;
import com.ns.utils.ConstantMethod;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Response;

public class FoodSubCategoryListFragment extends Fragment implements View.OnClickListener, FoodSubCategoryListAdapter.RowFoodMenuClick {

    private static final String TAG = "FoodMenuCategoryListFra";


    private Button mConfirmBtn;
    private RecyclerView recyclerView;
    private FoodSubCategoryListAdapter mFoodMenuListAdapter;

    private FoodMenuDataBean mFoodMenuCategoryBeans;
    private boolean checkConfirmStatus = false;
    private boolean rowCheckUnCheckStatus;

    private ArrayList<FoodItems> fooditemsList;
    private ArrayList<FoodMenuDataBean> foodMenuDataBeanList;


    // Bundle data get
    private BookingListBean mBookingListBean;
    private String mTitle;
    private int mPosition;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBookingListBean = (BookingListBean) this.getArguments().getSerializable("bookingListBean");
        Log.d(TAG, "onCreate: " + mBookingListBean);

        mTitle = this.getArguments().getString("title");

        mPosition = this.getArguments().getInt("row_pos");

        foodMenuDataBeanList = (ArrayList<FoodMenuDataBean>) this.getArguments().getSerializable("foodListData");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_foodmenulist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<String> foodList = SharedPref.getSharedPreferences(getContext()).getSelectdFoodCategories();

        if (mBookingListBean.getTravelling_self() == 1) {

            if (foodList.size() == 0) {
                ArrayList<FoodItems> foodBeanList = mBookingListBean.getPrefs().getMain_passenger().getFood_items();
                if (foodBeanList.size() != 0) {

                    ArrayList<String> foodIdList = new ArrayList<>();
                    for (FoodItems foodItems : foodBeanList) {
                        foodIdList.add(foodItems.getId() + "");

                    }
                    SharedPref.getSharedPreferences(getContext()).saveSelectdFoodCategories(foodIdList);
                }
            }
        }


        for (int i = 0; i < foodMenuDataBeanList.size(); i++) {

            String ids = foodMenuDataBeanList.get(i).getId() + "";

            for (String s : foodList) {
                if (s.equals(ids)) {
                    Log.d(TAG, "onViewCreated: ");
                    FoodMenuDataBean bean = foodMenuDataBeanList.get(i);
                    bean.setStatus(true);
                    foodMenuDataBeanList.set(i, bean);
                } else {
                    Log.d(TAG, "onViewCreated: ");
                }
            }


        }



        ((TextView) view.findViewById(R.id.foodmenu_top_title)).setText(mTitle);

        mConfirmBtn = view.findViewById(R.id.foodmenu_bottom_confirm);
        mConfirmBtn.setVisibility(View.VISIBLE);
        mConfirmBtn.setOnClickListener(this);

        recyclerView = view.findViewById(R.id.common_recycler);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        fooditemsList = mBookingListBean.getPrefs().getMain_passenger().getFood_items();
        mFoodMenuListAdapter = new FoodSubCategoryListAdapter(getActivity(), foodMenuDataBeanList);
        mFoodMenuListAdapter.setFoodMenuClick(this);

        recyclerView.setAdapter(mFoodMenuListAdapter);

    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id) {
            case R.id.foodmenu_bottom_confirm:


                ArrayList<String> foodCategoryIdList = SharedPref.getSharedPreferences(getContext()).getSelectdFoodCategories();
                Log.d(TAG, "onClick: " + foodCategoryIdList);

                APICall(foodCategoryIdList);

               /* ArrayList<Integer> list = new ArrayList<>();
                for (FoodMenuDataBean bean : categoriesList) {
                    if (bean.isStatus()) {
                        int ids = bean.getId();
                        list.add(ids);
                    }
                }

                if (list.size() != 0) {

                    if (SharedPref.getSharedPreferences(getContext()).getSelectdFoodCategories().size() != 0) {


                        ArrayList<Integer> foodList = SharedPref.getSharedPreferences(getContext()).getSelectdFoodCategories();

                        for (Integer l: foodList) {

                        }

                        list.addAll(foodList);

                    }

                    SharedPref.getSharedPreferences(getContext()).saveSelectdFoodCategories(list);
                }


                ArrayList<Integer> foodListTest = SharedPref.getSharedPreferences(getContext()).getSelectdFoodCategories();

                Log.d(TAG, "onClick: " + list + "" + foodListTest.size());*/

               /* List<String> foodCategoryIdList = new ArrayList<>();
                for (FoodItems items : fooditemsList) {

                    foodCategoryIdList.add(items.getId() + "");
                }

                if (rowCheckUnCheckStatus) {
                    foodCategoryIdList.add(mFoodMenuCategoryBeans.getId() + "");
                } else {
                    foodCategoryIdList.remove(mFoodMenuCategoryBeans.getId() + "");
                }
                Log.d(TAG, "onClick: " + foodCategoryIdList + " -- " + fooditemsList);

                APICall(foodCategoryIdList);*/
                break;
        }
    }


    @Override
    public void onClickRow(FoodMenuDataBean rowFoodMenuCategoryBeans, boolean check) {
        Log.d(TAG, "onClickRow: " + rowFoodMenuCategoryBeans);
        checkConfirmStatus = true;
        rowCheckUnCheckStatus = check;
        mFoodMenuCategoryBeans = rowFoodMenuCategoryBeans;

    }


    private void APICall(List<String> foodCategoryIdList) {

        APICallBack.getPersonalFoodCategories(new APICallBack.IResponseCommon() {

            @Override
            public void onResponseCommon(Response<CommonPojo> commonPojoResponse) {
                Log.d(TAG, "onPersonalizeCab: " + commonPojoResponse);

                int code = commonPojoResponse.code();
                switch (code) {
                    case 200:
                        Log.d(TAG, "onNext: " + commonPojoResponse);
                        SharedPref.getSharedPreferences(getContext()).setDiningkDetails(1, mPosition);


//                        SharedPref.getSharedPreferences(getContext()).setDiningkDetailsTest(1);


                        ConstantMethod.DialogShowFragmentBackPress(getActivity(), commonPojoResponse.message());

                        break;
                    case 400:
                        Log.d(TAG, "onNext: " + commonPojoResponse);
                        JSONObject mJsonObject;
                        try {
                            mJsonObject = new JSONObject(commonPojoResponse.errorBody().string());
                            String errorMessage = mJsonObject.getString("error_message");
                            ConstantMethod.DialogShow(getContext(), errorMessage);
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        }, getContext(), mBookingListBean.getBooking_id(), foodCategoryIdList);

       /* String token = SharedPref.getSharedPreferences(getContext()).getTocken();
        FoodPersonalizeUpdateRequest request = new FoodPersonalizeUpdateRequest(token, mBookingListBean.getBooking_id() + "", foodCategoryIdList);

        APIInterface mApiInterface = APIRetrofit.getRetrofitClient(getContext()).create(APIInterface.class);

        mApiInterface.getPersonalizeFood(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommonPojo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CommonPojo response) {
                        Log.d(TAG, "onNext: " + request);
                        if (response.getResultcode() == 1) {


//                            showResult();

                            ConstantMethod.DialogShowFragmentBackPress(getActivity(), response.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });*/
    }

    private void showResult() {

        if (checkConfirmStatus) {


            if (rowCheckUnCheckStatus) {
                int listSize = fooditemsList.size() + 1;
                FoodItems items = new FoodItems();
                items.setId(listSize);
                items.setName(mFoodMenuCategoryBeans.getName());
                items.setFood_type(mFoodMenuCategoryBeans.getFood_type());
                items.setFood_type_text(mFoodMenuCategoryBeans.getFood_type_text());

                fooditemsList.add(items);
                Log.d(TAG, "onClick: " + fooditemsList);
            } else {


                for (int i = 0; i < fooditemsList.size(); i++) {
                    if (mFoodMenuCategoryBeans.getName().equals(fooditemsList.get(i).getName())) {
                        fooditemsList.remove(i);
                    }
                }

                Log.d(TAG, "onClick: " + fooditemsList);
            }


        }

    }
}
