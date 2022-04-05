package com.ns.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ns.adapter.adapter_holder.PassengerListMyHolder;
import com.ns.database.SharedPref;
import com.ns.model.LoginResponse.ContactsBean;
import com.ns.model.PasengerInfoPojo;
import com.ns.stellarjet.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PassengerListAdapter extends RecyclerView.Adapter<PassengerListMyHolder> {

    private static final String TAG = "PassengerListAdapter";

    private Context mContext;
    private boolean changeViewForSelfWithGuest = true;
    private boolean checkfilleddata = true;

    private boolean checkMeAndGuest = true;

    private List<String> mGuestNameList = new ArrayList<>();
    private List<ContactsBean> mContactsGuestList;
    private List<PasengerInfoPojo> mPassengerInfoList;


    private Object mAutoName;
    private int guestPosition;
    private boolean mCheckNamePhone = true;


    public PassengerListAdapter(Context context, List<PasengerInfoPojo> seatNameList) {
        mContext = context;
        this.mPassengerInfoList = seatNameList;
    }

    @NonNull
    @Override
    public PassengerListMyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PassengerListMyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_passenger_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PassengerListMyHolder holder, int position) {


        holder.nameAutoCompleteTextView.setTag(position);
        holder.phoneEdt.setTag(position);

        PasengerInfoPojo pojo = mPassengerInfoList.get(position);


        if (changeViewForSelfWithGuest == true) {

            Log.d(TAG, "onBindViewHolder: " + mPassengerInfoList);
            if (position == 0) {
                String seatName = mPassengerInfoList.get(position).getSeatName();
                holder.nameTitle.setText("Me - " + seatName);
                holder.nameAutoCompleteTextView.setText(SharedPref.getSharedPreferences(mContext).getUserName());
                holder.nameAutoCompleteTextView.setEnabled(false);
                holder.phoneEdt.setText(SharedPref.getSharedPreferences(mContext).getUserPhone());
                holder.phoneEdt.setEnabled(false);


                PassengerSetPositionInfo(holder, position, seatName, true);
            } else {

                CheckSelfGuestData(holder, position);
            }
        } else if (changeViewForSelfWithGuest == false) {
            Log.d(TAG, "onBindViewHolder: " + mPassengerInfoList);
            CheckSelfGuestData(holder, position);
        }


        ArrayAdapter adapters = new
                ArrayAdapter(mContext, android.R.layout.simple_list_item_1, mGuestNameList);

        holder.nameAutoCompleteTextView.setAdapter(adapters);
        holder.nameAutoCompleteTextView.setThreshold(1);

        holder.nameAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                mCheckNamePhone = true;

                Log.d(TAG, "onItemClick: ");

                mAutoName = adapterView.getItemAtPosition(i);

                guestPosition = mGuestNameList.indexOf(mAutoName);
                String guestPhoneNumber = mContactsGuestList.get(guestPosition).getPhone();

                holder.phoneEdt.setText(guestPhoneNumber);

                int tagPos = (int) holder.nameAutoCompleteTextView.getTag();
                Log.d(TAG, "afterTextChanged: " + tagPos);
                PasengerInfoPojo infoPojo = new PasengerInfoPojo();


                infoPojo.setPos(tagPos);
                infoPojo.setName(mAutoName.toString());
                infoPojo.setPhone(holder.phoneEdt.getText().toString());
                infoPojo.setSeatName(mPassengerInfoList.get(tagPos).getSeatName());
                infoPojo.setSeatId(mPassengerInfoList.get(tagPos).getSeatId());
                infoPojo.setCheckStatus(true);
                infoPojo.setGuestId(mContactsGuestList.get(guestPosition).getId());
                mPassengerInfoList.set(tagPos, infoPojo);


            }
        });


        holder.nameAutoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                mCheckNamePhone = false;

                mAutoName = s.toString();


            }
        });


        holder.phoneEdt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                Log.d(TAG, "onEditorAction: " + textView);


                int tagPos = (int) holder.nameAutoCompleteTextView.getTag();
                Log.d(TAG, "afterTextChanged: " + tagPos);
                PasengerInfoPojo infoPojo = new PasengerInfoPojo();

                if (mCheckNamePhone ==  false) {

                    infoPojo.setPos(tagPos);
                    infoPojo.setName(mAutoName.toString());
                    infoPojo.setPhone(holder.phoneEdt.getText().toString());
                    infoPojo.setSeatName(mPassengerInfoList.get(tagPos).getSeatName());
                    infoPojo.setSeatId(mPassengerInfoList.get(tagPos).getSeatId());
                    infoPojo.setGuestId(0);
                    infoPojo.setCheckStatus(false);
                    mPassengerInfoList.set(tagPos, infoPojo);
                }

                return false;
            }
        });


    }

    private void CheckSelfGuestData(@NonNull PassengerListMyHolder holder, int position) {
        holder.nameAutoCompleteTextView.setEnabled(true);
        holder.phoneEdt.setEnabled(true);
        if (checkfilleddata) {
            SelfWithGuestView(holder, position);
        } else {
            SelfWithGuestViewFilledData(holder, position);
        }
    }

    private void SelfWithGuestViewFilledData(PassengerListMyHolder holder, int position) {
        int pos = position;
        pos++;
        holder.nameTitle.setText("Passenger Name (" + pos + ") - " + mPassengerInfoList.get(position).getSeatName());
        holder.nameAutoCompleteTextView.setText(mPassengerInfoList.get(position).getName());
        holder.phoneEdt.setText(mPassengerInfoList.get(position).getPhone());
    }

    private void SelfWithGuestView(@NonNull PassengerListMyHolder holder, int position) {

        int pos = position;
        pos++;
        String seatName = mPassengerInfoList.get(position).getSeatName();
        holder.nameTitle.setText("Passenger Name (" + pos + ") - " + seatName);
        holder.nameAutoCompleteTextView.setText("");
        holder.phoneEdt.setText("");

        PassengerSetPositionInfo(holder, position, seatName, false);
    }

    private void PassengerSetPositionInfo(@NonNull PassengerListMyHolder holder, int position, String seatName, boolean b) {

        PasengerInfoPojo infoPojo = new PasengerInfoPojo();

        if (b) {
            infoPojo.setCheckStatus(true);
        } else {
            infoPojo.setCheckStatus(mPassengerInfoList.get(position).isCheckStatus());
        }
        infoPojo.setPos(position);
        infoPojo.setName(holder.nameAutoCompleteTextView.getText().toString());
        infoPojo.setPhone(holder.phoneEdt.getText().toString());
        infoPojo.setSeatName(seatName);
        infoPojo.setSeatId(mPassengerInfoList.get(position).getSeatId());

        mPassengerInfoList.set(position, infoPojo);

    }

    @Override
    public int getItemCount() {
        return mPassengerInfoList.size();
    }

    public void TCClick() {
        checkfilleddata = false;
        notifyDataSetChanged();
        Log.d(TAG, "TCClick: " + mPassengerInfoList.size());
        iPersionInfoDetailsInterface.onPersionList(mPassengerInfoList, checkMeAndGuest);

    }

    public void setSelfView() {
        checkMeAndGuest = true;
        changeViewForSelfWithGuest = true;
        checkfilleddata = false;


        notifyDataSetChanged();
    }

    public void setSelfWithGuestView() {
        checkMeAndGuest = false;
        changeViewForSelfWithGuest = false;
        checkfilleddata = false;

        Log.d(TAG, "setSelfWithGuestView: " + mPassengerInfoList);
        PasengerInfoPojo pojo;
        for (int i = 0; i < mPassengerInfoList.size(); i++) {


            pojo = new PasengerInfoPojo();
            pojo.setPos(i);

            if (i == 0) {
                pojo.setName("");
                pojo.setPhone("");
            } else {
                pojo.setName(mPassengerInfoList.get(i).getName());
                pojo.setPhone(mPassengerInfoList.get(i).getPhone());
            }


            pojo.setSeatName(mPassengerInfoList.get(i).getSeatName());
            pojo.setGuestId(mPassengerInfoList.get(i).getGuestId());
            pojo.setSeatId(mPassengerInfoList.get(i).getSeatId());
            pojo.setCheckStatus(mPassengerInfoList.get(i).isCheckStatus());
            mPassengerInfoList.set(i, pojo);
        }

        notifyDataSetChanged();
    }

    public void setGuestDetailsList(List<ContactsBean> contactsGuestList) {
        mContactsGuestList = contactsGuestList;
        for (ContactsBean bean : contactsGuestList) {
            mGuestNameList.add(bean.getName());
        }
        notifyDataSetChanged();
    }


    // Interface create Passenger list information pass for fragment
    public PersionInfoDetailsInterface iPersionInfoDetailsInterface;

    public PersionInfoDetailsInterface setPersionInfoList(PersionInfoDetailsInterface persionInfoDetailsInterface) {
        return iPersionInfoDetailsInterface = persionInfoDetailsInterface;
    }

    public boolean checkAllDataFill() {
        boolean status = true;

        for (PasengerInfoPojo list: mPassengerInfoList) {
            if (list.getName().equals("") || list.getName().equals(null)) {
                status = false;
                break;
            }
        }

        return status;
    }

    public interface PersionInfoDetailsInterface {
        void onPersionList(List<PasengerInfoPojo> passengerInfoList, boolean tagMeAndGuest);
    }


}
