package com.ns.adapter.adapter_holder;

import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.ns.stellarjet.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PassengerListMyHolder extends RecyclerView.ViewHolder {
    public TextView nameTitle;
    public AutoCompleteTextView nameAutoCompleteTextView;
    public TextView phoneTitle;
    public EditText phoneEdt;
    public View rowClick;

    public PassengerListMyHolder(@NonNull View itemView) {
        super(itemView);

        rowClick = itemView;
        nameTitle = itemView.findViewById(R.id.row_passenger_name_title);
        phoneTitle = itemView.findViewById(R.id.row_passenger_name_title);
        phoneEdt = itemView.findViewById(R.id.row_passenger_phone);
        nameAutoCompleteTextView = itemView.findViewById(R.id.row_passenger_name_autoCompleteTextView);
    }
}
