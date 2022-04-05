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
import com.ns.retrofit.APIInterface;
import com.ns.retrofit.APIRetrofit;
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


public class SeatTwelveFragment extends Fragment implements View.OnClickListener, PassengerListFragment.BackButtonIcon {
    private static final String TAG = "SeatTwelveFragment";

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
    //    private boolean isReturnFromPassenger = false;
    private int mNumOfSeatsLocked = 0;
    private int numOfSeatsAvailable = 0;
    private int mGlobalSeatCount = 0;
    private String membershipUserType;
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

    private APIInterface mApiInterface;

    private boolean checkFrigmentVisible = false;

    private FlightScheduleData flightScheduleData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiInterface = APIRetrofit.getRetrofitClient(getContext()).create(APIInterface.class);

        flowFrom = this.getArguments().getString("flowFrom");
        mFromId = this.getArguments().getInt("fromId");
        mToId = this.getArguments().getInt("toId");

        flightScheduleData = (FlightScheduleData) this.getArguments().getSerializable("flightScheduleData");
        Log.d(TAG, "onCreate: " + flightScheduleData);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.seat_twelve_layout, container, false);
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


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);

        //
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

        mSeatConfirmedButton = view.findViewById(R.id.seat_confirmed_btn);
        mSeatConfirmedButton.setOnClickListener(this);
        mAlphaButton = view.findViewById(R.id.button_seats_alpha);
        mAlphaButton.setOnClickListener(this);
        mBetaButton = view.findViewById(R.id.button_seats_beta);
        mBetaButton.setOnClickListener(this);
        mCharlieButton = view.findViewById(R.id.button_seats_charlie);
        mCharlieButton.setOnClickListener(this);
        mDeltaButton = view.findViewById(R.id.button_seats_delta);
        mDeltaButton.setOnClickListener(this);
        mEchoButton = view.findViewById(R.id.button_seats_echo);
        mEchoButton.setOnClickListener(this);
        mFoxtrotButton = view.findViewById(R.id.button_seats_foxtrot);
        mFoxtrotButton.setOnClickListener(this);
        mGolfButton = view.findViewById(R.id.button_seats_golf);
        mGolfButton.setOnClickListener(this);
        mHotelButton = view.findViewById(R.id.button_seats_hotel);
        mHotelButton.setOnClickListener(this);
        mIndigoButton = view.findViewById(R.id.button_seats_indigo);
        mIndigoButton.setOnClickListener(this);
        mJulietButton = view.findViewById(R.id.button_seats_juliet);
        mKiloButton = view.findViewById(R.id.button_seats_kilo);
        mLimoButton = view.findViewById(R.id.button_seats_lima);
        mAlphaTextView = view.findViewById(R.id.textView_seat_alpha);
        mBetaTextView = view.findViewById(R.id.textView_seat_beta);
        mCharlieTextView = view.findViewById(R.id.textView_seat_charlie);
        mDeltaTextView = view.findViewById(R.id.textView_seat_delta);
        mEchoTextView = view.findViewById(R.id.textView_seat_echo);
        mFoxtrotTextView = view.findViewById(R.id.textView_seat_foxtrot);
        mGolfTextView = view.findViewById(R.id.textView_seat_golf);
        mHotelTextView = view.findViewById(R.id.textView_seat_hotel);
        mIndigoTextView = view.findViewById(R.id.textView_seat_indigo);
        mJulietTextView = view.findViewById(R.id.textView_seat_juliet);
        mJulietButton.setOnClickListener(this);
        mKiloTextView = view.findViewById(R.id.textView_seat_kilo);
        mKiloButton.setOnClickListener(this);
        mLimoTextView = view.findViewById(R.id.textView_seat_lima);
        mLimoButton.setOnClickListener(this);
        mSeatsAvailableTextView = view.findViewById(R.id.textView_seat_twelve_available_seats);
        mRightSunTextView = view.findViewById(R.id.textView_two_right_sun_status);
        mLeftSunTextView = view.findViewById(R.id.textView_two_left_sun_status);
        mLeftLinearLayout = view.findViewById(R.id.layout_two_left_sun_status);
        mRightLinearLayout = view.findViewById(R.id.layout_two_right_sun_status);

        mProgressBarShowHide = view.findViewById(R.id.relativeProgressBar);


    }

    private void ApiCall() {

        mProgressBarShowHide.setVisibility(View.VISIBLE);

        APICallBack.getFlightSeat(new APICallBack.IFlightSeatInterface() {
            @Override
            public void onFlightSeat(FlightSeatListResponse response) {

                mProgressBarShowHide.setVisibility(View.GONE);
                Log.d(TAG, "onFlightSeat: " + response);
                //    ConstantMethod.HideProgressBar(getActivity());

                if (response.getResultcode() == 1) {

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
                //   ConstantMethod.HideProgressBar(getActivity());
            }
        }, getContext(), mToken, mJourneyDate, mJourneyTime, mFlightId, mFromId, mToId);

    }


    private void resetSeats(Button mDesiredButton) {
        String seatPOsition = getSeatPosition(mDesiredButton);
        if (seatPOsition.equalsIgnoreCase(getResources().getString(R.string.tag_seat_straight))) {
            mDesiredButton.setBackgroundResource(R.drawable.ic_seat_available);
        } else if (seatPOsition.equalsIgnoreCase(getResources().getString(R.string.tag_seat_reverse))) {
            mDesiredButton.setBackgroundResource(R.drawable.ic_seat_available_reverse);
        }
    }

    // set the seat name and seat id dynamically from API response
    private void setSeats() {
        for (int i = 0; i < mFlightSeatList.size(); i++) {
            int sortedOrder = mFlightSeatList.get(i).getSort_order();
            switch (sortedOrder) {
                case 1:
                    setSeatName(i, mAlphaButton, mAlphaTextView);
                    break;
                case 2:
                    setSeatName(i, mBetaButton, mBetaTextView);
                    break;
                case 3:
                    setSeatName(i, mCharlieButton, mCharlieTextView);
                    break;
                case 4:
                    setSeatName(i, mDeltaButton, mDeltaTextView);
                    break;
                case 5:
                    setSeatName(i, mEchoButton, mEchoTextView);
                    break;
                case 6:
                    setSeatName(i, mFoxtrotButton, mFoxtrotTextView);
                    break;
                case 7:
                    setSeatName(i, mGolfButton, mGolfTextView);
                    break;
                case 8:
                    setSeatName(i, mHotelButton, mHotelTextView);
                    break;
                case 9:
                    setSeatName(i, mIndigoButton, mIndigoTextView);
                    break;
                case 10:
                    setSeatName(i, mJulietButton, mJulietTextView);
                    break;
                case 11:
                    setSeatName(i, mKiloButton, mKiloTextView);
                    break;
                case 12:
                    setSeatName(i, mLimoButton, mLimoTextView);
                    break;
            }
        }
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

    @Override
    public void onClick(View v) {
        flowFrom = "seats";
        checkFrigmentVisible = true;
        switch (v.getId()) {
            case R.id.button_seats_alpha:
                makeSelectedSeatList(mAlphaButton, getResources().getString(R.string.tag_seat_straight));
                break;
            case R.id.button_seats_beta:
                makeSelectedSeatList(mBetaButton, getResources().getString(R.string.tag_seat_straight));
                break;
            case R.id.button_seats_charlie:
                makeSelectedSeatList(mCharlieButton, getResources().getString(R.string.tag_seat_reverse));
                break;
            case R.id.button_seats_delta:
                makeSelectedSeatList(mDeltaButton, getResources().getString(R.string.tag_seat_reverse));
                break;
            case R.id.button_seats_echo:
                makeSelectedSeatList(mEchoButton, getResources().getString(R.string.tag_seat_straight));
                break;
            case R.id.button_seats_foxtrot:
                makeSelectedSeatList(mFoxtrotButton, getResources().getString(R.string.tag_seat_reverse));
                break;
            case R.id.button_seats_golf:
                makeSelectedSeatList(mGolfButton, getResources().getString(R.string.tag_seat_straight));
                break;
            case R.id.button_seats_hotel:
                makeSelectedSeatList(mHotelButton, getResources().getString(R.string.tag_seat_reverse));
                break;
            case R.id.button_seats_indigo:
                makeSelectedSeatList(mIndigoButton, getResources().getString(R.string.tag_seat_straight));
                break;
            case R.id.button_seats_juliet:
                makeSelectedSeatList(mJulietButton, getResources().getString(R.string.tag_seat_reverse));
                break;
            case R.id.button_seats_kilo:
                makeSelectedSeatList(mKiloButton, getResources().getString(R.string.tag_seat_straight));
                break;
            case R.id.button_seats_lima:
                makeSelectedSeatList(mLimoButton, getResources().getString(R.string.tag_seat_reverse));
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


              /*  HomeActivity.mSeatNames.clear();
                HomeActivity.mSeatNamesId.clear();
                for (int i = 0; i < mSelectedSeatList.size(); i++) {
                    if(mSelectedSeatList.get(i).isSelected()){
                        int seatID = Integer.valueOf(mSelectedSeatList.get(i).getSeatId());
                        HomeActivity.mSeatNames.add(mSelectedSeatList.get(i).getSeatName());
                        HomeActivity.mSeatNamesId.add(seatID);
                    }
                }
                int numOfGuests = HomeActivity.mSeatNamesId.size();
                if(numOfGuests == 0){
                    UiUtils.Companion.showSimpleDialog(
                            SeatSelectionActivity.this, "Please select seats"
                    );
                }else {
                    Intent mGuestAddIntent = new Intent(SeatSelectionActivity.this , PassengerListActivity.class);
                    mGuestAddIntent.putExtra("numOfGuests" , numOfGuests);
                    mGuestAddIntent.putStringArrayListExtra("seatNamesList" , mSeatNamesList);
                    mGuestAddIntent.putParcelableArrayListExtra("seatInfoList" , (ArrayList<? extends Parcelable>) mSeatInfoList);
                    startActivity(mGuestAddIntent);
                }*/
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
        }
    }


    private void setLockedSeatsByUser(List<SeatsLockedByUser> mSelectedAndLockedSeatsList) {
        int numOfConfirmedSeats = mSelectedAndLockedSeatsList.size();
        String confirmSeatsDisplay;


        if (numOfConfirmedSeats == 0) {
            confirmSeatsDisplay = getResources().getString(R.string.booking_confirm_seat);
        } else {
            if (numOfConfirmedSeats > 1) {
                confirmSeatsDisplay = getResources().getString(R.string.booking_confirm_seatss) + " - " + numOfConfirmedSeats;
            } else {
                confirmSeatsDisplay = getResources().getString(R.string.booking_confirm_seat) + " - " + numOfConfirmedSeats;
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
                } else {
                    // call unlock seats
                    if (!flowFrom.equalsIgnoreCase("home")) {

                        if (checkFrigmentVisible) {
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

    // returns the seat postion ie reverse or straight position
    private String getSeatPosition(Button mDesiredButton) {
        String seatPosition = "";
        if (mDesiredButton == mAlphaButton) {
            seatPosition = getResources().getString(R.string.tag_seat_straight);
        } else if (mDesiredButton == mBetaButton) {
            seatPosition = getResources().getString(R.string.tag_seat_straight);
        } else if (mDesiredButton == mCharlieButton) {
            seatPosition = getResources().getString(R.string.tag_seat_reverse);
        } else if (mDesiredButton == mDeltaButton) {
            seatPosition = getResources().getString(R.string.tag_seat_reverse);
        } else if (mDesiredButton == mEchoButton) {
            seatPosition = getResources().getString(R.string.tag_seat_straight);
        } else if (mDesiredButton == mFoxtrotButton) {
            seatPosition = getResources().getString(R.string.tag_seat_reverse);
        } else if (mDesiredButton == mGolfButton) {
            seatPosition = getResources().getString(R.string.tag_seat_straight);
        } else if (mDesiredButton == mHotelButton) {
            seatPosition = getResources().getString(R.string.tag_seat_reverse);
        } else if (mDesiredButton == mIndigoButton) {
            seatPosition = getResources().getString(R.string.tag_seat_straight);
        } else if (mDesiredButton == mJulietButton) {
            seatPosition = getResources().getString(R.string.tag_seat_reverse);
        } else if (mDesiredButton == mKiloButton) {
            seatPosition = getResources().getString(R.string.tag_seat_straight);
        } else if (mDesiredButton == mLimoButton) {
            seatPosition = getResources().getString(R.string.tag_seat_reverse);
        }

        return seatPosition;
    }


    private void confirmSingleSeats(List<Integer> mLockSeatsList, Button mDesiredButton, String seatPosition,
                                    int position, String seatName, String seatId) {

        //mProgressBarShowHide.setVisibility(View.VISIBLE);
        //ConstantMethod.ShowProgressBar(getActivity());

        APICallBack.getConfirmFlightSeat(new APICallBack.IConfirmFlightSeatInterface() {
            @Override
            public void onConfirmFlightSeat(Response<ConformSeatRequest> response) {
                Log.d(TAG, "onConfirmFlightSeat: " + response);
//                mProgressBarShowHide.setVisibility(View.GONE);

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
                //   ConstantMethod.HideProgressBar(getActivity());
//                mProgressBarShowHide.setVisibility(View.GONE);
                ConstantMethod.DialogShow(getActivity(), "Server Error Ex!");
            }
        }, getContext(), mToken, mJourneyDate, mJourneyTime, mFlightId, mFromId, mToId, null, mLockSeatsList);
    }


    private void unlockSingleSeats(List<Integer> mUnlockSeatsList, Button mDesiredButton,
                                   String seatPosition, int position, String seatName, String seatId) {

//        mProgressBarShowHide.setVisibility(View.VISIBLE);

        // ConstantMethod.ShowProgressBar(getActivity());

        APICallBack.getConfirmFlightSeat(new APICallBack.IConfirmFlightSeatInterface() {
            @Override
            public void onConfirmFlightSeat(Response<ConformSeatRequest> response) {

//                mProgressBarShowHide.setVisibility(View.GONE);
                //   ConstantMethod.HideProgressBar(getActivity());
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

                        if (checkFrigmentVisible) {
                            mSelectedSeatList.get(position).setSelected(false);

                            JSONObject mJsonObject;
                            try {
                                mJsonObject = new JSONObject(response.errorBody().string());
                                String errorMessage = mJsonObject.getString("message");
                                ConstantMethod.DialogShow(getActivity(), errorMessage);
                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                            }
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
                // ConstantMethod.HideProgressBar(getActivity());
//                mProgressBarShowHide.setVisibility(View.GONE);
                ConstantMethod.DialogShow(getActivity(), "Server Error Ex!");
            }
        }, getContext(), mToken, mJourneyDate, mJourneyTime, mFlightId, mFromId, mToId, mUnlockSeatsList, null);
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
