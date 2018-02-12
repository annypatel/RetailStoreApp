/*
 * Project    : RetailStoreApp
 * File       : HomeActivity
 * Created on : 10/30/2016 8:51 PM
 */
package com.vertaperic.store.home;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.vertaperic.android.support.NavigationTransactionInfo;
import com.vertaperic.store.R;
import com.vertaperic.store.app.NavigationDrawerActivity;

/**
 * This is the main landing activity after the splash screen.
 *
 * @author Anny Patel
 */
public class HomeActivity extends NavigationDrawerActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // enable fitSystemWindow to draw behind the status bar
        this.binding.container.setFitsSystemWindows(true);

        // create the fragment
        HomeFragment fragment = (HomeFragment) getSupportFragmentManager()
                .findFragmentById(R.id.container);
        if (fragment == null) {
            fragment = HomeFragment.newInstance();

            // select navigation item for home
            getNavigationDrawerController().select(
                    new NavigationTransactionInfo(fragment, R.id.itemHome, R.id.container)
            );
        }
    }
}
