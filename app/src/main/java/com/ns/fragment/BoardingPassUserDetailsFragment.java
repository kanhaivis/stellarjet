package com.ns.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ns.activity.TicketViewActivity;
import com.ns.database.SharedPref;
import com.ns.model.BoardingPass.BoardingPassBeanXX;
import com.ns.model.Download.DownloadPojo;
import com.ns.model.TCDataBean;
import com.ns.stellarjet.R;
import com.ns.utils.ConstantDateFormate;
import com.ns.utils.ConstantMethod;
import com.ns.utils.DodnloadMyThread;

import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BoardingPassUserDetailsFragment extends Fragment {

    private static final String TAG = "BoardingPassUserDetails";


    private WebView mWebView;

    private TextView mAgreeTxt;
    private boolean mAgreeCheck = false;


    String guestSetName = "";
    String seatCode = "";
    int seatCount = 0;

    private RelativeLayout mSlideUpLayout;


    public ClickBackButton mBackButton;

    public ClickBackButton setBackButton(ClickBackButton click) {
        return mBackButton = click;
    }


    public interface ClickBackButton {
        void backBtn(boolean check);
    }


    private ArrayList<DownloadPojo> mDownloadList = new ArrayList<>();

    BoardingPassBeanXX mBoardingPassBean;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBoardingPassBean = (BoardingPassBeanXX) this.getArguments().getSerializable("boardingPassBean");


        if (mBoardingPassBean.getTravelling_self() == 1) {
            if (mBoardingPassBean.getPrefs().getMain_passenger().getStatus().equals("Confirmed")) {
                seatCount++;
            }
        }

        // Particular Co-Passenger titcket status
        int count = mBoardingPassBean.getPrefs().getCo_passengers().size();
        if (count != 0) {
            for (int i = 0; i < count; i++) {
                if (mBoardingPassBean.getPrefs().getCo_passengers().get(i).getStatus().equals("Confirmed")) {
                    seatCount++;
                }
            }
        }


        DownloadPojo pojo;
        // Particular Main Passenger titcket status
        if (mBoardingPassBean.getTravelling_self() == 1) {
            if (mBoardingPassBean.getPrefs().getMain_passenger().getStatus().equals("Confirmed")) {
                guestSetName = mBoardingPassBean.getPrefs().getMain_passenger().getName();
                seatCode = mBoardingPassBean.getPrefs().getMain_passenger().getSeats_info().getSeat_code();
                String boarding_pass_url = mBoardingPassBean.getPrefs().getMain_passenger().getBoarding_pass_url();

                pojo = new DownloadPojo(seatCode, guestSetName, boarding_pass_url);
                mDownloadList.add(pojo);

                Log.d(TAG, "onClick: ");
            } else if (mBoardingPassBean.getPrefs().getMain_passenger().getStatus().equals("Cancelled")) {
                Log.d(TAG, "onClick: ");
            }
        }

        // Particular Co-Passenger titcket status
        int co_passengerInfoList = mBoardingPassBean.getPrefs().getCo_passengers().size();

        if (co_passengerInfoList != 0) {
            for (int i = 0; i < co_passengerInfoList; i++) {
                if (mBoardingPassBean.getPrefs().getCo_passengers().get(i).getStatus().equals("Confirmed")) {

                    if (guestSetName == "") {
                        guestSetName = mBoardingPassBean.getPrefs().getCo_passengers().get(i).getName();
                    } else {
                        guestSetName = guestSetName + "," + mBoardingPassBean.getPrefs().getCo_passengers().get(i).getName();
                    }

                    if (seatCode == "") {
                        seatCode = mBoardingPassBean.getPrefs().getCo_passengers().get(i).getSeats_info().getSeat_code();
                    } else {
                        seatCode = seatCode + "," + mBoardingPassBean.getPrefs().getCo_passengers().get(i).getSeats_info().getSeat_code();
                    }

                    String boarding_pass_url = mBoardingPassBean.getPrefs().getCo_passengers().get(i).getBoarding_pass_url();

                    pojo = new DownloadPojo(mBoardingPassBean.getPrefs().getCo_passengers().get(i).getSeats_info().getSeat_code(), mBoardingPassBean.getPrefs().getCo_passengers().get(i).getName(), boarding_pass_url);
                    mDownloadList.add(pojo);

                    Log.d(TAG, "onClick: ");
                }
            }
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_boarding_pass_user_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        long datetimeLong = mBoardingPassBean.getJourney_datetime();


        ((TextView) view.findViewById(R.id.textView_row_boarding_pass_details_from_city)).setText(mBoardingPassBean.getFrom_city_info().getName() + " to " + mBoardingPassBean.getTo_city_info().getName());
        ((TextView) view.findViewById(R.id.textView_row_boarding_pass_details_date)).setText(ConstantDateFormate.getFormattedBookingsDate(datetimeLong) + " \n" + ConstantDateFormate.getReachByPlaneHours(datetimeLong));
        ((TextView) view.findViewById(R.id.textView_boarding_pass_details_seats_name)).setText(seatCode);
        ((TextView) view.findViewById(R.id.textView_boarding_pass_details_passengers_name)).setText(guestSetName);
        ((TextView) view.findViewById(R.id.textView_boarding_pass_details_flight_no)).setText(mBoardingPassBean.getFlight_no());
        ((TextView) view.findViewById(R.id.textView_boarding_pass_details_foods_no)).setText("Standard");
        ((TextView) view.findViewById(R.id.textView_boarding_pass_details_seats_title)).setText("Seats(" + seatCount + ")");
        ((TextView) view.findViewById(R.id.textView_boarding_pass_details_flight_reporting_time)).setText(getResources().getString(R.string.flight_reportTime) + " " + mBoardingPassBean.getReportTime());

        mWebView = view.findViewById(R.id.webviews);
        mWebView.setWebViewClient(new Callback());

        loadTime();


        mSlideUpLayout = view.findViewById(R.id.content_slide_up_view);
        view.findViewById(R.id.button_download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mBackButton.backBtn(true);
                mSlideUpLayout.setVisibility(View.VISIBLE);
                mSlideUpLayout.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.slide_up));
            }
        });

        view.findViewById(R.id.tc_cross_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBackButton.backBtn(false);
                mSlideUpLayout.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.slide_down));
                mSlideUpLayout.setVisibility(View.GONE);
            }
        });


        // Proceed btn action then show Download list show
        view.findViewById(R.id.tc_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mAgreeCheck) {
                    mBackButton.backBtn(false);

                    onOpenPassDownloadFragment();

                } else {

                    ConstantMethod.ShowToastMessage(getContext(), "Please checked i agree TC ");
                }

            }
        });

        mAgreeTxt = view.findViewById(R.id.i_agree_textview);
        mAgreeTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAgreeCheck) {
                    mAgreeTxt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_tik_uncheck, 0, 0, 0);
                    mAgreeCheck = false;
                } else {
                    mAgreeTxt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_tik_check, 0, 0, 0);
                    mAgreeCheck = true;
                }
            }
        });


    }

    void loadTime() {

        String pageUrl = null;
        ArrayList<TCDataBean> tcUrlList = SharedPref.getSharedPreferences(getContext()).getTCUrl();

        for (TCDataBean tc : tcUrlList) {
            if (tc.getScreen().equals("BOARDING")) {
                pageUrl = tc.getScreenContent();
            }
        }

        mWebView.loadDataWithBaseURL("x-data://base", pageUrl,
                "text/html", "UTF-8",
                null);
    }

    private class Callback extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            loadTime();

            return (true);
        }
    }


    private void onOpenPassDownloadFragment() {


        if (mDownloadList.get(0).getTicketUrlLinked().equals("")) {
            ConstantMethod.DialogShow(getContext(), getResources().getString(R.string.bardingpass_url_empty));
            return;
        }

        if (mDownloadList.size() == 1) {
            DownloadPojo data = mDownloadList.get(0);
            onSinlgeTickeOpen(data);

        } else {

            Bundle bundle = new Bundle();
            bundle.putSerializable("downloadInfoList", mDownloadList);

            BoardingPassDownloadFragment fragment = new BoardingPassDownloadFragment();
            fragment.setArguments(bundle);

            new Handler().postDelayed(() -> Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.boardingPass_CPanel, fragment)
                    .addToBackStack(null)
                    .commit(), 450);
        }

        mSlideUpLayout.setVisibility(View.GONE);
        mBackButton.backBtn(false);
        mAgreeTxt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_tik_uncheck, 0, 0, 0);
        mAgreeCheck = false;
    }


    private void onSinlgeTickeOpen(DownloadPojo data) {


        String ticket_name = data.getTicketUrlLinked().substring(data.getTicketUrlLinked().lastIndexOf('/') + 1);
        boolean check = ConstantMethod.checks(ticket_name);


        if (check) {
            ArrayList<DownloadPojo> mDownloadPojos = new ArrayList<>();
            mDownloadPojos.add(data);
            Bundle bundle = new Bundle();
            bundle.putSerializable("downloadInfoList", mDownloadPojos);

            Intent intent = new Intent(getActivity(), TicketViewActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);


        } else {

            DodnloadMyThread mythreads = new DodnloadMyThread(mHandler, data.getTicketUrlLinked(), ticket_name, 1);
            Thread thread = new Thread(mythreads);
            thread.start();
        }

        mSlideUpLayout.setVisibility(View.GONE);
        mBackButton.backBtn(false);
        mAgreeTxt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_tik_uncheck, 0, 0, 0);
        mAgreeCheck = false;
    }


    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {

            Log.d(TAG, "handleMessage:-->  " + message.arg1);

            Log.d(TAG, "handleMessage: " + mDownloadList.size());

            int seatCount = mDownloadList.size();
            if (seatCount == message.arg2) {

                if (message.arg1 == 100) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("downloadInfoList", mDownloadList);

                    Intent intent = new Intent(getActivity(), TicketViewActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

            }

            return false;

        }
    });

}
