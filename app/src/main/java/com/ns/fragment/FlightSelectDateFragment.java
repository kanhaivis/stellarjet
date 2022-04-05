package com.ns.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.ns.activity.BoardingPassActivity;
import com.ns.activity.PurchaseActivity;
import com.ns.adapter.CalendarDaysAdapter;
import com.ns.adapter.WeekDaysAdapter;
import com.ns.database.SharedPref;
import com.ns.model.FlightScheduleData;
import com.ns.model.FlightScheduleResponse;
import com.ns.retrofit.APICallBack;
import com.ns.retrofit.APIInterface;
import com.ns.retrofit.APIRetrofit;
import com.ns.stellarjet.R;
import com.ns.utils.ConstantDateFormate;
import com.ns.utils.ConstantMethod;
import com.ns.utils.UiConstants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FlightSelectDateFragment extends Fragment implements View.OnClickListener, CalendarDaysAdapter.onDateSelectClickListener, SeatTwelveFragment.BackButtonIcon,
        SeatEightFragment.BackButtonIcon, SeatSeventeenFragment.BackButtonIcon, SeatSixFragment.BackButtonIcon, SeatTenFragment.BackButtonIcon {

    private static final String TAG = "FlightSelectDateFragmen";


    private ArrayList<FlightScheduleData> mFlightScheduleDataList = new ArrayList<>();
    private List<Calendar> mCalendarList = new ArrayList<>();
    private List<Calendar> mScheduledCalendarList = new ArrayList<>();
    private List<String> mDaysList = new ArrayList<>();

    private boolean isDateSelected = false;
    private int selectedIndex;

    private int currentMonth = 0;
    private int initialMonth = 0;
    private int lastMonth = 0;

    // UI View
    private RecyclerView mCalenderRecyclerView;
    private RecyclerView mCalenderDayRecyclerView;
    private TextView mMonthTxt;
    private TextView mScheduleDateTxt;
    private TextView mScheduleSeatsAvailableTxt;
    private TextView mCityFromTxt;
    private TextView mCityToTxt;
    private Button mPrevBtn;
    private Button mNextBtn;
    private Button mFlightConfirmDateBtn;


    private String mFromNameStr;
    private int mFromIdInt;
    private String mToNameStr;
    private int mToIdInt;

    private APIInterface mApiInterface;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFromNameStr = this.getArguments().getString(UiConstants.FLIGHT_FROM_NAME);
        mFromIdInt = this.getArguments().getInt(UiConstants.FLIGHT_FROM_ID);
        mToNameStr = this.getArguments().getString(UiConstants.FLIGHT_TO_NAME);
        mToIdInt = this.getArguments().getInt(UiConstants.FLIGHT_TO_ID);

        mApiInterface = APIRetrofit.getRetrofitClient(getContext()).create(APIInterface.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.flight_selectdate_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        mCityFromTxt.setText(mFromNameStr);
        mCityToTxt.setText(mToNameStr);


        if (ConstantMethod.isConnected(getContext())) {
            ApiCall();
        } else {
            ConstantMethod.ShowToastMessage(getContext(), UiConstants.NO_NETWORK);
        }


    }

    @Override
    public void onStop() {
        super.onStop();
        isDateSelected = false;
    }

    private void ApiCall() {

        ConstantMethod.ShowProgressBar(getActivity());

        // Call for flight schedule api
        // callback for flight schedule response..
        APICallBack.getFlightSchedule(new APICallBack.IFlightScheduleInterface() {
            @Override
            public void onFlightSchedule(FlightScheduleResponse managerResponse) {
                ConstantMethod.HideProgressBar(getActivity());
                if (managerResponse.getResultcode() == 1) {
                    Log.d(TAG, "onNext: " + managerResponse);
                    SetCalenderSeatAvalabel(managerResponse);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
                ConstantMethod.HideProgressBar(getActivity());

            }
        }, getContext(), mFromIdInt, mToIdInt);


    }

    private void SetCalenderSeatAvalabel(FlightScheduleResponse response) {

        mFlightConfirmDateBtn.setVisibility(View.VISIBLE);
        mPrevBtn.setVisibility(View.VISIBLE);
        mNextBtn.setVisibility(View.VISIBLE);

        mFlightScheduleDataList.addAll(response.getData());

        Calendar mCalendar = Calendar.getInstance();
        currentMonth = mCalendar.get(Calendar.MONTH);
        initialMonth = mCalendar.get(Calendar.MONTH);
        mCalendar.add(Calendar.MONTH, 2);
        lastMonth = mCalendar.get(Calendar.MONTH);
        setScheduledCalendar();
        setDaysNameList();
        setMonthView();


        if (initialMonth == currentMonth) {
            mPrevBtn.setEnabled(false);
            mPrevBtn.setAlpha(0.4f);
        }


    }

    private void initView(View view) {
        mCalenderRecyclerView = view.findViewById(R.id.recyclerView_calendar);
        mCalenderDayRecyclerView = view.findViewById(R.id.recyclerView_days);
        mMonthTxt = view.findViewById(R.id.textView_month);
        mPrevBtn = view.findViewById(R.id.button_last_month);
        mPrevBtn.setOnClickListener(this);
        mNextBtn = view.findViewById(R.id.button_next_month);
        mNextBtn.setOnClickListener(this);
        mCityFromTxt = view.findViewById(R.id.textView_calendar_from);
        mCityToTxt = view.findViewById(R.id.textView_calendar_to);
        mFlightConfirmDateBtn = view.findViewById(R.id.button_schedule_confirm_date);
        mFlightConfirmDateBtn.setOnClickListener(this);
        mScheduleDateTxt = view.findViewById(R.id.textView_schedule_date);
        mScheduleSeatsAvailableTxt = view.findViewById(R.id.textView_schedule_seats_available);
    }


    private void setScheduledCalendar() {
        for (int i = 0; i < mFlightScheduleDataList.size(); i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(mFlightScheduleDataList.get(i).getJourney_datetime_ms());
            int date = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            Calendar mTempCalendar = Calendar.getInstance();
            mTempCalendar.set(Calendar.DAY_OF_MONTH, date);
            mTempCalendar.set(Calendar.MONTH, month);
            mTempCalendar.set(Calendar.YEAR, year);
            mTempCalendar.set(Calendar.HOUR, 0);
            mTempCalendar.set(Calendar.MINUTE, 0);
            mTempCalendar.set(Calendar.SECOND, 0);
            mTempCalendar.set(Calendar.MILLISECOND, 0);
            mScheduledCalendarList.add(mTempCalendar);
        }
        Log.d("Date", "setScheduledCalendar: " + mScheduledCalendarList);
    }

    private void setDaysNameList() {
        mDaysList.add("SUN");
        mDaysList.add("MON");
        mDaysList.add("TUE");
        mDaysList.add("WED");
        mDaysList.add("THU");
        mDaysList.add("FRI");
        mDaysList.add("SAT");

        WeekDaysAdapter mDaysAdapter = new WeekDaysAdapter(
                mDaysList
        );

        GridLayoutManager layoutManager = new GridLayoutManager(
                getContext(),
                7
        );

        mCalenderDayRecyclerView.setAdapter(mDaysAdapter);
        mCalenderDayRecyclerView.setLayoutManager(layoutManager);
        mCalenderDayRecyclerView.setLayoutFrozen(true);
    }

    private void setMonthView() {
        mMonthTxt.setText(getCurrentMonthName());

        mCalendarList.clear();
        String firstDayinMonth = getFirstDayInWeek();
        int daysToPostpone = getFirstDayDelay(firstDayinMonth.toUpperCase());

        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.MONTH, currentMonth);
        int numOfDays = mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);

//        Toast.makeText(this, ""+numOfDays, Toast.LENGTH_SHORT).show();

        for (int i = 0; i < numOfDays; i++) {
            Calendar tempCalendar = Calendar.getInstance();
            tempCalendar.set(Calendar.MONTH, currentMonth);
            tempCalendar.set(Calendar.DAY_OF_MONTH, i + 1);
            tempCalendar.set(Calendar.HOUR, 0);
            tempCalendar.set(Calendar.MINUTE, 0);
            tempCalendar.set(Calendar.SECOND, 0);
            tempCalendar.set(Calendar.MILLISECOND, 0);
            mCalendarList.add(tempCalendar);
        }

        CalendarDaysAdapter mCalendarDaysAdapter = new CalendarDaysAdapter(
                this,
                mCalendarList,
                mScheduledCalendarList,
                daysToPostpone
        );

        GridLayoutManager layoutManager = new GridLayoutManager(
                getContext(),
                7
        );

        mCalenderRecyclerView.setAdapter(mCalendarDaysAdapter);
        mCalenderRecyclerView.setLayoutManager(layoutManager);
    }

    private String getCurrentMonthName() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, currentMonth);
        Date date = calendar.getTime();
        return new SimpleDateFormat("MMM yyyy", Locale.ENGLISH).format(date.getTime());
    }

    private String getFirstDayInWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, currentMonth);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date date = calendar.getTime();
        /*System.out.println(new SimpleDateFormat("EE", Locale.ENGLISH).format(date.getTime()));
        System.out.println()*/
        return new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());
    }

    private int getFirstDayDelay(String daysName) {
        int day = 0;
        switch (daysName) {
            case "SUNDAY":
                day = 0;
                break;
            case "MONDAY":
                day = 1;
                break;
            case "TUESDAY":
                day = 2;
                break;
            case "WEDNESDAY":
                day = 3;
                break;
            case "THURSDAY":
                day = 4;
                break;
            case "FRIDAY":
                day = 5;
                break;
            case "SATURDAY":
                day = 6;
                break;
        }
        return day;
    }

    @Override
    public void onDateSelected(Calendar calendar) {
        Date date = calendar.getTime();
        String selectedDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(date.getTime());
//        String selectedDate = selectedYear +"-"+selectedMonth +"-"+selectedDay;
        for (int i = 0; i < mFlightScheduleDataList.size(); i++) {
            String tempDate = mFlightScheduleDataList.get(i).getJourney_date();
            if (selectedDate.equalsIgnoreCase(tempDate)) {
                selectedIndex = i;
                isDateSelected = true;
                mScheduleDateTxt.setVisibility(View.VISIBLE);
                mScheduleDateTxt.setText(ConstantDateFormate.getFormattedCalendarBookDate(
                        mFlightScheduleDataList.get(i).getJourney_datetime_ms()));

                String seatsAvailable = mFlightScheduleDataList.get(i).getFlight_seat_availability()
                        .getAvailable_seats() + " seats available";

                mScheduleSeatsAvailableTxt.setVisibility(View.VISIBLE);
                mScheduleSeatsAvailableTxt.setText(seatsAvailable);
            }
        }

    }

    private void hideSelectedInfo() {
        isDateSelected = false;
        mScheduleDateTxt.setVisibility(View.INVISIBLE);
        mScheduleSeatsAvailableTxt.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.button_last_month:
                hideSelectedInfo();
                mNextBtn.setEnabled(true);
                mNextBtn.setAlpha(1.0f);
                if (initialMonth != currentMonth) {
                    currentMonth = currentMonth - 1;
                    setMonthView();
                }
                if (initialMonth == currentMonth) {
                    mPrevBtn.setEnabled(false);
                    mPrevBtn.setAlpha(0.4f);
                }
                break;
            case R.id.button_next_month:
                hideSelectedInfo();
                mPrevBtn.setEnabled(true);
                mPrevBtn.setAlpha(1.0f);
                if (currentMonth != lastMonth) {
                    currentMonth = currentMonth + 1;
                    setMonthView();
                }
                if (currentMonth == lastMonth) {
                    mNextBtn.setEnabled(false);
                    mNextBtn.setAlpha(0.4f);
                }
                break;
            case R.id.button_schedule_confirm_date:
                if (!isDateSelected) {
                    ConstantMethod.ShowToastMessage(getContext(), "Please select a date");
                } else if (isDateSelected) {


//                    int flightId = mFlightScheduleDataList.get(selectedIndex).getFlight_id();
//                    String journeyDate = mFlightScheduleDataList.get(selectedIndex).getJourney_date();
//                    String journeyTime = mFlightScheduleDataList.get(selectedIndex).getJourney_time();

//                    SharedPref.getSharedPreferences(getContext()).saveJourneyDate(journeyDate);
//                    SharedPref.getSharedPreferences(getContext()).saveJourneyTime(journeyTime);
//                    SharedPref.getSharedPreferences(getContext()).saveJourneyTimeImMillis(mFlightScheduleDataList.get(selectedIndex).getJourney_datetime_ms());
//                    SharedPref.getSharedPreferences(getContext()).saveArrivalTime(mFlightScheduleDataList.get(selectedIndex).getArrival_time());
//                    SharedPref.getSharedPreferences(getContext()).saveScheduleId(mFlightScheduleDataList.get(selectedIndex).getSchedule_id());
//                    SharedPref.getSharedPreferences(getContext()).saveFlightId(flightId);


//                    String direction = mFlightScheduleDataList.get(selectedIndex).getDirection();
//                    String sunRiseSet = mFlightScheduleDataList.get(selectedIndex).getSun_rise_set();
                    int numSeats = mFlightScheduleDataList.get(selectedIndex).getFlight_seat_availability().getTotal_seats();


//                    String arriveTimes = mFlightScheduleDataList.get(selectedIndex).getArrival_time();
//                    int scheduleIds = mFlightScheduleDataList.get(selectedIndex).getSchedule_id();

                    FlightScheduleData flightScheduleData = mFlightScheduleDataList.get(selectedIndex);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("flightScheduleData", flightScheduleData);
                    bundle.putString("fromCityName", mFromNameStr);
                    bundle.putString("toCityName", mToNameStr);
                    bundle.putInt("fromId", mFromIdInt);
                    bundle.putInt("toId", mToIdInt);
                    bundle.putString("flowFrom", "calendar");

                    if (numSeats == 6) {

                        SeatSixFragment seatSixFragment = new SeatSixFragment();
                        seatSixFragment.setArguments(bundle);
                        seatSixFragment.setBackButton(this);

                        new Handler().postDelayed(() -> Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                                .replace(R.id.bookflight_cPane, seatSixFragment)
                                .addToBackStack(null)
                                .commit(), UiConstants.DELAYMILLISECOND);

                    } else if (numSeats == 8) {

                        SeatEightFragment seatEightFragment = new SeatEightFragment();
                        seatEightFragment.setArguments(bundle);
                        seatEightFragment.setBackButton(this);

                        new Handler().postDelayed(() -> Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                                .replace(R.id.bookflight_cPane, seatEightFragment)
                                .addToBackStack(null)
                                .commit(), UiConstants.DELAYMILLISECOND);

                    } else if (numSeats == 10) {

                        SeatTenFragment seatTenFragment = new SeatTenFragment();
                        seatTenFragment.setArguments(bundle);
                        seatTenFragment.setBackButton(this);

                        new Handler().postDelayed(() -> Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                                .replace(R.id.bookflight_cPane, seatTenFragment)
                                .addToBackStack(null)
                                .commit(), UiConstants.DELAYMILLISECOND);

                    } else if (numSeats == 12) {

                        SeatTwelveFragment seatTwelveFragment = new SeatTwelveFragment();
                        seatTwelveFragment.setArguments(bundle);
                        seatTwelveFragment.setBackButton(this);

                        new Handler().postDelayed(() -> Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                                .replace(R.id.bookflight_cPane, seatTwelveFragment)
                                .addToBackStack(null)
                                .commit(), UiConstants.DELAYMILLISECOND);

                    } else if (numSeats == 17) {

                        SeatSeventeenFragment seatSeventeenFragment = new SeatSeventeenFragment();
                        seatSeventeenFragment.setArguments(bundle);
                        seatSeventeenFragment.setBackButton(this);

                        new Handler().postDelayed(() -> Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                                .replace(R.id.bookflight_cPane, seatSeventeenFragment)
                                .addToBackStack(null)
                                .commit(), UiConstants.DELAYMILLISECOND);
                    }
                }
                break;

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
