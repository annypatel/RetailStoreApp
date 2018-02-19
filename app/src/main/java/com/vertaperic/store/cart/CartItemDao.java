/*
 * Project    : RetailStoreApp
 * File       : CartItemDao
 * Created on : 7/11/16 3:31 PM
 */
package com.vertaperic.store.cart;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * The data access object for cart items.
 *
 * @author Anny Patel
 */
@Dao
public interface CartItemDao {

    /**
     * To add item to cart.
     *
     * @param cartItem The cart item to add.
     */
    @Insert
    void addCartItem(CartItem cartItem);

    /**
     * To get cart item for given product id.
     *
     * @param productId The product id.
     * @return The cart item if found, null otherwise.
     */
    @Query("SELECT * FROM cart WHERE productId = :productId")
    CartItem getCartItem(Long productId);

    /**
     * To remove item from cart.
     *
     * @param cartItem The cart item to remove.
     * @return Number of row removed.
     */
    @Delete
    int removeCardItem(CartItem cartItem);

    /**
     * To get the products added in cart.
     *
     * @return The list of cart product items.
     */
    @Query("SELECT products.*, cart.* FROM products, cart WHERE products.id = cart.productId")
    List<CartProductItem> getProductsInCart();

    /**
     * To get the sum of price of products added in cart.
     *
     * @return The total price.
     */
    @Query("SELECT sum(products.price) FROM products, cart WHERE products.id = cart.productId")
    double getTotalPrice();
}
