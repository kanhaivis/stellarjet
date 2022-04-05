package com.ns.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ns.database.SharedPref;
import com.ns.model.LoginResponse.LoginInfo;
import com.ns.model.LoginResponse.UserDataBean;
import com.ns.model.SecondaryUser.PrimaryUsersBean;
import com.ns.retrofit.APICallBack;
import com.ns.stellarjet.R;
import com.ns.stellarjet.SplaceScreen;
import com.ns.utils.ConstantMethod;
import com.ns.utils.UiConstants;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";


    private static final int PERMISSION_CALLBACK_CONSTANT = 100;
    private static final int REQUEST_PERMISSION_SETTING = 101;
    String[] permissionsRequired = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.CALL_PHONE};


    private TextView mSeatCountTxt;
    private Button vConfirmBtn;

    private boolean againNotCallApi = false;

    private ArrayList<PrimaryUsersBean> mPrimaryUserList;

    /// Secondery user Ui
    private TextView mBookingForTxt;
    private TextView mSeconderyUserNameTxt;


    private String mUserType;

    private int mSeatCount = -1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        // UI
        initView();


        if (!ConstantMethod.isConnected(HomeActivity.this)) {
            ConstantMethod.ShowToastMessage(HomeActivity.this, UiConstants.NO_NETWORK);
        }

