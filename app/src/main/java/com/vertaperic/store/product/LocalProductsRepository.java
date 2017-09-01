/*
 * Project    : RetailStoreApp
 * File       : LocalProductsRepository
 * Created on : 8/11/16 5:40 PM
 */
package com.vertaperic.store.product;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.vertaperic.android.database.DatabaseController;
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
     * The database controller.
     */
    private DatabaseController databaseController;

    /**
     * Constructs new LocalProductsRepository.
     *
     * @param databaseController The database controller.
     */
    @Inject
    LocalProductsRepository(@NonNull DatabaseController databaseController) {
        this.databaseController = databaseController;
    }


    @Override
    public void getProducts(@NonNull final Category category, @NonNull final LoadProductsCallback callback) {
        new AsyncTask<Void, Void, List<Product>>() {

            @Override
            protected List<Product> doInBackground(Void... params) {
                Simulation.sleep();

                // get products for category
                ProductDao dao = new ProductDao(databaseController);
                return dao.getProducts(category);
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
