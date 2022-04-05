package com.ns.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ns.database.SharedPref;
import com.ns.model.ReUseModel.BookingListBean;
import com.ns.stellarjet.R;
import com.ns.utils.UiConstants;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BookingConfirmedFragment extends Fragment implements PersonaliseYourTripFragment.BackBtnVisible {

    private static final String TAG = "BookingConfirmedFragmen";

    private TextView txtHeading;
    private TextView txtBottom;
    private Button btnPersonalize;

    public BackBtnVisible mBackBtnVisible;

    public BackBtnVisible setBtnVisible(BackBtnVisible backBtnVisible) {
        return mBackBtnVisible = backBtnVisible;
    }

    public interface BackBtnVisible {
        void onVisibleBtn(boolean show);
    }

    // Bundle data pass
    private BookingListBean mBookingListBean;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBookingListBean = (BookingListBean) this.getArguments().getSerializable("bookingListBean");
        Log.d(TAG, "onCreate: " + mBookingListBean);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_booking_confirmed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        InitView(view);


        // Sining set un tick

        // Bundle Action for fragment (Common)
        Bundle bundle = new Bundle();
        bundle.putSerializable("bookingListBean", mBookingListBean);

        PersonaliseYourTripFragment personaliseYourTripFragment = new PersonaliseYourTripFragment();
        personaliseYourTripFragment.setArguments(bundle);
        personaliseYourTripFragment.setBtnVisibles(this);

        btnPersonalize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Handler().postDelayed(() -> Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.framePane, personaliseYourTripFragment)
                        .addToBackStack(null)
                        .commit(), UiConstants.DELAYMILLISECOND);
            }
        });
    }

    private void InitView(View view) {

        txtHeading = view.findViewById(R.id.textView_confirmed_arrival_time);
        txtBottom = view.findViewById(R.id.textView_confirmed_contact_us_info);
        btnPersonalize = view.findViewById(R.id.button_confirmed_personalize);

        if (mBookingListBean.getTravelling_self() == 0) {
            txtHeading.setVisibility(View.GONE);
            txtBottom.setVisibility(View.GONE);
            btnPersonalize.setVisibility(View.GONE);

        } else if (mBookingListBean.getTravelling_self() == 1) {
            txtHeading.setVisibility(View.VISIBLE);
            txtBottom.setVisibility(View.VISIBLE);
            btnPersonalize.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onVisibleBtn(boolean show) {
        if (show) {
            mBackBtnVisible.onVisibleBtn(true);
        } else {
            mBackBtnVisible.onVisibleBtn(false);
        }

    }
}
