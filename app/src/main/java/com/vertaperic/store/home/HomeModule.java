/*
 * Project    : RetailStoreApp
 * File       : HomeModule
 * Created on : 11/11/16 12:19 PM
 */
package com.vertaperic.store.home;

import dagger.Module;
import dagger.Provides;

/**
 * The dagger module that provides dependencies for home screen.
 *
 * @author Anny Patel
 */
@Module
class HomeModule {

    @Provides
    HomeContract.Presenter provideHomePresenter(HomePresenter presenter) {
        return presenter;
    }
}
