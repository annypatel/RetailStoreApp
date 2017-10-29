/*
 * Project    : RetailStoreApp
 * File       : BrowseActivity
 * Created on : 11/3/2016 4:13 PM
 */
package com.vertaperic.store.browse;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.vertaperic.android.support.NavigationTransactionInfo;
import com.vertaperic.store.R;
import com.vertaperic.store.app.NavigationDrawerActivity;

/**
 * Activity for hosting the {@link BrowseFragment}, displays category wise products.
 *
 * @author Anny Patel
 */
public class BrowseActivity extends NavigationDrawerActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // create the fragment
        BrowseFragment fragment = (BrowseFragment) getSupportFragmentManager()
                .findFragmentById(R.id.container);
        if (fragment == null) {
            fragment = BrowseFragment.newInstance();

            // select navigation item for home
            getNavigationDrawerController().select(
                    new NavigationTransactionInfo(fragment, R.id.itemBrowse, R.id.container)
            );
        }

        // inject dependencies with dagger
        DaggerBrowseComponent.builder()
                .browseModule(new BrowseModule())
                .build()
                .inject(fragment);
    }
}
