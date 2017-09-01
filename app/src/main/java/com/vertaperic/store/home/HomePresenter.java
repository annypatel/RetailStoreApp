/*
 * Project    : RetailStoreApp
 * File       : HomePresenter
 * Created on : 11/4/2016 5:17 PM
 */
package com.vertaperic.store.home;

import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.vertaperic.store.R;

import javax.inject.Inject;

/**
 * The presenter for Home screen view, listens to the user actions from UI(@link {@link HomeFragment})
 * and update the UI if required.
 *
 * @author Anny Patel
 */
class HomePresenter implements HomeContract.Presenter {

    /**
     * The view attached with this presenter.
     */
    private HomeContract.View homeView;

    /**
     * @param homeView The view attached with this presenter.
     */
    @Inject
    HomePresenter(@NonNull HomeContract.View homeView) {
        this.homeView = homeView;
    }

    @Override
    public void onToolbarNavigationClick() {
        this.homeView.showNavigationView();
    }

    @Override
    public boolean onMenuItemClick(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemMyCart) {
            this.homeView.showMyCartScreen();
            return true;
        }
        return false;
    }

    @Override
    public void browseProducts() {
        this.homeView.showBrowseScreen();
    }

    @Override
    public void createGiftCard() {
        this.homeView.showGiftCardsScreen();
    }

    @Override
    public void createWishList() {
        this.homeView.showWishListsScreen();
    }
}
