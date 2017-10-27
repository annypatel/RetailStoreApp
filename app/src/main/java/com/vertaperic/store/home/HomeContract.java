/*
 * Project    : RetailStoreApp
 * File       : HomeContract
 * Created on : 11/3/2016 9:51 PM
 */
package com.vertaperic.store.home;

import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.vertaperic.store.mvp.IPresenter;
import com.vertaperic.store.mvp.IView;

/**
 * This interface specifies the contract between the Home screen view and the presenter.
 *
 * @author Anny Patel
 */
public interface HomeContract {

    /**
     * The view interface for Home screen functionality.
     */
    interface View extends IView {

        /**
         * To show product browsing screen.
         */
        void showBrowseScreen();

        /**
         * To show the gift cards screen.
         */
        void showGiftCardsScreen();

        /**
         * To show wish list screen.
         */
        void showWishListsScreen();

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
     * The presenter interface for Home screen functionality.
     */
    interface Presenter extends IPresenter {

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

        /**
         * To browse the products.
         */
        void browseProducts();

        /**
         * To create the gift card.
         */
        void createGiftCard();

        /**
         * To create the wish list.
         */
        void createWishList();
    }
}
