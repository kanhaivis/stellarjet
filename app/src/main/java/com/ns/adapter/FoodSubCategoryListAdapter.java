package com.ns.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ns.adapter.adapter_holder.MyHolderFoodMenuList;
import com.ns.database.SharedPref;
import com.ns.model.BoardingList.FoodItems;
import com.ns.model.FoodMenuList.FoodMenuDataBean;
import com.ns.stellarjet.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FoodSubCategoryListAdapter extends RecyclerView.Adapter<MyHolderFoodMenuList> {

    private static final String TAG = "FoodMenuCategoryListAda";

    private Context mContext;
    ArrayList<FoodMenuDataBean> mFoodMenuDataBeanList;

    public FoodSubCategoryListAdapter(Context context_, ArrayList<FoodMenuDataBean> foodMenuDataBeans_) {
        this.mContext = context_;
        this.mFoodMenuDataBeanList = foodMenuDataBeans_;
    }


    @NonNull
    @Override
    public MyHolderFoodMenuList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_foodlist_layout, parent, false);
        return new MyHolderFoodMenuList(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolderFoodMenuList holder, int position) {


        FoodMenuDataBean bean = mFoodMenuDataBeanList.get(position);

        holder.name.setText(bean.getName());
        holder.name.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_tik_uncheck, 0);

        if (bean.isStatus()) {

            holder.name.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_tik_check, 0);

            bean.setStatus(true);
            mFoodMenuDataBeanList.set(position, bean);
        } else {
            holder.name.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_tik_uncheck, 0);

            bean.setStatus(false);
            mFoodMenuDataBeanList.set(position, bean);
        }



        holder.mIView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (bean.isStatus()) {
                    holder.name.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_tik_uncheck, 0);


                    ArrayList<String> foodIdList = SharedPref.getSharedPreferences(mContext).getSelectdFoodCategories();

                    for (int i = 0; i < foodIdList.size(); i++) {
                        if (foodIdList.get(i).equals(bean.getId()+"")) {
                            foodIdList.remove(i);
                        }
                    }


                    SharedPref.getSharedPreferences(mContext).saveSelectdFoodCategories(foodIdList);

                    bean.setStatus(false);
                    mFoodMenuDataBeanList.set(position, bean);

                } else {
                    holder.name.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_tik_check, 0);

                    ArrayList<String> foodIdList = SharedPref.getSharedPreferences(mContext).getSelectdFoodCategories();

                    foodIdList.add(bean.getId()+"");
                    SharedPref.getSharedPreferences(mContext).saveSelectdFoodCategories(foodIdList);

                    bean.setStatus(true);
                    mFoodMenuDataBeanList.set(position, bean);
                }

                iRowFoodMenuClick.onClickRow(bean, false);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mFoodMenuDataBeanList.size();
    }


    public RowFoodMenuClick iRowFoodMenuClick;

    public RowFoodMenuClick setFoodMenuClick(RowFoodMenuClick rowFoodMenuClick) {
        return iRowFoodMenuClick = rowFoodMenuClick;
    }


    public interface RowFoodMenuClick {
        void onClickRow(FoodMenuDataBean filterBeans, boolean check);
    }
}
