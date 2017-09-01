/*
 * Project    : RetailStoreApp
 * File       : HomeContract
 * Created on : 11/3/2016 9:51 PM
 */
package com.vertaperic.store.home;

import com.vertaperic.store.app.BasePresenter;
import com.vertaperic.store.app.BaseView;

/**
 * This interface specifies the contract between the Home screen view and the presenter.
 *
 * @author Anny Patel
 */
public interface HomeContract {

    /**
     * The view interface for Home screen functionality.
     */
    interface View extends BaseView {

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
    interface Presenter extends BasePresenter {

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
