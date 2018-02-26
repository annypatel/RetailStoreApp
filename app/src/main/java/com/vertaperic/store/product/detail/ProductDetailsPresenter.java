/*
 * Project    : RetailStoreApp
 * File       : ProductDetailsPresenter
 * Created on : 8/11/16 7:20 PM
 */
package com.vertaperic.store.product.detail;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.vertaperic.store.R;
import com.vertaperic.store.app.RxSchedulers;
import com.vertaperic.store.cart.CartItem;
import com.vertaperic.store.cart.CartRepository;
import com.vertaperic.store.mvp.BasePresenter;
import com.vertaperic.store.product.Product;

import javax.inject.Inject;

/**
 * The presenter for product details view, listens to the user actions from UI
 * ({@link ProductDetailsFragment}) and update the UI if required.
 *
 * @author Anny Patel
 */
class ProductDetailsPresenter extends BasePresenter<ProductDetailsContract.View>
        implements ProductDetailsContract.Presenter {

    /**
     * Provider for reactive scheduler.
     */
    private final RxSchedulers schedulers;
    /**
     * The repository for adding the product to cart.
     */
    private final CartRepository cartRepository;

    /**
     * Constructs new ProductDetailsPresenter.
     *
     * @param schedulers     Provider for reactive scheduler.
     * @param cartRepository The repository for adding product to cart.
     */
    @Inject
    ProductDetailsPresenter(@NonNull RxSchedulers schedulers,
                            @NonNull CartRepository cartRepository) {
        this.schedulers = schedulers;
        this.cartRepository = cartRepository;
    }

    @Override
    public void onToolbarNavigationClick() {
        view().closeDetailsScreen();
    }

    @Override
    public boolean onMenuItemClick(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemMyCart) {
            view().showMyCartScreen();
            return true;
        }
        return false;
    }

    @Override
    public void loadProductDetails(@NonNull Product product, @Nullable CartItem cartItem) {
        // if cart item is already available then show it
        if (cartItem != null) {
            view().showProductDetails(product, cartItem);
            return;
        }

        // else get cart item from repository
        view().setLoadingIndicator(true);
        // get cart item for product
        this.cartRepository
                .getCartItem(product)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.mainThread())
                .subscribe(
                        item -> {
                            view().setLoadingIndicator(false);
                            view().showProductDetails(product, item);
                        },
                        t -> {
                            view().setLoadingIndicator(false);
                            view().showProductDetails(product, null);
                        }
                );
    }

    @Override
    public void addProductToCart(@NonNull Product product) {
        // add product to cart
        this.cartRepository
                .addProductToCart(product)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.mainThread())
                .subscribe(
                        cartItem -> view().showProductAddedToCart(product, cartItem),
                        t -> view().showAddToCartFailure(product)
                );
    }
}
