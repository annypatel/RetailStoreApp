/*
 * Project    : RetailStoreApp
 * File       : AboutPresenter
 * Created on : 11/4/2016 5:39 PM
 */
package com.vertaperic.store.about;

import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.vertaperic.store.R;
import com.vertaperic.store.mvp.BasePresenter;

import javax.inject.Inject;

/**
 * The presenter for About screen view, listens to the user actions from UI({@link AboutFragment})
 * and update the UI if required.
 *
 * @author Anny Patel
 */
class AboutPresenter extends BasePresenter<AboutContract.View>
        implements AboutContract.Presenter {

    @Inject
    AboutPresenter() {
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
