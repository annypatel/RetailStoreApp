/*
 * Project    : RetailStoreApp
 * File       : CartRepository
 * Created on : 9/11/16 2:24 PM
 */
package com.vertaperic.store.cart;

import android.support.annotation.NonNull;

import com.vertaperic.store.product.Product;

/**
 * The main entry point for accessing products in cart.
 *
 * @author Anny Patel
 */
public interface CartRepository {

    /**
     * To get the products added in the cart.
     *
     * @param callback The callback listener.
     */
    void getCartProductItems(@NonNull GetCartProductItemsCallback callback);

    /**
     * To add product to cart.
     *
     * @param product  The product to add in cart.
     * @param callback The callback listener.
     */
    void addProductToCart(@NonNull Product product, @NonNull AddProductToCartCallback callback);

    /**
     * To remove product from cart.
     *
     * @param cartProductItem The product to remove.
     * @param callback        The callback listener.
     */
    void removeProductFromCart(@NonNull CartProductItem cartProductItem, @NonNull RemoveProductFromCartCallback callback);

    /**
     * To get the cart item for given product.
     *
     * @param product  The product to get cart item for.
     * @param callback The callback listener.
     */
    void getCartItem(@NonNull Product product, @NonNull GetCartItemCallback callback);


    /**
     * The callback for getting products added in cart.
     */
    interface GetCartProductItemsCallback {

        /**
         * Called when products are loaded.
         *
         * @param cartProductItems The wrapper for products in cart.
         */
        void onCartProductItemsLoaded(@NonNull CartProductItems cartProductItems);

        /**
         * Called if no products are added in cart.
         */
        void onDataNotAvailable();
    }

    /**
     * The callback for adding product to cart.
     */
    interface AddProductToCartCallback {

        /**
         * Called when product is successfully added to cart.
         *
         * @param product  The product that was added to cart.
         * @param cartItem The cart item created after product added to cart.
         */
        void onProductAddedToCart(@NonNull Product product, @NonNull CartItem cartItem);

        /**
         * Called if any failure occurred during adding the product to cart.
         *
         * @param product The product to add.
         */
        void onFailure(@NonNull Product product);
    }

    /**
     * Callback for removing product from cart.
     */
    interface RemoveProductFromCartCallback {

        /**
         * Called if product successfully removed from cart.
         *
         * @param cartProductItem The cart item that was removed.
         */
        void onProductRemoved(@NonNull CartProductItem cartProductItem);

        /**
         * Called if any failure occurred during removing the product from cart.
         *
         * @param cartProductItem The product item to remove from cart.
         */
        void onFailure(@NonNull CartProductItem cartProductItem);
    }

    /**
     * The callback for getting cart item for product.
     */
    interface GetCartItemCallback {

        /**
         * Called if cart item was found for given product.
         *
         * @param product  The product to get cart item for.
         * @param cartItem The cart item that was found.
         */
        void onCartItemFound(@NonNull Product product, @NonNull CartItem cartItem);

        /**
         * Called if cart item was not found for given product.
         *
         * @param product The product to get cart item for.
         */
        void onCartItemNotFound(@NonNull Product product);
    }
}
