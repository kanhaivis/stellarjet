package com.ns.fragment;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.ns.database.SharedPref;
import com.ns.model.ForgotPasswordResponse;
import com.ns.model.StaticTCUrlModelResponse;
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

public class ForgotPasswordFragment extends Fragment {

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
        return inflater.inflate(R.layout.fragment_forgotpassword, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText emailEdt = view.findViewById(R.id.forgotpassword_email);

        view.findViewById(R.id.forgotpassword_confirm_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");

                String result = emailEdt.getText().toString();

                boolean check = Patterns.EMAIL_ADDRESS.matcher(result).matches();
                Log.d(TAG, "onClick: " + check);

                if (!result.isEmpty()) {

                    if (check) {

                        if (ConstantMethod.isConnected(getContext())) {
                            ConfirmBtnsClick(result);
                        } else {
                            ConstantMethod.ShowToastMessage(getContext(), UiConstants.NO_NETWORK);
                        }

                    } else {
                        ConstantMethod.ShowToastMessage(getContext(), "Please enter correct email! ");
                    }
                } else {
                    ConstantMethod.ShowToastMessage(getContext(), "Please enter email! ");
                }
            }
        });


    }

    private void ConfirmBtnsClick(String emailStr) {

        ConstantMethod.ShowProgressBar(getActivity());
        Map<String, String> map = new HashMap<>();
        map.put("email", emailStr);

        mApiInterface.forgotPassword(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ForgotPasswordResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<ForgotPasswordResponse> response) {

                        JSONObject mJsonObject;
                        switch (response.code()) {
                            case 200:
                                ConstantMethod.DialogShowFragmentBackPress(getActivity(), response.body().getMessage());
                                break;
                            case 400:


                                try {
                                    mJsonObject = new JSONObject(response.errorBody().string());
                                    String errorMessage = mJsonObject.getString("message");
                                    ConstantMethod.DialogShow(getActivity(), errorMessage);
                                } catch (JSONException | IOException e) {
                                    e.printStackTrace();
                                }

                                break;
                            case 404:
                                ConstantMethod.DialogShow(getActivity(), "User " + response.message());
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
                        ConstantMethod.HideProgressBar(getActivity());
                    }
                });
    }
}
