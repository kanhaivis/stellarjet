package com.ns.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ns.model.LoginResponse.AddressBean;
import com.ns.stellarjet.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SaveAddressAdapter extends RecyclerView.Adapter<SaveAddressAdapter.MyHolder> {

    private List<AddressBean> mAddressesList;

    public SaveAddressAdapter(List<AddressBean> addresses) {
        mAddressesList = addresses;
    }

    @NonNull
    @Override
    public SaveAddressAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_saved_address_layout, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SaveAddressAdapter.MyHolder holder, int position) {
        AddressBean bean = mAddressesList.get(position);
        holder.heading.setText(bean.getAddress_tag());
        holder.details.setText(bean.getAddress());
        holder.rowClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelectAddressRow.onAddress(bean.getAddress(), bean.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAddressesList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView heading;
        TextView details;
        View rowClick;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            rowClick = itemView;
            heading = itemView.findViewById(R.id.row_saved_address_heading);
            details = itemView.findViewById(R.id.row_saved_address_details);
        }
    }


    public SelectAddressRow mSelectAddressRow;

    public SelectAddressRow setSelectAddressRow(SelectAddressRow SelectAddressRow){
            return mSelectAddressRow = SelectAddressRow;
    }

    public interface SelectAddressRow{
        void onAddress(String address, int id);
    }
}
