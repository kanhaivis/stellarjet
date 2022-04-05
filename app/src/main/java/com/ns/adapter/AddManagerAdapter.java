package com.ns.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ns.adapter.adapter_holder.AddManagerMyHolder;
import com.ns.model.AddManager.SecondaryUsersBean;
import com.ns.stellarjet.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AddManagerAdapter extends RecyclerView.Adapter<AddManagerMyHolder> {

    private List<SecondaryUsersBean> mAddManagerList;

    public AddManagerAdapter(List<SecondaryUsersBean> addManager) {
        this.mAddManagerList = addManager;
    }

    @NonNull
    @Override
    public AddManagerMyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_addmanager_layout, parent, false);
        return new AddManagerMyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddManagerMyHolder holder, int position) {

        SecondaryUsersBean pojo = mAddManagerList.get(position);
        holder.title.setText(pojo.getSu_name());
        holder.phone.setText(pojo.getSu_phone());

        holder.onRowCLick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRowData.rowInfo(pojo.getSu_name(), pojo.getSu_phone(), pojo.getSu_email());
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRowData.onRowDelete(pojo.getSu_id(), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAddManagerList.size();
    }


    public RowData mRowData;

    public RowData OnClickRow(RowData data) {
        return mRowData = data;
    }

    public interface RowData {
        void rowInfo(String name, String phone, String email);

        void onRowDelete(int s_id, int position);
    }
}
