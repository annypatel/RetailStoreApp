/*
 * Project    : RetailStoreApp
 * File       : SearchPresenter
 * Created on : 11/4/2016 1:50 PM
 */
package com.vertaperic.store.search;

import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.vertaperic.store.R;

import javax.inject.Inject;

/**
 * The presenter for search view, listens to the user actions from UI(@link {@link SearchFragment})
 * and update the UI if required.
 *
 * @author Anny Patel
 */
class SearchPresenter implements SearchContract.Presenter {

    /**
     * The view attached with this presenter.
     */
    private SearchContract.View searchView;

    /**
     * @param searchView The view attached with this presenter.
     */
    @Inject
    SearchPresenter(@NonNull SearchContract.View searchView) {
        this.searchView = searchView;
    }

    @Override
    public void onToolbarNavigationClick() {
        this.searchView.showNavigationView();
    }

    @Override
    public boolean onMenuItemClick(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemMyCart) {
            this.searchView.showMyCartScreen();
            return true;
        }
        return false;
    }
}
