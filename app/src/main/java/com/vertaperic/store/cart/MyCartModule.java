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

    /**
     * The view for my cart screen.
     */
    private final MyCartContract.View view;

    /**
     * @param view The view for my cart screen.
     */
    MyCartModule(MyCartContract.View view) {
        this.view = view;
    }

    @Provides
    MyCartContract.View provideMyCartView() {
        return view;
    }

    @Provides
    MyCartContract.Presenter providePresenter(MyCartPresenter presenter) {
        return presenter;
    }
}
