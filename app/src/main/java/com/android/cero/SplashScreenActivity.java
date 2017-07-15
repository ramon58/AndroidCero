package com.android.cero;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Ramon on 18/01/2017.
 */

public class SplashScreenActivity extends Activity {

    private final int SPLASH_DISPLAY_LENGTH = 4000;
    private Bundle savedInstanceState;
    private View mRootView;
    private Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedIsnstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        mRootView = findViewById(R.id.btFile);
        mRootView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                mRootView.removeCallbacks(mRunnable);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isSplashEnabled = sp.getBoolean("isSplashEnabled", true);

        if (isSplashEnabled) {

                mRootView.postDelayed(mRunnable = new Runnable() {

                public void run() {
                    Intent I = new Intent(SplashScreenActivity.this, MainActivity.class);
                    I.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(I);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }

            }, SPLASH_DISPLAY_LENGTH);
        }
        else
        {
            Intent B = new Intent(SplashScreenActivity.this, MainActivity.class);
            B.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(B);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
        }
    }
}