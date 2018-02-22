/*
 * Project    : RetailStoreApp
 * File       : ProductDao
 * Created on : 7/11/16 3:26 PM
 */
package com.vertaperic.store.product;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Single;

/**
 * The data access object for the {@link Product}.
 *
 * @author Anny Patel
 */
@Dao
public interface ProductDao {

    /**
     * To get products for category.
     *
     * @param categoryId The id of category to get product for.
     * @return Single for list of products.
     */
    @Query("SELECT * FROM products WHERE categoryId = :categoryId")
    Single<List<Product>> getProducts(Long categoryId);
}
