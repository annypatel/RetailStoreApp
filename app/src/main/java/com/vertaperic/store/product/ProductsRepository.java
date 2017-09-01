/*
 * Project    : RetailStoreApp
 * File       : ProductsRepository
 * Created on : 8/11/16 5:32 PM
 */
package com.vertaperic.store.product;

import android.support.annotation.NonNull;

import com.vertaperic.store.category.Category;

import java.util.List;

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
     * @param callback The lading callback.
     */
    void getProducts(@NonNull Category category,@NonNull LoadProductsCallback callback);

    /**
     * The callback for loading products.
     */
    interface LoadProductsCallback {

        /**
         * Called when products are loaded.
         *
         * @param products The loaded products.
         */
        void onProductsLoaded(@NonNull List<Product> products);

        /**
         * Called if no products are available.
         */
        void onDataNotAvailable();
    }
}
