/*
 * Project    : RetailStoreApp
 * File       : SearchPresenter
 * Created on : 11/4/2016 1:50 PM
 */
package com.vertaperic.store.search;

import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.vertaperic.store.R;
import com.vertaperic.store.mvp.BasePresenter;

import javax.inject.Inject;

/**
 * The presenter for search view, listens to the user actions from UI(@link {@link SearchFragment})
 * and update the UI if required.
 *
 * @author Anny Patel
 */
class SearchPresenter extends BasePresenter<SearchContract.View>
        implements SearchContract.Presenter {

    @Inject
    SearchPresenter() {
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
