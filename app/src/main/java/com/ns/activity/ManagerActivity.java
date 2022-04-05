package com.ns.activity;

import android.os.Bundle;

import com.ns.fragment.ManagerFragment;
import com.ns.stellarjet.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ManagerActivity extends BaseActivity {
    @Override
    public int getLayout() {
        return R.layout.activity_preference_layout;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.preferences_CPane, new ManagerFragment());
        ft.commit();
    }
}
