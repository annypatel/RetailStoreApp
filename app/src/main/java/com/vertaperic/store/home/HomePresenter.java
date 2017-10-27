/*
 * Project    : RetailStoreApp
 * File       : HomePresenter
 * Created on : 11/4/2016 5:17 PM
 */
package com.vertaperic.store.home;

import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.vertaperic.store.R;
import com.vertaperic.store.mvp.BasePresenter;

import javax.inject.Inject;

/**
 * The presenter for Home screen view, listens to the user actions from UI(@link {@link HomeFragment})
 * and update the UI if required.
 *
 * @author Anny Patel
 */
class HomePresenter extends BasePresenter<HomeContract.View>
        implements HomeContract.Presenter {

    @Inject
    HomePresenter() {
    }

    @Override
    public void onToolbarNavigationClick() {
        view().showNavigationView();
    }

    @Override
    public boolean onMenuItemClick(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemMyCart) {
            view().showMyCartScreen();
            return true;
        }
        return false;
    }

    @Override
    public void browseProducts() {
        view().showBrowseScreen();
    }

    @Override
    public void createGiftCard() {
        view().showGiftCardsScreen();
    }

    @Override
    public void createWishList() {
        view().showWishListsScreen();
    }
}
