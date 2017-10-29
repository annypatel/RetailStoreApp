/*
 * Project    : RetailStoreApp
 * File       : BrowsePresenter
 * Created on : 11/4/2016 9:52 PM
 */
package com.vertaperic.store.browse;

import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.vertaperic.store.R;
import com.vertaperic.store.mvp.BasePresenter;

import javax.inject.Inject;

/**
 * The presenter for browse view, listens to the user actions from UI({@link BrowseFragment})
 * and update the UI if required.
 *
 * @author Anny Patel
 */
class BrowsePresenter extends BasePresenter<BrowseContract.View>
        implements BrowseContract.Presenter {

    @Inject
    BrowsePresenter() {
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
}
