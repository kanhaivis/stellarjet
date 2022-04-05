package com.ns.stellarjet;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.ns.activity.LoginActivity;
import com.ns.activity.PinActivity;
import com.ns.activity.TouchIdActivity;
import com.ns.database.SharedPref;
import com.ns.model.StaticTCUrlModelResponse;
import com.ns.retrofit.APIInterface;
import com.ns.retrofit.APIRetrofit;
import com.ns.utils.ConstantMethod;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SplaceScreen extends AppCompatActivity {

    private static final String TAG = "SplaceScreen";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splace_screen);

        // Initialization  for video view
        VideoView videoHolder = findViewById(R.id.splace_video);


        /**
         *
         *  0 is first time app is open for user
         *  1 is pass code is set and message show Hello
         *  2 is show message Hello Again.
         */
        int msgNumber = SharedPref.getSharedPreferences(SplaceScreen.this).getHelloAndAgainMessage();
        if (msgNumber == 1) {
            SharedPref.getSharedPreferences(SplaceScreen.this).setHelloAndAgainMessage(2);
        } else if (msgNumber == 0) {
            if (ConstantMethod.isConnected(this)) {
                StaticTCUrlAPICall();
            }

        }


        /**
         * Video is local store
         */
        try {
            Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.stellarjet_splash);
            videoHolder.setVideoURI(video);

            videoHolder.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    jump();
                }
            });
            videoHolder.start();
        } catch (Exception ex) {
            jump();
            Log.d(TAG, "onCreate: " + ex);
        }
    }


    /**
     * TC call api and get response for url
     *  then store for shared prefrence
     */
    private void StaticTCUrlAPICall() {

        APIInterface mApiInterface = APIRetrofit.getRetrofitClient(this).create(APIInterface.class);
        mApiInterface.getStaticTCUrl()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StaticTCUrlModelResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(StaticTCUrlModelResponse staticTCUrlModelResponse) {
                        if (staticTCUrlModelResponse.getResultcode() == 1) {
                            SharedPref.getSharedPreferences(SplaceScreen.this).setTCUrl(staticTCUrlModelResponse.getData());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * Video playing stop then action activity is change
     */
    private void jump() {

        String passCose = SharedPref.getSharedPreferences(this).getPassCode();
        if (passCose.isEmpty()) {

            Intent intent = new Intent(SplaceScreen.this, LoginActivity.class);
            intent.putExtra("key", false);
            startActivity(intent);
            finish();
        } else {
            startActivity(new Intent(SplaceScreen.this, TouchIdActivity.class));
//            startActivity(new Intent(SplaceScreen.this, PinActivity.class));
            finish();
        }
    }

}
