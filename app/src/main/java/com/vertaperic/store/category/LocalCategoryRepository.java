/*
 * Project    : RetailStoreApp
 * File       : LocalCategoryRepository
 * Created on : 8/11/16 2:59 PM
 */
package com.vertaperic.store.category;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Concrete implementation of {@link CategoryRepository} which uses local database as data source.
 *
 * @author Anny Patel
 */
class LocalCategoryRepository implements CategoryRepository {

    /**
     * The category DAO.
     */
    private final CategoryDao categoryDao;

    /**
     * Constructs new LocalCategoryRepository.
     *
     * @param categoryDao The category DAO.
     */
    @Inject
    LocalCategoryRepository(@NonNull CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public Single<List<Category>> getMainCategories() {
        // get main categories from database
        return this.categoryDao.getMainCategories();
    }

    @Override
    public Single<List<Category>> getSubCategories(@NonNull final Category mainCategory) {
        // get sub categories from database
        return this.categoryDao.getSubCategories(mainCategory.getId());
    }
}
