package com.ns.fragment;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.ns.database.SharedPref;
import com.ns.model.CommonPojo;
import com.ns.patterns.mvp.presenter.AddAddressPresenter;
import com.ns.patterns.mvp.view.IAddAddressView;
import com.ns.retrofit.APICallBack;
import com.ns.stellarjet.R;
import com.ns.utils.ConstantMethod;
import com.ns.utils.UiConstants;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class SaveAddressDetailsLocationPinFragment extends Fragment implements OnMapReadyCallback, IAddAddressView {

    private static final String TAG = "SaveAddressDetailsLocat";


    private EditText addressEdt;
    private EditText flatNumberEdt;
    private EditText addressTagEdt;


    private LatLng latLng;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Location mLocation;
    private LocationManager mLocationManager;


    private LocationManager locationManager;


    // Bundle data
    private int citi_id;
    private String mLat;
    private String mLng;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        citi_id = this.getArguments().getInt("CitiId");
        mLat = this.getArguments().getString("lat");
        mLng = this.getArguments().getString("lng");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_address_details_scroll, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        addressEdt = view.findViewById(R.id.editText_add_address);
        flatNumberEdt = view.findViewById(R.id.editText_add_address_flat_no);
        addressTagEdt = view.findViewById(R.id.editText_add_address_nickname);


        AppBarLayout appbar = view.findViewById(R.id.app_bar);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) appbar.getLayoutParams();
        AppBarLayout.Behavior behavior = new AppBarLayout.Behavior();
        behavior.setDragCallback(new AppBarLayout.Behavior.DragCallback() {
            @Override
            public boolean canDrag(AppBarLayout appBarLayout) {
                return false;
            }
        });
        params.setBehavior(behavior);


        ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                  .addApi(LocationServices.API)
                .build();

        mLocationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        checkLocation(); //check whether location service is enable or not in your  phone


        AddAddressPresenter addAddressPresenter = new AddAddressPresenter(this);
        view.findViewById(R.id.button_add_address_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAddressPresenter.onAddAddress(addressEdt.getText().toString(), flatNumberEdt.getText().toString(), addressTagEdt.getText().toString());
            }
        });


    }


    @Override
    public void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }


    private boolean checkLocation() {
        if (!isLocationEnabled())
            showAlert();
        return isLocationEnabled();
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle("Enable Location")
                .setMessage("Your Locations Settings is set to 'Off'.\nPlease Enable Location to " +
                        "use this app")
                .setPositiveButton("Location Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {

                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {

                    }
                });
        dialog.show();
    }

    private boolean isLocationEnabled() {
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (mMap != null) {
            Log.d(TAG, "onLocationChanged: ");


            double lat = Double.valueOf(mLat);
            double lng = Double.valueOf(mLng);
            LatLng latLng = new LatLng(lat, lng);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(16));

            mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
                @Override
                public void onCameraChange(CameraPosition cameraPosition) {
                    Log.d(TAG, "onCameraChange: " + cameraPosition.target.latitude);
                    if (cameraPosition.target.latitude != 0 && cameraPosition.target.longitude != 0) {
                        GetMarkerLocationName(cameraPosition.target);
                    }
                }
            });
        }
    }

    private void GetMarkerLocationName(LatLng target) {

        Geocoder geoCoder = new Geocoder(getContext(), Locale.getDefault()); //it is Geocoder
        StringBuilder builder = new StringBuilder();
        try {
            List<Address> address = geoCoder.getFromLocation(target.latitude, target.longitude, 1);
            /*int maxLines = address.get(0).getMaxAddressLineIndex();
            for (int i=0; i<maxLines; i++) {
                String addressStr = address.get(0).getAddressLine(i);
                builder.append(addressStr);
                builder.append(" ");
            }*/

            if (!address.isEmpty()) {
                String fnialAddress = address.get(0).getAddressLine(0); //This is the complete address.
                addressEdt.setText(fnialAddress);
            }
        } catch (IOException e) {
        } catch (NullPointerException e) {
        }

    }


    @Override
    public void show(String address, String houseflat, String addresstag) {
        if (ConstantMethod.isConnected(getContext())) {
            APICall(address, houseflat, addresstag);
        } else {
            ConstantMethod.ShowToastMessage(getContext(), UiConstants.NO_NETWORK);
        }
    }

    private void APICall(String address, String houseflat, String addresstag) {

        ConstantMethod.ShowProgressBar(getActivity());
        String token = SharedPref.getSharedPreferences(getContext()).getTocken();


        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("city", citi_id + "");
        map.put("address", address);
        map.put("address_tag", addresstag);

        APICallBack.getAddNewAddress(new APICallBack.ICommonInterface() {
            @Override
            public void onCommonData(CommonPojo commonPojo) {
                if (commonPojo.getResultcode() == 1) {
                    ConstantMethod.DialogShowFragmentBackPress(getActivity(), commonPojo.getMessage());
                }

            }

            @Override
            public void onError(Throwable e) {

            }
        }, getContext(), map);


    }

    @Override
    public void error(String msg) {
        ConstantMethod.ShowToastMessage(getContext(), msg);
    }
}
