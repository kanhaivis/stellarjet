package com.ns.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.JsonElement;
import com.ns.adapter.AddManagerAdapter;
import com.ns.database.SharedPref;
import com.ns.model.AddManager.ManagerResponse;
import com.ns.model.AddManager.SecondaryUsersBean;
import com.ns.model.CommonPojo;
import com.ns.model.SecondaryUser.ActiveDeactiveRequest;
import com.ns.retrofit.APICallBack;
import com.ns.retrofit.APIInterface;
import com.ns.retrofit.APIRetrofit;
import com.ns.stellarjet.R;
import com.ns.utils.ConstantMethod;
import com.ns.utils.UiConstants;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ManagerFragment extends Fragment implements AddManagerAdapter.RowData {

    private static final String TAG = "ManagerFragment";

    private APIInterface mApiInterface;

    private RecyclerView mRecyclerView;

    private String token;

    private AddManagerAdapter mAddManagerAdapter;
    private List<SecondaryUsersBean> mSecondaryUsersList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiInterface = APIRetrofit.getRetrofitClient(getContext()).create(APIInterface.class);

        token = SharedPref.getSharedPreferences(getContext()).getTocken();
    }


    @Override
    public void onResume() {
        super.onResume();
        if (ConstantMethod.isConnected(getContext())) {
            ManagerApiCall();
        } else {
            ConstantMethod.ShowToastMessage(getContext(), UiConstants.NO_NETWORK);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bookingmanager_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((TextView) view.findViewById(R.id.title)).setText(getContext().getResources().getString(R.string.booking_managet_title));
        ((TextView) view.findViewById(R.id.subtitle)).setText(getContext().getResources().getString(R.string.booking_managet_subtitle));


        mRecyclerView = view.findViewById(R.id.bookingmanager_recyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);


        view.findViewById(R.id.bookingmanager_addBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("name", "");
                bundle.putString("phone", "");
                bundle.putString("email", "");

                AddManagerFragmentAction(bundle);

            }
        });

    }


    private void ManagerApiCall() {

        APICallBack.getManagerResponse(new APICallBack.IManagerResponseInterface() {
            @Override
            public void onManagerResponse(ManagerResponse response) {
                Log.d(TAG, "onNext: " + response);
                if (response.getResultcode() == 1) {
                    ShowResult(response.getData().getSecondary_users());
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        }, getContext());

    }

    private void ShowResult(List<SecondaryUsersBean> secondary_users) {
        mSecondaryUsersList = secondary_users;
        mAddManagerAdapter = new AddManagerAdapter(mSecondaryUsersList);
        mAddManagerAdapter.OnClickRow(this);
        mRecyclerView.setAdapter(mAddManagerAdapter);
    }

    @Override
    public void rowInfo(String name, String phone, String email) {
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("phone", phone);
        bundle.putString("email", email);

        AddManagerFragmentAction(bundle);
    }

    @Override
    public void onRowDelete(int sid, int position) {

        ConstantMethod.ShowProgressBar(getActivity());
        APICallBack.getDeleteManagerSecondaryUser(new APICallBack.IDeleteManager() {
            @Override
            public void onDeleteManager(CommonPojo commonPojo) {
                ConstantMethod.HideProgressBar(getActivity());

                if (commonPojo.getResultcode() == 1) {
                    ConstantMethod.DialogShowSingleAction(new ConstantMethod.DialogSingleAction() {
                        @Override
                        public void onAction(boolean status) {
                            if (status) {
                                mSecondaryUsersList.remove(position);
                                mAddManagerAdapter.notifyDataSetChanged();
                            }
                        }
                    }, getContext(), commonPojo.getMessage());
                }
            }

            @Override
            public void onError(Throwable e) {
                ConstantMethod.HideProgressBar(getActivity());
            }
        }, getContext(), token, sid);
    }


    private void AddManagerFragmentAction(Bundle bundle) {

        ManagerAddFragment fragment = new ManagerAddFragment();
        fragment.setArguments(bundle);

        new Handler().postDelayed(() -> Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                .replace(R.id.preferences_CPane, fragment)
                .addToBackStack(null)
                .commit(), 450);
    }
}
