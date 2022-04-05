package com.ns.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ns.database.SharedPref;

import com.ns.model.CommonPojo;
import com.ns.patterns.mvp.presenter.AddManagerPresenter;
import com.ns.patterns.mvp.view.IAddManagerView;

import com.ns.retrofit.APIInterface;
import com.ns.retrofit.APIRetrofit;
import com.ns.stellarjet.R;
import com.ns.utils.ConstantMethod;
import com.ns.utils.UiConstants;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ManagerAddFragment extends Fragment implements IAddManagerView {
    private static final String TAG = "BookingAddManagerFragme";


    private APIInterface mApiInterface;

    private AddManagerPresenter mAddManagerPresenter;


    // UI
    private EditText nameEdt;
    private EditText phoneEdt;
    private EditText emailEdt;
    private Button mConfirmBtn;

    // Bundle data
    private String nameStr;
    private String phoneStr;
    private String emailStr;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        nameStr = getArguments().getString("name");
        phoneStr = getArguments().getString("phone");
        emailStr = getArguments().getString("email");

        mApiInterface = APIRetrofit.getRetrofitClient(getContext()).create(APIInterface.class);
        mAddManagerPresenter = new AddManagerPresenter(this);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_booking_addmanager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((TextView) view.findViewById(R.id.title)).setText(getContext().getResources().getString(R.string.booking_addmanager_title));
        ((TextView) view.findViewById(R.id.subtitle)).setText(getContext().getResources().getString(R.string.booking_addmanager_subtitle));


        nameEdt = view.findViewById(R.id.addmanager_nameEdt);
        nameEdt.setText(nameStr);
        phoneEdt = view.findViewById(R.id.addmanager_phoneEdt);
        phoneEdt.setText(phoneStr);
        emailEdt = view.findViewById(R.id.addmanager_emailEdt);
        emailEdt.setText(emailStr);

        mConfirmBtn = view.findViewById(R.id.addmanager_confirmBtn);

        if (nameStr.equals("")) {
            mConfirmBtn.setVisibility(View.VISIBLE);
            nameEdt.setEnabled(true);
            phoneEdt.setEnabled(true);
            emailEdt.setEnabled(true);
        } else {
            mConfirmBtn.setVisibility(View.GONE);
            nameEdt.setEnabled(false);
            phoneEdt.setEnabled(false);
            emailEdt.setEnabled(false);
        }

        mConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAddManagerPresenter.onAddManager(nameEdt.getText().toString(), phoneEdt.getText().toString(), emailEdt.getText().toString());
            }
        });
    }

    @Override
    public void show(String name, String phone, String email) {

        Log.d(TAG, "show: ");

        if (ConstantMethod.isConnected(getContext())) {
            APICall(name, phone, email);
        } else {
            ConstantMethod.ShowToastMessage(getContext(), UiConstants.NO_NETWORK);
        }

    }

    @Override
    public void error(String msg) {
        ConstantMethod.ShowToastMessage(getContext(), msg);
    }

    private void APICall(String name, String phone, String email) {

        ConstantMethod.ShowProgressBar(getActivity());
        String token = SharedPref.getSharedPreferences(getContext()).getTocken();


        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("name", name);
        map.put("email", email);
        map.put("phone", phone);


        mApiInterface.getAddManagerInfo(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommonPojo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CommonPojo response) {
                        Log.d(TAG, "onNext: " + response);
                        ResultShow(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onError: " + e.getMessage());
                        ConstantMethod.HideProgressBar(getActivity());
                    }

                    @Override
                    public void onComplete() {
                        ConstantMethod.HideProgressBar(getActivity());
                    }
                });

    }

    private void ResultShow(CommonPojo response) {
        int code = response.getResultcode();
        if (code == 1) {
            ConstantMethod.DialogShowFragmentBackPress(getActivity(), response.getMessage());
        }
    }
}
