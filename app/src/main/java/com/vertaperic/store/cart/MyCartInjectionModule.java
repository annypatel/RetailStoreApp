/*
 * Project    : RetailStoreApp
 * File       : MyCartInjectionModule
 * Created on : 30/10/2017 3:25 PM
 */
package com.vertaperic.store.cart;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * The dagger module that provides dependencies for my cart screen.
 *
 * @author Anny Patel
 */
@Module
public abstract class MyCartInjectionModule {

    @ContributesAndroidInjector(modules = Declarations.class)
    abstract MyCartFragment contributeMyCartFragmentInjector();

    @Module
    abstract class Declarations {

        @Binds
        abstract MyCartContract.Presenter bindsMyCartPresenter(MyCartPresenter presenter);

        @Binds
        abstract CartRepository bindsCartRepository(LocalCartRepository repository);
    }
}
