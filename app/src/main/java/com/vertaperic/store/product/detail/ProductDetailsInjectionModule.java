/*
 * Project    : RetailStoreApp
 * File       : ProductDetailsInjectionModule
 * Created on : 30/10/17 4:15 AM
 */
package com.vertaperic.store.product.detail;

import com.vertaperic.store.cart.CartItemDao;
import com.vertaperic.store.cart.CartRepository;
import com.vertaperic.store.cart.LocalCartRepository;
import com.vertaperic.store.database.AppDatabase;
import com.vertaperic.store.util.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * The dagger module that provides dependencies for product details functionality.
 *
 * @author Anny Patel
 */
@Module
public abstract class ProductDetailsInjectionModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = {
            Declarations.class,
            Bindings.class
    })
    abstract ProductDetailsFragment contributeProductDetailsFragmentInjector();

    @Module
    static abstract class Declarations {

        @Binds
        abstract ProductDetailsContract.Presenter provideProductDetailsPresenter(ProductDetailsPresenter presenter);

        @Binds
        abstract CartRepository provideCartRepository(LocalCartRepository repository);
    }

    @Module
    static class Bindings {

        @Provides
        static CartItemDao providesCartItemDao(AppDatabase database) {
            return database.cartItemDao();
        }
    }
}
