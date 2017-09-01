/*
 * Project    : RetailStoreApp
 * File       : ProductsPresenter
 * Created on : 8/11/16 5:31 PM
 */
package com.vertaperic.store.product;

import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.vertaperic.store.category.Category;

import java.util.List;

import javax.inject.Inject;

/**
 * The presenter for products view, listens to the user actions from UI({@link ProductsFragment})
 * and update the UI if required.
 *
 * @author Anny Patel
 */
class ProductsPresenter implements ProductsContract.Presenter {

    /**
     * The view attached with this presenter.
     */
    private ProductsContract.View productsView;
    /**
     * The repository for loading products.
     */
    private ProductsRepository repository;

    /**
     * Constructs new ProductsPresenter.
     *
     * @param productsView The view attached with this presenter.
     * @param repository   The repository for loading products.
     */
    @Inject
    ProductsPresenter(@NonNull ProductsContract.View productsView, @NonNull ProductsRepository repository) {
        this.productsView = productsView;
        this.repository = repository;
    }

    @Override
    public void onToolbarNavigationClick() {
        // do nothing
    }

    @Override
    public boolean onMenuItemClick(@NonNull MenuItem item) {
        // do nothing
        return false;
    }

    @Override
    public void loadProducts(@NonNull Category category) {
        this.productsView.setLoadingIndicator(true);

        // create loading callback
        ProductsRepository.LoadProductsCallback callback = new ProductsRepository.LoadProductsCallback() {

            @Override
            public void onProductsLoaded(@NonNull List<Product> products) {
                if (productsView.isActive()) {
                    productsView.setLoadingIndicator(false);
                    productsView.showProducts(products);
                }
            }

            @Override
            public void onDataNotAvailable() {
                if (productsView.isActive()) {
                    productsView.setLoadingIndicator(false);
                    productsView.showProductsNotAvailable();
                }
            }
        };

        // get products from repository
        this.repository.getProducts(category, callback);
    }

    @Override
    public void selectProduct(@NonNull Product product) {
        this.productsView.showProductDetailsScreen(product);
    }
}
