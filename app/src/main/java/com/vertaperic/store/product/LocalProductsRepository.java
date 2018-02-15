/*
 * Project    : RetailStoreApp
 * File       : LocalProductsRepository
 * Created on : 8/11/16 5:40 PM
 */
package com.vertaperic.store.product;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.vertaperic.store.category.Category;
import com.vertaperic.store.util.Simulation;

import java.util.List;

import javax.inject.Inject;

/**
 * Concrete implementation of {@link ProductsRepository} which uses local database as data source.
 *
 * @author Anny Patel
 */
class LocalProductsRepository implements ProductsRepository {

    /**
     * The product DAO.
     */
    private final ProductDao productDao;

    /**
     * Constructs new LocalProductsRepository.
     *
     * @param productDao The product DAO.
     */
    @Inject
    LocalProductsRepository(@NonNull ProductDao productDao) {
        this.productDao = productDao;
    }


    @Override
    public void getProducts(@NonNull final Category category, @NonNull final LoadProductsCallback callback) {
        new AsyncTask<Void, Void, List<Product>>() {

            @Override
            protected List<Product> doInBackground(Void... params) {
                Simulation.sleep();

                // get products for category
                return productDao.getProducts(category.getId());
            }

            @Override
            protected void onPostExecute(List<Product> products) {
                if (products.isEmpty()) {
                    callback.onDataNotAvailable();
                } else {
                    callback.onProductsLoaded(products);
                }
            }

        }.execute();
    }
}
