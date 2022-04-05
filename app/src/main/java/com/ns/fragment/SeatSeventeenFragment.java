package com.ns.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ns.activity.PurchaseActivity;
import com.ns.database.SharedPref;
import com.ns.model.BookedSeatsRequest;
import com.ns.model.FlightScheduleData;
import com.ns.model.FlightSeatList.FlightSeatListResponse;
import com.ns.model.FlightSeatList.FlightSeatsBean;
import com.ns.model.FlightSeatList.SeatsLockedByUser;
import com.ns.model.SeatInfo;
import com.ns.model.SeatResponse.ConformSeatRequest;
import com.ns.model.SeatSelectionRequest;
import com.ns.retrofit.APICallBack;
import com.ns.stellarjet.R;
import com.ns.utils.ConstantMethod;
import com.ns.utils.UiConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Response;


public class SeatSeventeenFragment extends Fragment implements View.OnClickListener, PassengerListFragment.BackButtonIcon {

    private static final String TAG = "SeatSeventeenFragment";

    private boolean mPurchangeBackPress = true;

    private Button mSeatConfirmedButton;
    private Button mAlphaButton;
    private Button mBetaButton;
    private Button mCharlieButton;
    private Button mDeltaButton;
    private Button mEchoButton;
    private Button mFoxtrotButton;
    private Button mGolfButton;
    private Button mHotelButton;
    private Button mIndigoButton;
    private Button mJulietButton;
    private Button mKiloButton;
    private Button mLimoButton;
    private Button mMikeButton;
    private Button mNovemberButton;
    private Button mOscarButton;
    private Button mPapaButton;
    private Button mQuebecButton;

    private TextView mAlphaTextView;
    private TextView mBetaTextView;
    private TextView mCharlieTextView;
    private TextView mDeltaTextView;
    private TextView mEchoTextView;
    private TextView mFoxtrotTextView;
    private TextView mGolfTextView;
    private TextView mHotelTextView;
    private TextView mIndigoTextView;
    private TextView mJulietTextView;
    private TextView mKiloTextView;
    private TextView mLimoTextView;
    private TextView mMikeTextView;
    private TextView mNovemberTextView;
    private TextView mOscarTextView;
    private TextView mPapaTextView;
    private TextView mQuebecTextView;

    private TextView mRightSunTextView;
    private TextView mLeftSunTextView;
    private LinearLayout mLeftLinearLayout;
    private LinearLayout mRightLinearLayout;
    private TextView mSeatsAvailableTextView;


    private RelativeLayout mProgressBarShowHide;


    private List<FlightSeatsBean> mFlightSeatList = new ArrayList<>();
    private List<SeatSelectionRequest> mSelectedSeatList = new ArrayList<>();
    private List<BookedSeatsRequest> mBookedSeatsList = new ArrayList<>();
    private List<Integer> mConfirmedSeatsList = new ArrayList<>();
    private String flowFrom = "calendar";
    private boolean isReturnFromPassenger = false;
    private int mNumOfSeatsLocked = 0;
    private int numOfSeatsAvailable = 0;
    private int mGlobalSeatCount = 0;
    private ArrayList<String> mSeatNamesList = new ArrayList<>();
    private List<SeatInfo> mSeatInfoList = new ArrayList<>();


    private String mDirectionStr;
    private String mSunRiseSetStr;
    private String mJourneyDate;
    private String mJourneyTime;
    private int mFlightId;
    private int mFromId;
    private int mToId;

    private String mToken;
    private int mSeatCount;


    private FlightScheduleData flightScheduleData;
    private boolean checkFrigmentVisible = false;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        flowFrom = this.getArguments().getString("flowFrom");
        mFromId = this.getArguments().getInt("fromId");
        mToId = this.getArguments().getInt("toId");

