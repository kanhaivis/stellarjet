package com.ns.activity;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.ns.fragment.BoardingPassListFragment;
import com.ns.stellarjet.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class BoardingPassActivity extends AppCompatActivity implements BoardingPassListFragment.CBackButton {
    private static final String TAG = "BoardingPassActivity";

    private Button mBackPressImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boarding_pass);


        BoardingPassListFragment boardingPassListFragment = new BoardingPassListFragment();
        boardingPassListFragment.setBackButton(this);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.boardingPass_CPanel, boardingPassListFragment);
        ft.commit();

        mBackPressImg = findViewById(R.id.button_boarding_pass_back);

        mBackPressImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



    }

    @Override
    public void backBtn(boolean check) {
        if (check) {
            mBackPressImg.setEnabled(false);
        } else {
            mBackPressImg.setEnabled(true);
        }
    }


}
