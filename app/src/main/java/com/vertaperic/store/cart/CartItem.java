/*
 * Project    : RetailStoreApp
 * File       : CartItem
 * Created on : 7/11/16 3:28 PM
 */
package com.vertaperic.store.cart;

import java.io.Serializable;

/**
 * The model for the cart item.
 *
 * @author Anny Patel
 */
public class CartItem implements Serializable {

    /**
     * The unique id for each cart items.
     * <p/>
     * INTEGER AUTO INCREMENT PRIMARY KEY
     */
    private Long cartItemId;
    /**
     * The product id in products table.
     * <p/>
     * INTEGER FOREIGN KEY products(id) NOT NULL
     */
    private Long productId;

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
