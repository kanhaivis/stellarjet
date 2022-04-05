package com.ns.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ns.activity.PinActivity;
import com.ns.database.SharedPref;
import com.ns.model.SecondaryUser.SecondaryUserLoginResponse;
import com.ns.retrofit.APICallBack;
import com.ns.stellarjet.R;
import com.ns.utils.ConstantMethod;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class OtpFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "OtpFragment";

    private EditText mCurrentEditText;
    private String mFirstEditTextPassCode;
    private String mSecondEditTextPassCode;
    private String mThreeEditTextPassCode;
    private String mFourEditTextPassCode;
    private Button mPasscodeOneButton;
    private Button mPasscodeTwoButton;
    private Button mPasscodeThreeButton;
    private Button mPasscodeFourButton;
    private Button mPasscodeFiveButton;
    private Button mPasscodeSixButton;
    private Button mPasscodeSevenButton;
    private Button mPasscodeEightButton;
    private Button mPasscodeNineButton;
    private Button mPasscodeZeroButton;
    private Button mPasscodeDelButton;
    private TextView mPasswordHeading;
    private EditText mNumberOneEditText;
    private EditText mNumberTwoEditText;
    private EditText mNumberThreeEditText;
    private EditText mNumberFourEditText;


    private WebView mWebView;
    private RelativeLayout mSlideUpLayout;
    private boolean mAgreeCheck = false;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.opt_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mPasscodeOneButton = view.findViewById(R.id.button_passcode_one);
        mPasscodeOneButton.setOnClickListener(this);
        mPasscodeTwoButton = view.findViewById(R.id.button_passcode_two);
        mPasscodeTwoButton.setOnClickListener(this);
        mPasscodeThreeButton = view.findViewById(R.id.button_passcode_three);
        mPasscodeThreeButton.setOnClickListener(this);
        mPasscodeFourButton = view.findViewById(R.id.button_passcode_four);
        mPasscodeFourButton.setOnClickListener(this);
        mPasscodeFiveButton = view.findViewById(R.id.button_passcode_five);
        mPasscodeFiveButton.setOnClickListener(this);
        mPasscodeSixButton = view.findViewById(R.id.button_passcode_six);
        mPasscodeSixButton.setOnClickListener(this);
        mPasscodeSevenButton = view.findViewById(R.id.button_passcode_seven);
        mPasscodeSevenButton.setOnClickListener(this);
        mPasscodeEightButton = view.findViewById(R.id.button_passcode_eight);
        mPasscodeEightButton.setOnClickListener(this);
        mPasscodeNineButton = view.findViewById(R.id.button_passcode_nine);
        mPasscodeNineButton.setOnClickListener(this);
        mPasscodeZeroButton = view.findViewById(R.id.button_passcode_zero);
        mPasscodeZeroButton.setOnClickListener(this);
        mPasscodeDelButton = view.findViewById(R.id.button_passcode_del);
        mPasscodeDelButton.setOnClickListener(this);

        mNumberOneEditText = view.findViewById(R.id.editText_passcode_number_one);
        mNumberTwoEditText = view.findViewById(R.id.editText_passcode_number_two);
        mNumberThreeEditText = view.findViewById(R.id.editText_passcode_number_three);
        mNumberFourEditText = view.findViewById(R.id.editText_passcode_number_four);

        mPasswordHeading = view.findViewById(R.id.textView_otp_heading);


        mNumberOneEditText.requestFocus();
        mCurrentEditText = mNumberOneEditText;

        mNumberOneEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String inputText = s.toString();
                if (inputText.length() == 1) {
                    mFirstEditTextPassCode = inputText;
                    mCurrentEditText = mNumberTwoEditText;
                    mNumberTwoEditText.requestFocus();
                }
            }
        });

        mNumberTwoEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String inputText = s.toString();
                if (inputText.length() == 1) {
                    mSecondEditTextPassCode = inputText;
                    mCurrentEditText = mNumberThreeEditText;
                    mNumberThreeEditText.requestFocus();
                }
            }
        });

        mNumberThreeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String inputText = s.toString();
                if (inputText.length() == 1) {
                    mThreeEditTextPassCode = inputText;
                    mCurrentEditText = mNumberFourEditText;
                    mNumberFourEditText.requestFocus();
                }
            }
        });

        mNumberFourEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String inputText = s.toString();
                if (inputText.length() == 1) {
                    mFourEditTextPassCode = inputText;
                    disableKeyPad();
                    new Handler().postDelayed(
                            () -> {
                                String passCode = mFirstEditTextPassCode + mSecondEditTextPassCode +
                                        mThreeEditTextPassCode + mFourEditTextPassCode;
                                doSecondaryUserLogin(passCode);
                                enableKeyPad();
                            }, 150
                    );
                }
            }
        });


        mWebView = view.findViewById(R.id.webviews);
        mWebView.setWebViewClient(new OtpFragment.Callback());

        loadTime();

        mSlideUpLayout = view.findViewById(R.id.content_slide_up_view);

        view.findViewById(R.id.tc_cross_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlideUpLayout.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.slide_down));
                mSlideUpLayout.setVisibility(View.GONE);
            }
        });


        view.findViewById(R.id.tc_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mAgreeCheck) {
                    Intent intent = new Intent(getContext(), PinActivity.class);

                    startActivity(intent);
                    getActivity().finish();
                } else {

                    ConstantMethod.ShowToastMessage(getContext(), "Please checked i agree TC ");
                }

            }
        });

        TextView agreeTxt = view.findViewById(R.id.i_agree_textview);
        agreeTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAgreeCheck) {
                    agreeTxt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_tik_uncheck, 0, 0, 0);
                    mAgreeCheck = false;
                } else {
                    agreeTxt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_tik_check, 0, 0, 0);
                    mAgreeCheck = true;
                }
            }
        });

    }

    private void disableKeyPad() {
        mPasscodeOneButton.setEnabled(false);
        mPasscodeTwoButton.setEnabled(false);
        mPasscodeThreeButton.setEnabled(false);
        mPasscodeFourButton.setEnabled(false);
        mPasscodeFiveButton.setEnabled(false);
        mPasscodeSixButton.setEnabled(false);
        mPasscodeSevenButton.setEnabled(false);
        mPasscodeEightButton.setEnabled(false);
        mPasscodeNineButton.setEnabled(false);
        mPasscodeZeroButton.setEnabled(false);
        mPasscodeDelButton.setEnabled(false);
    }


    private void enableKeyPad() {
        mPasscodeOneButton.setEnabled(true);
        mPasscodeTwoButton.setEnabled(true);
        mPasscodeThreeButton.setEnabled(true);
        mPasscodeFourButton.setEnabled(true);
        mPasscodeFiveButton.setEnabled(true);
        mPasscodeSixButton.setEnabled(true);
        mPasscodeSevenButton.setEnabled(true);
        mPasscodeEightButton.setEnabled(true);
        mPasscodeNineButton.setEnabled(true);
        mPasscodeZeroButton.setEnabled(true);
        mPasscodeDelButton.setEnabled(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_passcode_one:
                mCurrentEditText.setText(getString(R.string.passcode_kwypad_one));
                break;
            case R.id.button_passcode_two:
                mCurrentEditText.setText(getString(R.string.passcode_kwypad_two));
                break;
            case R.id.button_passcode_three:
                mCurrentEditText.setText(getString(R.string.passcode_kwypad_three));
                break;
            case R.id.button_passcode_four:
                mCurrentEditText.setText(getString(R.string.passcode_kwypad_four));
                break;
            case R.id.button_passcode_five:
                mCurrentEditText.setText(getString(R.string.passcode_kwypad_five));
                break;
            case R.id.button_passcode_six:
                mCurrentEditText.setText(getString(R.string.passcode_kwypad_six));
                break;
            case R.id.button_passcode_seven:
                mCurrentEditText.setText(getString(R.string.passcode_kwypad_seven));
                break;
            case R.id.button_passcode_eight:
                mCurrentEditText.setText(getString(R.string.passcode_kwypad_eight));
                break;
            case R.id.button_passcode_nine:
                mCurrentEditText.setText(getString(R.string.passcode_kwypad_nine));
                break;
            case R.id.button_passcode_zero:
                mCurrentEditText.setText(getString(R.string.passcode_kwypad_zero));
                break;
            case R.id.button_passcode_del:
                deletePassCode();
                break;
        }
    }

    private void deletePassCode() {
        if (mCurrentEditText == mNumberOneEditText) {
            mCurrentEditText.setText("");
        } else if (mCurrentEditText == mNumberTwoEditText) {
            mCurrentEditText.setText("");
            mCurrentEditText = mNumberOneEditText;
        } else if (mCurrentEditText == mNumberThreeEditText) {
            mCurrentEditText.setText("");
            mCurrentEditText = mNumberTwoEditText;
        } else if (mCurrentEditText == mNumberFourEditText) {
            mCurrentEditText.setText("");
            mCurrentEditText = mNumberThreeEditText;
        }
    }


    /// API call

    private void doSecondaryUserLogin(String otp) {

        String userName = SharedPref.getSharedPreferences(getContext()).getUser();


        Map<String, String> map = new HashMap<>();
        map.put("username", userName);
        map.put("otp", otp);


        APICallBack.getSecondaryUserDetails(new APICallBack.ISecondaryUserInterface() {
            @Override
            public void onSecondaryUserDetails(SecondaryUserLoginResponse secondaryUserLoginResponse) {
                if (secondaryUserLoginResponse.getResultcode() == 1) {

                    SecondaryLoginShow(secondaryUserLoginResponse);

                } else if (secondaryUserLoginResponse.getResultcode() == 0) {
                    ConstantMethod.DialogShow(getContext(), secondaryUserLoginResponse.getMessage());
                }

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }
        }, getContext(), map);


    }

    private void SecondaryLoginShow(SecondaryUserLoginResponse secondaryUserLoginResponse) {

        mSlideUpLayout.setVisibility(View.VISIBLE);

        mSlideUpLayout.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.slide_up));
        // Save for Token and ReToken
        SharedPref.getSharedPreferences(getContext()).saveToken(secondaryUserLoginResponse.getData().getToken(), secondaryUserLoginResponse.getData().getRefresh_token());

        SharedPref.getSharedPreferences(getContext()).setPrimaryUser(secondaryUserLoginResponse.getData().getPrimary_users());

    }

    void loadTime() {
        String page = "<html lang=\"en\">\r\n<head>\r\n    <title>Signup Terms</title>\r\n    <meta charset=\"utf-8\">\r\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css\">\r\n    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js\"></script>\r\n    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js\"></script>\r\n    </style>\r\n</head>\r\n<body>\r\n\t<div class=\"container text-justify\">\r\n\t\t<h4 class=\"text-left\">FLIGHT SERVICES AGREEMENT FOR THE STELLARJET CLUB</h4>\r\n\t\t<br>\r\n\t\tThe terms and conditions set forth below, together with the applicable provisions of the Membership Agreement, constitute the agreement (the \"Agreement\") between Stellarjet Private Limited, a company incorporated under the provisions of the Companies Act, 2013, bearing corporate identification number U63030KA2017PTC107346 and having its registered office at No. 1311, 13th Floor, Brigade Towers, 135 Brigade Road, Bangalore 560025 and Member for participation in the Club (as such terms are defined below). All currency references herein shall be in INR - Indian Rupees. Except as otherwise defined herein, capitalized terms used herein shall have the respective meanings set forth in Schedule A hereto.\r\n\t\t<br><br>\r\n\t\t<b>1. Membership Club.</b> For as long as Member maintains its account in good standing, Member will have the right to participate in the Club pursuant to the terms and conditions set forth herein. Member must designate to Stellarjet, in writing, any other person who is authorized to use Member's Membership Account to make flight requests on behalf of Member or receive any information about Member or its account. The Member shall request to enroll and obtain the services provided by Stellarjet, in the form and manner provided under Annexure A hereto.\r\n\t\t<br><br>\r\n\t\t<b>2. Agency Appointment;</b> Disclosures. Member hereby appoints Stellarjet as its authorized agent, in its sole capacity as Member's agent to (a) arrange at the request of Member the provision of services under the Club, including, without limitation, to arrange on demand air transportation services with Operator on behalf of Member and Member's guests, as the case may be, and (b) take all actions necessary to coordinate such services on behalf of Member. Member acknowledges and agrees that such 'On Demand Air Transportation Contract' entered into on Member's behalf shall bind Member and be subject to terms and conditions not materially inconsistent with those stated herein.\r\n\t\t<br><br>\r\n\t\tAll flights under the Club will be operated by Operators that are 'Non-Scheduled Operators' registered air carriers that meet all applicable regulatory, operational and safety standards. Operators will exercise complete, effective and sustainable operational control over each flight at all times. Stellarjet is not affiliated with or commonly owned by any Operator providing flights for Stellarjet Members. \r\n\t\t<br><br>\r\n\t\t<b>3. Membership Account.</b> Upon becoming a member in the Club, Stellarjet shall create a Membership Account in the name of Member, as per the details provided by the Member. Stellarjet will charge all flight costs and any additional charges for related services incurred by Member to Member's Membership Account. \r\n\t\t<br><br>\r\n\t\t<b>4. Travel Booking.</b> To make a booking request for a sector travel, Member may call or email Stellarjet's dedicated Member Services Representatives at 1-800-425-77777 or members@stellarjet.com or make such booking request online through the Stellarjet App or Stellarjet Website. Stellarjet reserves the right to not accept any booking request from Member if it has an unpaid balance in its Membership Account.\r\n\t\t<br><br>\r\n\t\t<b>5. Passenger.</b> In the event any passenger is less than 12 years of age, appropriate and / or documentation may be required. Members may ask for these from Member Service Representatives.  All Passengers shall provide valid government issued picture identification to the security and /or flight crew prior to flight. Stellarjet shall in no manner whatsoever be liable for any misrepresentation of any government identification cards/documents by the Members and/or its friends, family, business associates, employees, friends and any such person authorized by Member to avail the said services and the same shall be solely attributable to the Member. The Member shall at all times keep Stellarjet indemnified, in this regard.\r\n\t\t<br><br>\r\n\t\t<b>6. Pricing per seat.</b>\r\n\t\t<br>\r\n\t\t\ta) <u>Pricing of Sectors.</u> Rates and fees with respect to sector include the following-<br>\r\n\t\t\t<ul>\r\n\t\t\t\t<li>Cost of the seat for the sector.</li>\r\n\t\t\t\t<li>Taxes applicable for the seat cost.</li>\r\n\t\t\t\t<li>Catering</li>\r\n\t\t\t\t<li>All charges that may be applicable for the flight such as 'Route Navigation Charges', 'Landing Charges', 'Parking Charges', 'Terminal Charges', 'Airport Charges', 'Ground Handling Charges',  and any other charges that may be applicable to the aircraft at the departure and arrival airport.</li>\r\n\t\t\t</ul>\r\n\t\t\tb) <u>Availability.</u> Aircraft shall be made available as per proposed schedule and timings as provided to members in advance on the website. Members can see updated information through the Stellarjet App or the Stellarjet Website or call Stellarjet offices for any queries and information. Stellarjet operations information for every flight shall be posted on all communication. If there is a delay in departure the same will also be informed to all Members.\r\n\t\t\t<br><br>\r\n\t\t\tc) <u>Replacement Aircraft.</u> In the event of the non-availability of an aircraft scheduled for the proposed departure date and time due to any technical or safety issues, Stellarjet shall endeavor to provide Members with an alternate aircraft capable of fulfilling the mission subject to availability. There will be no charges for the aircraft even if it is better in capability and performance. In case there is no alternate aircraft available Stellarjet will endeavor to make arrangements on a earliest commercial flight on a business class seat if avaailable subject to customer acceptance. \r\n\t\t\t<br><br>\r\n\t\t\td) <u>Additional Aircraft.</u> With respect to aircraft that are not owned, leased or otherwise controlled by Stellarjet but are available through the Club for flights by Members, certain alternate or additional terms and conditions may apply. Members will always be notified when booking a flight on an aircraft for which different terms would apply. The Members will be provided with such terms and may be asked to sign a document evidencing agreement thereto.\r\n\t\t\t<br><br>\r\n\t\t\te) <u>Stellarjet Flight Desk.</u> Member may request access to off-fleet chartered aircraft services through the 'Stellarjet Flight Desk', which in all cases shall be subject to Section 6(d) above. Prior to taking any charter flight, Member shall be required to agree to the 'Stellarjet Charter Service Agreement', a copy of which can be obtained from the Stellarjet Flight Desk. To make a booking request for a chartered aircraft, Member may call or email the Stellarjet Flight Desk 7 Days a week, 365 Days a year at 91 802 227 8613 or ops@stellarjet.com or make such booking request online through the Stellarjet App or Stellarjet Website.\r\n\t\t<br><br>\r\n\t\t<b>7. Departure and Landing Slots.</b> All itineraries that require departure and landing slots mandated by the Airports Authority of India (AAI) are subject to availability. Customer understands and accepts that slot availability, weather, Air Traffic Control directives or other factors not within its control.\r\n\t\t\t<br><br>\r\n\t\t\t(a) <u>Flight Confirmation.</u> Member request are considered confirmed when all of the following has occurred: (a) Member requests a reservation with the Member Services Representative either electronically or by telephone and such request is confirmed as received; (b) Stellarjet, as the agent for Member, confirms this flight request (c) a flight confirmation is issued and sent to Member by Stellarjet. Once confirmed, the cancellation provisions and applicable charges specified in Section 9(b) below will apply. Hard copies of itineraries confirmed by e-mail can be faxed to Member upon request. Stellarjet reserves the right to move a scheduled flight to a time that is 60 minutes earlier or 60 minutes later than the originally scheduled Flight Time by notifying Member no later than the Day prior to the scheduled flight due to operational reasons.\r\n\t\t\t<br><br>\r\n\t\t\t(b) <u>Cancellation or Changes.</u> Stellarjet reserves the right to charge a customer 100% of the sector cost purchased by the member for no show. There is no charge if a cancellation occurs or a change is made to a confirmed itinerary without giving notice at least 12 hours prior to departure time for flights originating and terminating of a sector.\r\n\t\t\t<br><br>\r\n\t\t\t(c) <u>Invoicing and Payments.</u> All sector seats purchased have to be paid in advance. Members shall receive tax paid Invoice for the purchase.\r\n\t\t\t<br><br>\r\n\t\t<b>8. Additional Services and Charges.</b>\r\n\t\t<br>\r\n\t\t\tAircraft Charter Services: Member may request charter the full aircraft for a particular sector or may request for charter of the same aircraft for a specific sector. In such cases Stellarjet may provide an aircraft to the member suitable for the mission from other operators. A service charge of 15% will be added to the charter Invoice. Member agrees that this service charge is acceptable as a charge over all the third party invoices.\r\n\t\t<br>\r\n\t\tUse of Stellarjet aircraft for Charter: If the member wishes to proceed to another sector after the schedule as provided, Stellarjet will provide the service for the additional sector as charter without any service charge. Charter rates will apply for the non-scheduled sector and additional charges may apply as per charter standards. All charters are subject to aircraft availability.  \r\n\t\t<br><br>\r\n\t\t<b>9. Catering.</b> Stellarjet will have standard On-Board Provisions on flights. Member may request Catering \r\n\t\t\tfrom the Member Services Representative from the menu, which will be provided from time to time.\r\n\t\t<br><br>\r\n\t\t<b>10. No Smoking.</b>  All flights are operated as non-smoking flights and any breach of this provision will be considered to be an offence and adequate and appropriate penal charges will be applicable in addition to the extra fees for cleaning and downtime may be charged for any breach of this policy.\r\n\t\t<br><br>\r\n\t\t<b>11. Identification and Travel Documentation.</b> In accordance with the Civil Aviation Rules and other Governmental regulations Member and Member's authorized guests are required to comply with all regulations, and will be required to present valid government issued photo identification prior to departure for all flights. Operator has the right to refuse boarding to any person that does not have the required documentation or identification, or if such person is not in compliance with BCAS (Bureau of Civil Aviation Security) or other government regulations without recourse or further obligation to Stellarjet.\r\n\t\t<br><br>\r\n\t\t<b>12. Termination.</b> Member may terminate its participation in the Club, at any time, with or without cause, and without penalty (except as otherwise provided herein) by the delivery of prior written notice of 30 (thirty) days to Stellarjet. Notwithstanding anything contained herein, Stellarjet reserves the right to terminate Member's participation in the Club at any time for an act or actions that are materially harmful to the Club, its members or its assets as determined by Stellarjet (\"Cause\") by delivery of written notice to Member of such termination for Cause and the reasons related thereto. No refund shall be made to Member for any Initiation Fees (as defined in the Membership Agreement), Annual Dues (as defined in the Membership Agreement) upon termination, for any reason whatsoever and as set forth herein. Additionally, Member shall remain liable to Stellarjet for any amounts due and owing to Stellarjet at the termination of Member's participation in the Club pursuant to this Agreement.\r\n\t\t<br><br>\r\n\t\t<b>13. Security.</b> Members agrees to cooperate with Stellarjet / Operator in fulfilling any requirements pertaining to security. Stellarjet / Operator will have no liability for any inconvenience, delay, loss, or damage in connection therewith.\r\n\t\t<br><br>\r\n\t\t<b>14. Insurance.</b> All aircrafts are fully insured as per Indian laws for aircraft operations. Member may request for Aircraft Insurance copy. Member agrees and acknowledges that the aircraft Operator shall maintain insurance coverage and in no event shall Stellarjet be liable for any claims arising herein.\r\n\t\t<br><br>\r\n\t\t<b>15. Liability.</b> (a) Stellarjet shall not have nor assume any responsibility or liability to Member for activities performed by Operator; (b) Operator shall be solely responsible for all claims arising out of any and all occurrences, accidents or incidents that occur on or in connection with the aircraft operated by Operator, including, without limitation, all personal injuries, property damage or wrongful death;  (c) Stellarjet is not responsible for any negligent act or omission by Operator or its personnel and is not responsible for any personal injury, property damage, accident, delay, inconvenience, or change in itinerary that may occur for the benefit of Member; and (d) Stellarjet shall not be liable under any contract, negligence, strict liability or other legal or equitable theory for any (i) consequential, indirect, incidental, special, punitive, lost profits, exemplary or reliance damages; (ii) amounts in excess of the price paid for a particular flight; or (iii) matter beyond its reasonable control. Notwithstanding the foregoing, in no event shall Member pursue Stellarjet for any amount in excess of actual Initiation Fees (as defined in the Membership Agreement) and Stellarjet's liability, if any, shall be strictly limited to an amount equivalent to such Initiation Fees actually paid by Member.\r\n\t\t The Member shall indemnify and keep indemnified and hold Stellarjet harmless from and against any loss, claim, damage, costs, penalties, interest thereon or expenses of any kind, including reasonable attorney's fees, incurred/sustained or caused to be incurred/sustained by Stellarjet on account of: (i) any breach of the terms and conditions provided under this Agreement by the Member and/or its guests; (ii) failure to perform any obligations under this Agreement by the Member and/or its guests; (iii) misrepresentation of any facts/details by the Member and/or its guests; (iv) infringement of the intellectual property owned by Stellarjet either by the Member and/or its guests and/or (v) violation/non-compliance with applicable laws/rules/regulations by the Member and/or its guests.\r\n\t\t<br><br>\r\n\t\t<b>16. Disclaimer of Responsibility for Delay / Cancellation, Other.</b> Stellarjet shall not be liable for any delay or failure by it or Operator to perform in connection with any flight, service or in the performance of any obligation hereunder, including any such delay or failure is due to or in any manner caused by acts of God, rebellion, riots, hijacking, insurrection, civil commotion, strikes or labor disputes, fires, floods, laws, regulations, acts, demands or orders of any government or agency, seizure of the aircraft under legal process, adverse weather conditions, inability to obtain fuel, aircraft damage or loss, lack of essential parts or supplies, mechanical problems, illness or incapacitation of crew members, denial of operating or landing approvals, clearances or permits by governmental authority, or any other cause which is beyond the control of Stellarjet or Operator.  Stellarjet is hereby released from any claim or demand for any direct or consequential damages arising out of its failure or that of Operator to perform as a result of a force majeure event whether or not herein enumerated or other event referenced above. Stellarjet will use commercially reasonable efforts to make alternate travel arrangements should any of the above occur and cause a delay.\r\n\t\t<br><br>\r\n\t\t<b>17. No warranties.</b> Stellarjet represents that it is a limited liability company organized under the laws of India and that it has the corporate authority to enter into this Agreement. The Member agrees and acknowledges that Stellarjet does not own or operate any aircraft and the same is provided and administered by the Operator or the respective third parties. The Member acknowledges that the role of Stellarjet is solely limited to providing the Member with access to requesting/booking aircrafts and/or flights and providing other benefits available for Members. STELLARJET MAKES NO OTHER REPRESENTATIONS OR WARRANTIES OF ANY KIND AS TO ANY MATTER ARISING OUT OF THIS AGREEMENT OR THE SERVICES PROVIDED TO MEMBER OR ANY GUEST OF MEMBER; STELLARJET HEREBY DISCLAIMS ALL OTHER WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION IMPLIED WARRANTIES OF MERCHANT ABILITY, FITNESS FOR PARTICULAR PURPOSE OR ARISING OUT OF COURSE OF DEALING, COURSE OF PERFORMANCE OR USAGE IN TRADE. MEMBER ACKNOWLEDGES AND AGREES THAT THE ENTIRE RISK ARISING OUT OF THEIR USE OF THE SERVICE PROVIDED HEREUNDER (INCLUDING ANY USE OF SOFTWARE, INCLUDING THE STELLARJET APP AND STELLARJET WEBSITE), AND ANY THIRD PARTY SERVICES OR PRODUCTS REMAINS SOLELY WITH THEM TO THE MAXIMUM EXTENT PERMITTED BY LAW.\r\n\t\t<br><br>\r\n\t\t<b>20. Electronic Signatures.</b> Member and Stellarjet agree that: (a) receipt of information electronically that the recipient reasonably believes to be authorized by the transmitting party shall constitute the valid signature on behalf of the transmitting party (it being agreed that transmission from an email address identified by Member as an authorized email address of Member shall be reasonable to accept); (b) such electronic transmissions shall be deemed to satisfy any federal, state or local laws or regulations  requiring that agreements be in writing; (c) neither party shall contest the validity or enforceability of any such electronic transmission; and (d) computer maintained records when produced in hard copy form shall constitute business records and shall have the same  validity  as  any  other  generally  recognized  business records.\r\n\t\t<br><br>\r\n\t\t<b>21. Privacy of Member Data.</b> Stellarjet takes all appropriate measures to maintain data regarding its Members and their guests as confidential. Stellarjet shall use and protect the information received by the Members in accordance with the Information Technology Act, 2000 and Information Technology (Reasonable Security Practices and Procedures and Sensitive Personal Data or Information) Rules, 2011. The Member may refer to the privacy policy of Stellarjet displayed on Stellarjet's Website, which lays down the manner in which the Member's data would be processed, used and stored by Stellarjet.  All flights flown hereunder will require disclosure of the name of all persons on a flight to the Operator performing the flight. Additionally, Stellarjet may be required to furnish Member and Member's guests' data, such as name and date of birth, or passport information, to comply with national and international security requirements or governing bodies. It may also be necessary for Stellarjet to provide names of persons on a flight to third parties providing services related to a flight such as ground transportation at Member's request. Stellarjet does not sell Member data to any third party. In no manner whatsoever shall Stellarjet be liable for any incorrect data/information provided to Stellarjet by its Members and/or its guests.\r\n\t\t<br><br>\r\n\t\t<b>22. Reselling Stellarjet Services.</b> Member shall not resell any services, offers or benefits provided by Stellarjet, or those of its partners. Member may not act as an agent, use, copy and reproduce Stellarjet logo, trademarks or service marks, or those of its partners, in any manner whatsoever. In this regard, the Member shall be solely liable to indemnify Stellarjet for any breach committed by it\r\n\t\t<br><br>\r\n\t\t<b>23. Transportation of Hazardous Materials.</b> Member acknowledges that applicable laws in India including but not limited to the rules and regulations laid down by the Directorate General of Civil Aviation (DGCA) forbids the carriage of hazardous materials aboard aircraft.  Hazardous materials include explosives, compressed gases including oxygen, flammable/combustible liquids and solids, oxidizers and organic peroxides, poisons, radioactive material, corrosive material, magnetized material, medical/scientific pharmaceuticals with potential infectious agent, undeclared firearms, undeclared   ammunition, undeclared dry ice, undeclared lithium batteries, undeclared self-defense spray, undeclared   CO2 cartridges, undeclared mercury and undeclared large voltage batteries or spillable batteries. \r\n\t\t<br><br>\r\n\t\t<b>24. Terms Subject to Change.</b> All terms and conditions contained herein are subject to change upon 15 Days' notice from Stellarjet to Member. Changes to the terms and conditions and any additional services will be posted to Member's section of the Stellarjet Website.\r\n\t\t<br><br>\r\n\t\t<b>25. Member Representations.</b> Member expressly represents and warrants that (i) it shall only use the \r\n\t\t<br><br>\r\n\t\t26. Services and Software provided in connection with the Club in accordance with applicable law and the terms of this Agreement, (ii) it is at least 18 years old, (iii) it has the right, authority and capacity to enter into this Agreement and to abide by the terms and conditions of this Agreement and (iv) participation in using the services provided hereunder is for Member's sole, personal use. Member may only access the services provided hereunder using authorized means.\r\n\t\t<br><br>\r\n\t\t<b>27. Intellectual Property.</b> Stellarjet alone (and its licensors, where applicable) shall own all right, title and interest, in all trademarks, tradenames, copyrights, patents, designs including all related intellectual property rights, in and to the services provided hereunder (including the Software that may become available to arrange flight services) and any suggestions, ideas, enhancement requests, feedback, recommendations or other information provided by Member or any other party relating to the services provided hereunder. This Agreement is not a sale and does not convey any rights of ownership in or related to the services provided hereunder or any intellectual property rights owned by Stellarjet. The Stellarjet trademarks tradenames, logo(s), and the names associated with the services provided hereunder are trademarks of Stellarjet or third parties, and no right or license is granted to use them. The Member shall in no manner whatsoever use, copy, reproduce, reverse engineer, decompile, disassemble etc., any of the intellectual property of Stellarjet. The Member shall be liable to indemnify Stellarjet for any breach of this clause by the Member and/or its guests.\r\n\t\t<br><br>\r\n\t\t<b>28. Dispute Resolution.</b> These terms and conditions and the provision of services by Stellarjet hereunder shall be governed by and construed in accordance with the laws of India and applicable laws of the State of Karnataka, without giving effect to conflict of law principles. Any dispute arising under these terms and conditions or the services provided by Stellarjet shall amicably settled by the Parties. However, in the event of failure to settle the disputes amicably, such disputes shall be finally settled by binding arbitration before a panel of one (1) arbitrator, mutually appointed by the Parties, in accordance with the Arbitration and Conciliation Act, 1996, as amended by the Arbitration and Conciliation (Amendment) Act, 2015 or any statutory amendment or re-enactment thereof for the time being in force. Judgment on the award may be entered in any court of competent jurisdiction. The location of arbitration shall be in Bangalore. No class arbitration shall be permissible. In the event Member fails to pay any sums due to Stellarjet hereunder at the time such sums are due to be paid, Stellarjet shall be entitled to recover all attorneys' fees and costs from Member related to or arising out of any efforts to collect such sums from Member, including any legal proceedings or arbitration that is commenced in order to collect such sums.\r\n\t\t<br><br>\r\n\t\t<b>29. Further Assurances.</b> Member hereby agrees to take such further actions as may be reasonably requested by Stellarjet in connection with the services to be provided hereunder for the performance of its obligations hereunder on behalf of Member.\r\n\t\t<br><br>\r\n\t\t<b>30. Non-Standard Flight Opportunities.</b> From time to time, there may be certain shared flight, scheduled shuttle service, empty leg, public charter or other non-standard flight opportunities available to Member through the Stellarjet App, the Stellarjet Website or otherwise. The terms and conditions with respect to Member's participation in these flights may differ in certain respects from, and in some cases override, the terms set forth herein. Member shall be required to agree to the terms and conditions applicable to such flight(s) prior to participating in any such non-standard flight opportunity. There is no guarantee that any such flight opportunities shall become, or if provided, will remain, available to members of the Club, and, if available, there is no guarantee as to Member's opportunity to participate in such flights. Except as otherwise set forth herein, a Member participating in any non-standard flight opportunity shall not be entitled to maintain a booking in his or her account for more than (1) flight to the same destination (defined as city or region, on a case-by-case basis) within any twenty-four (24) hour period. For the purposes of this Section, a flight shall include travel on the Stellarjet fleet, through available charter services and participation in empty legs, scheduled shuttle service, shared flights, public charter and other flight opportunities that may become available to Member from time to time.\r\n\t\t<br><br>\r\n\t\t<b>31. Miscellaneous.</b> Club services can only be provided to Member and Member's invited guests. Guests are limited to two per member. If any provision of this Agreement is declared by an arbitrator or a court of competent jurisdiction to be invalid, illegal, or unenforceable, such provision shall be limited or eliminated to the minimum extent necessary so that this Agreement shall otherwise remain in full effect and enforceable. This Agreement together with the Membership Agreement, exhibits, schedules and attachments, and agreements referenced herein and incorporated herein by reference constitute the entire agreement between the parties concerning its subject matter and supersedes any prior or contemporaneous agreements, understandings or proposals. Paragraph headings are for convenience of reference only and shall not affect or be utilized in construing or interpreting these terms and conditions. No provision of, right, power or privilege under this Agreement shall be deemed to have been waived by any act, delay, omission or acquiescence on the part of any party, its agents or employees, but only by an instrument in writing signed by an authorized representative of each party. No waiver by any party of any breach or default of any provision of this Agreement by the other party shall be effective as to any other breach or default. The relationship between the Parties is that of an independent contractor and nothing herein shall be construed to make a party a partner, an agent or legal representative of the other Party for any purpose.\r\n\t\t<br><br>\r\n\t\t<b>32. Notices.</b> Stellarjet may give notice by means of a general notice through the Software, electronic mail to Member's email address on record in Stellarjet's account information, by posting such notice to the Stellarjet Website or Stellarjet App, or by written communication sent by mail or pre-paid post to Member's address on record in Stellarjet's account information. Such notice shall be deemed to have been given upon the expiration of (i) 48 hours after mailing (if sent by mail or prepaid post) or posting to the Stellarjet Website or Stellarjet App, or (ii) 12 hours after sending electronically if sent by email. Member may give notice to Stellarjet (such notice shall be deemed given when received by Stellarjet) at any time by any of the following: letter sent by confirmed email to Stellarjet at the following email address notices@stellarjet.com; letter delivered by nationally recognized delivery service or registered postage prepaid mail to Stellarjet at the following address: Stellarjet, 1311 Brigade Towers, 135 Brigade Road, Bangalore 560025. India addressed to the attention of: Legal.\r\n\t\t<br><br>\r\n\t\t<h4>Schedule A: Definitions</h4>\r\n\t\t<br>\r\n\t\t\"Flight Time\" means the time that Member is on the aircraft from the takeoff time to the landing time \r\n\t\t<br><br>\r\n\t\t\"Member\" means the individual identified in the Membership Agreement and on the signature page hereto. \r\n\t\t<br><br>\r\n\t\t \"Additional Members\" means additional persons named by the Subscriber Member who are authorized by him to avail reservation facilities on Member's account.\r\n\t\t<br><br>\r\n\t\t\"Invoice\" means any billing document sent to Member by Stellarjet identifying additional costs and related charges incurred- if any- by Member for a particular flight or other service.\r\n\t\t<br><br>\r\n\t\t\"Catering\" means food and beverages offered for the flight to the Member as requested or standard On-Board Provisions provided if no request have been made.\r\n\t\t<br><br>\r\n\t\t\"Club\" means the Stellarjet Membership Club, including the  benefits  referred  to  in  the  Membership Agreement and Section 15 hereof.\r\n\t\t<br><br>\r\n\t\t\"India\" means all the states of the Republic of India.\r\n\t\t<br><br>\r\n\t\t\"Day\" means a twenty-four-hour calendar day. \r\n\t\t<br><br>\r\n\t\t\"DGCA\" means the Director General of Civil Aviation. \r\n\t\t<br><br>\r\n\t\t\"BCAS\" means Bureau of Civil Aviation Security. \r\n\t\t<br><br>\r\n\t\t\"GST\" means the Goods and Services Tax\r\n\t\t<br><br>\r\n\t\t\"Membership Account\" means the account created in the name of Member in accordance to this Agreement.\r\n\t\t<br><br>\r\n\t\t\"Membership Agreement\" means the Membership Agreement that accompanies these terms and conditions at the time Member initially become a member of the Club.\r\n\t\t<br><br>\r\n\t\t\"Member   Services   Representatives\" means the personnel employed by Stellarjet to handle Member's flight requests and certain other member activities such as ground transportation through telephone, Stellarjet Website, Stellarjet App or email communications.\r\n\t\t<br><br>\r\n\t\t\"On Board Provisions\" means select non-alcoholic beverages; and a variety of snacks that come standard on all Member flights at no additional charge. On Board Provisions may vary by flight, location and geography.\r\n\t\t<br><br>\r\n\t\t\"Charter Contract\" means the agreement executed by Stellarjet as agent for Member with Operator for each flight, which shall include the Standard Terms of Flight for such Operator, for the provision of any particular flight for on demand air transportation, as requested by Member. \r\n\t\t<br><br>\r\n\t\t\"Operating Sector\" means each aircraft Operating Sector offered by and such may be amended from time to time. Stellarjet may add or delete operating sectors and shall notify Member for any changes.\r\n\t\t<br><br>\r\n\t\t\"Operator\" means any entity that provides or offers to provide air transportation to Member and who has control over the operational functions performed in providing such air transportation and acts as the air carrier (as defined in Civil Aviation regulations), as may be utilized or designated with prior notice by Stellarjet on behalf of Member from time to time.\r\n\t\t<br><br>\t\t \r\n\t\t\"Service Charge\" means 15% ( fifteen percent) charge made on the total amount of the invoice received from a third party on behalf of the members' request.\r\n\t\t<br><br>\r\n\t\t\"Seat Cost\" means charge made for the cost of travel for the sector by the Charterer per seat. \r\n\t\t<br><br>\r\n\t\t\"Software\" means the software used for the Stellarjet App as well as the software used on the Stellarjet Website, as may be modified and updated from time to time.\r\n\t\t<br><br>\r\n\t\t\"Standard Terms of Flight\" means those standard terms and conditions of Operator that will apply to all transportation contracts entered into by Stellarjet in its capacity as agent for Member with such Operator.\r\n\t\t<br><br>\r\n\t\t\"Stellarjet Pvt. Ltd.\" means a Pvt. Ltd. Company. The Club is a program of Stellarjet Pvt. Ltd. \r\n\t\t<br><br>\r\n\t\t\"Stellarjet App\" means the Stellarjet proprietary mobile software technology that is available for Member to request flights and conduct other member activities.\r\n\t\t<br><br>\r\n\t\t\"Stellarjet Website\" means the Stellarjet proprietary website located at members.stellarjet.com that is available for Member to request flights and conduct other member activities.\r\n\t\t<br><br>\r\n\t</div>\r\n</body>\r\n</html>";

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
