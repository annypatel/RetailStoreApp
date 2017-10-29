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

    @Provides
    ProductsContract.Presenter provideProductsPresenter(ProductsPresenter presenter) {
        return presenter;
    }

    @Provides
    ProductsRepository provideProductsRepository(LocalProductsRepository repository) {
        return repository;
    }
}
