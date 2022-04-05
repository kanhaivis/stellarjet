package com.ns.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ns.adapter.PlaceSelectAdapter;
import com.ns.database.SharedPref;
import com.ns.model.LoginResponse.CitiesBean;
import com.ns.model.PaymentResponse.PaymentLastOrderDetails;
import com.ns.retrofit.APICallBack;
import com.ns.stellarjet.R;
import com.ns.utils.ConstantMethod;
import com.ns.utils.UiConstants;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.ns.utils.ConstantListMethod.nSaveTiemList;
import static com.ns.utils.ConstantListMethod.nSaveTiemLists;

public class DrawerActivity extends AppCompatActivity implements View.OnClickListener, PlaceSelectAdapter.onPlaceSelectClickListener {

    private static final String TAG = "DrawerActivity";

    private RecyclerView mRecyclerView;
    private PlaceSelectAdapter mPlaceSelectAdapter;

    private PaymentLastOrderDetails mLastOrderResponse;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        findViewById(R.id.button_drawer_close).setOnClickListener(this);
        findViewById(R.id.button_drawer_personal_assistance).setOnClickListener(this);


        mRecyclerView = findViewById(R.id.recyclerView_drawer);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        String userType = SharedPref.getSharedPreferences(this).getUserType();
        if (userType.equals("primary")) {
            mPlaceSelectAdapter = new PlaceSelectAdapter(this, nSaveTiemList(), false);
        } else if (userType.equals("secondary")) {
            mPlaceSelectAdapter = new PlaceSelectAdapter(this, nSaveTiemLists(), false);
        }

        mRecyclerView.setAdapter(mPlaceSelectAdapter);
        ConstantMethod.RunLayoutAnimationRecyclerview(mRecyclerView);

    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.button_drawer_close:
                onBackPressed();
                break;
            case R.id.button_drawer_personal_assistance:
                // phone call
                String phoneNumber = SharedPref.getSharedPreferences(this).getCustomeHelpPhoneNumber();
                ConstantMethod.CustomerSupportPhoneCall(this, phoneNumber);
                break;
        }
    }

    @Override
    public void onPlaceSelected(String placeName, int placeId) {

        switch (placeId) {
            case 0:
                if (ConstantMethod.isConnected(this)) {
                    startActivity(new Intent(this, BoardingPassActivity.class));
                } else {
                    ConstantMethod.ShowToastMessage(this, UiConstants.NO_NETWORK);
                }

                break;
            case 1:
                if (ConstantMethod.isConnected(this)) {
                    startActivity(new Intent(this, MyBookingsActivity.class));
                } else {
                    ConstantMethod.ShowToastMessage(this, UiConstants.NO_NETWORK);
                }

                break;
            case 2:
                startActivity(new Intent(this, PreferencesActivity.class));
                break;
            case 3:

                if (ConstantMethod.isConnected(this)) {
                    APICallLastOrdet();
                } else {
                    ConstantMethod.ShowToastMessage(this, UiConstants.NO_NETWORK);
                }
                break;
        }
    }


    private void APICallLastOrdet() {

        APICallBack.getPurchaseLastOrder(new APICallBack.IPurchaseLastOrderInterface() {
            @Override
            public void onPurchanseLastOrder(PaymentLastOrderDetails orderResponse) {
                if (orderResponse.getResultcode() == 1) {
                    mLastOrderResponse = orderResponse;
                    Log.d(TAG, "onPurchanseLastOrder: " + orderResponse);
                    onShowResponse(orderResponse);
                }

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.getMessage());
            }
        }, this);
    }

    private void onShowResponse(PaymentLastOrderDetails orderResponse) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("lastOrderData", mLastOrderResponse);
        Intent intent = new Intent(this, PurchaseActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
