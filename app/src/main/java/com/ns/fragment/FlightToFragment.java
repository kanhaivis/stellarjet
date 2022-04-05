package com.ns.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ns.adapter.PlaceSelectAdapter;
import com.ns.model.LoginResponse.CitiesBean;
import com.ns.stellarjet.R;
import com.ns.utils.ConstantMethod;
import com.ns.utils.UiConstants;

import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FlightToFragment extends Fragment implements PlaceSelectAdapter.onPlaceSelectClickListener, FlightSelectDateFragment.BackButtonIcon {

    private static final String TAG = "FlightToFragment";

    private ArrayList<CitiesBean> mCitilist;
    private ArrayList<CitiesBean> mNewCitilist = new ArrayList<>();
    private String mFromNameStr;
    private int mFromIdInt;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCitilist = (ArrayList<CitiesBean>) this.getArguments().getSerializable("CitiData");
        mFromNameStr = this.getArguments().getString(UiConstants.FLIGHT_FROM_NAME);
        mFromIdInt = this.getArguments().getInt(UiConstants.FLIGHT_FROM_ID);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.flight_from_to_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((TextView) view.findViewById(R.id.from_to_ttitle_Txt)).setText(R.string.booking_to);

        RecyclerView recyclerView = view.findViewById(R.id.common_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        PlaceSelectAdapter adapter = new PlaceSelectAdapter(this, mCitilist, true);
        recyclerView.setAdapter(adapter);
        ConstantMethod.RunLayoutAnimationRecyclerview(recyclerView);

    }

    @Override
    public void onPlaceSelected(String placeName, int placeId) {

        if (ConstantMethod.isConnected(getContext())) {
            getFlightSchedules(placeName, placeId);
        } else {
            ConstantMethod.ShowToastMessage(getContext(), UiConstants.NO_NETWORK);
        }
        /*SharedPreferencesHelper.saveToCityId(getActivity() , placeId);
        SharedPreferencesHelper.saveToCity(getActivity() , placeName);
        if(StellarJetUtils.isConnectingToInternet(getActivity())){
            getFlightSchedules();
        }else{
            UiUtils.Companion.showNoInternetDialog(getActivity());
        }*/
    }

    private void getFlightSchedules(String placeName, int placeId) {


        Bundle bundle = new Bundle();
        bundle.putString(UiConstants.FLIGHT_FROM_NAME, mFromNameStr);
        bundle.putInt(UiConstants.FLIGHT_FROM_ID, mFromIdInt);
        bundle.putString(UiConstants.FLIGHT_TO_NAME, placeName);
        bundle.putInt(UiConstants.FLIGHT_TO_ID, placeId);

        FlightSelectDateFragment flightSelectDateFragment = new FlightSelectDateFragment();
        flightSelectDateFragment.setArguments(bundle);
        flightSelectDateFragment.setBackButton(this);

        new Handler().postDelayed(() -> Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                .replace(R.id.bookflight_cPane, flightSelectDateFragment)
                .addToBackStack(null)
                .commit(), 450);
    }


    @Override
    public void onBackBtn(boolean b) {
        if (b) {
            mBackButtonIcon.onBackBtn(b);
        } else {
            mBackButtonIcon.onBackBtn(b);
        }
    }

    @Override
    public void onTopTitleShow(boolean show) {
        if (show) {
            mBackButtonIcon.onTopTitleShow(show);
        } else {
            mBackButtonIcon.onTopTitleShow(show);
        }
    }

    public BackButtonIcon mBackButtonIcon;

    public BackButtonIcon setBackButton(BackButtonIcon btn) {
        return mBackButtonIcon = btn;
    }

    public interface BackButtonIcon {
        void onBackBtn(boolean b);

        void onTopTitleShow(boolean show);
    }
}
