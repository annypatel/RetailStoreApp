/*
 * Project    : RetailStoreApp
 * File       : SearchActivity
 * Created on : 11/3/2016 2:57 PM
 */
package com.vertaperic.store.search;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.vertaperic.android.support.NavigationTransactionInfo;
import com.vertaperic.store.R;
import com.vertaperic.store.app.NavigationDrawerActivity;

/**
 * Activity for hosting the {@link SearchFragment}. This activity is for demo purpose only, no
 * search functionality is implemented.
 *
 * @author Anny Patel
 */
public class SearchActivity extends NavigationDrawerActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // create the fragment
        SearchFragment fragment = (SearchFragment) getSupportFragmentManager()
                .findFragmentById(R.id.container);
        if (fragment == null) {
            fragment = SearchFragment.newInstance();

            // select navigation item for search
            getNavigationDrawerController().select(
                    new NavigationTransactionInfo(fragment, R.id.itemSearch, R.id.container)
            );
        }
    }
}
