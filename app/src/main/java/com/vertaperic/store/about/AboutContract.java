/*
 * Project    : RetailStoreApp
 * File       : AboutContract
 * Created on : 11/4/2016 5:31 PM
 */
package com.vertaperic.store.about;

import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.vertaperic.store.app.BasePresenter;
import com.vertaperic.store.app.BaseView;

/**
 * This interface specifies the contract between the About screen view and the presenter.
 *
 * @author Anny Patel
 */
public interface AboutContract {

    /**
     * The view interface for About screen functionality.
     */
    interface View extends BaseView {

        /**
         * To show the navigation view.
         */
        void showNavigationView();

        /**
         * To show my cart screen.
         */
        void showMyCartScreen();
    }

    /**
     * The presenter interface for About screen functionality.
     */
    interface Presenter extends BasePresenter {

        /**
         * Called when the toolbar navigation icon is clicked.
         */
        void onToolbarNavigationClick();

        /**
         * Called when toolbar menu item is clicked.
         *
         * @param item The item that was clicked.
         * @return true if the event was handled, false otherwise.
         */
        boolean onMenuItemClick(@NonNull MenuItem item);
    }
}
