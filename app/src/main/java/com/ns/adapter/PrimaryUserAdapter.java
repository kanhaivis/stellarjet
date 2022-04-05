package com.ns.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ns.activity.PrimaryUserListActivity;
import com.ns.adapter.adapter_holder.PrimaryUserMyHolder;
import com.ns.database.SharedPref;
import com.ns.model.SecondaryUser.PrimaryUsersBean;
import com.ns.stellarjet.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PrimaryUserAdapter extends RecyclerView.Adapter<PrimaryUserMyHolder> {

    private Activity mActivity;
    private ArrayList<PrimaryUsersBean> mPrimaryUsersBeans;

    public PrimaryUserAdapter(Activity _Activity, ArrayList<PrimaryUsersBean> _PrimaryUsersBeans) {
        this.mActivity = _Activity;
        this.mPrimaryUsersBeans = _PrimaryUsersBeans;
    }

    @NonNull
    @Override
    public PrimaryUserMyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PrimaryUserMyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_single_txt_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PrimaryUserMyHolder holder, int position) {

        int pos = SharedPref.getSharedPreferences(mActivity).getSinglePrimaryPos();
        if (position == pos) {

            holder.mName.setCompoundDrawablesWithIntrinsicBounds(null, null, mActivity.getResources().getDrawable(R.drawable.ic_tik_check), null);
        } else {

            holder.mName.setCompoundDrawablesWithIntrinsicBounds(null, null, mActivity.getResources().getDrawable(R.drawable.ic_tik_uncheck), null);
        }
        holder.mName.setText(mPrimaryUsersBeans.get(position).getName());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRowClickPostion.onRowClick(mPrimaryUsersBeans.get(position).getId(), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPrimaryUsersBeans.size();
    }


    public RowClickPostion mRowClickPostion;

    public RowClickPostion setRowClickPostion(RowClickPostion rowClickPostion) {
        return mRowClickPostion = rowClickPostion;
    }

    public interface RowClickPostion {
        void onRowClick(int pid, int pos);
    }
}
