package com.ns.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ns.activity.PinActivity;
import com.ns.database.SharedPref;
import com.ns.model.LoginResponse.AddressBean;
import com.ns.model.LoginResponse.DataBean;
import com.ns.model.LoginResponse.LoginInfo;
import com.ns.model.LoginResponse.UserDataBean;
import com.ns.model.TCDataBean;
import com.ns.retrofit.APIInterface;
import com.ns.retrofit.APIRetrofit;
import com.ns.stellarjet.R;
import com.ns.utils.ConstantMethod;
import com.ns.utils.UiConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import retrofit2.Response;


public class PasswordFragment extends Fragment {
    private static final String TAG = "PasswordFragment";


    private WebView mWebView;
    private RelativeLayout mSlideUpLayout;
    private boolean mAgreeCheck = false;
    private APIInterface mApiInterface;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiInterface = APIRetrofit.getRetrofitClient(getContext()).create(APIInterface.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_password_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText editText = view.findViewById(R.id.editText_account_password);

        mWebView = view.findViewById(R.id.webviews);
        mWebView.setWebViewClient(new Callback());

        loadTime();

        mSlideUpLayout = view.findViewById(R.id.content_slide_up_view);
        view.findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String userName = SharedPref.getSharedPreferences(getContext()).getUser();
                String password = editText.getText().toString();
                if (!password.isEmpty()) {

                    editText.setBackgroundResource(R.drawable.drawable_edittext_background);
                    editText.setHintTextColor(getResources().getColor(R.color.black));

                    if (ConstantMethod.isConnected(getContext())) {
                        loginUserClick(userName, password);
                    } else {
                        ConstantMethod.ShowToastMessage(getContext(), UiConstants.NO_NETWORK);
                    }

                } else {
                    editText.setBackgroundResource(R.drawable.drawable_edittext_background_error);
                    editText.setHintTextColor(getResources().getColor(R.color.red));
                }
            }
        });

        view.findViewById(R.id.tc_cross_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlideUpLayout.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.slide_down));
                mSlideUpLayout.setVisibility(View.GONE);
            }
        });


        view.findViewById(R.id.tc_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mAgreeCheck) {
                    Intent intent = new Intent(getContext(), PinActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                } else {

                    ConstantMethod.ShowToastMessage(getContext(), "Please checked i agree TC ");
                }

            }
        });


        TextView agreeTxt = view.findViewById(R.id.i_agree_textview);
        agreeTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAgreeCheck) {
                    agreeTxt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_tik_uncheck, 0, 0, 0);
                    mAgreeCheck = false;
                } else {
                    agreeTxt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_tik_check, 0, 0, 0);
                    mAgreeCheck = true;
                }
            }
        });

        view.findViewById(R.id.textview_forgot_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ForgotPasswordShow();
            }
        });
    }

    private void loginUserClick(String userName, String password) {
        ConstantMethod.ShowProgressBar(getActivity());
        Map<String, String> map = new HashMap<>();
        map.put("username", userName);
        map.put("password", password);

        mApiInterface.userLogin(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<LoginInfo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<LoginInfo> loginInfoResponse) {
                        ConstantMethod.HideProgressBar(getActivity());
                        Log.d(TAG, "onNext: " + loginInfoResponse);

                        int code = loginInfoResponse.code();
                        switch (code) {
                            case 200:
                                if (loginInfoResponse.body().getResultcode() == 1) {
                                    Log.d(TAG, "onNext: " + loginInfoResponse);
                                    LaunchPinScree(loginInfoResponse.body().getData());
                                }
                                break;
                            case 400:
                                JSONObject mJsonObject;
                                try {
                                    mJsonObject = new JSONObject(loginInfoResponse.errorBody().string());
                                    String errorMessage = mJsonObject.getString("message");
                                    ConstantMethod.DialogShow(getActivity(), errorMessage);
                                } catch (JSONException | IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        ConstantMethod.HideProgressBar(getActivity());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void LaunchPinScree(DataBean data) {


        mSlideUpLayout.setVisibility(View.VISIBLE);

        mSlideUpLayout.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.slide_up));

        SharedPref.getSharedPreferences(getContext()).saveToken(data.getToken(), data.getRefresh_token());
        SharedPref.getSharedPreferences(getContext()).setUserData(data.getUser_data().getName(), data.getUser_data().getEmail(), data.getUser_data().getPhone());
        SharedPref.getSharedPreferences(getContext()).saveLoginStatus(true);
        SharedPref.getSharedPreferences(getContext()).saveSeatCount(data.getUser_data().getCustomer_prefs().getSeats_available());

        String prepaidTerms = data.getUser_data().getCustomer_prefs().getMembership_details().getPrepaid_terms();
        if (prepaidTerms.equalsIgnoreCase("true")) {
            SharedPref.getSharedPreferences(getContext()).saveMembershipType(UiConstants.PREFERENCES_MEMBERSHIP_SUBSCRIPTION);
        } else if (prepaidTerms.equalsIgnoreCase("false")) {
            SharedPref.getSharedPreferences(getContext()).saveMembershipType(UiConstants.PREFERENCES_MEMBERSHIP_PAY_AS_U_GO);
        }
        String setCoust = data.getUser_data().getCustomer_prefs().getMembership_details().getSeat_cost();
        SharedPref.getSharedPreferences(getContext()).saveSeatCost(Integer.valueOf(setCoust));


    }


    private void ForgotPasswordShow() {


        ForgotPasswordFragment forgotPasswordFragment = new ForgotPasswordFragment();

        new Handler().postDelayed(() -> Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                .replace(R.id.login_cPane, forgotPasswordFragment)
                .addToBackStack(null)
                .commit(), 450);


    }

    void loadTime() {

        String pageUrl = null;
        ArrayList<TCDataBean> tcUrlList = SharedPref.getSharedPreferences(getContext()).getTCUrl();

        for (TCDataBean tc : tcUrlList) {
            if (tc.getScreen().equals("SIGNUP")) {
                pageUrl = tc.getScreenContent();
            }
        }

        mWebView.loadDataWithBaseURL("x-data://base", pageUrl,
                "text/html", "UTF-8",
                null);
    }

    private class Callback extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            loadTime();

            return (true);
        }
    }


}
