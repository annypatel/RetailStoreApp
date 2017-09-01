/*
 * Project    : RetailStoreApp
 * File       : ProductDetailsModule
 * Created on : 10/11/16 9:13 PM
 */
package com.vertaperic.store.product.detail;

import dagger.Module;
import dagger.Provides;

/**
 * The dagger module that provides dependencies for product details functionality.
 *
 * @author Anny Patel
 */
@Module
class ProductDetailsModule {

    /**
     * The view for product details screen.
     */
    private final ProductDetailsContract.View view;

    /**
     * @param view The view for product details screen.
     */
    ProductDetailsModule(ProductDetailsContract.View view) {
        this.view = view;
    }

    @Provides
    ProductDetailsContract.View provideProductDetailsView() {
        return view;
    }

    @Provides
    ProductDetailsContract.Presenter provideProductDetailsPresenter(ProductDetailsPresenter presenter) {
        return presenter;
    }
}
