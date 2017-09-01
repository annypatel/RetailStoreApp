/*
 * Project    : RetailStoreApp
 * File       : MyCartRepositoryModule
 * Created on : 10/11/16 9:07 PM
 */
package com.vertaperic.store.cart;

import dagger.Module;
import dagger.Provides;

/**
 * Separate module for My Cart repository as it is used in product details as well, provides the
 * {@link LocalCartRepository} as repository.
 *
 * @author Anny Patel
 */
@Module
public class MyCartRepositoryModule {

    @Provides
    CartRepository provideCartRepository(LocalCartRepository repository) {
        return repository;
    }
}
