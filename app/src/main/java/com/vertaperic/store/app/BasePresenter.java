/*
 * Project    : RetailStoreApp
 * File       : BasePresenter
 * Created on : 11/3/2016 7:43 PM
 */
package com.vertaperic.store.app;

import android.support.annotation.NonNull;
import android.view.MenuItem;

/**
 * Base presenter interface with common methods.
 *
 * @author Anny Patel
 */
public interface BasePresenter {

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
}
