package com.ns.activity;

import android.content.Intent;
import android.os.Bundle;

import com.ns.fragment.MyBookingCheckUserDetailsFragment;
import com.ns.stellarjet.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MyBookingUserDetailsActivity extends BaseActivity implements MyBookingCheckUserDetailsFragment.ITicketCanceled {

    @Override
    public int getLayout() {
        return R.layout.base_frame_activity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();

        MyBookingCheckUserDetailsFragment fragment = new MyBookingCheckUserDetailsFragment();
        fragment.setArguments(bundle);
        fragment.setTicketAction(this);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.framePane, fragment);
        ft.commit();


    }

    @Override
    public void onAction(boolean status) {

        Intent intent = new Intent();
        setResult(101, intent);
        finish();
    }
}
