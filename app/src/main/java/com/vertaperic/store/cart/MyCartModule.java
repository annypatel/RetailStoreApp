/*
 * Project    : RetailStoreApp
 * File       : MyCartModule
 * Created on : 10/11/16 7:37 PM
 */
package com.vertaperic.store.cart;

import dagger.Module;
import dagger.Provides;

/**
 * The dagger module that provides dependencies for my cart functionality.
 *
 * @author Anny Patel
 */
@Module
class MyCartModule {

    @Provides
    MyCartContract.Presenter providePresenter(MyCartPresenter presenter) {
        return presenter;
    }
}
