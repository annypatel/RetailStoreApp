/*
 * Project    : RetailStoreApp
 * File       : CategoryDao
 * Created on : 7/11/16 3:16 PM
 */
package com.vertaperic.store.category;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Single;

/**
 * The data access object for the {@link Category}.
 *
 * @author Anny Patel
 */
@Dao
public interface CategoryDao {

    /**
     * To get main categories(categories with no parent has -1 as parentCategoryId).
     *
     * @return The Single of list of main categories.
     */
    @Query("SELECT * FROM categories WHERE parentCategoryId = -1")
    Single<List<Category>> getMainCategories();

    /**
     * To get the sub categories.
     *
     * @param mainCategoryId The main category id.
     * @return The Single of list of sub categories.
     */
    @Query("SELECT * FROM categories WHERE parentCategoryId = :mainCategoryId")
    Single<List<Category>> getSubCategories(Long mainCategoryId);
}
