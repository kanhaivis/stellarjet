package com.ns.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.JsonElement;
import com.ns.activity.SignUpActivity;
import com.ns.database.SharedPref;
import com.ns.model.StaticTCUrlModelResponse;
import com.ns.model.UserValidResponse;
import com.ns.retrofit.APIInterface;
import com.ns.retrofit.APIRetrofit;
import com.ns.stellarjet.R;
import com.ns.utils.ConstantMethod;
import com.ns.utils.UiConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginEmailPhoneFragment extends Fragment {

    private static final String TAG = "LoginEmailPhoneFragment";

    private APIInterface mApiInterface;

    LoginConfirmBtnClick mConfirmBtnClick;

    public LoginConfirmBtnClick setBtnConfirmClicl(LoginConfirmBtnClick click) {
        return mConfirmBtnClick = click;
    }

    public interface LoginConfirmBtnClick {
        void ClickBtnConfirm(String msg);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApiInterface = APIRetrofit.getRetrofitClient(getContext()).create(APIInterface.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_emailphone_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText emailPhoneEdt = view.findViewById(R.id.editText_account_id);

        view.findViewById(R.id.btn_login_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");

                String result = emailPhoneEdt.getText().toString();
                if (!result.isEmpty()) {
                    emailPhoneEdt.setBackgroundResource(R.drawable.drawable_edittext_background);
                    emailPhoneEdt.setHintTextColor(getResources().getColor(R.color.black));
                    emailPhoneEdt.setHint("");
                    if (ConstantMethod.isConnected(getContext())) {

                        ConfirmBtnsClick(result);
                    } else {
                        ConstantMethod.ShowToastMessage(getContext(), UiConstants.NO_NETWORK);
                    }

                } else {
                    emailPhoneEdt.setBackgroundResource(R.drawable.drawable_edittext_background_error);
                    emailPhoneEdt.setHintTextColor(getResources().getColor(R.color.red));
                    emailPhoneEdt.setHint(R.string.place_enter_email_phone_hint);
                }
            }
        });

        view.findViewById(R.id.login_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SignUpActivity.class);
                startActivity(intent);
            }
        });

    }



    private void ConfirmBtnsClick(String result) {

        ConstantMethod.ShowProgressBar(getActivity());
        Map<String, String> map = new HashMap<>();
        map.put("username", result);

        mApiInterface.doValidateCustomer(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<UserValidResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<UserValidResponse> userValidResponseResponse) {
                        ConstantMethod.HideProgressBar(getActivity());
                        switch (userValidResponseResponse.code()) {
                            case 200:

                                String user = userValidResponseResponse.body().getData().getUsername();
                                String usertype = userValidResponseResponse.body().getData().getUsertype();

                                SharedPref.getSharedPreferences(getContext()).setUserType(user, usertype);

                                mConfirmBtnClick.ClickBtnConfirm(usertype);

                                break;
                            case 400:

                                JSONObject mJsonObject;
                                try {
                                    mJsonObject = new JSONObject(userValidResponseResponse.errorBody().string());
                                    String errorMessage = mJsonObject.getString("message");
                                    ConstantMethod.DialogShow(getActivity(), errorMessage);
                                } catch (JSONException | IOException e) {
                                    e.printStackTrace();
                                }

                                break;
                            case 500:
                                ConstantMethod.DialogShow(getActivity(), "Server Error!");
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
}
