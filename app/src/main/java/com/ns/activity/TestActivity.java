package com.ns.activity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.ns.database.SharedPref;
import com.ns.model.BoardingPass.BoardingPassResponse;
import com.ns.model.Booking.BookingIdResponse;
import com.ns.retrofit.APIInterface;
import com.ns.retrofit.APIRetrofit;
import com.ns.stellarjet.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TestActivity extends AppCompatActivity {
    private static final String TAG = "TestActivity";

    //https://maps.googleapis.com/maps/api/geocode/json?address=delhi&sensor=false&key=AIzaSyC1uZYkROp06LPKTv6kkj7Zt1FUmsZGAq4

    TextView infoText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);


        startActivity(new Intent(TestActivity.this, TouchIdActivity.class));


    }




}







