package com.ns.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ns.database.SharedPref;
import com.ns.model.PaymentResponse.PurchaseSeatDetails;
import com.ns.model.PaymentResponse.VerifyPurchaseResponse;
import com.ns.retrofit.APICallBack;
import com.ns.stellarjet.R;
import com.ns.utils.ConstantMethod;
import com.ns.utils.UiConstants;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Response;

public class PurchaseSeatFragment extends Fragment implements PaymentResultListener {
    private static final String TAG = "PurchaseSeatFragment";

    private Button mBuyNowBtn;
    private EditText eSeatEdt;
    private TextView tSeatCountCost;
    private ProgressBar vProgressBar;


    private int mSeatCost;
    private int mTotalSeatCost;
    private int mSeatCount = 0;
    private String mToken;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Checkout.preload(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_purchaseseat, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mToken = SharedPref.getSharedPreferences(getContext()).getTocken();
        mSeatCost = SharedPref.getSharedPreferences(getContext()).getSeatCost();
        tSeatCountCost = view.findViewById(R.id.textView_purchase_pay_amount);

        vProgressBar = view.findViewById(R.id.progressBar);

        mBuyNowBtn = view.findViewById(R.id.button_purchase_buy_now);


        eSeatEdt = view.findViewById(R.id.editText_purchase_seat_count);
        eSeatEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                String word = editable.toString();
                if (!word.isEmpty()) {
                    mSeatCount = Integer.valueOf(word);
                    if (mSeatCount <= 10) {

                        mTotalSeatCost = mSeatCount * mSeatCost;
                        eSeatEdt.setTextColor(getResources().getColor(R.color.whight));
                        tSeatCountCost.setText("Rs. " + mTotalSeatCost);
                        mBuyNowBtn.setVisibility(View.VISIBLE);
                    } else {
                        mBuyNowBtn.setVisibility(View.GONE);
                        eSeatEdt.setText("");
                        tSeatCountCost.setText("Rs. 0");
                    }
                } else {
                    tSeatCountCost.setText("Rs. 0");
                    mBuyNowBtn.setVisibility(View.GONE);
                }

            }
        });


        mBuyNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ConstantMethod.isConnected(getContext())) {
                    if (mSeatCount != 0) {
                        APICall(mSeatCount);
                    } else {
                        ConstantMethod.ShowToastMessage(getContext(), "Please select seat!");
                    }
                } else {
                    ConstantMethod.ShowToastMessage(getContext(), UiConstants.NO_NETWORK);
                }
            }
        });
    }


    private void APICall(int seatCount) {
        Log.d(TAG, "APICall: " + seatCount);
        vProgressBar.setVisibility(View.VISIBLE);
        APICallBack.getPurchaseSeat(new APICallBack.IPurchaseSeatInterface() {
            @Override
            public void onPurchanseSeat(Response<PurchaseSeatDetails> orderResponse) {
                Log.d(TAG, "onPurchanseSeat: " + orderResponse);

                vProgressBar.setVisibility(View.GONE);
                int code = orderResponse.code();
                switch (code) {
                    case 200:
                        PurchaseSeatDetails response = orderResponse.body();
                        SharedPref.getSharedPreferences(getContext()).savePurchaseId(response.getData().getPurchase_id());
                        onShowPurchaseDetals(response.getData().getPurchase_id(), response);

                        break;
                    case 400:

                        JSONObject mJsonObject;
                        try {
                            mJsonObject = new JSONObject(orderResponse.errorBody().string());
                            String errorMessage = mJsonObject.getString("message");
                            ConstantMethod.DialogShow(getContext(), errorMessage);
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e);
                vProgressBar.setVisibility(View.GONE);

            }
        }, getContext(), mToken, seatCount);


    }

    private void onShowPurchaseDetals(int mPurchanseID, PurchaseSeatDetails response) {


        Bundle bundle = new Bundle();
        bundle.putInt("purchase_id", response.getData().getPurchase_id());
        bundle.putInt("total_amount", response.getData().getTotal_amount());
        bundle.putInt("unit", response.getData().getOrder_details().getTotal().getUnit());
        bundle.putInt("NEFT_amount_limt", response.getData().getNEFT_amount_limt());
        bundle.putString("purchase_status", response.getData().getPurchase_status());
        bundle.putString("ifsc_code", response.getData().getIfsc_code());
        bundle.putString("bank_ac", response.getData().getBank_ac());


        if (response.getData().getTotal_amount() > response.getData().getNEFT_amount_limt()) {

            PurchaseSeatNEFTPaymentFragment purchaseSeatNEFTPaymentFragment = new PurchaseSeatNEFTPaymentFragment();
            purchaseSeatNEFTPaymentFragment.setArguments(bundle);

            new Handler().postDelayed(() -> Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.framePane, purchaseSeatNEFTPaymentFragment)
                    .addToBackStack(null)
                    .commit(), 450);
        } else {

            ConstantMethod.DialogShowDoubleAction(new ConstantMethod.DialogAction() {
                @Override
                public void onAction(boolean status) {
                    if (status) {
                        onPaymentProgressShow.onShowPregress(false);
                        startPayment(mPurchanseID, response.getData().getTotal_amount());
                    }

                }
            }, getContext(), response.getMessage() + "\n Seats coast Rs. " + response.getData().getTotal_amount(), "Buy Now", "No");
        }


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


    @Override
    public void onPaymentSuccess(String s) {
        Log.d(TAG, "onPaymentSuccess: " + s);

    }

    @Override
    public void onPaymentError(int i, String s) {
        Log.d(TAG, "onPaymentError: " + i + " --> " + s);

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

    public interface onPaymentProgressShow {
        void onShowPregress(boolean status);
    }
}
