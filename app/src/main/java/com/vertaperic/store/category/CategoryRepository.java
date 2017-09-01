/*
 * Project    : RetailStoreApp
 * File       : CategoryRepository
 * Created on : 8/11/16 2:52 PM
 */
package com.vertaperic.store.category;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * The main entry point for accessing categories data.
 *
 * @author Anny Patel
 */
public interface CategoryRepository {

    /**
     * To get the main categories.
     *
     * @param callback The loading callback.
     */
    void getMainCategories(@NonNull LoadCategoriesCallback callback);

    /**
     * To get the sub categories for given main category.
     *
     * @param mainCategory The main category.
     * @param callback     The loading callback.
     */
    void getSubCategories(@NonNull Category mainCategory, @NonNull LoadCategoriesCallback callback);

    /**
     * The callback for loading categories.
     */
    interface LoadCategoriesCallback {

        /**
         * Called when categories are loaded.
         *
         * @param categories The loaded categories.
         */
        void onCategoriesLoaded(@NonNull List<Category> categories);

        /**
         * Called if no categories are available.
         */
        void onDataNotAvailable();
    }
}
