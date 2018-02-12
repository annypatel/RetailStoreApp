/*
 * Project    : RetailStoreApp
 * File       : CartProductItem
 * Created on : 9/11/16 5:55 PM
 */
package com.vertaperic.store.cart;

import com.vertaperic.store.product.Product;

/**
 * The holder class for the product and cart item.
 *
 * @author Anny Patel
 */
public class CartProductItem {

    /**
     * The cart item for the product.
     */
    private final CartItem cartItem;
    /**
     * The product added in the cart.
     */
    private final Product product;

    /**
     * Constructs new CartProductItem.
     *
     * @param cartItem The cart item for the product
     * @param product  The product added in the cart.
     */
    public CartProductItem(CartItem cartItem, Product product) {
        this.cartItem = cartItem;
        this.product = product;
    }

    public CartItem getCartItem() {
        return cartItem;
    }

    public Product getProduct() {
        return product;
    }
}
