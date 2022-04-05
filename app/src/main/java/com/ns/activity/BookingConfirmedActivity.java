package com.ns.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ns.fragment.BookingConfirmedFragment;
import com.ns.stellarjet.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class BookingConfirmedActivity extends BaseActivity implements BookingConfirmedFragment.BackBtnVisible {
    @Override
    public int getLayout() {
        return R.layout.base_frame_activity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();

        ((Button)findViewById(R.id.button_boarding_pass_back)).setVisibility(View.GONE);
        ((Button)findViewById(R.id.button_home)).setVisibility(View.VISIBLE);


        BookingConfirmedFragment fragment = new BookingConfirmedFragment();
        fragment.setArguments(bundle);
        fragment.setBtnVisible(this);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft =fm.beginTransaction();
        ft.replace(R.id.framePane, fragment);
        ft.commit();
    }

    @Override
    public void onVisibleBtn(boolean show) {
        if (show) {
            ((Button)findViewById(R.id.button_boarding_pass_back)).setVisibility(View.VISIBLE);
            ((Button)findViewById(R.id.button_home)).setVisibility(View.GONE);
        } else {
            ((Button)findViewById(R.id.button_boarding_pass_back)).setVisibility(View.GONE);
            ((Button)findViewById(R.id.button_home)).setVisibility(View.VISIBLE);
        }
    }
}
