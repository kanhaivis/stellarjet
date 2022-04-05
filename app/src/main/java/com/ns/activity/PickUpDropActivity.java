package com.ns.activity;

import android.os.Bundle;

import com.ns.fragment.MyBookingCheckUserDetailsFragment;
import com.ns.stellarjet.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class PickUpDropActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickupdrop);

        Bundle bundle = getIntent().getExtras();

        MyBookingCheckUserDetailsFragment fragment = new MyBookingCheckUserDetailsFragment();
        fragment.setArguments(bundle);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.framePane, fragment);
        ft.commit();

    }
}
