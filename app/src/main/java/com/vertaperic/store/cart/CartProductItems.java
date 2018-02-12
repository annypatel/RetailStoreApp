/*
 * Project    : RetailStoreApp
 * File       : CartProductItems
 * Created on : 9/11/16 6:26 PM
 */
package com.vertaperic.store.cart;

import java.util.List;

/**
 * The holder class for products that are added in cart with total price.
 *
 * @author Anny Patel
 */
public class CartProductItems {

    /**
     * The products added in cart.
     */
    private final List<CartProductItem> cartProductItems;
    /**
     * The sum of price of products added in cart.
     */
    private final double totalPrice;

    /**
     * Constructs new CartProductItem.
     *
     * @param cartProductItems The products added in cart.
     * @param totalPrice       The sum of price of products added in cart.
     */
    public CartProductItems(List<CartProductItem> cartProductItems, double totalPrice) {
        this.cartProductItems = cartProductItems;
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public List<CartProductItem> getCartProductItems() {
        return cartProductItems;
    }
}
