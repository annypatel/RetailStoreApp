/*
 * Project    : RetailStoreApp
 * File       : ProductsContract
 * Created on : 8/11/16 5:21 PM
 */
package com.vertaperic.store.product;

import android.support.annotation.NonNull;

import com.vertaperic.store.mvp.IPresenter;
import com.vertaperic.store.mvp.IView;
import com.vertaperic.store.category.Category;

import java.util.List;

/**
 * This interface specifies the contract between the Products screen view and the presenter.
 *
 * @author Anny Patel
 */
public interface ProductsContract {

    /**
     * The view interface for Products screen functionality.
     */
    interface View extends IView {

        /**
         * To set the visibility of loading indicator.
         *
         * @param active true to show the indicator, false otherwise.
         */
        void setLoadingIndicator(boolean active);

        /**
         * To show the products to user.
         *
         * @param products The products to display.
         */
        void showProducts(@NonNull List<Product> products);

        /**
         * To show error if no products are available.
         */
        void showProductsNotAvailable();

        /**
         * To show the product details screen.
         *
         * @param product The product to display on detail screen.
         */
        void showProductDetailsScreen(@NonNull Product product);
    }

    /**
     * The presenter interface for Products screen functionality.
     */
    interface Presenter extends IPresenter {

        /**
         * To load products for given category.
         *
         * @param category The category.
         */
        void loadProducts(@NonNull Category category);

        /**
         * To select the product, called by data binding library when product card is clicked.
         *
         * @param product The product that was selected.
         */
        void selectProduct(@NonNull Product product);
    }
}