//        runtimeSetPermission();
        // Runtime Permission check then dialog show
        ActivityCompat.requestPermissions(HomeActivity.this, permissionsRequired, PERMISSION_CALLBACK_CONSTANT);


        // Confirm Button for Booking flight or Purchanse the seat...
        vConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ConstantMethod.isConnected(HomeActivity.this)) {

                    String mPrepaidTerm = SharedPref.getSharedPreferences(HomeActivity.this).getMembershipPreparedTerms();
                    if (mPrepaidTerm.equals("true")) {

                        if (mSeatCount == 0) {
                            Intent intent = new Intent(view.getContext(), PurchaseActivity.class);
                            startActivity(intent);
                        } else {
                            BookingFlightAction();
                        }

                    } else if (mPrepaidTerm.equals("False")) {
                        BookingFlightAction();
                    }


                } else {
                    ConstantMethod.ShowToastMessage(view.getContext(), UiConstants.NO_NETWORK);
                }


            }
        });


        // Drawer Button click event
        findViewById(R.id.button_drawer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ConstantMethod.isConnected(view.getContext())) {
                    Intent intent = new Intent(view.getContext(), DrawerActivity.class);
                    startActivity(intent);

                } else {
                    ConstantMethod.ShowToastMessage(view.getContext(), UiConstants.NO_NETWORK);
                }
            }
        });

        // Primary User List open activity
        mSeconderyUserNameTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent primaryUserIntent = new Intent(HomeActivity.this, PrimaryUserListActivity.class);
                primaryUserIntent.putExtra("primaryUserid", false);
                startActivity(primaryUserIntent);

            }
        });


    }

    /**
     * Action for BookFlightActivity open
     */
    public void BookingFlightAction() {
        Intent intent = new Intent(HomeActivity.this, BookFlightActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();

        String mUserName = "";

        // Check user type and every time api call for seat number update...
        mUserType = SharedPref.getSharedPreferences(this).getUserType();
        if (mUserType.equals("primary")) {
            // get user name and show hello ..
            mUserName = SharedPref.getSharedPreferences(this).getUserName();
            getPrimaryUser();

        } else if (mUserType.equals("secondary")) {

            mPrimaryUserList = SharedPref.getSharedPreferences(this).getPrimaryUser();

            int pos = SharedPref.getSharedPreferences(this).getSinglePrimaryPos();
            PrimaryUsersBean bean = mPrimaryUserList.get(pos);
            Log.d(TAG, "onCreate: " + mPrimaryUserList);

            mUserName = bean.getName();
            int mPrimaryUserId = bean.getId();
            int sizes = mPrimaryUserList.size();

            if (sizes > 1) {
                mSeconderyUserNameTxt.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_down), null);
            }

            mBookingForTxt.setText("Booking for");
            mSeconderyUserNameTxt.setText(mUserName);

            APICallPrimaryUserDetails(mPrimaryUserId);


        }


        // Check the statue for app open first time or second time...
        TextView mUserNameTxt = findViewById(R.id.textview_home_hello_again);
        int msgNumber = SharedPref.getSharedPreferences(HomeActivity.this).getHelloAndAgainMessage();
        if (msgNumber == 2) {
            mUserNameTxt.setText("Hello again\n" + mUserName + ", \nWelcome back");
        } else {
            mUserNameTxt.setText("Hello\n" + mUserName + ", \nWelcome");
        }

    }


    private void initView() {

        mSeatCountTxt = findViewById(R.id.textView_seat_limits);
        vConfirmBtn = findViewById(R.id.button_book_flight);
       // vConfirmBtn.setText("Book a Flight");

        mBookingForTxt = findViewById(R.id.textView_home_booking_for);
        mSeconderyUserNameTxt = findViewById(R.id.textView_home_booking_primary_user);
    }


    private void runtimeSetPermission() {

        if (ActivityCompat.checkSelfPermission(HomeActivity.this, permissionsRequired[0]) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(HomeActivity.this, permissionsRequired[1]) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(HomeActivity.this, permissionsRequired[2]) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(HomeActivity.this, permissionsRequired[3]) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(HomeActivity.this, permissionsRequired[0])
                    || ActivityCompat.shouldShowRequestPermissionRationale(HomeActivity.this, permissionsRequired[1])
                    || ActivityCompat.shouldShowRequestPermissionRationale(HomeActivity.this, permissionsRequired[2])
                    || ActivityCompat.shouldShowRequestPermissionRationale(HomeActivity.this, permissionsRequired[3])) {

                //Show Information about why you need the permission
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setTitle("Need Multiple Permissions");
                builder.setMessage("This app needs Camera and Location permissions.");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        ActivityCompat.requestPermissions(HomeActivity.this, permissionsRequired, PERMISSION_CALLBACK_CONSTANT);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            } else {
                //just request the permission
                ActivityCompat.requestPermissions(HomeActivity.this, permissionsRequired, PERMISSION_CALLBACK_CONSTANT);
            }

        } else {
            //You already have the permission, just go ahead.
            proceedAfterPermission();
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_CALLBACK_CONSTANT) {
            //check if all permissions are granted

            againNotCallApi = true;

            boolean allgranted = false;
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    allgranted = true;
                } else {
                    allgranted = false;
                    break;
                }
            }

            if (allgranted) {
                proceedAfterPermission();
            } else if (ActivityCompat.shouldShowRequestPermissionRationale(HomeActivity.this, permissionsRequired[0])
                    || ActivityCompat.shouldShowRequestPermissionRationale(HomeActivity.this, permissionsRequired[1])
                    || ActivityCompat.shouldShowRequestPermissionRationale(HomeActivity.this, permissionsRequired[2])
                    || ActivityCompat.shouldShowRequestPermissionRationale(HomeActivity.this, permissionsRequired[3])) {


                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setTitle("Need Multiple Permissions");
                builder.setMessage("This app needs Location permissions.");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        ActivityCompat.requestPermissions(HomeActivity.this, permissionsRequired, PERMISSION_CALLBACK_CONSTANT);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            } else {
//                Toast.makeText(getBaseContext(),"Unable to get Permission",Toast.LENGTH_LONG).show();
            }
        }
    }

    private void proceedAfterPermission() {
//        Toast.makeText(getBaseContext(), "We got All Permissions", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (ActivityCompat.checkSelfPermission(HomeActivity.this, permissionsRequired[0]) == PackageManager.PERMISSION_GRANTED) {
            //Got Permission
            proceedAfterPermission();
        }

    }


    public void getPrimaryUser() {
        if (ConstantMethod.isConnected(HomeActivity.this)) {
            APICallBack.getCustomerData(this, new APICallBack.ICustomerData() {
                @Override
                public void onCustomerData(LoginInfo loginInfo) {
                    if (loginInfo.getResultcode() == 1) {
                        ResultShow(loginInfo);
                    }
                }

                @Override
                public void onError(Throwable e) {
                    Log.e(TAG, "onError: " + e.getMessage());
                }
            });
        }
    }

    private void APICallPrimaryUserDetails(int sid) {

        APICallBack.getSwitchSecondaryUser(new APICallBack.ICustomerData() {
            @Override
            public void onCustomerData(LoginInfo loginInfo) {
                if (loginInfo.getResultcode() == 1) {

                    SharedPref.getSharedPreferences(HomeActivity.this).saveToken(loginInfo.getData().getToken(), loginInfo.getData().getRefresh_token());
                    SharedPref.getSharedPreferences(HomeActivity.this).saveSeatCount(loginInfo.getData().getUser_data().getCustomer_prefs().getSeats_available());
                    SharedPref.getSharedPreferences(HomeActivity.this).setUserData(loginInfo.getData().getUser_data().getName(), loginInfo.getData().getUser_data().getEmail(), loginInfo.getData().getUser_data().getPhone());

                    ResultShow(loginInfo);
                }

            }

            @Override
            public void onError(Throwable e) {

            }
        }, HomeActivity.this, sid);
    }

    /**
     * UI Update
     *
     * @param response
     */
    private void ResultShow(LoginInfo response) {

        UserDataBean userDataBean = response.getData().getUser_data();

        mSeatCount = userDataBean.getCustomer_prefs().getSeats_available();
        String mSeatCost = userDataBean.getCustomer_prefs().getMembership_details().getSeat_cost();
        SharedPref.getSharedPreferences(this).saveSeatCost(Integer.valueOf(mSeatCost));
        SharedPref.getSharedPreferences(this).saveCustomeHelpPhoneNumber(userDataBean.getCustomer_care_info().getPhone());
        SharedPref.getSharedPreferences(this).setMembershipDdetails(userDataBean.getCustomer_prefs().getMembership_details().getPrepaid_terms(), userDataBean.getCustomer_prefs().getMembership_details().getId());

        vConfirmBtn.setVisibility(View.VISIBLE);
        if (userDataBean.getCustomer_prefs().getMembership_details().getPrepaid_terms().equals("true")) {

            if (mSeatCount == 0) {
                vConfirmBtn.setText("Buy and book a flight");
            } else {
                vConfirmBtn.setText("Book a Flight");
            }

            mSeatCountTxt.setText("You still have " + mSeatCount + " seats available before you need to top up your credit.");

        } else if (userDataBean.getCustomer_prefs().getMembership_details().getPrepaid_terms().equals("False")) {

            vConfirmBtn.setText("Book a Flight");

        }

        String mUserName = userDataBean.getName();
        String mUserType = SharedPref.getSharedPreferences(this).getUserType();
        if (mUserType.equals("secondary")) {

            mSeconderyUserNameTxt.setText(mUserName);

        }

    }


}

