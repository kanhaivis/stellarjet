package com.ns.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ns.adapter.PlaceSelectAdapter;
import com.ns.model.LoginResponse.CitiesBean;
import com.ns.stellarjet.R;
import com.ns.utils.ConstantMethod;
import com.ns.utils.UiConstants;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FlightFromFragment extends Fragment implements PlaceSelectAdapter.onPlaceSelectClickListener, FlightToFragment.BackButtonIcon {
    private static final String TAG = "FlightFromFragment";



    private List<ObjectAnimator> mExitObjectAnimatorList = new ArrayList<>();
    private ArrayList<CitiesBean> mCitilist;
    private ArrayList<CitiesBean> mTOCitilist = new ArrayList<>();



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCitilist = (ArrayList<CitiesBean>) this.getArguments().getSerializable("CitiData");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.flight_from_to_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((TextView) view.findViewById(R.id.from_to_ttitle_Txt)).setText(R.string.booking_from);

        RecyclerView recyclerView = view.findViewById(R.id.common_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        PlaceSelectAdapter adapter = new PlaceSelectAdapter(this, mCitilist, true);
        recyclerView.setAdapter(adapter);
        ConstantMethod.RunLayoutAnimationRecyclerview(recyclerView);


    }


    @Override
    public void onResume() {
        super.onResume();

        if (mTOCitilist.size() != 0) {
            mTOCitilist.clear();
        }
        mTOCitilist.addAll(mCitilist);
//        PlaceSelectionActivity.isFromFragmentVisible = true;
//        PlaceSelectionActivity.isToFragmentVisible= false;
    }

    @Override
    public void onPlaceSelected(String placeName, int placeId) {
        startExitAnimation();

        CitiesBean bean = new CitiesBean();
        bean.setId(placeId);
        bean.setName(placeName);

        int i = mTOCitilist.indexOf(bean);
        Log.d(TAG, "onPlaceSelected: " + i);

        mTOCitilist.remove(i);

        Bundle bundle = new Bundle();
        bundle.putString(UiConstants.FLIGHT_FROM_NAME, placeName);
        bundle.putInt(UiConstants.FLIGHT_FROM_ID, placeId);
        bundle.putSerializable("CitiData", mTOCitilist);

        FlightToFragment flightToFragment = new FlightToFragment();
        flightToFragment.setArguments(bundle);
        flightToFragment.setBackButton(this);

        new Handler().postDelayed(() -> Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                .replace(R.id.bookflight_cPane, flightToFragment)
                .addToBackStack(null)
                .commit(), 450);


    }

    private void startExitAnimation() {
        for (int i = 0; i < mExitObjectAnimatorList.size(); i++) {
            mExitObjectAnimatorList.get(i).start();
        }
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
