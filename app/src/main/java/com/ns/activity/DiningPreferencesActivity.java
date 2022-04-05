package com.ns.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ns.database.SharedPref;
import com.ns.fragment.DiningPreferenceFragment;
import com.ns.model.LoginResponse.CitiesBean;
import com.ns.model.LoginResponse.CustomerPrefsBean;
import com.ns.model.LoginResponse.LoginInfo;
import com.ns.retrofit.APICallBack;
import com.ns.retrofit.APIInterface;
import com.ns.retrofit.APIRetrofit;
import com.ns.stellarjet.R;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class DiningPreferencesActivity extends BaseActivity {

    private static final String TAG = "DiningPreferencesActivi";

    private APIInterface mApiInterface;

    @Override
    public int getLayout() {
        return R.layout.activity_preference_layout;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiInterface = APIRetrofit.getRetrofitClient(this).create(APIInterface.class);

        APICall();

    }

    private void APICall() {

        APICallBack.getCustomerData(this, new APICallBack.ICustomerData() {
            @Override
            public void onCustomerData(LoginInfo response) {
                if (response.getResultcode() == 1) {
                    CustomerPrefsBean customerPrefsBean = response.getData().getUser_data().getCustomer_prefs();
                    ResultShowFragment(customerPrefsBean);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: "+e.getMessage());
            }
        });
    }

    private void ResultShowFragment(CustomerPrefsBean customerPrefsBean) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("customer_prefs", customerPrefsBean);

        DiningPreferenceFragment fragment = new DiningPreferenceFragment();
        fragment.setArguments(bundle);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.preferences_CPane, fragment);
        ft.commit();
    }
}
