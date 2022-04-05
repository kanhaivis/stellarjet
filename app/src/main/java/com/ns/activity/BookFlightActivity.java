package com.ns.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ns.database.SharedPref;
import com.ns.fragment.FlightFromFragment;
import com.ns.model.LoginResponse.CitiesBean;
import com.ns.model.LoginResponse.LoginInfo;
import com.ns.retrofit.APICallBack;
import com.ns.retrofit.APIInterface;
import com.ns.retrofit.APIRetrofit;
import com.ns.stellarjet.R;
import com.ns.utils.ConstantMethod;
import com.ns.utils.UiConstants;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BookFlightActivity extends BaseActivity implements FlightFromFragment.BackButtonIcon {

    private static final String TAG = "BookFlightActivity";

    private ProgressBar mProgressBar;
    private TextView headerTxt;
    private Button backButton;

    @Override
    public int getLayout() {
        return R.layout.bookflight_activity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mProgressBar = findViewById(R.id.bookingflight_progressBar);

        headerTxt = findViewById(R.id.back_title);
        backButton = findViewById(R.id.button_boarding_pass_back);

        mProgressBar.setVisibility(View.VISIBLE);


        APICallBack.getCustomerData(this, new APICallBack.ICustomerData() {
            @Override
            public void onCustomerData(LoginInfo response) {
                if (response.getResultcode() == 1) {
                    mProgressBar.setVisibility(View.GONE);
                    ArrayList<CitiesBean> Citilist = response.getData().getUser_data().getCities();
                    ResultShowFragment(Citilist);
                }
            }

            @Override
            public void onError(Throwable e) {
                mProgressBar.setVisibility(View.GONE);
            }
        });


    }

    private void ResultShowFragment(ArrayList<CitiesBean> citilist) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("CitiData", citilist);

        FlightFromFragment fromFlightFragment = new FlightFromFragment();
        fromFlightFragment.setArguments(bundle);
        fromFlightFragment.setBackButton(this);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.bookflight_cPane, fromFlightFragment);
        ft.commit();
    }

    @Override
    public void onBackBtn(boolean b) {

        if (b) {
            backButton.setEnabled(false);
        } else {
            backButton.setEnabled(true);
        }
    }

    @Override
    public void onTopTitleShow(boolean show) {
        if (show) {
            headerTxt.setText(getResources().getString(R.string.personalize_seat_title));
        } else {
            headerTxt.setText("");
        }
    }
}
