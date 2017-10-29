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

    @Provides
    BrowseContract.Presenter provideBrowsePresenter(BrowsePresenter presenter) {
        return presenter;
    }
}
