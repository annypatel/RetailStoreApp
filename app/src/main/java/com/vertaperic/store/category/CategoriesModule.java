/*
 * Project    : RetailStoreApp
 * File       : CategoriesModule
 * Created on : 11/11/16 11:42 AM
 */
package com.vertaperic.store.category;

import dagger.Module;
import dagger.Provides;

/**
 * The dagger module that provides dependencies for category browsing functionality.
 *
 * @author Anny Patel
 */
@Module
class CategoriesModule {

    /**
     * The view for category browsing screen.
     */
    private final CategoriesContract.View view;

    /**
     * @param view The view for category browsing screen.
     */
    CategoriesModule(CategoriesContract.View view) {
        this.view = view;
    }

    @Provides
    CategoriesContract.View provideCategoriesView() {
        return view;
    }

    @Provides
    CategoriesContract.Presenter provideCategoriesPresenter(CategoriesPresenter presenter) {
        return presenter;
    }

    @Provides
    CategoryRepository provideCategoryRepository(LocalCategoryRepository repository) {
        return repository;
    }
}
