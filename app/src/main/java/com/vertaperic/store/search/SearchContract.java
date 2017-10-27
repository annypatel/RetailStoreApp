/*
 * Project    : RetailStoreApp
 * File       : SearchContract
 * Created on : 11/4/2016 1:48 PM
 */
package com.vertaperic.store.search;

import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.vertaperic.store.app.BasePresenter;
import com.vertaperic.store.app.BaseView;

/**
 * This interface specifies the contract between the Search screen view and the presenter.
 *
 * @author Anny Patel
 */
public interface SearchContract {

    /**
     * The view interface for Search screen functionality.
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
     * The presenter interface for Search screen functionality.
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
