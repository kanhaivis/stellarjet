package com.ns.activity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.ns.adapter.MyBookingViewPagerAdapter;
import com.ns.custom.MyCustomViewPager;
import com.ns.fragment.CompletedFragment;
import com.ns.fragment.UpcomingFragment;
import com.ns.stellarjet.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MyBookingsActivity extends AppCompatActivity  {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybookings);

        MyCustomViewPager viewPager = findViewById(R.id.home_viewpager);
        viewPager.setPagingEnabled(false);
        setViewPagera(viewPager);


        TabLayout tableLayout = findViewById(R.id.home_tabs);
        tableLayout.setupWithViewPager(viewPager);


        findViewById(R.id.button_boarding_pass_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void setViewPagera(ViewPager viewPagera) {
        MyBookingViewPagerAdapter adapter = new MyBookingViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new UpcomingFragment(), "Upcoming");
        adapter.addFragment(new CompletedFragment(), "Completed");
        viewPagera.setAdapter(adapter);

    }


}
