/*
 * Project    : RetailStoreApp
 * File       : SearchInjectionModule
 * Created on : 30/10/17 4:23 AM
 */
package com.vertaperic.store.search;

import com.vertaperic.store.util.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * The dagger module that provides dependencies for search screen.
 *
 * @author Anny Patel
 */
@Module
public abstract class SearchInjectionModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = Declarations.class)
    abstract SearchFragment contributeSearchFragmentInjector();

    @Module
    abstract class Declarations {

        @Binds
        abstract SearchContract.Presenter provideSearchPresenter(SearchPresenter presenter);
    }
}
