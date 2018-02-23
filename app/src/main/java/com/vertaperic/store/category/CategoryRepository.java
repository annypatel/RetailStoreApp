/*
 * Project    : RetailStoreApp
 * File       : CategoryRepository
 * Created on : 8/11/16 2:52 PM
 */
package com.vertaperic.store.category;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Single;

/**
 * The main entry point for accessing categories data.
 *
 * @author Anny Patel
 */
public interface CategoryRepository {

    /**
     * To get the main categories.
     *
     * @return Single of list of category.
     */
    Single<List<Category>> getMainCategories();

    /**
     * To get the sub categories for given main category.
     *
     * @param mainCategory The main category.
     * @return Single of list of category.
     */
    Single<List<Category>> getSubCategories(@NonNull Category mainCategory);
}
