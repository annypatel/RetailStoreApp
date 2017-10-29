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

    @Provides
    CategoriesContract.Presenter provideCategoriesPresenter(CategoriesPresenter presenter) {
        return presenter;
    }

    @Provides
    CategoryRepository provideCategoryRepository(LocalCategoryRepository repository) {
        return repository;
    }
}
