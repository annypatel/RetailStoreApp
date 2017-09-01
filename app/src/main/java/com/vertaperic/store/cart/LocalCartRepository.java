/*
 * Project    : RetailStoreApp
 * File       : LocalCartRepository
 * Created on : 9/11/16 2:25 PM
 */
package com.vertaperic.store.cart;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.vertaperic.android.database.DatabaseController;
import com.vertaperic.store.product.Product;
import com.vertaperic.store.util.Simulation;

import java.util.List;

import javax.inject.Inject;

/**
 * @author Anny Patel
 */
public class LocalCartRepository implements CartRepository {

    /**
     * The database controller.
     */
    private DatabaseController databaseController;

    /**
     * Constructs new LocalCartRepository.
     *
     * @param databaseController The database controller.
     */
    @Inject
    LocalCartRepository(@NonNull DatabaseController databaseController) {
        this.databaseController = databaseController;
    }

    @Override
    public void getCartProductItems(@NonNull final GetCartProductItemsCallback callback) {
        new AsyncTask<Void, Void, CartProductItems>() {

            @Override
            @Nullable
            protected CartProductItems doInBackground(Void... params) {
                Simulation.sleep();

                CartItemDao dao = new CartItemDao(databaseController);
                List<CartProductItem> products = dao.getProductsInCart();
                // if no items found
                if (products.isEmpty()) {
                    return null;
                }

                // else get total price
                double price = dao.getTotalPrice();
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

                CartItemDao dao = new CartItemDao(databaseController);
                return dao.addProductToCart(product);
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

                CartItemDao dao = new CartItemDao(databaseController);
                return dao.removeProductFromCart(cartProductItem);
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

                CartItemDao dao = new CartItemDao(databaseController);
                return dao.getCartItem(product);
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
