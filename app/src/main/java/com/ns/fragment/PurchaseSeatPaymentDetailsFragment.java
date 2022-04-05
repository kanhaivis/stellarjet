package com.ns.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ns.database.SharedPref;
import com.ns.stellarjet.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PurchaseSeatPaymentDetailsFragment extends Fragment implements PaymentResultListener {

    private static final String TAG = "PurchaseSeatPaymentDeta";

    private int mPurchaseId;
    private int mTotalAmount;
    private int mTotalAmountPaid;
    private int mUnit;
    private int mNeft;
    private String mPurchaseStatus;
    private String mIfsc;
    private String mBankAcc;

    private ProgressBar vProgressBar;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Checkout.preload(getContext());

        mPurchaseId = getArguments().getInt("purchase_id");
        mTotalAmount = getArguments().getInt("total_amount");
        mTotalAmountPaid = getArguments().getInt("total_amount_paid");
        mUnit = getArguments().getInt("unit");
        mNeft = getArguments().getInt("NEFT_amount_limt");
        mPurchaseStatus = getArguments().getString("purchase_status");
        mIfsc = getArguments().getString("ifsc_code");
        mBankAcc = getArguments().getString("bank_ac");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_purchaseseat_payment_details, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        String purchaseStatus = "";
        String seatTitle = "";
        String belowMsg = "";

        if (mUnit == 1) {
            seatTitle = "seat";
        } else {
            seatTitle = "seats";
        }


        TextView reciveTitle = view.findViewById(R.id.purchaseseat_recivedtitleTxt);
        TextView reciveAmount = view.findViewById(R.id.purchaseseat_recivedamountTxt);

        if (mPurchaseStatus.equals("pending")) {
            purchaseStatus = "Payment Pending";
            belowMsg = getResources().getString(R.string.purchase_below_successfull);

        } else {
            purchaseStatus = "Partial Payment";
            belowMsg = getResources().getString(R.string.purchase_below_proceed);

            reciveTitle.setVisibility(View.VISIBLE);
            reciveAmount.setVisibility(View.VISIBLE);

            reciveTitle.setText(getResources().getString(R.string.purchase_amount_received));
            reciveAmount.setText(mTotalAmountPaid);
        }

        ((TextView) view.findViewById(R.id.purchaseseat_payamountseatTitleTxt)).setText(getResources().getString(R.string.purchase_amount) + " " + mUnit + " " + seatTitle);
        ((TextView) view.findViewById(R.id.purchaseseat_payamountseat_amountTxt)).setText("Rs. " + mTotalAmount);
        ((TextView) view.findViewById(R.id.purchaseseat_statusTxt)).setText(purchaseStatus);
        ((TextView) view.findViewById(R.id.purchaseseat_purchaseidTxt)).setText(mPurchaseId + "");
        ((TextView) view.findViewById(R.id.purchaseseat_below_messageTxt)).setText(belowMsg);

        vProgressBar = view.findViewById(R.id.progressBar);


        /**
         * Done event
         */
        view.findViewById(R.id.purchase_DoneBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPref.getSharedPreferences(getContext()).savePurchaseId(mPurchaseId);

                onPaymentProgressShow.onShowPregress(true);
                startPaymentRazorpay(mPurchaseId, mTotalAmount);



            }
        });
    }

    @Override
    public void onPaymentSuccess(String s) {
        Log.d(TAG, "onPaymentSuccess: " + s);
    }

    @Override
    public void onPaymentError(int i, String s) {

        Log.d(TAG, "onPaymentError: " + i + " " + s);
    }

    private void startPaymentRazorpay(int purchanseID, int totalAmount) {
        startPayment(purchanseID, totalAmount);
    }

    public void startPayment(int purchanseID, int totalAmount) {
        // Instantiate Checkout
        Checkout checkout = new Checkout();

        // Set your logo here
        checkout.setImage(R.mipmap.ic_stellar_launcher);

        // Reference to current activity
//        final Activity activity = this;

        // Pass your payment options to the Razorpay Checkout as a JSONObject
        try {
            JSONObject options = new JSONObject();

            //pass app name
            options.put("name", getResources().getString(R.string.app_name));


            JSONObject prefillDate = new JSONObject();
            prefillDate.put("contact", SharedPref.getSharedPreferences(getContext()).getUserPhone());
            prefillDate.put("email", SharedPref.getSharedPreferences(getContext()).getUserEmail());

            options.put("prefill", prefillDate);
            options.put("description", purchanseID);

            options.put("currency", "INR");

            /**     * Amount is always passed in PAISE     * Eg: "500" = Rs 5.00     */
            options.put("amount", totalAmount * 100);
            checkout.open(getActivity(), options);
        } catch (Exception e) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }


    public void showProgressBar(boolean status) {
        if (status) {
            vProgressBar.setVisibility(View.VISIBLE);
        } else {
            vProgressBar.setVisibility(View.GONE);
        }
    }


    public onPaymentProgressShow onPaymentProgressShow;

    public onPaymentProgressShow onPaymentPgressBar(onPaymentProgressShow onPaymentProgressShow_) {
        return onPaymentProgressShow = onPaymentProgressShow_;
    }

    public interface onPaymentProgressShow{
        void onShowPregress(boolean status);
    }
}
