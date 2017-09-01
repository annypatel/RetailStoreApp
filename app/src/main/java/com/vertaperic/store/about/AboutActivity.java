/*
 * Project    : RetailStoreApp
 * File       : AboutActivity
 * Created on : 11/3/2016 2:54 PM
 */
package com.vertaperic.store.about;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.vertaperic.android.support.NavigationTransactionInfo;
import com.vertaperic.store.R;
import com.vertaperic.store.app.NavigationDrawerActivity;

/**
 * Activity for hosting the {@link AboutFragment}.
 *
 * @author Anny Patel
 */
public class AboutActivity extends NavigationDrawerActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // create the fragment
        AboutFragment fragment = (AboutFragment) getSupportFragmentManager()
                .findFragmentById(R.id.container);
        if (fragment == null) {
            fragment = AboutFragment.newInstance();

            // select navigation item for about
            getNavigationDrawerController().select(
                    new NavigationTransactionInfo(fragment, R.id.itemAbout, R.id.container)
            );
        }

        // inject dependencies with dagger
        DaggerAboutComponent.builder()
                .aboutModule(new AboutModule(fragment))
                .build()
                .inject(fragment);
    }
}
