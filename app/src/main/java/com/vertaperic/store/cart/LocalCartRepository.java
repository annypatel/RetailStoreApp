/*
 * Project    : RetailStoreApp
 * File       : LocalCartRepository
 * Created on : 9/11/16 2:25 PM
 */
package com.vertaperic.store.cart;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.vertaperic.store.product.Product;
import com.vertaperic.store.util.Simulation;

import java.util.List;

import javax.inject.Inject;

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
    public void getCartProductItems(@NonNull final GetCartProductItemsCallback callback) {
        new AsyncTask<Void, Void, CartProductItems>() {

            @Override
            @Nullable
            protected CartProductItems doInBackground(Void... params) {
                Simulation.sleep();

                List<CartProductItem> products = cartItemDao.getProductsInCart();
                // if no items found
                if (products.isEmpty()) {
                    return null;
                }

                // else get total price
                double price = cartItemDao.getTotalPrice();
                return new CartProductItems(products, price);
            }

            @Override
            protected void onPostExecute(@Nullable CartProductItems cartProductItems) {
                if (cartProductItems == null) {
                    callback.onDataNotAvailable();
                } else {
                    callback.onCartProductItemsLoaded(cartProductItems);
                }
            }

        }.execute();
    }

    @Override
    public void addProductToCart(@NonNull final Product product, @NonNull final AddProductToCartCallback callback) {
        new AsyncTask<Void, Void, CartItem>() {

            @Override
            @Nullable
            protected CartItem doInBackground(Void... params) {
                Simulation.sleep();

                CartItem cartItem = new CartItem();
                cartItem.setProductId(product.getId());
                cartItemDao.addCartItem(cartItem);
                return cartItem;
            }

            @Override
            protected void onPostExecute(@Nullable CartItem cartItem) {
                if (cartItem == null) {
                    callback.onFailure(product);
                } else {
                    callback.onProductAddedToCart(product, cartItem);
                }
            }

        }.execute();
    }

    @Override
    public void removeProductFromCart(@NonNull final CartProductItem cartProductItem, @NonNull final RemoveProductFromCartCallback callback) {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            @NonNull
            protected Boolean doInBackground(Void... params) {
                Simulation.sleep();

                int count = cartItemDao.removeCardItem(cartProductItem.getCartItem());
                return count > 0;
            }

            @Override
            protected void onPostExecute(@NonNull Boolean deleted) {
                if (deleted) {
                    callback.onProductRemoved(cartProductItem);
                } else {
                    callback.onFailure(cartProductItem);
                }
            }

        }.execute();
    }

    @Override
    public void getCartItem(@NonNull final Product product, @NonNull final GetCartItemCallback callback) {
        new AsyncTask<Void, Void, CartItem>() {

            @Override
            @Nullable
            protected CartItem doInBackground(Void... params) {
                Simulation.sleep();

                return cartItemDao.getCartItem(product.getId());
            }

            @Override
            protected void onPostExecute(@Nullable CartItem cartItem) {
                if (cartItem == null) {
                    callback.onCartItemNotFound(product);
                } else {
                    callback.onCartItemFound(product, cartItem);
                }
            }

        }.execute();
    }
}
