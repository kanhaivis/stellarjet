package com.ns.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ns.adapter.adapter_holder.MyHolderFoodMenuList;
import com.ns.database.SharedPref;
import com.ns.model.FoodMenuList.FoodMenuDataBean;
import com.ns.stellarjet.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FoodMenuListAdapter extends RecyclerView.Adapter<MyHolderFoodMenuList> {


    private List<String> mCategoryList;
    private ArrayList<FoodMenuDataBean> mFoodMenuDataBeanList;
    private ArrayList<FoodMenuDataBean> mFoodMenuDataBeanFilterList = new ArrayList<>();

    public FoodMenuListAdapter(List<String> categoryList, ArrayList<FoodMenuDataBean> data) {
        this.mCategoryList = categoryList;
        this.mFoodMenuDataBeanList = data;
    }

    @NonNull
    @Override
    public MyHolderFoodMenuList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_foodlist_layout, parent, false);
        return new MyHolderFoodMenuList(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolderFoodMenuList holder, int position) {

        String catName = mCategoryList.get(position);
        holder.name.setText(catName);
        holder.mIView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFoodMenuDataBeanFilterList.clear();
                for (FoodMenuDataBean bean : mFoodMenuDataBeanList) {

                    if (catName.equals(bean.getFood_type_text())) {
                        mFoodMenuDataBeanFilterList.add(bean);
                    }
                }
                iRowFoodMenuClick.onClickRow(mFoodMenuDataBeanFilterList, catName);
            }
        });

    }

    @Override
    public int getItemCount() {

        return mCategoryList.size();

    }


    public RowFoodMenuClick iRowFoodMenuClick;

    public RowFoodMenuClick setFoodMenuClick(RowFoodMenuClick rowFoodMenuClick) {
        return iRowFoodMenuClick = rowFoodMenuClick;
    }

    public interface RowFoodMenuClick {
        void onClickRow(ArrayList<FoodMenuDataBean> filterBeans, String categoryName);
    }
}
