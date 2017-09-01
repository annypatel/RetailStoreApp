/*
 * Project    : RetailStoreApp
 * File       : NavigationDrawerActivity
 * Created on : 10/31/2016 8:04 PM
 */
package com.vertaperic.store.app;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.vertaperic.android.support.NavigationDrawerController;
import com.vertaperic.android.support.NavigationItemSelectionCallback;
import com.vertaperic.android.support.SupportAppCompatActivity;
import com.vertaperic.android.support.SupportFactory;
import com.vertaperic.store.R;

/**
 * Base navigation drawer activity with {@link NavigationDrawerController}.
 *
 * @author Anny Patel
 */
public class NavigationDrawerActivity extends SupportAppCompatActivity {

    /**
     * The activity binding instance.
     */
    protected NavigationDrawerActivityBinding binding;
    /**
     * The navigation drawer controller.
     */
    private NavigationDrawerController navigationController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_navigation_drawer);

        // create navigation drawer controller
        this.navigationController = SupportFactory.navigationDrawerController(getFragmentController());
        NavigationItemSelectionCallback navCallback = new AppNavigationItemSelectionCallback(this);
        this.navigationController.create(this.binding.drawerLayout, this.binding.navigationView, navCallback);
    }

    @Override
    public void onBackPressed() {
        // dispatch back press event to navigation controller
        if (this.navigationController.handleOnBackPressed()) {
            return;
        }

        // else let super handle the event
        super.onBackPressed();
    }

    /**
     * To get the navigation drawer controller.
     *
     * @return The navigation drawer controller attached with this activity.
     */
    public NavigationDrawerController getNavigationDrawerController() {
        return this.navigationController;
    }
}
