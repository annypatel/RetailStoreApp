/*
 * Project    : RetailStoreApp
 * File       : SplashScreenActivity
 * Created on : 10/30/2016 6:41 PM
 */
package com.vertaperic.store.launcher;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.vertaperic.store.R;
import com.vertaperic.store.home.HomeActivity;

/**
 * The splash screen activity to be shown when the app is lunched.
 *
 * @author Anny Patel
 */
public class SplashScreenActivity extends AppCompatActivity {

    /**
     * The time in milliseconds for which splash screen will be show when app is launched.
     */
    private static final int SPLASH_TIME = 3000;
    /**
     * Boolean flag to indicate that the activity is in resumed state. It is <code>true</code>
     * if activity is resumed otherwise <code>false</code>.
     */
    private boolean resumed;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // post runnable to start home activity with the SPLASH_TIME delay
        new Handler().postDelayed(
                () -> {

                    if (isResumed()) {
                        startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
                    }
                    finish();
                    overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out);
                },
                SPLASH_TIME);
    }

    /**
     * @return <code>true</code> if activity resumed else <code>false</code>.
     */
    private boolean isResumed() {
        return this.resumed;
    }

    /**
     * @param resumed <code>true</code> if activity resumed else <code>false</code>.
     */
    private void setResumed(boolean resumed) {
        this.resumed = resumed;
    }

    @Override
    protected void onPause() {
        setResumed(false);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setResumed(true);
    }
}
