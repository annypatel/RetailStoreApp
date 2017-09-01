/*
 * Project    : RetailStoreApp
 * File       : NavigationDrawerController
 * Created on : 21/10/16, 3:35 PM
 */
package com.vertaperic.android.support;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

/**
 * The default implementation of the NavigationDrawerController.
 *
 * @author Anny Patel
 */
class NavigationDrawerControllerImpl extends NavigationDrawerController {

    /**
     * The fragment controller.
     */
    private final FragmentController controller;
    /**
     * The drawer layout.
     */
    private DrawerLayout drawerLayout;
    /**
     * The navigation view.
     */
    private NavigationView navigationView;

    /**
     * Constructs new NavigationDrawerControllerImpl.
     *
     * @param controller The fragment controller.
     */
    NavigationDrawerControllerImpl(@NonNull FragmentController controller) {
        this.controller = controller;
    }

    @Override
    public void create(@NonNull DrawerLayout drawerLayout, @NonNull NavigationView navigationView,
                       @NonNull NavigationItemSelectionCallback callback) {

        // initialize views
        this.drawerLayout = drawerLayout;
        this.navigationView = navigationView;

        // add navigation item selection callback listener
        OnNavigationItemSelectedListener navListener = NavigationItemSelectionCallback.Wrapper.wrap(callback);
        this.navigationView.setNavigationItemSelectedListener(navListener);
    }

    @Override
    public void select(@NonNull NavigationTransactionInfo transactionInfo) {
        // find menu item from menu
        MenuItem menuItem = this.navigationView.getMenu().findItem(transactionInfo.itemId);
        if (menuItem == null) {
            throw new IllegalArgumentException("No menu item found for id " + transactionInfo.itemId);
        }

        // check menu item if not checked
        if (!menuItem.isChecked()) {
            menuItem.setChecked(true);
        }

        // add fragment to back stack manager
        this.controller.getBackStackManager()
                .add(new FragmentTransactionInfo(transactionInfo.fragment, transactionInfo.containerViewId)
                        .tag(transactionInfo.tag)
                        .animate(transactionInfo.animate)
                        .addToBackStack(transactionInfo.addToBackStack));
    }

    @Override
    public boolean handleOnBackPressed() {
        // if drawer is open then close it
        if (this.drawerLayout.isDrawerOpen(this.navigationView)) {
            closeNavigationDrawer();
            return true;
        }
        return false;
    }

    @Override
    public void openNavigationDrawer() {
        this.drawerLayout.openDrawer(this.navigationView);
    }

    @Override
    public void closeNavigationDrawer() {
        this.drawerLayout.closeDrawer(this.navigationView);
    }

    @Override
    public void lockNavigationDrawer() {
        this.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, this.navigationView);
    }

    @Override
    public void unlockNavigationDrawer() {
        this.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, this.navigationView);
    }
}
