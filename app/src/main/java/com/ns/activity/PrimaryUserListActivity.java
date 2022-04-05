package com.ns.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ns.adapter.PrimaryUserAdapter;
import com.ns.database.SharedPref;
import com.ns.model.SecondaryUser.PrimaryUsersBean;
import com.ns.stellarjet.R;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PrimaryUserListActivity extends AppCompatActivity implements PrimaryUserAdapter.RowClickPostion {
    private static final String TAG = "PrimaryUserListActivity";

    private boolean check;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primaryuserlist);

        // Intent get boolean data check for which activity is coming. ...
        // PinActivity show true
        // HomeActivity false
        check = getIntent().getBooleanExtra("primaryUserid", false);
        Log.d(TAG, "onCreate: " + check);

        // initialization for recyclerview
        RecyclerView recyclerView = findViewById(R.id.common_recycler);

        // initialization LinearLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        // Primary user data store in shared preference  and get list...
        ArrayList<PrimaryUsersBean> primaryUserList = SharedPref.getSharedPreferences(this).getPrimaryUser();

        // Adapter call for primary user name list show...
        PrimaryUserAdapter adapter = new PrimaryUserAdapter(this,primaryUserList);
        adapter.setRowClickPostion(this);
        recyclerView.setAdapter(adapter);


        // back button event then finish the activity..
        findViewById(R.id.button_boarding_pass_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    /**
     *  List Row event then get pessenget id and position
     * @param pid
     * @param pos
     */
    @Override
    public void onRowClick(int pid, int pos) {

        // Save the passenger id and position..
        // use for HomeActivity
        SharedPref.getSharedPreferences(PrimaryUserListActivity.this).setSinglePrimaryId(pid, pos);

        if (check) {
            Intent mHomeIntent = new Intent(this, HomeActivity.class);
            mHomeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(mHomeIntent);
            finish();
        } else {
            finish();
        }

    }
}
