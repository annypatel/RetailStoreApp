/*
 * Project    : RetailStoreApp
 * File       : ProductsModule
 * Created on : 11/11/16 10:41 AM
 */
package com.vertaperic.store.product;

import dagger.Module;
import dagger.Provides;

/**
 * The dagger module that provides dependencies for product browsing functionality.
 *
 * @author Anny Patel
 */
@Module
class ProductsModule {

    /**
     * The view for product browsing screen.
     */
    private final ProductsContract.View view;

    /**
     * @param view The view for product browsing screen.
     */
    ProductsModule(ProductsContract.View view) {
        this.view = view;
    }

    @Provides
    ProductsContract.View provideProductsView() {
        return view;
    }

    @Provides
    ProductsContract.Presenter provideProductsPresenter(ProductsPresenter presenter) {
        return presenter;
    }

    @Provides
    ProductsRepository provideProductsRepository(LocalProductsRepository repository) {
        return repository;
    }
}
