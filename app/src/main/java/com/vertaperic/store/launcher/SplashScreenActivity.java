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
     * Boolean flag to indicate that the activity has gone into paused state. It
     * is <code>true</code> if activity is paused otherwise <code>false</code>.
     */
    private boolean paused;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // post runnable to start home activity with the SPLASH_TIME delay
        new Handler().postDelayed(
                new Runnable() {

                    @Override
                    public void run() {

                        if (!isPaused()) {
                            startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
                        }
                        finish();
                        overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out);
                    }
                },
                SPLASH_TIME);
    }

    /**
     * @return <code>true</code> if activity paused else <code>false</code>.
     */
    private boolean isPaused() {
        return this.paused;
    }

    /**
     * @param isPaused <code>true</code> if activity paused else
     *                 <code>false</code>.
     */
    private void setPaused(boolean isPaused) {
        this.paused = isPaused;
    }

    @Override
    protected void onPause() {
        setPaused(true);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setPaused(false);
    }
}
