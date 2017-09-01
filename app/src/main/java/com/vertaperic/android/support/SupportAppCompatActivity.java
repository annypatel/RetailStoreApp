/*
 * Project    : RetailStoreApp
 * File       : SupportAppCompatActivity
 * Created on : 17/10/16, 1:13 PM
 */
package com.vertaperic.android.support;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * The activity sub class that provides access to fragment controller API.
 *
 * @author Anny Patel
 */
public class SupportAppCompatActivity extends AppCompatActivity {

    /**
     * The fragment controller.
     */
    private FragmentController fragmentController;

    /**
     * @return The fragment controller.
     */
    public FragmentController getFragmentController() {
        if (this.fragmentController == null) {
            this.fragmentController = SupportFactory.fragmentController(createBackStackManager());
        }
        return this.fragmentController;
    }

    /**
     * To create the back stack manager.
     *
     * @return The back stack manager.
     */
    @NonNull
    protected BackStackManager createBackStackManager() {
        return SupportFactory.backStackManager(this, getSupportFragmentManager());
    }

    @Override
    public void onBackPressed() {
        // dispatch back press, if event consumed then return
        if (getFragmentController().handleOnBackPressed()) {
            return;
        }
        // finish activity
        ActivityCompat.finishAfterTransition(this);
    }
}
