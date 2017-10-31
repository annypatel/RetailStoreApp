/*
 * Project    : RetailStoreApp
 * File       : LocalCategoryRepository
 * Created on : 8/11/16 2:59 PM
 */
package com.vertaperic.store.category;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.vertaperic.store.util.Simulation;

import java.util.List;

import javax.inject.Inject;

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
    public void getMainCategories(@NonNull final LoadCategoriesCallback callback) {
        new AsyncTask<Void, Void, List<Category>>() {

            @Override
            protected List<Category> doInBackground(Void... params) {
                Simulation.sleep();

                // get main categories from database
                return categoryDao.getMainCategories();
            }

            @Override
            protected void onPostExecute(List<Category> categories) {
                if (categories.isEmpty()) {
                    callback.onDataNotAvailable();
                } else {
                    callback.onCategoriesLoaded(categories);
                }
            }

        }.execute();
    }

    @Override
    public void getSubCategories(@NonNull final Category mainCategory, @NonNull final LoadCategoriesCallback callback) {
        new AsyncTask<Void, Void, List<Category>>() {

            @Override
            protected List<Category> doInBackground(Void... params) {
                Simulation.sleep();

                // get sub categories from database
                return categoryDao.getSubCategories(mainCategory);
            }

            @Override
            protected void onPostExecute(List<Category> categories) {
                if (categories.isEmpty()) {
                    callback.onDataNotAvailable();
                } else {
                    callback.onCategoriesLoaded(categories);
                }
            }

        }.execute();
    }
}
