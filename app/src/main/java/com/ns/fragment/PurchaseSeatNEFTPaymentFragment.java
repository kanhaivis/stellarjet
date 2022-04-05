package com.ns.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ns.activity.BaseActivity;
import com.ns.activity.HomeActivity;
import com.ns.model.PaymentResponse.PaymentLastOrderDetails;
import com.ns.model.PaymentResponse.TotalBean;
import com.ns.stellarjet.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PurchaseSeatNEFTPaymentFragment extends Fragment {


    private int mPurchaseId;
    private int mTotalAmount;
    private int mUnit;
    private int mNeft;
    private String mPurchaseStatus;
    private String mIfsc;
    private String mBankAcc;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mPurchaseId = getArguments().getInt("purchase_id");
        mTotalAmount = getArguments().getInt("total_amount");
        mUnit = getArguments().getInt("unit");
        mNeft = getArguments().getInt("NEFT_amount_limt");
        mPurchaseStatus = getArguments().getString("purchase_status");
        mIfsc = getArguments().getString("ifsc_code");
        mBankAcc = getArguments().getString("bank_ac");


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_purchaseseat_neft_payment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        ((TextView) view.findViewById(R.id.neft_total_seat_countTxt)).setText(getResources().getString(R.string.purchase_amount) + " " + mUnit + " seats");
        ((TextView) view.findViewById(R.id.neft_total_seat_amountTxt)).setText("Rs. " + mTotalAmount);
        ((TextView) view.findViewById(R.id.neft_account_Txt)).setText(mBankAcc);
        ((TextView) view.findViewById(R.id.neft_ifsc_Txt)).setText(mIfsc);
        ((TextView) view.findViewById(R.id.neft_purchaseid_Txt)).setText(mPurchaseId + "");

        view.findViewById(R.id.neft_DoneBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}
