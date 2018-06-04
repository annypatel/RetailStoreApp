/*
 * Project    : RetailStoreApp
 * File       : ProductsPresenter
 * Created on : 8/11/16 5:31 PM
 */
package com.vertaperic.store.product;

import android.support.annotation.NonNull;

import com.vertaperic.store.app.RxSchedulers;
import com.vertaperic.store.category.Category;
import com.vertaperic.store.mvp.RxBasePresenter;

import javax.inject.Inject;

/**
 * The presenter for products view, listens to the user actions from UI({@link ProductsFragment})
 * and update the UI if required.
 *
 * @author Anny Patel
 */
class ProductsPresenter extends RxBasePresenter<ProductsContract.View>
        implements ProductsContract.Presenter {

    /**
     * Provider for reactive schedulers.
     */
    private final RxSchedulers schedulers;
    /**
     * The repository for loading products.
     */
    private final ProductsRepository repository;

    /**
     * Constructs new ProductsPresenter.
     *
     * @param schedulers Provider for reactive schedulers.
     * @param repository The repository for loading products.
     */
    @Inject
    ProductsPresenter(@NonNull RxSchedulers schedulers,
                      @NonNull ProductsRepository repository) {
        this.schedulers = schedulers;
        this.repository = repository;
    }

    @Override
    public void loadProducts(@NonNull Category category) {
        view().setLoadingIndicator(true);
        // get products from repository
        this.disposables.add(this.repository
                .getProducts(category)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.mainThread())
                .subscribe((products) -> {

                    view().setLoadingIndicator(false);
                    if (products.isEmpty()) {
                        view().showProductsNotAvailable();
                    } else {
                        view().showProducts(products);
                    }
                }));
    }

    @Override
    public void selectProduct(@NonNull Product product) {
        view().showProductDetailsScreen(product);
    }
}
