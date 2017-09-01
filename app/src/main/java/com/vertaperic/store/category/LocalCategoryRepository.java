/*
 * Project    : RetailStoreApp
 * File       : LocalCategoryRepository
 * Created on : 8/11/16 2:59 PM
 */
package com.vertaperic.store.category;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.vertaperic.android.database.DatabaseController;
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
     * The database controller.
     */
    private DatabaseController databaseController;

    /**
     * Constructs new LocalCategoryRepository.
     *
     * @param databaseController The database controller.
     */
    @Inject
    LocalCategoryRepository(@NonNull DatabaseController databaseController) {
        this.databaseController = databaseController;
    }

    @Override
    public void getMainCategories(@NonNull final LoadCategoriesCallback callback) {
        new AsyncTask<Void, Void, List<Category>>() {

            @Override
            protected List<Category> doInBackground(Void... params) {
                Simulation.sleep();

                // get main categories from database
                CategoryDao dao = new CategoryDao(databaseController);
                return dao.getMainCategories();
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
                CategoryDao dao = new CategoryDao(databaseController);
                return dao.getSubCategories(mainCategory);
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
