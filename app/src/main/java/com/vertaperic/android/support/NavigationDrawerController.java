/*
 * Project    : RetailStoreApp
 * File       : NavigationDrawerController
 * Created on : 21/10/16, 4:02 PM
 */
package com.vertaperic.android.support;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

/**
 * Navigation drawer controller provides api to manage the navigation drawer with fragment.
 *
 * @author Anny Patel
 */
public abstract class NavigationDrawerController {

    /**
     * To initialize the navigation drawer controller.
     *
     * @param drawerLayout   The drawer layout.
     * @param navigationView The navigation view.
     * @param callback       The selection callback listener.
     */
    public abstract void create(@NonNull DrawerLayout drawerLayout, @NonNull NavigationView navigationView,
                                @NonNull NavigationItemSelectionCallback callback);

    /**
     * To select the navigation drawer item.
     *
     * @param transactionInfo The navigation transaction info.
     */
    public abstract void select(@NonNull NavigationTransactionInfo transactionInfo);

    /**
     * To handle the back press event.
     *
     * @return true if event is consumed otherwise false.
     */
    public abstract boolean handleOnBackPressed();

    /**
     * To open the navigation drawer.
     */
    public abstract void openNavigationDrawer();

    /**
     * To close the navigation drawer.
     */
    public abstract void closeNavigationDrawer();

    /**
     * To lock the navigation drawer.
     */
    public abstract void lockNavigationDrawer();

    /**
     * To unlock the navigation drawer.
     */
    public abstract void unlockNavigationDrawer();
}
