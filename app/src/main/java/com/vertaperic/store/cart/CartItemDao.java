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

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * The data access object for cart items.
 *
 * @author Anny Patel
 */
@Dao
public abstract class CartItemDao {

    /**
     * To add item to cart.
     *
     * @param cartItem The cart item to add.
     * @return Completable for adding item to cart table.
     */
    Completable addCartItem(CartItem cartItem) {
        return Completable.fromRunnable(() -> internalAddCartItem(cartItem));
    }

    /**
     * To add item to cart.
     *
     * @param cartItem The cart item to add.
     */
    @Insert
    protected abstract void internalAddCartItem(CartItem cartItem);

    /**
     * To remove item from cart.
     *
     * @param cartItem The cart item to remove.
     * @return Single of the number of row removed.
     */
    Single<Integer> removeCardItem(CartItem cartItem) {
        return Single.fromCallable(() -> internalRemoveCardItem(cartItem));
    }

    /**
     * To remove item from cart.
     *
     * @param cartItem The cart item to remove.
     * @return Number of row removed.
     */
    @Delete
    protected abstract int internalRemoveCardItem(CartItem cartItem);

    /**
     * To get cart item for given product id.
     *
     * @param productId The product id.
     * @return Single of the cart item.
     */
    @Query("SELECT * FROM cart WHERE productId = :productId")
    abstract Single<CartItem> getCartItem(Long productId);

    /**
     * To get the products added in cart.
     *
     * @return Single of the list of cart product items.
     */
    @Query("SELECT products.*, cart.* FROM products, cart WHERE products.id = cart.productId")
    abstract Single<List<CartProductItem>> getProductsInCart();
}