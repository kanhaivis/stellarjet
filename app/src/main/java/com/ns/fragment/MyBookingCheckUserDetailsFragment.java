package com.ns.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ns.database.SharedPref;
import com.ns.model.BoardingList.FoodItems;
import com.ns.model.CancelBooking.CancelBookingInfo;
import com.ns.model.CancelBooking.CancelBookingRequest;
import com.ns.model.CommonPojo;
import com.ns.model.ReUseModel.BookingListBean;
import com.ns.retrofit.APICallBack;
import com.ns.stellarjet.R;
import com.ns.utils.ConstantDateFormate;
import com.ns.utils.ConstantMethod;
import com.ns.utils.UiConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyBookingCheckUserDetailsFragment extends Fragment implements CancelMultipleTicketFragment.ITicketCanceled, FeedBackFragment.FeedBackAction {

    private static final String TAG = "MyBookingCheckUserDetai";


    // UI View
    private TextView vPickDropUpTxt;
    private TextView vDiningTxt;
    private LinearLayout vPicDropupDiningViewLayout;

    private Button mFeedBackBtn;

    // Bundle data pass
    BookingListBean mBookingListBean;
    private boolean mStatus;
    private int mPosition;


    private long datetimeLong;
    ArrayList<Integer> seatListIDs = new ArrayList<>();
    private int bookingId;

    String guestSetName = "";
    String seatCode = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBookingListBean = (BookingListBean) this.getArguments().getSerializable("bookingListBean");

        mStatus = this.getArguments().getBoolean("status");
        mPosition = this.getArguments().getInt("row_pos");
        Log.d(TAG, "onCreate: " + mBookingListBean);

        ShowFirstTime();



    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mybooking_userdetails_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if (!mBookingListBean.isFeedback_available()) {
            if (SharedPref.getSharedPreferences(getContext()).getFeedBackDeatils() != 0) {
                mBookingListBean.setFeedback_available(true);
            }
        }

        List<Integer> diningSelectList = SharedPref.getSharedPreferences(getContext()).getDiningSelectList();

        if (diningSelectList.get(0) == 0) {
            if (diningSelectList.get(1) == mPosition) {
                Log.d(TAG, "onViewCreated: ");
                ShowAgain();
            } else if (diningSelectList.get(1) != mPosition){
                Log.d(TAG, "onViewCreated: ");
//                ShowFirstTime();
            }
        } else if (diningSelectList.get(0) == 1){
            if (diningSelectList.get(1) == mPosition) {
                Log.d(TAG, "onViewCreated: ");
                ShowAgain();
            } else if (diningSelectList.get(1) != mPosition){
                Log.d(TAG, "onViewCreated: ");
//                SharedPref.getSharedPreferences(getContext()).saveSelectdFoodCategories(new ArrayList<>());
            }
        }




        bookingId = mBookingListBean.getBooking_id();
        long datetimeLong = mBookingListBean.getJourney_datetime();


        vDiningTxt = view.findViewById(R.id.mybooking_uesrdetails_dining);
        vPickDropUpTxt = view.findViewById(R.id.mybooking_uesrdetails_pickupanddropoff);


        vPicDropupDiningViewLayout = view.findViewById(R.id.picdropup_dining_viewLayout);
        if (mBookingListBean.getTravelling_self() == 0) {
            vPicDropupDiningViewLayout.setVisibility(View.INVISIBLE);
        } else if (mBookingListBean.getTravelling_self() == 1) {
            vPicDropupDiningViewLayout.setVisibility(View.VISIBLE);


            if (mBookingListBean.getPick_address_main().isEmpty() && mBookingListBean.getDrop_address_main().isEmpty()) {
                if (SharedPref.getSharedPreferences(getContext()).getPickUpDropUpDeatils() == 0) {
                    vPickDropUpTxt.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_date_next), null);
                } else {
                    vPickDropUpTxt.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.tick), null, getResources().getDrawable(R.drawable.ic_date_next), null);
                }
            } else {
                vPickDropUpTxt.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.tick), null, getResources().getDrawable(R.drawable.ic_date_next), null);
            }


            if (SharedPref.getSharedPreferences(getContext()).getSelectdFoodCategories().size() != 0) {
                vDiningTxt.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.tick), null, getResources().getDrawable(R.drawable.ic_date_next), null);
            } else {
                vDiningTxt.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_date_next), null);
            }

        }

        /// Showing user information
        ((TextView) view.findViewById(R.id.mybooking_uesrdetails_fromtocity)).setText(mBookingListBean.getFrom_city_info().getName() + " to " + mBookingListBean.getTo_city_info().getName());
        ((TextView) view.findViewById(R.id.mybooking_uesrdetails_date)).setText(ConstantDateFormate.getFormattedBookingsDate(datetimeLong) + " \n" + ConstantDateFormate.getReachByPlaneHours(datetimeLong));
        ((TextView) view.findViewById(R.id.mybooking_uesrdetails_seats_name)).setText(seatCode);
        ((TextView) view.findViewById(R.id.mybooking_uesrdetails_passengers_name)).setText(guestSetName);


        mFeedBackBtn = view.findViewById(R.id.ticket_feedback_btn);
        LinearLayout ticketStatus = view.findViewById(R.id.ticket_status_view);


        ImageView cancleIcon = view.findViewById(R.id.mybooking_uesrdetails_cancleicon);
        if (mBookingListBean.getStatus().equals("Cancelled")) {
            cancleIcon.setVisibility(View.VISIBLE);
        } else {
            cancleIcon.setVisibility(View.GONE);
        }

        /**
         * mStatus is true coming on ConpletedFragment
         * mStatus is false coming on UpcomingFragment
         */
        if (mStatus) {
            if (mBookingListBean.getStatus().equals("Cancelled")) {
                mFeedBackBtn.setVisibility(View.GONE);
            } else {

                if (mBookingListBean.isFeedback_available()) {
                    mFeedBackBtn.setVisibility(View.GONE);
                } else {
                    mFeedBackBtn.setVisibility(View.VISIBLE);

                }
            }
        } else {
            ticketStatus.setVisibility(View.VISIBLE);
            if (mBookingListBean.getStatus().equals("Cancelled")) {
                ticketStatus.setVisibility(View.GONE);

            } else {
                ticketStatus.setVisibility(View.VISIBLE);

            }

        }


        ((TextView) view.findViewById(R.id.mybooking_uesrdetails_tickecreatebyname)).setText("Tickets booked by " + mBookingListBean.getBooked_by());

        // Bundle Action for fragment (Common)
        Bundle bundle = new Bundle();
        bundle.putSerializable("bookingListBean", mBookingListBean);


        FeedBackFragment feedBackFragment = new FeedBackFragment();
        feedBackFragment.setFeedBackAction(this);

        //  FeedBack event show
        mFeedBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                bundle.putInt("row_pos", mPosition);
                feedBackFragment.setArguments(bundle);


                new Handler().postDelayed(() -> Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.framePane, feedBackFragment)
                        .addToBackStack(null)
                        .commit(), UiConstants.DELAYMILLISECOND);

            }
        });


        /**
         * Ticket Cancled Action
         */
        view.findViewById(R.id.mybooking_uesrdetails_cancelbooking).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // check seat count
                if (seatListIDs.size() == 1) {
                    CancelActionDialog();
                } else {
                    MultipleGuest();
                }
            }
        });


        /**
         * Food Action
         */

        /**/
        vDiningTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FoodCategoriesListFragment fragment = new FoodCategoriesListFragment();
                bundle.putInt("row_pos", mPosition);
                fragment.setArguments(bundle);


                new Handler().postDelayed(() -> Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.framePane, fragment)
                        .addToBackStack(null)
                        .commit(), UiConstants.DELAYMILLISECOND);

            }
        });


        PickUpAndDropSetAddressFragment pickUpAndDropSetAddressFragment = new PickUpAndDropSetAddressFragment();

        /**
         * PickUp and Drop Off Action
         */
        vPickDropUpTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bundle.putBoolean("key", true);
                pickUpAndDropSetAddressFragment.setArguments(bundle);


                new Handler().postDelayed(() -> Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.framePane, pickUpAndDropSetAddressFragment)
                        .addToBackStack(null)
                        .commit(), UiConstants.DELAYMILLISECOND);
            }
        });


    }


    private void ShowAgain() {
    }

    private void ShowFirstTime() {
        // Particular Main Passenger titcket status
        if (mBookingListBean.getTravelling_self() == 1) {

            guestSetName = mBookingListBean.getPrefs().getMain_passenger().getName();
            seatCode = mBookingListBean.getPrefs().getMain_passenger().getSeats_info().getSeat_code();

            if (mBookingListBean.getPrefs().getMain_passenger().getStatus().equals("Confirmed")) {
                seatListIDs.add(mBookingListBean.getPrefs().getMain_passenger().getSeats_info().getSeat_id());
                Log.d(TAG, "onClick: ");
            }

            List<Integer> diningSelectList = SharedPref.getSharedPreferences(getContext()).getDiningSelectList();

            if (diningSelectList.get(1) != mPosition) {
                SharedPref.getSharedPreferences(getContext()).saveSelectdFoodCategories(new ArrayList<>());
            }

            ArrayList<FoodItems> foodBeanList = mBookingListBean.getPrefs().getMain_passenger().getFood_items();
            if (foodBeanList.size() != 0) {

                ArrayList<String> foodIdList = new ArrayList<>();
                for (FoodItems foodItems : foodBeanList) {
                    foodIdList.add(foodItems.getId() + "");
                }
                SharedPref.getSharedPreferences(getContext()).saveSelectdFoodCategories(foodIdList);
            }

        }


        // Particular Co-Passenger titcket status
        int co_passengerInfoList = mBookingListBean.getPrefs().getCo_passengers().size();

        if (co_passengerInfoList != 0) {
            for (int i = 0; i < co_passengerInfoList; i++) {

                if (seatCode == "") {
                    seatCode = mBookingListBean.getPrefs().getCo_passengers().get(i).getSeats_info().getSeat_code();
                } else {
                    seatCode = seatCode + "," + mBookingListBean.getPrefs().getCo_passengers().get(i).getSeats_info().getSeat_code();
                }

                if (guestSetName == "") {
                    guestSetName = mBookingListBean.getPrefs().getCo_passengers().get(i).getName();
                } else {
                    guestSetName = guestSetName + "," + mBookingListBean.getPrefs().getCo_passengers().get(i).getName();
                }

                if (mBookingListBean.getPrefs().getCo_passengers().get(i).getStatus().equals("Confirmed")) {

                    seatListIDs.add(mBookingListBean.getPrefs().getCo_passengers().get(i).getSeats_info().getSeat_id());
                    Log.d(TAG, "onClick: ");
                }

            }
        }
    }

    private void MultipleGuest() {


        ArrayList<CancelBookingInfo> cancelBookingInfosList = new ArrayList<>();

        String[] seatList = seatCode.split(",");

        String[] nameList = guestSetName.split(",");

        CancelBookingInfo userInfo;
        for (int i = 0; i < seatListIDs.size(); i++) {
            userInfo = new CancelBookingInfo(seatList[i], nameList[i], seatListIDs.get(i), false);
            cancelBookingInfosList.add(userInfo);
        }

        Bundle bundle = new Bundle();
        bundle.putInt("bookingid", bookingId);
        bundle.putSerializable("canceledList", cancelBookingInfosList);
        CancelMultipleTicketFragment multipleTickerFragment = new CancelMultipleTicketFragment();
        multipleTickerFragment.setTicketAction(this);
        multipleTickerFragment.setArguments(bundle);


        new Handler().postDelayed(() -> Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                .replace(R.id.framePane, multipleTickerFragment)
                .addToBackStack(null)
                .commit(), 450);
    }

    private void CancelActionDialog() {
        ConstantMethod.DialogShowDoubleAction(new ConstantMethod.DialogAction() {
            @Override
            public void onAction(boolean status) {
                if (status) {
                    String token = SharedPref.getSharedPreferences(getContext()).getTocken();
                    CancelBookingRequest request = new CancelBookingRequest(token, bookingId, seatListIDs);
                    Log.e(TAG, "onAction: " + request);

                    ConstantMethod.ShowProgressBar(getActivity());
                    APICallBack.getCancelData(new APICallBack.ICancelTicket() {
                        @Override
                        public void onCancelData(CommonPojo commonPojo) {

                            Log.d(TAG, "onCancelData: " + commonPojo);
                            ConstantMethod.HideProgressBar(getActivity());
                            if (commonPojo.getResultcode() == 1) {
                                ConstantMethod.ShowToastMessage(getContext(), commonPojo.getMessage());
                                mITicketCanceled.onAction(true);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            ConstantMethod.HideProgressBar(getActivity());
                        }
                    }, getActivity(), request);

                }

            }
        }, getContext(), "Are you sure you want to cancel the ticket.", "Yes", "No");
    }


    public ITicketCanceled mITicketCanceled;

    public ITicketCanceled setTicketAction(ITicketCanceled iTicketCanceled) {
        return mITicketCanceled = iTicketCanceled;
    }


/*    @Override
    public void onEventPickUpDropUp(boolean status) {

        if (status) {
            mITicketCanceled.onAction(true);
        }
    }*/

    @Override
    public void onActionFeedback(boolean status) {

    }

    public interface ITicketCanceled {
        void onAction(boolean status);
    }

    @Override
    public void onAction(boolean status) {
        if (status) {
            mITicketCanceled.onAction(true);
        }
    }


}
