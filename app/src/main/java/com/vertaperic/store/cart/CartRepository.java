/*
 * Project    : RetailStoreApp
 * File       : CartRepository
 * Created on : 9/11/16 2:24 PM
 */
package com.vertaperic.store.cart;

import android.support.annotation.NonNull;

import com.vertaperic.store.product.Product;

import io.reactivex.Single;

/**
 * The main entry point for accessing products in cart.
 *
 * @author Anny Patel
 */
public interface CartRepository {

    /**
     * To get the products added in the cart.
     *
     * @return Single of the products added in cart.
     */
    Single<CartProductItems> getCartProductItems();

    /**
     * To add product to cart.
     *
     * @param product The product to add in cart.
     * @return Single of the cart item.
     */
    Single<CartItem> addProductToCart(@NonNull Product product);

    /**
     * To remove product from cart.
     *
     * @param cartProductItem The product to remove.
     * @return Single of the number of row removed.
     */
    Single<Integer> removeProductFromCart(@NonNull CartProductItem cartProductItem);

    /**
     * To get the cart item for given product.
     *
     * @param product The product to get cart item for.
     * @return Single of cart item for given product.
     */
    Single<CartItem> getCartItem(@NonNull Product product);
}
