/*
 * Project    : RetailStoreApp
 * File       : LocalCartRepository
 * Created on : 9/11/16 2:25 PM
 */
package com.vertaperic.store.cart;

import android.support.annotation.NonNull;

import com.vertaperic.store.product.Product;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * @author Anny Patel
 */
public class LocalCartRepository implements CartRepository {

    /**
     * The cart item DAO.
     */
    private final CartItemDao cartItemDao;

    /**
     * Constructs new LocalCartRepository.
     *
     * @param cartItemDao The cart item DAO.
     */
    @Inject
    LocalCartRepository(@NonNull CartItemDao cartItemDao) {
        this.cartItemDao = cartItemDao;
    }

    @Override
    public Single<CartProductItems> getCartProductItems() {
        return cartItemDao
                .getProductsInCart()
                .flatMap(cartProductItems ->

                        Observable.fromIterable(cartProductItems)
                                .reduce(0d, (sum, cartProductItem) -> sum + cartProductItem.getProduct().getPrice())
                                .map(sum -> new CartProductItems(cartProductItems, sum))
                );
    }

    @Override
    public Single<CartItem> addProductToCart(@NonNull final Product product) {
        CartItem cartItem = new CartItem();
        cartItem.setProductId(product.getId());
        return cartItemDao
                .addCartItem(cartItem)
                .toSingle(() -> cartItem);
    }

    @Override
    public Single<Integer> removeProductFromCart(@NonNull final CartProductItem cartProductItem) {
        return cartItemDao.removeCardItem(cartProductItem.getCartItem());
    }

    @Override
    public Single<CartItem> getCartItem(@NonNull final Product product) {
        return cartItemDao.getCartItem(product.getId());
    }
}
