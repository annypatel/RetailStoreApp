/*
 * Project    : RetailStoreApp
 * File       : BrowsePresenter
 * Created on : 11/4/2016 9:52 PM
 */
package com.vertaperic.store.browse;

import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.vertaperic.store.R;

import javax.inject.Inject;

/**
 * The presenter for browse view, listens to the user actions from UI({@link BrowseFragment})
 * and update the UI if required.
 *
 * @author Anny Patel
 */
class BrowsePresenter implements BrowseContract.Presenter {

    /**
     * The view attached with this presenter.
     */
    private BrowseContract.View browseView;

    /**
     * @param browseView The view attached with this presenter.
     */
    @Inject
    BrowsePresenter(@NonNull BrowseContract.View browseView) {
        this.browseView = browseView;
    }

    @Override
    public void onToolbarNavigationClick() {
        this.browseView.showNavigationView();
    }

    @Override
    public boolean onMenuItemClick(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemMyCart) {
            this.browseView.showMyCartScreen();
            return true;
        }
        return false;
    }
}
