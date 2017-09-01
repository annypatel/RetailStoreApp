/*
 * Project    : RetailStoreApp
 * File       : AboutPresenter
 * Created on : 11/4/2016 5:39 PM
 */
package com.vertaperic.store.about;

import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.vertaperic.store.R;

import javax.inject.Inject;

/**
 * The presenter for About screen view, listens to the user actions from UI({@link AboutFragment})
 * and update the UI if required.
 *
 * @author Anny Patel
 */
class AboutPresenter implements AboutContract.Presenter {

    /**
     * The view attached with this presenter.
     */
    private AboutContract.View aboutView;

    /**
     * @param aboutView The view attached with this presenter.
     */
    @Inject
    AboutPresenter(@NonNull AboutContract.View aboutView) {
        this.aboutView = aboutView;
    }

    @Override
    public void onToolbarNavigationClick() {
        this.aboutView.showNavigationView();
    }

    @Override
    public boolean onMenuItemClick(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemMyCart) {
            this.aboutView.showMyCartScreen();
            return true;
        }
        return false;
    }
}
