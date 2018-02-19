/*
 * Project    : RetailStoreApp
 * File       : ProductInjectionModule
 * Created on : 30/10/17 4:05 AM
 */
package com.vertaperic.store.product;

import com.vertaperic.store.cart.CartItemDao;
import com.vertaperic.store.database.AppDatabase;
import com.vertaperic.store.util.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * The dagger module that provides dependencies for product browsing functionality.
 *
 * @author Anny Patel
 */
@Module
public abstract class ProductInjectionModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = {
            Declarations.class,
            Bindings.class
    })
    abstract ProductsFragment contributeProductsFragmentInjector();

    @Module
    static abstract class Declarations {

        @Binds
        abstract ProductsContract.Presenter provideProductsPresenter(ProductsPresenter presenter);

        @Binds
        abstract ProductsRepository provideProductsRepository(LocalProductsRepository repository);
    }

    @Module
    static class Bindings {

        @Provides
        static ProductDao providesProductDao(AppDatabase database) {
            return database.productDao();
        }

        @Provides
        static CartItemDao providesCartItemDao(AppDatabase database) {
            return database.cartItemDao();
        }
    }
}
