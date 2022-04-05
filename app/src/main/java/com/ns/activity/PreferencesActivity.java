package com.ns.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ns.stellarjet.R;
import com.ns.utils.ConstantMethod;
import com.ns.utils.UiConstants;

import androidx.annotation.Nullable;

public class PreferencesActivity extends BaseActivity {

    @Override
    public int getLayout() {
        return R.layout.activity_preference;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        findViewById(R.id.preference_managerLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ConstantMethod.isConnected(PreferencesActivity.this)) {
                    startActivity(new Intent(v.getContext(), ManagerActivity.class));
                } else {
                    ConstantMethod.ShowToastMessage(v.getContext(), UiConstants.NO_NETWORK);
                }

            }
        });

        findViewById(R.id.preference_diningLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ConstantMethod.isConnected(PreferencesActivity.this)) {
                    startActivity(new Intent(v.getContext(), DiningPreferencesActivity.class));
                } else {
                    ConstantMethod.ShowToastMessage(v.getContext(), UiConstants.NO_NETWORK);
                }
            }
        });
    }
}
