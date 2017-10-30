/*
 * Project    : RetailStoreApp
 * File       : BrowseInjectionModule
 * Created on : 30/10/2017 3:25 PM
 */
package com.vertaperic.store.browse;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * The dagger module that provides dependencies for browse screen.
 *
 * @author Anny Patel
 */
@Module
public abstract class BrowseInjectionModule {

    @ContributesAndroidInjector(modules = Declarations.class)
    abstract BrowseFragment contributeBrowseFragmentInjector();

    @Module
    abstract class Declarations {

        @Binds
        abstract BrowseContract.Presenter provideBrowsePresenter(BrowsePresenter presenter);
    }
}
