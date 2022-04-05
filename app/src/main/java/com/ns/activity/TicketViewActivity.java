package com.ns.activity;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.media.Image;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ns.adapter.TicketDownloadPager;
import com.ns.model.Download.DownloadPojo;
import com.ns.stellarjet.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class TicketViewActivity extends BaseActivity {

    private ImageView mSharedIcon;
    private String mBoardingPassUrl = "";

    // Bundle Data
    ArrayList<DownloadPojo> mDownloadPojos;

    @Override
    public int getLayout() {
        return R.layout.activity_ticketview;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getIntent().getExtras();

        mDownloadPojos = (ArrayList<DownloadPojo>) bundle.getSerializable("downloadInfoList");

        for (DownloadPojo bean : mDownloadPojos) {
            mBoardingPassUrl = mBoardingPassUrl + bean.getTicketUrlLinked() + "\n";
        }

        ViewPager pager = findViewById(R.id.shared_viewpager);
        TicketDownloadPager pagerAdapter = new TicketDownloadPager(this, mDownloadPojos);
        pager.setAdapter(pagerAdapter);

        mSharedIcon = findViewById(R.id.shared_img);
        mSharedIcon.setVisibility(View.VISIBLE);
        mSharedIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                prepareIntentToShare(intent);
                test(intent);
            }
        });
    }

    private void prepareIntentToShare(Intent intent) {
        intent.setAction(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_STREAM, "mUri");
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Boarding Pass");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "Boarding pass link: \n\n" + mBoardingPassUrl);
    }

    private void test(Intent shareIntent) {
        List<Intent> targetedShareIntents = new ArrayList<>();
        final List<ResolveInfo> resInfo = getPackageManager().queryIntentActivities(shareIntent, 0);
        if (!resInfo.isEmpty()) {
            for (ResolveInfo resolveInfo : resInfo) {
                Intent targetedShareIntent = new Intent(shareIntent);
                targetedShareIntent.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
                targetedShareIntents.add(targetedShareIntent);
            }
            Intent chooserIntent = Intent.createChooser(targetedShareIntents.remove(targetedShareIntents.size() - 1), "Select app to share");
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetedShareIntents.toArray(new Parcelable[targetedShareIntents.size()]));
            startActivity(chooserIntent);
        }
    }
}
