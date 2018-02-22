/*
 * Project    : RetailStoreApp
 * File       : ProductsRepository
 * Created on : 8/11/16 5:32 PM
 */
package com.vertaperic.store.product;

import android.support.annotation.NonNull;

import com.vertaperic.store.category.Category;

import java.util.List;

import io.reactivex.Single;

/**
 * The main entry point for accessing products data.
 *
 * @author Anny Patel
 */
public interface ProductsRepository {

    /**
     * To get products for given category.
     *
     * @param category The category.
     * @return Single of products for given category.
     */
    Single<List<Product>> getProducts(@NonNull Category category);
}
