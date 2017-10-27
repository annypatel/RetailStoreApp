/*
 * Project    : RetailStoreApp
 * File       : AboutModule
 * Created on : 11/11/16 12:08 PM
 */
package com.vertaperic.store.about;

import dagger.Module;
import dagger.Provides;

/**
 * The dagger module that provides dependencies for about screen.
 *
 * @author Anny Patel
 */
@Module
class AboutModule {

    @Provides
    AboutContract.Presenter provideAboutPresenter(AboutPresenter presenter) {
        return presenter;
    }
}
