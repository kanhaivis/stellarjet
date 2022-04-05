package com.ns.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ns.activity.HomeActivity;
import com.ns.database.SharedPref;
import com.ns.model.Booking.DetailsBean;
import com.ns.model.ReUseModel.BookingListBean;
import com.ns.stellarjet.R;
import com.ns.utils.UiConstants;

import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PersonaliseYourTripFragment extends Fragment {
    private static final String TAG = "PersonaliseYourTripFrag";



    private TextView vPickDropUpTxt;
    private TextView vDiningTxt;

    private ArrayList<DetailsBean> mDetailsBeans;

    public BackBtnVisible mBackBtnVisible;

    public BackBtnVisible setBtnVisibles(BackBtnVisible backBtnVisible) {
        return mBackBtnVisible = backBtnVisible;
    }

    public interface BackBtnVisible {
        void onVisibleBtn(boolean show);
    }


    // Bundle data pass
    BookingListBean mBookingListBean;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBookingListBean = (BookingListBean) this.getArguments().getSerializable("bookingListBean");
        Log.d(TAG, "onCreate: " + mBookingListBean);
    }


    @Override
    public void onResume() {
        super.onResume();
        mBackBtnVisible.onVisibleBtn(false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.personalise_your_trip, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        vDiningTxt = view.findViewById(R.id.trip_booking_dining);
        vPickDropUpTxt = view.findViewById(R.id.trip_booking_pickupdropup);


        // Pick and Drop Up Location tick and un tick
        if (mBookingListBean.getPick_address_main().isEmpty() && mBookingListBean.getDrop_address_main().isEmpty()) {
            vPickDropUpTxt.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_date_next), null);
        } else {
            vPickDropUpTxt.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.tick), null, getResources().getDrawable(R.drawable.ic_date_next), null);
        }


        // Dining tick and un tick
        /*if (SharedPref.getSharedPreferences(getContext()).getDiningDeatils() == 0) {
            vDiningTxt.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_date_next), null);
        } else {
            vDiningTxt.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.tick), null, getResources().getDrawable(R.drawable.ic_date_next), null);
        }*/

       // Bundle Action for fragment (Common)
        Bundle bundle = new Bundle();
        bundle.putSerializable("bookingListBean", mBookingListBean);

        vPickDropUpTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mBackBtnVisible.onVisibleBtn(true);

                PickUpAndDropSetAddressFragment fragment = new PickUpAndDropSetAddressFragment();
                bundle.putBoolean("key", true);
                fragment.setArguments(bundle);

                new Handler().postDelayed(() -> Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.framePane, fragment)
                        .addToBackStack(null)
                        .commit(), UiConstants.DELAYMILLISECOND);
            }
        });

        vDiningTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                FoodCategoriesListFragment fragment = new FoodCategoriesListFragment();
                fragment.setArguments(bundle);


                new Handler().postDelayed(() -> Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.framePane, fragment)
                        .addToBackStack(null)
                        .commit(), UiConstants.DELAYMILLISECOND);

            }
        });


        view.findViewById(R.id.button_confirmed_come_back_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}
