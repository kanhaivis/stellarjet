package com.ns.activity;

import android.app.ActivityManager;
import android.os.Bundle;
import android.util.Log;

import com.ns.fragment.LoginEmailPhoneFragment;
import com.ns.fragment.PasswordFragment;
import com.ns.fragment.OtpFragment;
import com.ns.stellarjet.R;


import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class LoginActivity extends AppCompatActivity implements LoginEmailPhoneFragment.LoginConfirmBtnClick {

    private static final String TAG = "LoginActivity";

    boolean mKayBoolean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);


        mKayBoolean = this.getIntent().getBooleanExtra("key", false);

        // Fragment initialization
        LoginEmailPhoneFragment emailPhoneFragment = new LoginEmailPhoneFragment();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.login_cPane, emailPhoneFragment);
        ft.addToBackStack(null);
        ft.commit();


        emailPhoneFragment.setBtnConfirmClicl(this);
    }

    @Override
    public void ClickBtnConfirm(String userType) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (userType.equals("primary")) {
            Log.d(TAG, "ClickBtnConfirm: ");

            ft.replace(R.id.login_cPane, new PasswordFragment());
            ft.addToBackStack(null);
            ft.commit();
        } else if (userType.equals("secondary")) {

            ft.replace(R.id.login_cPane, new OtpFragment());
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    @Override
    public void onBackPressed() {

        if (getFragmentManager().getBackStackEntryCount() > 0) {
            Log.d(TAG, "onBackPressed: ");
            getFragmentManager().popBackStack();
        } else {
            Log.d(TAG, "onBackPressed: ");
            super.onBackPressed();
        }


        /**
         * key check data which activity coming.
         *
         * PinActivity mKayBoolean true
         * SplaceScreen mKayBoolean false
         */
        if (mKayBoolean) {
            ActivityManager mngr = (ActivityManager) getSystemService(ACTIVITY_SERVICE);

            List<ActivityManager.RunningTaskInfo> taskList = mngr.getRunningTasks(10);
            int taskListSize = taskList.size();
            Log.d(TAG, "onBackPressed: " + taskListSize);

            if (taskListSize > 0) {
                finish();
            }
        }

    }
}
