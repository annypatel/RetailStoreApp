/*
 * Project    : RetailStoreApp
 * File       : BrowseModule
 * Created on : 11/11/16 12:16 PM
 */
package com.vertaperic.store.browse;

import dagger.Module;
import dagger.Provides;

/**
 * The dagger module that provides dependencies for browse screen.
 *
 * @author Anny Patel
 */
@Module
class BrowseModule {

    /**
     * The view for browse screen.
     */
    private final BrowseContract.View view;

    /**
     * @param view The view for browse screen.
     */
    BrowseModule(BrowseContract.View view) {
        this.view = view;
    }

    @Provides
    BrowseContract.View provideBrowseView() {
        return view;
    }

    @Provides
    BrowseContract.Presenter provideBrowsePresenter(BrowsePresenter presenter) {
        return presenter;
    }
}
