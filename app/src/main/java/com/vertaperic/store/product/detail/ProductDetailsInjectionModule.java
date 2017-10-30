/*
 * Project    : RetailStoreApp
 * File       : ProductDetailsInjectionModule
 * Created on : 30/10/17 4:15 AM
 */
package com.vertaperic.store.product.detail;

import com.vertaperic.store.cart.CartRepository;
import com.vertaperic.store.cart.LocalCartRepository;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * The dagger module that provides dependencies for product details functionality.
 *
 * @author Anny Patel
 */
@Module
public abstract class ProductDetailsInjectionModule {

    @ContributesAndroidInjector(modules = Declarations.class)
    abstract ProductDetailsFragment contributeProductDetailsFragmentInjector();

    @Module
    abstract class Declarations {

        @Binds
        abstract ProductDetailsContract.Presenter provideProductDetailsPresenter(ProductDetailsPresenter presenter);

        @Binds
        abstract CartRepository provideCartRepository(LocalCartRepository repository);
    }
}
