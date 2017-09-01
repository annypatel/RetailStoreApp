/*
 * Project    : RetailStoreApp
 * File       : AppNavigationItemSelectionCallback
 * Created on : 11/3/2016 3:47 PM
 */
package com.vertaperic.store.app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.vertaperic.android.support.NavigationItemSelectionCallback;
import com.vertaperic.store.R;
import com.vertaperic.store.about.AboutActivity;
import com.vertaperic.store.browse.BrowseActivity;
import com.vertaperic.store.home.HomeActivity;
import com.vertaperic.store.search.SearchActivity;

/**
 * The navigation view callback for this app, used with all the activity that extends
 * {@link NavigationDrawerActivity}.
 *
 * @author Anny Patel
 */
class AppNavigationItemSelectionCallback implements NavigationItemSelectionCallback {

    /**
     * The navigation drawer activity.
     */
    private final NavigationDrawerActivity activity;

    /**
     * @param activity The navigation drawer activity.
     */
    AppNavigationItemSelectionCallback(@NonNull NavigationDrawerActivity activity) {
        this.activity = activity;
    }

    @Override
    public boolean shouldCheckedNavigationItem(@NonNull MenuItem item) {
        // close drawer every time item is selected
        this.activity.getNavigationDrawerController().closeNavigationDrawer();
        // do not check the selected item
        return false;
    }

    @Override
    public void onNavigationItemSelected(@NonNull MenuItem item) {
        // map menu item to activity class
        Class clazz = mapToActivityClass(item);
        if (clazz == null) {
            return;
        }

        // start activity
        Intent intent = new Intent(this.activity, clazz);
        // to clear the activity already on top of this add following flags
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        this.activity.startActivity(intent);
    }

    /**
     * To map the menu item to its corresponding activity.
     *
     * @param item The menu item.
     * @return The activity class if mapping provided, otherwise null.
     */
    @Nullable
    private Class mapToActivityClass(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemHome:
                return HomeActivity.class;

            case R.id.itemBrowse:
                return BrowseActivity.class;

            case R.id.itemSearch:
                return SearchActivity.class;

            case R.id.itemAbout:
                return AboutActivity.class;
        }
        return null;
    }
}
