package com.ns.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ns.database.SharedPref;
import com.ns.model.CommonPojo;
import com.ns.model.ReUseModel.BookingListBean;
import com.ns.retrofit.APICallBack;
import com.ns.stellarjet.R;
import com.ns.utils.ConstantMethod;
import com.ns.utils.UiConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Response;


public class PickUpAndDropSetAddressFragment extends Fragment implements SaveAddressInfoFragment.SelectAddress {

    private static final String TAG = "PickUpAndDropSetAddress";


    private TextView pickupTxt;
    private TextView dropTxt;

    String picupStr = null;
    String dropStr = null;

    private String mPickUpLocation;
    private int mPickUpId = 0;
    private String mDropUpLocation;
    private int mDropId = 0;


    // Bundle data
    private BookingListBean mBookingListBean;
    private boolean keyStatus;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        keyStatus = this.getArguments().getBoolean("key");
        mBookingListBean = (BookingListBean) this.getArguments().getSerializable("bookingListBean");
        Log.d(TAG, "onCreate: " + mBookingListBean);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_picupdrop_setaddress, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        picupStr = mBookingListBean.getFrom_city_info().getName();
        dropStr = mBookingListBean.getTo_city_info().getName();

        ((TextView) view.findViewById(R.id.textView_cab_pickup_location)).setText("Pickup location (" + picupStr + ")");
        ((TextView) view.findViewById(R.id.textView_cab_drop_location)).setText("Drop location (" + dropStr + ")");


        pickupTxt = view.findViewById(R.id.editText_pick_location1);
        dropTxt = view.findViewById(R.id.editText_drop_location1);


        if (keyStatus) {
            pickupTxt.setText(mBookingListBean.getPick_address_main());
            mPickUpId = mBookingListBean.getPick_address_main_id();
            dropTxt.setText(mBookingListBean.getDrop_address_main());
            mDropId = mBookingListBean.getDrop_address_main_id();
        } else {
            pickupTxt.setText(mPickUpLocation);
            dropTxt.setText(mDropUpLocation);
        }


        SaveAddressInfoFragment saveAddressInfoFragment = new SaveAddressInfoFragment();
        saveAddressInfoFragment.onSelectAddress(this);


        /**
         * PickUp Location event
         */
        pickupTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putBoolean("Pin", true);
                bundle.putInt("CitiId", mBookingListBean.getFrom_city());
                bundle.putString("CitiName", mBookingListBean.getFrom_city_info().getName());
                saveAddressInfoFragment.setArguments(bundle);

                new Handler().postDelayed(() -> Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.framePane, saveAddressInfoFragment)
                        .addToBackStack(null)
                        .commit(), UiConstants.DELAYMILLISECOND);
            }
        });


        /**
         * DropUp Location event
         */
        dropTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putBoolean("Pin", false);
                bundle.putInt("CitiId", mBookingListBean.getTo_city());
                bundle.putString("CitiName", mBookingListBean.getTo_city_info().getName());
                saveAddressInfoFragment.setArguments(bundle);

                new Handler().postDelayed(() -> Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.framePane, saveAddressInfoFragment)
                        .addToBackStack(null)
                        .commit(), UiConstants.DELAYMILLISECOND);
            }
        });


        /**
         * Confirm event then api call
         */
        view.findViewById(R.id.button_cab_preferences_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ConstantMethod.isConnected(getContext())) {

                    Log.d(TAG, "onClick: " + mPickUpId + " " + mDropId);

                    if (mDropId == 0 && mPickUpId == 0) {
                        Log.d(TAG, "onClick: ");
                        ConstantMethod.ShowToastMessage(getActivity(), "Please select pick and drop address");
                    } else {
                        Log.d(TAG, "onClick: ");
                        ConfirmPersonalizeAPICall(mBookingListBean.getBooking_id(), mPickUpId, mDropId);
                    }

                } else {
                    ConstantMethod.ShowToastMessage(getContext(), UiConstants.NO_NETWORK);
                }

            }
        });

    }

    /**
     * Confirm Personal cab
     *
     * @param bookingid
     * @param pickaddress
     * @param dropaddress
     */
    private void ConfirmPersonalizeAPICall(int bookingid, int pickaddress, int dropaddress) {

        Log.d(TAG, "ConfirmPersonalizeAPICall: " + bookingid);

        APICallBack.getPersonalizeCab(new APICallBack.IResponseCommon() {
            @Override
            public void onResponseCommon(Response<CommonPojo> commonPojoResponse) {
                Log.d(TAG, "onPersonalizeCab: " + commonPojoResponse);

                int code = commonPojoResponse.code();
                switch (code) {
                    case 200:
                        Log.d(TAG, "onNext: " + commonPojoResponse);

                        SharedPref.getSharedPreferences(getContext()).setPickUpDropUpkDetails(1);

                        String picup = pickupTxt.getText().toString();
                        String dropup = dropTxt.getText().toString();
                        mBookingListBean.setPick_address_main(picup);
                        mBookingListBean.setPick_address_main_id(mPickUpId);

                        mBookingListBean.setDrop_address_main(dropup);
                        mBookingListBean.setDrop_address_main_id(mDropId);


                        ConstantMethod.DialogShowFragmentBackPress(getActivity(), commonPojoResponse.message());
                        /*ConstantMethod.DialogShowSingleAction(new ConstantMethod.DialogSingleAction() {
                            @Override
                            public void onAction(boolean status) {
                                if (status) {
                                    iPickUpDropUp.onEventPickUpDropUp(true);
                                }
                            }
                        }, getContext(),commonPojoResponse.message());*/

                        break;
                    case 400:
                        Log.d(TAG, "onNext: " + commonPojoResponse);
                        JSONObject mJsonObject;
                        try {
                            mJsonObject = new JSONObject(commonPojoResponse.errorBody().string());
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

            }
        }, getContext(), bookingid, pickaddress, dropaddress);

    }


    @Override
    public void onSelectAddress(String address, boolean status, int id) {
        keyStatus = false;
        if (status) {
            mPickUpLocation = address;
            mPickUpId = id;
        } else {
            mDropUpLocation = address;
            mDropId = id;
        }
    }


    public IPickUpDropUp iPickUpDropUp;

    public IPickUpDropUp onPickUpDropUps(IPickUpDropUp PickUpDropUp_) {
        return iPickUpDropUp = PickUpDropUp_;
    }

    public interface IPickUpDropUp {
        void onEventPickUpDropUp(boolean status);
    }


}
