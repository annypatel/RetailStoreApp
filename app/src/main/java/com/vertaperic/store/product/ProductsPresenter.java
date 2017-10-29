/*
 * Project    : RetailStoreApp
 * File       : ProductsPresenter
 * Created on : 8/11/16 5:31 PM
 */
package com.vertaperic.store.product;

import android.support.annotation.NonNull;

import com.vertaperic.store.category.Category;
import com.vertaperic.store.mvp.BasePresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * The presenter for products view, listens to the user actions from UI({@link ProductsFragment})
 * and update the UI if required.
 *
 * @author Anny Patel
 */
class ProductsPresenter extends BasePresenter<ProductsContract.View>
        implements ProductsContract.Presenter {

    /**
     * The repository for loading products.
     */
    private ProductsRepository repository;

    /**
     * Constructs new ProductsPresenter.
     *
     * @param repository The repository for loading products.
     */
    @Inject
    ProductsPresenter(@NonNull ProductsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void loadProducts(@NonNull Category category) {
        view().setLoadingIndicator(true);

        // create loading callback
        ProductsRepository.LoadProductsCallback callback = new ProductsRepository.LoadProductsCallback() {

            @Override
            public void onProductsLoaded(@NonNull List<Product> products) {
                if (isAttached()) {
                    view().setLoadingIndicator(false);
                    view().showProducts(products);
                }
            }

            @Override
            public void onDataNotAvailable() {
                if (isAttached()) {
                    view().setLoadingIndicator(false);
                    view().showProductsNotAvailable();
                }
            }
        };

        // get products from repository
        this.repository.getProducts(category, callback);
    }

    @Override
    public void selectProduct(@NonNull Product product) {
        view().showProductDetailsScreen(product);
    }
}
