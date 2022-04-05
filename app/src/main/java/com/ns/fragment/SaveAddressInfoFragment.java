package com.ns.fragment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment;
import com.google.android.gms.maps.model.LatLng;
import com.ns.adapter.SaveAddressAdapter;
import com.ns.database.SharedPref;
import com.ns.model.AddressDetailsPojo;
import com.ns.model.City.CitiesBean;
import com.ns.model.City.CityResponse;
import com.ns.retrofit.APICallBack;
import com.ns.retrofit.APIInterface;
import com.ns.retrofit.APIRetrofit;
import com.ns.stellarjet.R;
import com.ns.utils.UiConstants;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class SaveAddressInfoFragment extends Fragment implements SaveAddressAdapter.SelectAddressRow {

    private static final String TAG = "SaveAddressInfoFragment";

    private static final int PERMISSION_REQUEST_CODE = 200;

    private SaveAddressAdapter aSaveAddressAdapter;
    private RecyclerView vSaveAddressRecyclerView;

    private ProgressBar mProgressBar;

    private String mLat;
    private String mLng;


    // Bundle data
    private boolean pin;
    private int citi_id;
    private String name;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pin = this.getArguments().getBoolean("Pin");
        citi_id = this.getArguments().getInt("CitiId");
        name = this.getArguments().getString("CitiName");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.saved_address_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);


        CityListAPICall();


    }

    private void CityListAPICall() {

        APICallBack.getCityDetails(new APICallBack.ICityInterface() {
            @Override
            public void onCityDetails(CityResponse cityResponse) {
                List<CitiesBean> cities = cityResponse.getData().getCities();
                for (CitiesBean bean: cities) {
                    if (bean.getId() == citi_id) {
                        mLat = bean.getAirport_lat();
                        mLng = bean.getAirport_long();
                    }
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        }, getContext());
    }

    private void initView(View view) {
        vSaveAddressRecyclerView = view.findViewById(R.id.recyclerView_saved_address);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        vSaveAddressRecyclerView.setLayoutManager(layoutManager);

        mProgressBar = view.findViewById(R.id.progressBar);

        view.findViewById(R.id.textView_saved_address_current_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!checkPermission()) {

                    requestPermission();
                } else {
                    onOpenPinLicationFragment();
                }


            }
        });
    }


    private void onOpenPinLicationFragment() {


        Bundle bundle = new Bundle();
        bundle.putInt("CitiId", citi_id);
        bundle.putString("CitiName", name);
        bundle.putString("lat", mLat);
        bundle.putString("lng", mLng);
        SaveAddressDetailsLocationPinFragment fragment = new SaveAddressDetailsLocationPinFragment();
        fragment.setArguments(bundle);

        new Handler().postDelayed(() -> Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                .replace(R.id.framePane, fragment)
                .addToBackStack(null)
                .commit(), 450);
    }

    @Override
    public void onResume() {
        super.onResume();
        AddreddAPICall();
    }

    private void AddreddAPICall() {

        mProgressBar.setVisibility(View.VISIBLE);
        APICallBack.getAddressDetails(new APICallBack.IAddressDetailsInterface() {
            @Override
            public void onAddressDetails(AddressDetailsPojo addressDetailsPojo) {
                mProgressBar.setVisibility(View.GONE);
                if (addressDetailsPojo.getResultcode() == 1) {
                    AddressResult(addressDetailsPojo);
                }
            }

            @Override
            public void onError(Throwable e) {
                mProgressBar.setVisibility(View.GONE);
                Log.e(TAG, "onError: "+e.getMessage() );
            }
        }, getContext(), citi_id);

    }

    private void AddressResult(AddressDetailsPojo addressDetailsPojo) {
        aSaveAddressAdapter = new SaveAddressAdapter(addressDetailsPojo.getData().getAddresses());
        aSaveAddressAdapter.setSelectAddressRow(this);
        vSaveAddressRecyclerView.setAdapter(aSaveAddressAdapter);
    }


    @Override
    public void onAddress(String address, int id) {
        if (pin == true) {
            iSelectAddress.onSelectAddress(address, true, id);
        } else if (pin == false) {
            iSelectAddress.onSelectAddress(address, false, id);
        }

        getActivity().onBackPressed();
    }


    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getContext(), ACCESS_FINE_LOCATION);

        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {

                    boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if (locationAccepted) {
                        Log.d(TAG, "onRequestPermissionsResult: " + "Permission Granted, Now you can access location data and camera.");
                        onOpenPinLicationFragment();
                    } else {

                        Log.d(TAG, "onRequestPermissionsResult: " + "Permission Denied, You cannot access location data and camera.");

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
                                showMessageOKCancel("You need to allow access to location the permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{ACCESS_FINE_LOCATION},
                                                            PERMISSION_REQUEST_CODE);
                                                }
                                            }
                                        });
                                return;
                            }
                        }

                    }
                }


                break;
        }
    }


    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(getContext())
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }


    public SelectAddress iSelectAddress;

    public SelectAddress onSelectAddress(SelectAddress SelectAddress_){
        return iSelectAddress = SelectAddress_;
    }

    public interface SelectAddress {
        void onSelectAddress(String address, boolean status, int ids);
    }

}
