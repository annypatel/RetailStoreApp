/*
 * Project    : RetailStoreApp
 * File       : AboutInjectionModule
 * Created on : 30/10/2017 3:25 PM
 */
package com.vertaperic.store.about;

import com.vertaperic.store.util.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * The dagger module that provides dependencies for about screen.
 *
 * @author Anny Patel
 */
@Module
public abstract class AboutInjectionModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = Declarations.class)
    abstract AboutFragment contributeAboutFragmentInjector();

    @Module
    abstract class Declarations {

        @Binds
        abstract AboutContract.Presenter bindsAboutPresenter(AboutPresenter presenter);
    }
}
