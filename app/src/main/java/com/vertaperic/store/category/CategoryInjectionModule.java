/*
 * Project    : RetailStoreApp
 * File       : CategoryInjectionModule
 * Created on : 30/10/17 3:42 AM
 */
package com.vertaperic.store.category;

import com.vertaperic.store.util.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * The dagger module that provides dependencies for category browsing functionality.
 *
 * @author Anny Patel
 */
@Module
public abstract class CategoryInjectionModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = Declarations.class)
    abstract CategoriesFragment contributeCategoriesFragmentInjector();

    @Module
    abstract class Declarations {

        @Binds
        abstract CategoriesContract.Presenter provideCategoriesPresenter(CategoriesPresenter presenter);

        @Binds
        abstract CategoryRepository provideCategoryRepository(LocalCategoryRepository repository);
    }
}