        flightScheduleData = (FlightScheduleData) this.getArguments().getSerializable("flightScheduleData");
        Log.d(TAG, "onCreate: " + flightScheduleData);

    }


    @Override
    public void onResume() {
        super.onResume();
        mBackButtonIcon.onTopTitleShow(true);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mPurchangeBackPress) {
            mBackButtonIcon.onTopTitleShow(false);
            mConfirmedSeatsList.clear();
            mSelectedSeatList.clear();
            mFlightSeatList.clear();
            mSeatNamesList.clear();
            mBookedSeatsList.clear();
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.seat_seventeen_layout, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);

        mDirectionStr = flightScheduleData.getDirection();
        mSunRiseSetStr = flightScheduleData.getSun_rise_set();
        mJourneyDate = flightScheduleData.getJourney_date();
        mJourneyTime = flightScheduleData.getJourney_time();
        mFlightId = flightScheduleData.getFlight_id();


        // Shared Prefrences get data for user related data.
        mToken = SharedPref.getSharedPreferences(getContext()).getTocken();
        mSeatCount = SharedPref.getSharedPreferences(getContext()).getSeatCount();


        if (ConstantMethod.isConnected(getContext())) {
            ApiCall();
        } else {
            ConstantMethod.ShowToastMessage(getContext(), UiConstants.NO_NETWORK);
        }


        String sunStatusDisplay = null;
        if (mDirectionStr != null) {
            if (mSunRiseSetStr.equalsIgnoreCase("rise")) {
                sunStatusDisplay = getResources().getString(R.string.info_flight_sunrise);
            } else if (mSunRiseSetStr.equalsIgnoreCase("set")) {
                sunStatusDisplay = getResources().getString(R.string.info_flight_sunset);
            }
        }
        if (mDirectionStr != null) {
            if (mDirectionStr.equalsIgnoreCase("right")) {
                mRightLinearLayout.setVisibility(View.VISIBLE);
                mRightSunTextView.setText(sunStatusDisplay);
            } else if (mDirectionStr.equalsIgnoreCase("left")) {
                mLeftLinearLayout.setVisibility(View.VISIBLE);
                mLeftSunTextView.setText(sunStatusDisplay);
            }
        }

    }

    private void initView(View view) {

        mRightSunTextView = view.findViewById(R.id.textView_right_sun_status);
        mLeftSunTextView = view.findViewById(R.id.textView_left_sun_status);
        mLeftLinearLayout = view.findViewById(R.id.layout_left_sun_status);
        mRightLinearLayout = view.findViewById(R.id.layout_right_sun_status);


        mSeatConfirmedButton = view.findViewById(R.id.seat_confirmed_btn);
        mSeatConfirmedButton.setOnClickListener(this);
        mAlphaButton = view.findViewById(R.id.button_seats_a);
        mAlphaButton.setOnClickListener(this);
        mBetaButton = view.findViewById(R.id.button_seats_b);
        mBetaButton.setOnClickListener(this);
        mCharlieButton = view.findViewById(R.id.button_seats_c);
        mCharlieButton.setOnClickListener(this);
        mDeltaButton = view.findViewById(R.id.button_seats_d);
        mDeltaButton.setOnClickListener(this);
        mEchoButton = view.findViewById(R.id.button_seats_e);
        mEchoButton.setOnClickListener(this);
        mFoxtrotButton = view.findViewById(R.id.button_seats_f);
        mFoxtrotButton.setOnClickListener(this);
        mGolfButton = view.findViewById(R.id.button_seats_g);
        mGolfButton.setOnClickListener(this);
        mHotelButton = view.findViewById(R.id.button_seats_h);
        mHotelButton.setOnClickListener(this);
        mIndigoButton = view.findViewById(R.id.button_seats_i);
        mIndigoButton.setOnClickListener(this);
        mJulietButton = view.findViewById(R.id.button_seats_j);
        mJulietButton.setOnClickListener(this);
        mKiloButton = view.findViewById(R.id.button_seats_k);
        mKiloButton.setOnClickListener(this);
        mLimoButton = view.findViewById(R.id.button_seats_l);
        mLimoButton.setOnClickListener(this);
        mMikeButton = view.findViewById(R.id.button_seats_m);
        mMikeButton.setOnClickListener(this);
        mNovemberButton = view.findViewById(R.id.button_seats_n);
        mNovemberButton.setOnClickListener(this);
        mOscarButton = view.findViewById(R.id.button_seats_o);
        mOscarButton.setOnClickListener(this);
        mPapaButton = view.findViewById(R.id.button_seats_p);
        mPapaButton.setOnClickListener(this);
        mQuebecButton = view.findViewById(R.id.button_seats_q);
        mQuebecButton.setOnClickListener(this);


        mAlphaTextView = view.findViewById(R.id.textView_seat_a);
        mBetaTextView = view.findViewById(R.id.textView_seat_b);
        mCharlieTextView = view.findViewById(R.id.textView_seat_c);
        mDeltaTextView = view.findViewById(R.id.textView_seat_d);
        mEchoTextView = view.findViewById(R.id.textView_seat_e);
        mFoxtrotTextView = view.findViewById(R.id.textView_seat_f);
        mGolfTextView = view.findViewById(R.id.textView_seat_g);
        mHotelTextView = view.findViewById(R.id.textView_seat_h);
        mIndigoTextView = view.findViewById(R.id.textView_seat_i);
        mJulietTextView = view.findViewById(R.id.textView_seat_j);
        mKiloTextView = view.findViewById(R.id.textView_seat_k);
        mLimoTextView = view.findViewById(R.id.textView_seat_l);
        mMikeTextView = view.findViewById(R.id.textView_seat_m);
        mNovemberTextView = view.findViewById(R.id.textView_seat_n);
        mOscarTextView = view.findViewById(R.id.textView_seat_o);
        mPapaTextView = view.findViewById(R.id.textView_seat_p);
        mQuebecTextView = view.findViewById(R.id.textView_seat_q);
        mSeatsAvailableTextView = view.findViewById(R.id.textView_cabin_seats_available);

        mProgressBarShowHide = view.findViewById(R.id.relativeProgressBar);


    }


    private void ApiCall() {
        mProgressBarShowHide.setVisibility(View.VISIBLE);
        // ConstantMethod.ShowProgressBar(getActivity());
        APICallBack.getFlightSeat(new APICallBack.IFlightSeatInterface() {
            @Override
            public void onFlightSeat(FlightSeatListResponse response) {

                mProgressBarShowHide.setVisibility(View.GONE);
                //  ConstantMethod.HideProgressBar(getActivity());

                if (response.getResultcode() == 1) {

                    Log.d(TAG, "onNext: " + response);

                    mFlightSeatList.addAll(response.getData().getFlight_seats());
                    List<Integer> mBookedSeats = response.getData().getFlight_seat_availability().getBooked();
                    List<Integer> mLockedSeats = response.getData().getFlight_seat_availability().getLocked();
                    List<SeatsLockedByUser> mLockedSeatsByUser = response.getData().getSeats_locked_by_user();

                    setSeats();
                    setBookedAndLockedSeats(mBookedSeats);
                    setBookedAndLockedSeats(mLockedSeats);
                    setLockedSeatsByUser(mLockedSeatsByUser);
                    numOfSeatsAvailable = response.getData().getFlight_seat_availability().getAvailable_seats();
                    String seatsAvailable =
                            String.valueOf(numOfSeatsAvailable)
                                    + " seats available";
                    mSeatsAvailableTextView.setText(seatsAvailable);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());

                mProgressBarShowHide.setVisibility(View.GONE);

                //  ConstantMethod.HideProgressBar(getActivity());
            }
        }, getContext(), mToken, mJourneyDate, mJourneyTime, mFlightId, mFromId, mToId);

    }

    // set the seat name and seat id dynamically from API response
    private void setSeats() {
        for (int i = 0; i < mFlightSeatList.size(); i++) {
//            int sortedOrder = mFlightSeatList.get(i).getSort_order();
            switch (i) {
                case 0:
                    setSeatName(i, mAlphaButton, mAlphaTextView);
                    break;
                case 1:
                    setSeatName(i, mBetaButton, mBetaTextView);
                    break;
                case 2:
                    setSeatName(i, mCharlieButton, mCharlieTextView);
                    break;
                case 3:
                    setSeatName(i, mDeltaButton, mDeltaTextView);
                    break;
                case 4:
                    setSeatName(i, mEchoButton, mEchoTextView);
                    break;
                case 5:
                    setSeatName(i, mFoxtrotButton, mFoxtrotTextView);
                    break;
                case 6:
                    setSeatName(i, mGolfButton, mGolfTextView);
                    break;
                case 7:
                    setSeatName(i, mHotelButton, mHotelTextView);
                    break;
                case 8:
                    setSeatName(i, mIndigoButton, mIndigoTextView);
                    break;
                case 9:
                    setSeatName(i, mJulietButton, mJulietTextView);
                    break;
                case 10:
                    setSeatName(i, mKiloButton, mKiloTextView);
                    break;
                case 11:
                    setSeatName(i, mLimoButton, mLimoTextView);
                    break;
                case 12:
                    setSeatName(i, mMikeButton, mMikeTextView);
                    break;
                case 13:
                    setSeatName(i, mNovemberButton, mNovemberTextView);
                    break;
                case 14:
                    setSeatName(i, mOscarButton, mOscarTextView);
                    break;
                case 15:
                    setSeatName(i, mPapaButton, mPapaTextView);
                    break;
                case 16:
                    setSeatName(i, mQuebecButton, mQuebecTextView);
                    break;
            }
        }
    }

    // sets the booked amd locked seats button to disable state
    private void setBookedAndLockedSeats(List<Integer> mSelectedAndLockedSeatsList) {
        for (int i = 0; i < mBookedSeatsList.size(); i++) {
            int seatId = mBookedSeatsList.get(i).getSeatId();
            String seatPosition = mBookedSeatsList.get(i).getSeatPosition();
            Button mDesiredButton = mBookedSeatsList.get(i).getmDesiredButton();
            String mSeatName = mBookedSeatsList.get(i).getSeatName();
            for (int j = 0; j < mSelectedAndLockedSeatsList.size(); j++) {
                int bookedSeatId = mSelectedAndLockedSeatsList.get(j);
                if (seatId == bookedSeatId) {
                    mDesiredButton.setEnabled(false);
                    mDesiredButton.setText("");
                    hideBookedSeatsText(mSeatName);
                    if (seatPosition.equalsIgnoreCase(getResources().getString(R.string.tag_seat_straight))) {
                        mDesiredButton.setBackgroundResource(R.drawable.ic_seat_booked);
                    } else if (seatPosition.equalsIgnoreCase(getResources().getString(R.string.tag_seat_reverse))) {
                        mDesiredButton.setBackgroundResource(R.drawable.ic_seat_booked_reverse);
                    } else if (seatPosition.equalsIgnoreCase(getResources().getString(R.string.tag_seat_right))) {
                        mDesiredButton.setBackgroundResource(R.drawable.ic_seat_booked_right_face);
                    } else if (seatPosition.equalsIgnoreCase(getResources().getString(R.string.tag_seat_left))) {
                        mDesiredButton.setBackgroundResource(R.drawable.ic_seat_booked_left_face);
                    }
                }
            }
        }
    }

    private void hideBookedSeatsText(String seatName) {
        if (seatName.equalsIgnoreCase("alpha")) {
            mAlphaTextView.setAlpha(0.3f);
        } else if (seatName.equalsIgnoreCase("bravo")) {
            mBetaTextView.setAlpha(0.3f);
        } else if (seatName.equalsIgnoreCase("charlie")) {
            mCharlieTextView.setAlpha(0.3f);
        } else if (seatName.equalsIgnoreCase("delta")) {
            mDeltaTextView.setAlpha(0.3f);
        } else if (seatName.equalsIgnoreCase("echo")) {
            mEchoTextView.setAlpha(0.3f);
        } else if (seatName.equalsIgnoreCase("foxtrot")) {
            mFoxtrotTextView.setAlpha(0.3f);
        } else if (seatName.equalsIgnoreCase("golf")) {
            mGolfTextView.setAlpha(0.3f);
        } else if (seatName.equalsIgnoreCase("hotel")) {
            mHotelTextView.setAlpha(0.3f);
        } else if (seatName.equalsIgnoreCase("Indigo")) {
            mIndigoTextView.setAlpha(0.3f);
        } else if (seatName.equalsIgnoreCase("Juliet")) {
            mJulietTextView.setAlpha(0.3f);
        } else if (seatName.equalsIgnoreCase("Kilo")) {
            mKiloTextView.setAlpha(0.3f);
        } else if (seatName.equalsIgnoreCase("Lima")) {
            mLimoTextView.setAlpha(0.3f);
        } else if (seatName.equalsIgnoreCase("Mike")) {
            mMikeTextView.setAlpha(0.3f);
        } else if (seatName.equalsIgnoreCase("November")) {
            mNovemberTextView.setAlpha(0.3f);
        } else if (seatName.equalsIgnoreCase("Oscar")) {
            mOscarTextView.setAlpha(0.3f);
        } else if (seatName.equalsIgnoreCase("Papa")) {
            mPapaTextView.setAlpha(0.3f);
        } else if (seatName.equalsIgnoreCase("Quebec")) {
            mQuebecTextView.setAlpha(0.3f);
        }
    }


    private void
    setLockedSeatsByUser(List<SeatsLockedByUser> mSelectedAndLockedSeatsList) {
        int numOfConfirmedSeats = mSelectedAndLockedSeatsList.size();
        String confirmSeatsDisplay;
        if (numOfConfirmedSeats == 0) {
            confirmSeatsDisplay = getResources().getString(R.string.booking_confirm_seat);
        } else {
            if (numOfConfirmedSeats > 1) {
                confirmSeatsDisplay = getResources().getString(R.string.booking_confirm_seatss) + " - " + numOfConfirmedSeats;
            } else {
                if (numOfConfirmedSeats > 1) {
                    confirmSeatsDisplay = getResources().getString(R.string.booking_confirm_seatss) + " - " + numOfConfirmedSeats;
                } else {
                    confirmSeatsDisplay = getResources().getString(R.string.booking_confirm_seat) + " - " + numOfConfirmedSeats;
                }
            }
        }
        mNumOfSeatsLocked = mSelectedAndLockedSeatsList.size();
        mGlobalSeatCount = mGlobalSeatCount - mNumOfSeatsLocked;
        ConfirmButtonShow(confirmSeatsDisplay);
        for (int i = 0; i < mSelectedAndLockedSeatsList.size(); i++) {
            mConfirmedSeatsList.add(mSelectedAndLockedSeatsList.get(i).getFlight_seat_id());
        }
        for (int i = 0; i < mBookedSeatsList.size(); i++) {
            int seatId = mBookedSeatsList.get(i).getSeatId();
            String seatPosition = mBookedSeatsList.get(i).getSeatPosition();
            Button mDesiredButton = mBookedSeatsList.get(i).getmDesiredButton();
            for (int j = 0; j < mSelectedAndLockedSeatsList.size(); j++) {
                int bookedSeatId = mSelectedAndLockedSeatsList.get(j).getFlight_seat_id();
                if (seatId == bookedSeatId) {
                    makeSelectedSeatList(mDesiredButton, seatPosition);
                    if (seatPosition.equalsIgnoreCase(getResources().getString(R.string.tag_seat_straight))) {
                        mDesiredButton.setBackgroundResource(R.drawable.ic_seat_selected);
                    } else if (seatPosition.equalsIgnoreCase(getResources().getString(R.string.tag_seat_reverse))) {
                        mDesiredButton.setBackgroundResource(R.drawable.ic_seat_selected_reverse);
                    } else if (seatPosition.equalsIgnoreCase(getResources().getString(R.string.tag_seat_left))) {
                        mDesiredButton.setBackgroundResource(R.drawable.ic_seat_selected_left_face);
                    } else if (seatPosition.equalsIgnoreCase(getResources().getString(R.string.tag_seat_right))) {
                        mDesiredButton.setBackgroundResource(R.drawable.ic_seat_selected_right_face);
                    }
                }
            }
        }
    }


    // set the selected seat selection to true
    private void makeSelectedSeatList(Button mSelectedButton, String seatPosition) {
        String seatId = (String) mSelectedButton.getTag();
        for (int i = 0; i < mSelectedSeatList.size(); i++) {
            if (mSelectedSeatList.get(i).getSeatId().equalsIgnoreCase(seatId)) {
                boolean isSelected = mSelectedSeatList.get(i).isSelected();
                String seatName = mSelectedSeatList.get(i).getSeatName();
                isSelected = !isSelected;
                mSelectedSeatList.get(i).setSelected(isSelected);
                if (flowFrom.equalsIgnoreCase("home")) {
                    mSeatNamesList.add(seatName);
                    mSeatInfoList.add(new SeatInfo(seatName, Integer.parseInt(seatId)));
                }
                if (isSelected) {

                    String mPrepaidTerm = SharedPref.getSharedPreferences(getContext()).getMembershipPreparedTerms();

                    // Membership Primary user
                    //  primary member is true
                    // gold member id false
                    if (mPrepaidTerm.equals("true")) {

                        if (mNumOfSeatsLocked >= mSeatCount) {
                            mSelectedSeatList.get(i).setSelected(false);
                            if (flowFrom.equalsIgnoreCase("home")) {
                                mSeatNamesList.add(seatName);
                                mSeatInfoList.add(new SeatInfo(seatName, Integer.parseInt(seatId)));
                            }

                            showPrimaryUserSeatUnavailabilityDialog();


                        } else {

                            // call confirm seats
                            if (!flowFrom.equalsIgnoreCase("home")) {
                                confirmSingleSeatsAPICall(mSelectedButton, seatPosition, i, seatName, seatId);
                            }
                        }

                    } else if (mPrepaidTerm.equals("False")) {
                        confirmSingleSeatsAPICall(mSelectedButton, seatPosition, i, seatName, seatId);

                    }

                    /*if (mNumOfSeatsLocked >= mSeatCount) {
                        mSelectedSeatList.get(i).setSelected(false);
                        if (flowFrom.equalsIgnoreCase("home")) {
                            mSeatNamesList.add(seatName);
                            mSeatInfoList.add(new SeatInfo(seatName, Integer.parseInt(seatId)));
                        }

                        showPrimaryUserSeatUnavailabilityDialog();

                    } else {

                        // call confirm seats
                        if (!flowFrom.equalsIgnoreCase("home")) {


                            List<Integer> mLockedSeatList = new ArrayList<>();
                            mLockedSeatList.add(Integer.valueOf(mSelectedSeatList.get(i).getSeatId()));


                            confirmSingleSeats(mLockedSeatList,
                                    mSelectedButton,
                                    seatPosition,
                                    i,
                                    seatName,
                                    seatId);


                        }
                    }*/
                } else {
                    // call unlock seats
                    if (!flowFrom.equalsIgnoreCase("home")) {
                        List<Integer> mLockedSeatList = new ArrayList<>();
                        mLockedSeatList.add(Integer.valueOf(mSelectedSeatList.get(i).getSeatId()));
                        unlockSingleSeats(mLockedSeatList,
                                mSelectedButton,
                                seatPosition,
                                i,
                                seatName,
                                seatId);
                    }
                }
            }
        }
    }

    private void confirmSingleSeatsAPICall(Button mSelectedButton, String seatPosition, int i, String seatName, String seatId) {

        List<Integer> mLockedSeatList = new ArrayList<>();
        mLockedSeatList.add(Integer.valueOf(mSelectedSeatList.get(i).getSeatId()));

        confirmSingleSeats(mLockedSeatList,
                mSelectedButton,
                seatPosition,
                i,
                seatName,
                seatId);
    }


    // create a booked and looked seat list and list with seatid , seat selection ,
    private void setSeatName(int index, Button mSelectedButton, TextView mSelectedTextView) {
        String seatName = mFlightSeatList.get(index).getSeat_code();
        String seatButtonText = seatName.substring(0, 1);
        mSelectedButton.setText(seatButtonText);
        mSelectedTextView.setText(seatName);
        int seatId = mFlightSeatList.get(index).getId();
        mSelectedButton.setTag(String.valueOf(seatId));
        SeatSelectionRequest mSeatSelectionRequest = new SeatSelectionRequest();
        mSeatSelectionRequest.setSeatId(String.valueOf(seatId));
        mSeatSelectionRequest.setSelected(false);
        mSeatSelectionRequest.setSeatName(seatName);
        mSelectedSeatList.add(mSeatSelectionRequest);
        BookedSeatsRequest mBookedSeatsRequest = new BookedSeatsRequest();
        mBookedSeatsRequest.setSeatId(seatId);
        mBookedSeatsRequest.setmDesiredButton(mSelectedButton);
        mBookedSeatsRequest.setSeatPosition(getSeatPosition(mSelectedButton));
        mBookedSeatsRequest.setSeatName(seatName);
        mBookedSeatsList.add(mBookedSeatsRequest);
    }

    // returns the seat postion ie reverse or straight position
    private String getSeatPosition(Button mDesiredButton) {
        String seatPosition = "";
        if (mDesiredButton == mAlphaButton) {
            seatPosition = getResources().getString(R.string.tag_seat_right);
        } else if (mDesiredButton == mBetaButton) {
            seatPosition = getResources().getString(R.string.tag_seat_left);
        } else if (mDesiredButton == mCharlieButton) {
            seatPosition = getResources().getString(R.string.tag_seat_left);
        } else if (mDesiredButton == mDeltaButton) {
            seatPosition = getResources().getString(R.string.tag_seat_right);
        } else if (mDesiredButton == mEchoButton) {
            seatPosition = getResources().getString(R.string.tag_seat_right);
        } else if (mDesiredButton == mFoxtrotButton) {
            seatPosition = getResources().getString(R.string.tag_seat_left);
        } else if (mDesiredButton == mGolfButton) {
            seatPosition = getResources().getString(R.string.tag_seat_straight);
        } else if (mDesiredButton == mHotelButton) {
            seatPosition = getResources().getString(R.string.tag_seat_reverse);
        } else if (mDesiredButton == mIndigoButton) {
            seatPosition = getResources().getString(R.string.tag_seat_straight);
        } else if (mDesiredButton == mJulietButton) {
            seatPosition = getResources().getString(R.string.tag_seat_reverse);
        } else if (mDesiredButton == mKiloButton) {
            seatPosition = getResources().getString(R.string.tag_seat_reverse);
        } else if (mDesiredButton == mLimoButton) {
            seatPosition = getResources().getString(R.string.tag_seat_reverse);
        } else if (mDesiredButton == mMikeButton) {
            seatPosition = getResources().getString(R.string.tag_seat_left);
        } else if (mDesiredButton == mNovemberButton) {
            seatPosition = getResources().getString(R.string.tag_seat_left);
        } else if (mDesiredButton == mOscarButton) {
            seatPosition = getResources().getString(R.string.tag_seat_left);
        } else if (mDesiredButton == mPapaButton) {
            seatPosition = getResources().getString(R.string.tag_seat_straight);
        } else if (mDesiredButton == mQuebecButton) {
            seatPosition = getResources().getString(R.string.tag_seat_reverse);
        }

        return seatPosition;
    }

    @Override
    public void onClick(View v) {
        flowFrom = "seats";
        isReturnFromPassenger = false;
        switch (v.getId()) {
            case R.id.button_seats_a:
                makeSelectedSeatList(mAlphaButton, getResources().getString(R.string.tag_seat_right));
                break;
            case R.id.button_seats_b:
                makeSelectedSeatList(mBetaButton, getResources().getString(R.string.tag_seat_left));
                break;
            case R.id.button_seats_c:
                makeSelectedSeatList(mCharlieButton, getResources().getString(R.string.tag_seat_left));
                break;
            case R.id.button_seats_d:
                makeSelectedSeatList(mDeltaButton, getResources().getString(R.string.tag_seat_right));
                break;
            case R.id.button_seats_e:
                makeSelectedSeatList(mEchoButton, getResources().getString(R.string.tag_seat_right));
                break;
            case R.id.button_seats_f:
                makeSelectedSeatList(mFoxtrotButton, getResources().getString(R.string.tag_seat_left));
                break;
            case R.id.button_seats_g:
                makeSelectedSeatList(mGolfButton, getResources().getString(R.string.tag_seat_straight));
                break;
            case R.id.button_seats_h:
                makeSelectedSeatList(mHotelButton, getResources().getString(R.string.tag_seat_straight));
                break;
            case R.id.button_seats_i:
                makeSelectedSeatList(mIndigoButton, getResources().getString(R.string.tag_seat_straight));
                break;
            case R.id.button_seats_j:
                makeSelectedSeatList(mJulietButton, getResources().getString(R.string.tag_seat_reverse));
                break;
            case R.id.button_seats_k:
                makeSelectedSeatList(mKiloButton, getResources().getString(R.string.tag_seat_reverse));
                break;
            case R.id.button_seats_l:
                makeSelectedSeatList(mLimoButton, getResources().getString(R.string.tag_seat_reverse));
                break;
            case R.id.button_seats_m:
                makeSelectedSeatList(mMikeButton, getResources().getString(R.string.tag_seat_left));
                break;
            case R.id.button_seats_n:
                makeSelectedSeatList(mNovemberButton, getResources().getString(R.string.tag_seat_left));
                break;
            case R.id.button_seats_o:
                makeSelectedSeatList(mOscarButton, getResources().getString(R.string.tag_seat_left));
                break;
            case R.id.button_seats_p:
                makeSelectedSeatList(mPapaButton, getResources().getString(R.string.tag_seat_straight));
                break;
            case R.id.button_seats_q:
                makeSelectedSeatList(mQuebecButton, getResources().getString(R.string.tag_seat_reverse));
                break;
            case R.id.seat_confirmed_btn:
                Log.d("BookSeats", "onClick: ");


                int seat = mSelectedSeatList.size();
                Log.d(TAG, "onClick: " + seat);
                int seat1 = mSeatNamesList.size();
                Log.d(TAG, "onClick: " + seat1);
                int seat2 = mConfirmedSeatsList.size();
                Log.d(TAG, "onClick: " + seat2);

                int ss = mBookedSeatsList.size();
                Log.d(TAG, "onClick: " + ss);


                ArrayList<SeatSelectionRequest> finalSelectedlist = new ArrayList<>();
                SeatSelectionRequest seats;
                for (SeatSelectionRequest request : mSelectedSeatList) {

                    if (request.isSelected() == true) {
                        seats = new SeatSelectionRequest(request.getSeatId(), request.getSeatName());
                        finalSelectedlist.add(seats);
                    }
                }


                if (finalSelectedlist.size() == 0) {
                    ConstantMethod.DialogShow(getContext(), "Please select seats");
                } else {

                    Bundle bundle = new Bundle();
                    bundle.putInt("fromId", mFromId);
                    bundle.putInt("toId", mToId);
                    bundle.putSerializable("seatNamesList", finalSelectedlist);
                    bundle.putSerializable("flightScheduleData", flightScheduleData);


                    PassengerListFragment passengerListFragment = new PassengerListFragment();
                    passengerListFragment.setArguments(bundle);
                    passengerListFragment.setBackButton(this);

                    new Handler().postDelayed(() -> Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.bookflight_cPane, passengerListFragment)
                            .addToBackStack(null)
                            .commit(), 450);
                }
                break;
        }
    }


    // change the seat ui to reserve or available
    private void selectButtonSelection(Button mSelectedButton, String seatFace, boolean isSelected) {
        if (seatFace.equalsIgnoreCase(getResources().getString(R.string.tag_seat_straight))) {
            if (isSelected) {
                mSelectedButton.setBackgroundResource(R.drawable.ic_seat_selected);
            } else {
                mSelectedButton.setBackgroundResource(R.drawable.ic_seat_available);
            }
        } else if (seatFace.equalsIgnoreCase(getResources().getString(R.string.tag_seat_reverse))) {
            if (isSelected) {
                mSelectedButton.setBackgroundResource(R.drawable.ic_seat_selected_reverse);
            } else {
                mSelectedButton.setBackgroundResource(R.drawable.ic_seat_available_reverse);
            }
        } else if (seatFace.equalsIgnoreCase(getResources().getString(R.string.tag_seat_right))) {
            if (isSelected) {
                mSelectedButton.setBackgroundResource(R.drawable.ic_seat_selected_right_face);
            } else {
                mSelectedButton.setBackgroundResource(R.drawable.ic_seat_available_right_face);
            }
        } else if (seatFace.equalsIgnoreCase(getResources().getString(R.string.tag_seat_left))) {
            if (isSelected) {
                mSelectedButton.setBackgroundResource(R.drawable.ic_seat_selected_left_face);
            } else {
                mSelectedButton.setBackgroundResource(R.drawable.ic_seat_available_left_face);
            }
        }
    }


    private void confirmSingleSeats(List<Integer> mLockSeatsList, Button mDesiredButton, String seatPosition,
                                    int position, String seatName, String seatId) {


        // ConstantMethod.ShowProgressBar(getActivity());

        APICallBack.getConfirmFlightSeat(new APICallBack.IConfirmFlightSeatInterface() {
            @Override
            public void onConfirmFlightSeat(Response<ConformSeatRequest> response) {
                Log.d(TAG, "onConfirmFlightSeat: " + response);
                //  ConstantMethod.HideProgressBar(getActivity());

                switch (response.code()) {
                    case 200:
                        Log.d(TAG, "onNext: " + 200);

                        /* adding the seats to list*/
                        mConfirmedSeatsList.addAll(mLockSeatsList);
                        /* increase locked seats count to thi booking */
                        mNumOfSeatsLocked = mNumOfSeatsLocked + 1;
                        String confirmSeatsDisplay = "";

                        if (mNumOfSeatsLocked > 1) {
                            confirmSeatsDisplay = getResources().getString(R.string.booking_confirm_seatss) + " - " + mNumOfSeatsLocked;
                        } else {
                            confirmSeatsDisplay = getResources().getString(R.string.booking_confirm_seat) + " - " + mNumOfSeatsLocked;
                        }
                        ConfirmButtonShow(confirmSeatsDisplay);
                        numOfSeatsAvailable = numOfSeatsAvailable - 1;
                        String seatsAvailable =
                                String.valueOf(numOfSeatsAvailable)
                                        + " seats available";
                        mSeatsAvailableTextView.setText(seatsAvailable);


                        selectButtonSelection(mDesiredButton, seatPosition, true);
                        mSelectedSeatList.get(position).setSelected(true);
                        mSeatNamesList.add(seatName);
                        mSeatInfoList.add(new SeatInfo(seatName, Integer.parseInt(seatId)));
                        break;
                    case 400:
                        Log.d(TAG, "onNext: " + 400);
                        if (checkFrigmentVisible) {
                            mSelectedSeatList.get(position).setSelected(false);
                            JSONObject mJsonObject;
                            try {
                                mJsonObject = new JSONObject(response.errorBody().string());
                                String errorMessage = mJsonObject.getString("message");
                                ConstantMethod.DialogShow(getContext(), errorMessage);
                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    case 500:
                        Log.d(TAG, "onNext: " + 500);
                        ConstantMethod.DialogShow(getContext(), "Server Error!");
                        break;
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
                //    ConstantMethod.HideProgressBar(getActivity());
                ConstantMethod.DialogShow(getActivity(), "Server Error Ex!");
            }
        }, getContext(), mToken, mJourneyDate, mJourneyTime, mFlightId, mFromId, mToId, null, mLockSeatsList);


    }


    private void unlockSingleSeats(List<Integer> mUnlockSeatsList, Button mDesiredButton,
                                   String seatPosition, int position, String seatName, String seatId) {

        // ConstantMethod.ShowProgressBar(getActivity());

        APICallBack.getConfirmFlightSeat(new APICallBack.IConfirmFlightSeatInterface() {
            @Override
            public void onConfirmFlightSeat(Response<ConformSeatRequest> response) {

                //  ConstantMethod.HideProgressBar(getActivity());
                Log.d(TAG, "onNext: " + response);

                switch (response.code()) {
                    case 200:
                        Log.d(TAG, "onNext: " + 200);
                        mConfirmedSeatsList.removeAll(mUnlockSeatsList);
                        mNumOfSeatsLocked = mNumOfSeatsLocked - 1;
                        String confirmSeatsDisplay;
                        if (mNumOfSeatsLocked == 0) {
                            confirmSeatsDisplay = getResources().getString(R.string.booking_confirm_seat);
                        } else {
                            if (mNumOfSeatsLocked > 1) {
                                confirmSeatsDisplay = getResources().getString(R.string.booking_confirm_seatss) + " - " + mNumOfSeatsLocked;
                            } else {
                                confirmSeatsDisplay = getResources().getString(R.string.booking_confirm_seat) + " - " + mNumOfSeatsLocked;
                            }
                        }
                        ConfirmButtonShow(confirmSeatsDisplay);
                        numOfSeatsAvailable = numOfSeatsAvailable + 1;
                        String seatsAvailable =
                                String.valueOf(numOfSeatsAvailable)
                                        + " seats available";
                        mSeatsAvailableTextView.setText(seatsAvailable);

                        selectButtonSelection(mDesiredButton, seatPosition, false);
                        mSelectedSeatList.get(position).setSelected(false);
                        mSeatNamesList.remove(seatName);
                        for (int i = 0; i < mSeatInfoList.size(); i++) {
                            if (mSeatInfoList.get(i).getSeatName().equalsIgnoreCase(seatName)) {
                                mSeatInfoList.remove(i);
                            }
                        }
                        break;
                    case 400:
                        Log.d(TAG, "onNext: " + 400);
                        mSelectedSeatList.get(position).setSelected(false);
                        JSONObject mJsonObject;
                        try {
                            mJsonObject = new JSONObject(response.errorBody().string());
                            String errorMessage = mJsonObject.getString("message");
                            ConstantMethod.DialogShow(getActivity(), errorMessage);
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 500:
                        Log.d(TAG, "onNext: " + 500);
                        ConstantMethod.DialogShow(getActivity(), "Server Error!");
                        break;
                }
            }

            @Override
            public void onError(Throwable e) {
                //  ConstantMethod.HideProgressBar(getActivity());
                ConstantMethod.DialogShow(getActivity(), "Server Error Ex!");
            }
        }, getContext(), mToken, mJourneyDate, mJourneyTime, mFlightId, mFromId, mToId, mUnlockSeatsList, null);


    }


    private void showPrimaryUserSeatUnavailabilityDialog() {
        String userName = SharedPref.getSharedPreferences(getContext()).getUserName();
        String msg = "Hi Mr." + userName + ", you have consumed your seats.Please recharge";

        ConstantMethod.DialogShowDoubleAction(new ConstantMethod.DialogAction() {
            @Override
            public void onAction(boolean status) {

                if (status) {
                    mPurchangeBackPress = false;
                    Intent intent = new Intent(getContext(), PurchaseActivity.class);
                    startActivityForResult(intent, 202);
                } else {
                    mPurchangeBackPress = true;
                }

            }
        }, getContext(), msg,"Yes", "No");

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 202) {
            mSeatCount = SharedPref.getSharedPreferences(getContext()).getSeatCount();
        }
    }


    /**
     * Seat Confitm title show
     *
     * @param confirmSeatsDisplay
     */
    private void ConfirmButtonShow(String confirmSeatsDisplay) {
        if (confirmSeatsDisplay.equals(getResources().getString(R.string.booking_confirm_seat))) {
            mSeatConfirmedButton.setText(confirmSeatsDisplay);
            mSeatConfirmedButton.setBackgroundResource(R.drawable.drawable_seat_confirm_select_bg_light);
        } else {
            mSeatConfirmedButton.setText(confirmSeatsDisplay);
            mSeatConfirmedButton.setBackgroundResource(R.drawable.drawable_seat_confirm_select_bg);
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
    public void PrevFragment(boolean prev) {
        if (prev == false) {
            checkFrigmentVisible = false;
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
