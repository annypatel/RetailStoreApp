/*
 * Project    : RetailStoreApp
 * File       : ProductDetailsContract
 * Created on : 8/11/16 7:20 PM
 */
package com.vertaperic.store.product.detail;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.vertaperic.store.app.IPresenter;
import com.vertaperic.store.app.IView;
import com.vertaperic.store.cart.CartItem;
import com.vertaperic.store.product.Product;

/**
 * This interface specifies the contract between the Product details screen view and the presenter.
 *
 * @author Anny Patel
 */
public interface ProductDetailsContract {

    /**
     * The view interface for Product details screen functionality.
     */
    interface View extends IView {

        /**
         * To set the visibility of loading indicator.
         *
         * @param active true to show the indicator, false otherwise.
         */
        void setLoadingIndicator(boolean active);

        /**
         * To show the product details with the cart item(if available).
         *
         * @param product  The product to display.
         * @param cartItem The cart item to display, can be null if not found.
         */
        void showProductDetails(@NonNull Product product, @Nullable CartItem cartItem);

        /**
         * To show that product was successfully added to cart.
         *
         * @param product  The product that was added to cart.
         * @param cartItem The cart item created by adding product to cart.
         */
        void showProductAddedToCart(@NonNull Product product, @NonNull CartItem cartItem);

        /**
         * To show the failure occurred while adding product to cart.
         *
         * @param product The product to add to cart.
         */
        void showAddToCartFailure(@NonNull Product product);

        /**
         * To close the details screen.
         */
        void closeDetailsScreen();

        /**
         * To show my cart screen.
         */
        void showMyCartScreen();

        /**
         * To check if view is active or not.
         *
         * @return true if view is still active, otherwise false.
         */
        boolean isActive();
    }

    /**
     * The presenter interface for Product details screen functionality.
     */
    interface Presenter extends IPresenter {

        /**
         * Called when the toolbar navigation icon is clicked.
         */
        void onToolbarNavigationClick();

        /**
         * Called when toolbar menu item is clicked.
         *
         * @param item The item that was clicked.
         * @return true if the event was handled, false otherwise.
         */
        boolean onMenuItemClick(@NonNull MenuItem item);

        /**
         * To load the product details.
         *
         * @param product  The product to get details for.
         * @param cartItem The cart item if available.
         */
        void loadProductDetails(@NonNull Product product, @Nullable CartItem cartItem);

        /**
         * To add product to the cart.
         *
         * @param product The product to add.
         */
        void addProductToCart(@NonNull Product product);
    }
}
