package com.ns.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ns.database.SharedPref;
import com.ns.stellarjet.R;
import com.ns.stellarjet.SplaceScreen;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class PinActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "PinActivity";

    private TextView mPassCodeForgotTxt;

    private EditText mOneEdt, mCurrentEditText;
    private EditText mTwoEdt;
    private EditText mThreeEdt;
    private EditText mFourEdt;


    private Button mOneBtn;
    private Button mTwoBtn;
    private Button mThreeBtn;
    private Button mFourBtn;
    private Button mFiveBtn;
    private Button mSixBtn;
    private Button mSevenBtn;
    private Button mEightBtn;
    private Button mNineBtn;
    private Button mZeroBtn;
    private Button mDeleteBtn;

    private String mFirstEditTextPassCode;
    private String mSecondEditTextPassCode;
    private String mThreeEditTextPassCode;
    private String mFourEditTextPassCode;

    private int mPassCodeEntryOne = -1;
    private int mAttemptsTried = 0;
    private TextView mHeadingTxt;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pin_activity);

        mPassCodeForgotTxt = findViewById(R.id.passcode_forgot_txt);
        mHeadingTxt = findViewById(R.id.textView_passcode_heading);

        TextView mTitle = findViewById(R.id.textView_passcode_title);
        int check = SharedPref.getSharedPreferences(this).getHelloAndAgainMessage();
        if (check == 2) {
            mTitle.setText("Hello again.");
            mPassCodeForgotTxt.setVisibility(View.VISIBLE);
        } else {
            mTitle.setText("Hello");
            mPassCodeForgotTxt.setVisibility(View.GONE);
        }

        mOneBtn = findViewById(R.id.button_passcode_one);
        mOneBtn.setOnClickListener(this);
        mTwoBtn = findViewById(R.id.button_passcode_two);
        mTwoBtn.setOnClickListener(this);
        mThreeBtn = findViewById(R.id.button_passcode_three);
        mThreeBtn.setOnClickListener(this);
        mFourBtn = findViewById(R.id.button_passcode_four);
        mFourBtn.setOnClickListener(this);
        mFiveBtn = findViewById(R.id.button_passcode_five);
        mFiveBtn.setOnClickListener(this);
        mSixBtn = findViewById(R.id.button_passcode_six);
        mSixBtn.setOnClickListener(this);
        mSevenBtn = findViewById(R.id.button_passcode_seven);
        mSevenBtn.setOnClickListener(this);
        mEightBtn = findViewById(R.id.button_passcode_eight);
        mEightBtn.setOnClickListener(this);
        mNineBtn = findViewById(R.id.button_passcode_nine);
        mNineBtn.setOnClickListener(this);
        mZeroBtn = findViewById(R.id.button_passcode_zero);
        mZeroBtn.setOnClickListener(this);
        mDeleteBtn = findViewById(R.id.button_passcode_del);
        mDeleteBtn.setOnClickListener(this);


        mOneEdt = findViewById(R.id.editText_passcode_number_one);
        mTwoEdt = findViewById(R.id.editText_passcode_number_two);
        mThreeEdt = findViewById(R.id.editText_passcode_number_three);
        mFourEdt = findViewById(R.id.editText_passcode_number_four);

        mOneEdt.requestFocus();
        mCurrentEditText = mOneEdt;

        mOneEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String inputText = editable.toString();
                if (inputText.length() == 1) {
                    mFirstEditTextPassCode = inputText;
                    mCurrentEditText = mTwoEdt;
                    mTwoEdt.requestFocus();
                }
            }
        });

        mTwoEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                String inputText = editable.toString();
                if (inputText.length() == 1) {

                    mSecondEditTextPassCode = inputText;
                    mCurrentEditText = mThreeEdt;
                    mThreeEdt.requestFocus();
                }
            }
        });

        mThreeEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String inputText = editable.toString();
                if (inputText.length() == 1) {
                    mThreeEditTextPassCode = inputText;
                    mCurrentEditText = mFourEdt;
                    mFourEdt.requestFocus();
                }
            }
        });

        mFourEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String inputText = editable.toString();
                if (inputText.length() == 1) {
                    mFourEditTextPassCode = inputText;
                    disableKeyPad();

                    new Handler().postDelayed(
                            () -> {
                                savePassCode();
                                enableKeyPad();
                            }, 150
                    );
                }
            }
        });


        /**
         * back button event.
         */

        findViewById(R.id.passcode_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        /**
         * Forgot pass code button event..
         */
        mPassCodeForgotTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PinActivity.this, LoginActivity.class);
                intent.putExtra("key", true);
                startActivity(intent);
                finish();
            }
        });


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

    private void enableKeyPad() {
        mOneBtn.setEnabled(true);
        mTwoBtn.setEnabled(true);
        mThreeBtn.setEnabled(true);
        mFourBtn.setEnabled(true);
        mFiveBtn.setEnabled(true);
        mSixBtn.setEnabled(true);
        mSevenBtn.setEnabled(true);
        mEightBtn.setEnabled(true);
        mNineBtn.setEnabled(true);
        mZeroBtn.setEnabled(true);
        mDeleteBtn.setEnabled(true);
    }

    private void disableKeyPad() {
        mOneBtn.setEnabled(false);
        mTwoBtn.setEnabled(false);
        mThreeBtn.setEnabled(false);
        mFourBtn.setEnabled(false);
        mFiveBtn.setEnabled(false);
        mSixBtn.setEnabled(false);
        mSevenBtn.setEnabled(false);
        mEightBtn.setEnabled(false);
        mNineBtn.setEnabled(false);
        mZeroBtn.setEnabled(false);
        mDeleteBtn.setEnabled(false);
    }

    private void deletePassCode() {
        if (mCurrentEditText == mOneEdt) {
            mCurrentEditText.setText("");
        } else if (mCurrentEditText == mTwoEdt) {
            mCurrentEditText.setText("");
            mCurrentEditText = mOneEdt;
        } else if (mCurrentEditText == mThreeEdt) {
            mCurrentEditText.setText("");
            mCurrentEditText = mTwoEdt;
        } else if (mCurrentEditText == mFourEdt) {
            mCurrentEditText.setText("");
            mCurrentEditText = mThreeEdt;
        }
    }

    private void clearPassCode() {
        mOneEdt.setText("");
        mTwoEdt.setText("");
        mThreeEdt.setText("");
        mFourEdt.setText("");
        mOneEdt.requestFocus();
        mCurrentEditText = mOneEdt;
        mFirstEditTextPassCode = "";
        mSecondEditTextPassCode = "";
        mThreeEditTextPassCode = "";
        mFourEditTextPassCode = "";
    }

    private void savePassCode() {
        String passCode = mFirstEditTextPassCode + mSecondEditTextPassCode +
                mThreeEditTextPassCode + mFourEditTextPassCode;
        Log.d("Passcode", "savePassCode: " + passCode);


        String savedPassCode = SharedPref.getSharedPreferences(this).getPassCode();
        if (savedPassCode.equals("")) {
            mHeadingTxt.setText(getResources().getString(R.string.passcode_title_heading_confirm));


            SharedPref.getSharedPreferences(this).setUserFirstTimeStatus(false);

            clearPassCode();

            if (mPassCodeEntryOne == -1) {
                mPassCodeEntryOne = Integer.parseInt(passCode);

            } else {
                int mPassCodeEntryTwo = Integer.parseInt(passCode);
                if (mPassCodeEntryOne == mPassCodeEntryTwo) {
                    SharedPref.getSharedPreferences(this).savePassCode(passCode);
                    decideNextLaunchActivity();
                } else {
                    clearPassCode();
                    mHeadingTxt.setText(getResources().getString(R.string.passcode_title_heading_create));
                    Toast.makeText(this, "PassCode not matching ", Toast.LENGTH_SHORT).show();
//                    UiUtils.Companion.showSimpleDialog(PassCodeActivity.this, "PassCode not matching ");
                    mPassCodeEntryOne = -1;
                }
            }

            Log.d("Passcode", "savePassCode: save new preferences");
        } else if (!savedPassCode.equals(passCode)) {
            Log.d("Passcode", "savePassCode: Access Denied");
            clearPassCode();
            mAttemptsTried = mAttemptsTried + 1;
            int totalAttempts = 3;
            int attemptNumber = totalAttempts - mAttemptsTried;
            if (attemptNumber == 0) {
                SharedPref.getSharedPreferences(this).clearAllSharedPreferencesData();
                Intent mIntent = new Intent(PinActivity.this, LoginActivity.class);
                mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(mIntent);
                finish();

                Toast.makeText(this, "Please login again", Toast.LENGTH_SHORT).show();
//                UiUtils.Companion.showToast(PassCodeActivity.this , "Please login again");
            } else if (attemptNumber == 2) {
                showAttemptsRemaining(attemptNumber);
            } else {

                Toast.makeText(this, (totalAttempts - mAttemptsTried) + " attempt remaining", Toast.LENGTH_SHORT).show();
//                UiUtils.Companion.showToast(PassCodeActivity.this , (totalAttempts - mAttemptsTried) + " attempt remaining");
            }
        } else if (savedPassCode.equals(passCode)) {
            Log.d("Passcode", "savePassCode: Access Granted");
            decideNextLaunchActivity();
        }
    }


    private void decideNextLaunchActivity() {
        String userType = SharedPref.getSharedPreferences(this).getUserType();

        int msgNumber = SharedPref.getSharedPreferences(PinActivity.this).getHelloAndAgainMessage();
        if (msgNumber == 0) {
            SharedPref.getSharedPreferences(PinActivity.this).setHelloAndAgainMessage(1);
        }

        launchHomeActivity();

//        int currentPrimaryUserId = SharedPreferencesHelper.getCurrentPrimaryUserId(PassCodeActivity.this);
//        boolean isPrimaryUserSelected = SharedPreferencesHelper.isPrimaryUserSelected(PassCodeActivity.this);
        /* if (userType.equalsIgnoreCase("Primary")) {
            launchHomeActivity();
        }else if(userType.equalsIgnoreCase("Secondary") &&
                currentPrimaryUserId!=0 &&
                isPrimaryUserSelected){
            launchHomeActivity();
        }else if(userType.equalsIgnoreCase("Secondary") &&
                currentPrimaryUserId==0 &&
                !isPrimaryUserSelected){
            launchPrimaryUserListActivity();
        } */
    }


    private void showAttemptsRemaining(int attemptsRemaining) {
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(PinActivity.this);
        builder.setMessage(attemptsRemaining + " attempts remaining");

        // add the buttons
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(dialogParams -> dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorButtonNew)));
        dialog.show();
    }


    private void launchHomeActivity() {

        String mUserType = SharedPref.getSharedPreferences(this).getUserType();
        if (mUserType.equals("primary")) {

            Intent mHomeIntent = new Intent(this, HomeActivity.class);
            mHomeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(mHomeIntent);
            finish();

        } else if (mUserType.equals("secondary")) {

            int pid = SharedPref.getSharedPreferences(this).getSinglePrimaryId();
            if (pid == 0) {

                Intent primaryUserIntent = new Intent(PinActivity.this, PrimaryUserListActivity.class);
                primaryUserIntent.putExtra("primaryUserid", true);
                startActivity(primaryUserIntent);
                finish();
            } else {
                Intent mHomeIntent = new Intent(this, HomeActivity.class);
                mHomeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(mHomeIntent);
                finish();
            }

        }

    }

}
