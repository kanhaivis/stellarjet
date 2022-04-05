package com.ns.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonElement;
import com.ns.activity.BookingConfirmedActivity;
import com.ns.activity.PurchaseActivity;
import com.ns.adapter.PassengerListAdapter;
import com.ns.database.SharedPref;
import com.ns.model.Booking.ConfirmSeatBookingResponse;
import com.ns.model.ConfirmFlightBookingResponse;
import com.ns.model.ContactDetailsPojo;
import com.ns.model.FlightScheduleData;
import com.ns.model.Guest.GuestAddResponse;
import com.ns.model.GuestBean;
import com.ns.model.GuestInfoRequest;
import com.ns.model.OrderResponse;
import com.ns.model.PasengerInfoPojo;
import com.ns.model.PaymentResponse.MembershipCostdetailsResponse;
import com.ns.model.PaymentResponse.VerifyPurchaseResponse;
import com.ns.model.ReUseModel.BookingListBean;
import com.ns.model.SeatPurchaseRequest;
import com.ns.model.SeatSelectionRequest;
import com.ns.retrofit.APICallBack;
import com.ns.retrofit.APIInterface;
import com.ns.retrofit.APIRetrofit;
import com.ns.stellarjet.R;
import com.ns.utils.ConstantMethod;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class PassengerListFragment extends Fragment implements View.OnClickListener, PaymentResultListener, PassengerListAdapter.PersionInfoDetailsInterface {

    private static final String TAG = "PassengerListFragment";


    private PassengerListAdapter mPassengerListAdapter;
    private RecyclerView rRecyclerView;

    private WebView mWebView;

    private TextView tPassengerSelf;
    private TextView tPassengerGuest;

    private RelativeLayout vSlideUpLayout;
    private TextView tAgreeTxt;
    private boolean mAgreeCheck = false;
    private APIInterface mApiInterface;

    private String mToken;
    private int purchanseID;

    private List<PasengerInfoPojo> PasengetList = new ArrayList<>();


    // Bundle Pass the Data
    private ArrayList<SeatSelectionRequest> mSeatNameList;
    private String mJourneyDate;
    private String mJourneyTime;
    private int mFlightId;
    private int mFromId;
    private int mToId;
    private int mScheduleId;
    private String mArriveTime;
    private FlightScheduleData flightScheduleData;


    public BackButtonIcon mBackButtonIcon;

    public BackButtonIcon setBackButton(BackButtonIcon btn) {
        return mBackButtonIcon = btn;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.textView_passenger_self:
                tPassengerSelf.setBackground(getResources().getDrawable(R.drawable.drawable_passenger_select_left_bg));
                tPassengerGuest.setBackground(getResources().getDrawable(R.drawable.drawable_passenger_select_right));
                mPassengerListAdapter.setSelfView();
                break;
            case R.id.textView_passenger_guests:
                tPassengerGuest.setBackground(getResources().getDrawable(R.drawable.drawable_passenger_select_right_bg));
                tPassengerSelf.setBackground(getResources().getDrawable(R.drawable.drawable_passenger_select_left));
                mPassengerListAdapter.setSelfWithGuestView();
                break;
        }
    }


    public interface BackButtonIcon {
        void onBackBtn(boolean b);

        void PrevFragment(boolean prev);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Checkout.preload(getActivity());

        mFromId = this.getArguments().getInt("fromId");
        mToId = this.getArguments().getInt("toId");
        mSeatNameList = (ArrayList<SeatSelectionRequest>) this.getArguments().getSerializable("seatNamesList");
        flightScheduleData = (FlightScheduleData) this.getArguments().getSerializable("flightScheduleData");
        Log.d(TAG, "onCreate: " + flightScheduleData);



        mApiInterface = APIRetrofit.getRetrofitClient(getContext()).create(APIInterface.class);

        mToken = SharedPref.getSharedPreferences(getContext()).getTocken();

        ContactAPICall();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_passenget_list, container, false);
    }

    @Override
    public void onStop() {
        super.onStop();
        mBackButtonIcon.PrevFragment(false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        initView(view);


        mJourneyDate = flightScheduleData.getJourney_date();
        mJourneyTime = flightScheduleData.getJourney_time();
        mFlightId = flightScheduleData.getFlight_id();
        mScheduleId = flightScheduleData.getSchedule_id();
        mArriveTime = flightScheduleData.getArrival_time();

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rRecyclerView.setLayoutManager(manager);


        PasengerInfoPojo pojo;
        PasengetList.clear();
        for (int i = 0; i < mSeatNameList.size(); i++) {
            pojo = new PasengerInfoPojo();
            pojo.setPos(i);
            pojo.setSeatName(mSeatNameList.get(i).getSeatName());
            pojo.setSeatId(Integer.valueOf(mSeatNameList.get(i).getSeatId()));
            PasengetList.add(pojo);
        }


        mPassengerListAdapter = new PassengerListAdapter(getContext(), PasengetList);
        mPassengerListAdapter.setPersionInfoList(this);
        rRecyclerView.setAdapter(mPassengerListAdapter);


        // Click confirm booking
        view.findViewById(R.id.button_confirm_booking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Check the status field is not empty...
               boolean check = mPassengerListAdapter.checkAllDataFill();
               if (check) {
                   mBackButtonIcon.onBackBtn(true);
                   vSlideUpLayout.setVisibility(View.VISIBLE);
                   vSlideUpLayout.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.slide_up));
               } else {
                   ConstantMethod.DialogShow(getContext()," Please enter all empty fill!");
               }

            }
        });


        // Click cross button then down dialog
        view.findViewById(R.id.tc_cross_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBackButtonIcon.onBackBtn(false);
                vSlideUpLayout.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.slide_down));
                vSlideUpLayout.setVisibility(View.GONE);
            }
        });


        // Click textview for afreement checked and unchecked
        tAgreeTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAgreeCheck) {
                    tAgreeTxt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_tik_uncheck, 0, 0, 0);
                    mAgreeCheck = false;
                } else {
                    tAgreeTxt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_tik_check, 0, 0, 0);
                    mAgreeCheck = true;
                }
            }
        });

        view.findViewById(R.id.tc_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAgreeCheck) {
                    mBackButtonIcon.onBackBtn(false);

                    String userType = SharedPref.getSharedPreferences(getContext()).getUserType();
                    if (userType.equals("primary")) {

                        String mPrepaidTerm = SharedPref.getSharedPreferences(getContext()).getMembershipPreparedTerms();

                        if (mPrepaidTerm.equals("true")) {
                            mPassengerListAdapter.TCClick();
                        } else if (mPrepaidTerm.equals("False")) {
                            APICallMembershipCalculated(mSeatNameList.size());
                        }


                    } else if (userType.equals("secondary")) {

                        mPassengerListAdapter.TCClick();
                    }


                } else {
                    ConstantMethod.ShowToastMessage(getContext(), "Please checked i agree TC ");
                }
            }
        });


    }


    private void initView(View view) {
        ((TextView) view.findViewById(R.id.title)).setText(getContext().getResources().getString(R.string.traveling));
        ((TextView) view.findViewById(R.id.subtitle)).setText(getContext().getResources().getString(R.string.subtraveling));
        vSlideUpLayout = view.findViewById(R.id.content_slide_up_view);
        tAgreeTxt = view.findViewById(R.id.i_agree_textview);
        rRecyclerView = view.findViewById(R.id.recyclerView_passenger_list);
        tPassengerSelf = view.findViewById(R.id.textView_passenger_self);
        tPassengerSelf.setOnClickListener(this);
        tPassengerGuest = view.findViewById(R.id.textView_passenger_guests);
        tPassengerGuest.setOnClickListener(this);

        mWebView = view.findViewById(R.id.webviews);
        mWebView.setWebViewClient(new Callback());

        loadTime();
    }

    private void APICallMembershipCalculated(int seatCount) {


        MembershipCalculatedSeat(seatCount,3);

       /* APICallBack.getPurchaseSeat(new APICallBack.IPurchaseSeatInterface() {
            @Override
            public void onPurchanseSeat(Response<OrderResponse> orderResponse) {
                Log.d(TAG, "onPurchanseSeat: " + orderResponse);

                int code = orderResponse.code();
                switch (code) {
                    case 200:
                        OrderResponse response = orderResponse.body();
                        int  purchanseID = response.getData().getPurchase_id();
                        int seatCount = response.getData().getNo_of_seats();
                        MembershipCalculatedSeat(seatCount,purchanseID);
//                        int totalAmount = response.getData().getTax_details().get(0).getTax_amnt();
//
                        break;
                    case 400:
                        JSONObject mJsonObject;
                        try {
                            mJsonObject = new JSONObject(orderResponse.errorBody().string());
                            String errorMessage = mJsonObject.getString("message");
                            ConstantMethod.DialogShow(getContext(), errorMessage);
                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e);

            }
        }, getContext(), mToken, seatCount);*/

    }

    private void MembershipCalculatedSeat(int seatCount,int purchanseid) {

        int mid = SharedPref.getSharedPreferences(getContext()).getMembershipId();

        Map<String, Integer> map = new HashMap<>();
        map.put("no_of_seats", seatCount);
        map.put("membership_id", mid);


        APICallBack.getMembershipSeatCalculated(new APICallBack.IMemberShipInterface() {
            @Override
            public void onMemberShip(MembershipCostdetailsResponse verifyPurchaseResponse) {
                ShowPaymentGetwayOpen(verifyPurchaseResponse.getData().getParticulars().getMembership().getTotal_amount(), purchanseid);
            }

            @Override
            public void onError(Throwable e) {

            }
        }, getContext(), map);
    }

    private void ShowPaymentGetwayOpen(int totalAmount, int purchanseID) {
        startPayment(purchanseID, totalAmount);
    }


    @Override
    public void onPaymentSuccess(String s) {
        Log.d(TAG, "onPaymentSuccess: " + s);
//        String paymentId = "kasdfj3o0s9dfjksjdfkas";
//        VerifiAPICall(paymentId);

    }

    @Override
    public void onPaymentError(int i, String s) {
        Log.d(TAG, "onPaymentError: " + i + " --> " + s);
//        String paymentId = "kasdfj3o0s9dfjksjdfkassfas";
//        VerifiAPICall(paymentId);
    }


    private void VerifiAPICall(String paymentId) {

        Map<String, String> map = new HashMap<>();
        map.put("token", mToken);
        map.put("payment_id", paymentId);
        map.put("purchase_id", purchanseID + "");

        mApiInterface.getVerifyPurchase(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VerifyPurchaseResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VerifyPurchaseResponse verifyPurchaseResponse) {
                        Log.d(TAG, "onNext: " + verifyPurchaseResponse);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }


    /**
     * @param mPassengerInfoList
     * @param tagMeGuest
     */
    @Override
    public void onPersionList(List<PasengerInfoPojo> mPassengerInfoList, boolean tagMeGuest) {

        List<Integer> guestIdList = new ArrayList<>();
        List<Integer> seatIdList = new ArrayList<>();


        // Check Guest add for name and phone
        List<GuestBean> addList = new ArrayList<>();
        GuestBean bean;


        for (PasengerInfoPojo pojo : mPassengerInfoList) {

            if (!pojo.isCheckStatus()) {
                bean = new GuestBean();
                bean.setName(pojo.getName());
                bean.setPhone(pojo.getPhone());
                bean.setGuestId(pojo.getSeatId());
                addList.add(bean);
            } else {
                guestIdList.add(pojo.getGuestId());
            }

            seatIdList.add(pojo.getSeatId());
        }


        if (addList.size() != 0) {

            GuestInfoRequest request = new GuestInfoRequest(mToken, addList);
            mApiInterface.getAddMultipleGuest(request)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<GuestAddResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(GuestAddResponse response) {
                            Log.d(TAG, "onNext: " + response);

                            if (response.getResultcode() == 1) {

                                for (GuestAddResponse.DataBean.NewContactsBean bean : response.getData().getNew_contacts()) {
                                    guestIdList.add(bean.getGuest_id());
                                }

                            }

                            bookSeatFlight(guestIdList, seatIdList, tagMeGuest);
                            Log.d(TAG, "onNext: " + mPassengerInfoList);

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d(TAG, "onError: " + e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } else {

            bookSeatFlight(guestIdList, seatIdList, tagMeGuest);

        }
    }


    /**
     * @param guestIdList
     * @param seatIdList
     * @param tagMeGuest
     */
    private void bookSeatFlight(List<Integer> guestIdList, List<Integer> seatIdList, boolean tagMeGuest) {


        // Added for guest id
        List<Integer> guestListId = new ArrayList<>();

        boolean checkSelfTravelling = true;
        int selfTravelling;
        if (tagMeGuest) {
            selfTravelling = 1;

            for (int i = 1; i < guestIdList.size(); i++) {
                guestListId.add(guestIdList.get(i));
            }

        } else {
            checkSelfTravelling = false;
            selfTravelling = 0;
            for (int i = 0; i < guestIdList.size(); i++) {
                guestListId.add(guestIdList.get(i));
            }
        }

        JSONArray mRelationJsonArray = new JSONArray();
        for (int i = 0; i < guestIdList.size(); i++) {
            JSONObject mIndividualJsonObject = new JSONObject();
            try {
                mIndividualJsonObject.put("seat_id", Integer.valueOf(seatIdList.get(i)));
                if (checkSelfTravelling) {
                    checkSelfTravelling = false;
                    mIndividualJsonObject.put("passenger", 0);
                } else {
                    mIndividualJsonObject.put("passenger", guestIdList.get(i));
                }
                mRelationJsonArray.put(mIndividualJsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Log.d(TAG, "bookSeatFlight: " + mRelationJsonArray.toString());

        ConfirmSeatBookingResponse confirm = new ConfirmSeatBookingResponse(mFromId, mToId, mFlightId, mToken, selfTravelling, mJourneyDate, mJourneyTime, mArriveTime, mScheduleId, seatIdList, guestListId, mRelationJsonArray.toString());

        Log.d(TAG, "bookSeatFlight: " + confirm);

        ConstantMethod.ShowProgressBar(getActivity());

        SeatConfirmBookingApiCall(confirm);


    }

    /**
     * Seat confirm allocation api calling...
     *
     * @param confirm
     */
    private void SeatConfirmBookingApiCall(ConfirmSeatBookingResponse confirm) {

        Log.d(TAG, "SeatConfirmBookingApiCall: " + confirm);
        mApiInterface.confirmFlightBooking(confirm)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ConfirmFlightBookingResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<ConfirmFlightBookingResponse> flightBookingResponseResponse) {
                        Log.d(TAG, "onNext: " + flightBookingResponseResponse);

                        int code = flightBookingResponseResponse.code();
                        switch (code) {
                            case 200:
                                BookingConfirmed(flightBookingResponseResponse.body().getData().getBooking_details());
                                break;
                            case 400:
                                JSONObject mJsonObject;
                                try {
                                    mJsonObject = new JSONObject(flightBookingResponseResponse.errorBody().string());
                                    String errorMessage = mJsonObject.getString("message");
                                    ConstantMethod.DialogShow(getContext(), errorMessage);
                                } catch (JSONException | IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 500:
                                ConstantMethod.DialogShow(getContext(), "Server Error!");
                                break;
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                        ConstantMethod.HideProgressBar(getActivity());
                    }

                    @Override
                    public void onComplete() {
                        ConstantMethod.HideProgressBar(getActivity());
                    }
                });
    }


    // 
    private void BookingConfirmed(List<BookingListBean> booking_details) {

        Intent intent = new Intent(getContext(), BookingConfirmedActivity.class);
        intent.putExtra("bookingListBean", booking_details.get(0));
        startActivity(intent);
    }


    /**
     * Guest contact list api call
     */
    private void ContactAPICall() {

        mApiInterface.getContactDetails(mToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ContactDetailsPojo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ContactDetailsPojo contactDetialPojo) {

                        if (contactDetialPojo.getResultcode() == 1) {
                            mPassengerListAdapter.setGuestDetailsList(contactDetialPojo.getData().getContacts());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    // Goald type member purchange seat.
    public void startPayment(int purchanseID, int allSeatCoast) {
        // Instantiate Checkout
        Checkout checkout = new Checkout();

        // Set your logo here
        checkout.setImage(R.mipmap.ic_stellar_launcher);

        // Reference to current activity
        final Activity activity = getActivity();

        try {
            JSONObject options = new JSONObject();

            //pass app name
            options.put("name", getResources().getString(R.string.app_name));

            JSONObject prefillDate = new JSONObject();
            prefillDate.put("contact", SharedPref.getSharedPreferences(getContext()).getUserPhone());
            prefillDate.put("email", SharedPref.getSharedPreferences(getContext()).getUserEmail());

            options.put("prefill", prefillDate);
            options.put("description", purchanseID);

            options.put("currency", "INR");
            options.put("amount", allSeatCoast * 100);
            checkout.open(activity, options);
        } catch (Exception e) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }


    void loadTime() {
        String page = "<html lang=\"en\">\r\n<head>\r\n    <title>Signup Terms</title>\r\n    <meta charset=\"utf-8\">\r\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css\">\r\n    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js\"></script>\r\n    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js\"></script>\r\n    </style>\r\n</head>\r\n<body>\r\n\t<div class=\"container text-justify\">\r\n\t\t\t<h5><b>Security Regulations:</b></h5>\r\n\t\t\tAccording to security regulations, passengers are advised:<br>\r\n\t\t\t1. not to accept any packets from unknown persons.<br>\r\n\t\t\t2. not to leave baggage unobserved at any time, especially within airport areas. Unattended baggage may be removed by Airport/Security Staff as an object of suspicion.<br>\r\n\t\t\t3. to declare before checking-in, if carrying any arms or explosive substances. Concealment is an offence under the Aircraft Act & Rules.<br>\r\n\t\t\t4. to carry only one hand baggage, the sum of the three dimensions (viz. length, breadth & height) of which should not exceed 115 cms.<br>\r\n\t\t\t5. battery cells/dry cells carried in the hand baggage or in any electrical/electronic items are liable to be removed & the airline would not be in a position to hand over the same at the destination. The same may be carried in registered baggage.<br>\r\n\t\t\t<br>\r\n\t\t\t<b>Prohibited Articles:</b> Also carriage of dry cell batteries, knives, scissors, sharp instruments, tools, fire arms, ammunition, & their toy replicas are prohibited in the passenger cabin.<br>\r\n\t\t\t<br>\r\n\t\t\t<b>Valuable Articles:</b> Currency, precious metals, jewellery, negotiable instruments, securities, personal identification documents & other items of value, are best carried with the passengers in the cabin.<br>\r\n\t\t\t<br>\r\n\t\t\t<b>Dangerous Articles in Baggage:</b> For safety reasons, passengers may not carry dangerous or hazardous articles in checked baggage, carry-on baggage or on their person.<br>\r\n\t\t\tDangerous articles include but are not limited tothose listed below, must not be carried in checked baggage, carry-on baggage or on person:<br>\r\n\t\t\t<br>\t\t\t\r\n\t\t\tExplosives, Gases, Flammable liquids and solids, oxidizing substances, organic peroxides, toxic and infectious substances, radioactive material, corrosives, magnetized materials, briefcases/attache cases/safe boxes with installed alarm devices or lithium batteries or pyrotechnic materials, disabling/incapacitating devices such as a mace/pepper spray or tasers, liquid oxygen medical devices and any other materials as prohibited in the IATA Dangerous Goods Regulations or ICAO Annex 18 Technical Instructions for the carriage of Dangerous goods.<br>\r\n\t\t\t<br><br>\r\n\t\t\tThe Govt. of India prohibits the use of mobile phones on board (Check with in-flight safety, apparently this has been changed - as a concession they may use it before take off and after landing, provided the announcement is made).\r\n\t\t\t<br><br>\r\n\t</div>\r\n</body>\r\n</html>";

        page = page.replace("\r", "").replace("\t", "").replace("\n", "");
        mWebView.loadDataWithBaseURL("x-data://base", page,
                "text/html", "UTF-8",
                null);
    }

    private class Callback extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            loadTime();

            return (true);
        }
    }
}
