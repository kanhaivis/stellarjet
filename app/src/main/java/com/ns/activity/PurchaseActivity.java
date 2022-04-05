package com.ns.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.ns.database.SharedPref;
import com.ns.fragment.PurchaseSeatFragment;
import com.ns.fragment.PurchaseSeatNEFTPaymentFragment;
import com.ns.fragment.PurchaseSeatPaymentDetailsFragment;
import com.ns.model.PaymentResponse.PaymentLastOrderDetails;
import com.ns.model.PaymentResponse.VerifyPurchaseResponse;
import com.ns.retrofit.APICallBack;
import com.ns.stellarjet.R;
import com.ns.utils.ConstantMethod;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class PurchaseActivity extends AppCompatActivity implements PaymentResultListener, PurchaseSeatPaymentDetailsFragment.onPaymentProgressShow, PurchaseSeatFragment.onPaymentProgressShow {

    private static final String TAG = "PurchaseActivity";

    private PurchaseSeatPaymentDetailsFragment purchaseSeatPaymentDetailsFragment;
    private PurchaseSeatFragment purchaseSeatFragment;

    private boolean iStatus;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);


        Checkout.preload(this);

        Bundle bundle = this.getIntent().getExtras();

        PaymentLastOrderDetails mLastOrderDetails = (PaymentLastOrderDetails) bundle.getSerializable("lastOrderData");


        if (mLastOrderDetails.getData().getPurchaseData().getPurchase_status() == null) {

            ShowPaymentUi();

        } else {
            Log.d(TAG, "onPurchanseLastOrder: " + mLastOrderDetails);

            onShowResult(mLastOrderDetails);

        }

        findViewById(R.id.button_boarding_pass_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }


    private void ShowPaymentUi() {

        purchaseSeatFragment = new PurchaseSeatFragment();
        purchaseSeatFragment.onPaymentPgressBar(this);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.framePane, purchaseSeatFragment);
        ft.commit();
    }

    private void onShowResult(PaymentLastOrderDetails orderResponse) {


        Bundle bundle = new Bundle();
        bundle.putInt("purchase_id", orderResponse.getData().getPurchaseData().getPurchase_id());
        bundle.putInt("total_amount", orderResponse.getData().getPurchaseData().getTotal_amount());
        bundle.putInt("total_amount_paid", orderResponse.getData().getPurchaseData().getTotal_amount_paid());
        bundle.putInt("unit", orderResponse.getData().getPurchaseData().getOrder_details().getTotal().getUnit());
        bundle.putInt("NEFT_amount_limt", orderResponse.getData().getPurchaseData().getNEFT_amount_limt());
        bundle.putString("purchase_status", orderResponse.getData().getPurchaseData().getPurchase_status());
        bundle.putString("ifsc_code", orderResponse.getData().getPurchaseData().getIfsc_code());
        bundle.putString("bank_ac", orderResponse.getData().getPurchaseData().getBank_ac());

        if (orderResponse.getData().getPurchaseData().getTotal_amount() > orderResponse.getData().getPurchaseData().getNEFT_amount_limt()) {

            PurchaseSeatNEFTPaymentFragment purchaseSeatNEFTPaymentFragment = new PurchaseSeatNEFTPaymentFragment();
            purchaseSeatNEFTPaymentFragment.setArguments(bundle);

            new Handler().postDelayed(() -> Objects.requireNonNull(this).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.framePane, purchaseSeatNEFTPaymentFragment)
                    .addToBackStack(null)
                    .commit(), 450);
        } else {


            purchaseSeatPaymentDetailsFragment = new PurchaseSeatPaymentDetailsFragment();
            purchaseSeatPaymentDetailsFragment.setArguments(bundle);
            purchaseSeatPaymentDetailsFragment.onPaymentPgressBar(this);


            new Handler().postDelayed(() -> Objects.requireNonNull(this).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.framePane, purchaseSeatPaymentDetailsFragment)
                    .addToBackStack(null)
                    .commit(), 450);
        }


    }

    @Override
    public void onPaymentSuccess(String paymentId) {
        Log.d(TAG, "onPaymentSuccess: " + paymentId);

        VerifiAPICall(paymentId);

    }

    @Override
    public void onPaymentError(int i, String s) {
        Log.d(TAG, "onPaymentError: " + i + " --> " + s);

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        finish();
    }

    /*@Override
    public void onBackPressed() {
//        super.onBackPressed();


        if (check) {
            onBackPressed();
        } else {
            Intent intent = new Intent();
            setResult(202, intent);
            finish();
        }
    }*/


    private void VerifiAPICall(String paymentId_) {
        if (iStatus) {
            purchaseSeatPaymentDetailsFragment.showProgressBar(true);
        } else {
            purchaseSeatFragment.showProgressBar(true);
        }

        String mToken = SharedPref.getSharedPreferences(this).getTocken();
        int mPurchanseID = SharedPref.getSharedPreferences(this).getPurchaseId();

        Map<String, String> map = new HashMap<>();
        map.put("token", mToken);
        map.put("payment_id", paymentId_);
        map.put("purchase_id", mPurchanseID + "");


        APICallBack.getVerifyPurchaseSeat(new APICallBack.IVerifyPurchaseSeatInterface() {
            @Override
            public void onVerifyPurchaseSeat(VerifyPurchaseResponse verifyPurchaseResponse) {
                Log.d(TAG, "onVerifyPurchaseSeat: " + verifyPurchaseResponse);

                if (iStatus) {
                    purchaseSeatPaymentDetailsFragment.showProgressBar(false);
                } else {
                    purchaseSeatFragment.showProgressBar(false);
                }

                ConstantMethod.DialogShowSingleAction(new ConstantMethod.DialogSingleAction() {
                    @Override
                    public void onAction(boolean status) {
                        if (status) {
                            onBackPressed();
                        }

                    }
                }, PurchaseActivity.this, verifyPurchaseResponse.getMessage());

            }

            @Override
            public void onError(Throwable e) {

                if (iStatus) {
                    purchaseSeatPaymentDetailsFragment.showProgressBar(false);
                } else {
                    purchaseSeatFragment.showProgressBar(false);
                }

            }
        }, this, map);

    }

    @Override
    public void onShowPregress(boolean status_) {
        iStatus = status_;
    }
}
