/*
 * Project    : RetailStoreApp
 * File       : NavigationDrawerFragment
 * Created on : 11/1/2016 8:13 PM
 */
package com.vertaperic.store.app;

import com.vertaperic.android.support.NavigationDrawerController;
import com.vertaperic.android.support.SupportFragment;

/**
 * Base fragment that will provide the access to {@link NavigationDrawerController} of the hosting
 * activity.
 *
 * @author Anny Patel
 */
public class NavigationDrawerFragment extends SupportFragment {

    /**
     * To get the navigation drawer controller.
     *
     * @return The navigation drawer controller attached with the host activity.
     */
    public NavigationDrawerController getNavigationDrawerController() {
        return ((NavigationDrawerActivity) getActivity())
                .getNavigationDrawerController();
    }
}
