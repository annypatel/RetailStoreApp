/*
 * Project    : RetailStoreApp
 * File       : HomeInjectionModule
 * Created on : 30/10/17 3:55 AM
 */
package com.vertaperic.store.home;

import com.vertaperic.store.util.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * The dagger module that provides dependencies for home screen.
 *
 * @author Anny Patel
 */
@Module
public abstract class HomeInjectionModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = Declarations.class)
    abstract HomeFragment contributeHomeFragmentInjector();

    @Module
    abstract class Declarations {

        @Binds
        abstract HomeContract.Presenter bindsHomePresenter(HomePresenter presenter);
    }
}
