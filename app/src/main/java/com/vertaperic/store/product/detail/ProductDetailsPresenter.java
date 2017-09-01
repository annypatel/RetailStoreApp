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
import com.vertaperic.store.cart.CartItem;
import com.vertaperic.store.cart.CartRepository;
import com.vertaperic.store.product.Product;

import javax.inject.Inject;

/**
 * The presenter for product details view, listens to the user actions from UI
 * ({@link ProductDetailsFragment}) and update the UI if required.
 *
 * @author Anny Patel
 */
class ProductDetailsPresenter implements ProductDetailsContract.Presenter {

    /**
     * The view attached with this presenter.
     */
    private ProductDetailsContract.View detailsView;
    /**
     * The repository for adding the product to cart.
     */
    private CartRepository cartRepository;

    /**
     * Constructs new ProductDetailsPresenter.
     *
     * @param detailsView    The view to attach with this presenter.
     * @param cartRepository The repository for adding product to cart.
     */
    @Inject
    ProductDetailsPresenter(@NonNull ProductDetailsContract.View detailsView, @NonNull CartRepository cartRepository) {
        this.detailsView = detailsView;
        this.cartRepository = cartRepository;
    }

    @Override
    public void onToolbarNavigationClick() {
        this.detailsView.closeDetailsScreen();
    }

    @Override
    public boolean onMenuItemClick(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemMyCart) {
            this.detailsView.showMyCartScreen();
            return true;
        }
        return false;
    }

    @Override
    public void loadProductDetails(@NonNull Product product, @Nullable CartItem cartItem) {
        // if cart item is already available then show it
        if (cartItem != null) {
            this.detailsView.showProductDetails(product, cartItem);
            return;
        }

        // else get cart item from repository
        this.detailsView.setLoadingIndicator(true);
        // create the callback
        CartRepository.GetCartItemCallback callback = new CartRepository.GetCartItemCallback() {

            @Override
            public void onCartItemFound(@NonNull Product product, @NonNull CartItem cartItem) {
                if (detailsView.isActive()) {
                    detailsView.setLoadingIndicator(false);
                    detailsView.showProductDetails(product, cartItem);
                }
            }

            @Override
            public void onCartItemNotFound(@NonNull Product product) {
                if (detailsView.isActive()) {
                    detailsView.setLoadingIndicator(false);
                    detailsView.showProductDetails(product, null);
                }
            }
        };

        // get cart item for product
        this.cartRepository.getCartItem(product, callback);
    }

    @Override
    public void addProductToCart(@NonNull Product product) {
        // create the callback
        CartRepository.AddProductToCartCallback callback = new CartRepository.AddProductToCartCallback() {

            @Override
            public void onProductAddedToCart(@NonNull Product product, @NonNull CartItem cartItem) {
                if (detailsView.isActive()) {
                    detailsView.showProductAddedToCart(product, cartItem);
                }
            }

            @Override
            public void onFailure(@NonNull Product product) {
                if (detailsView.isActive()) {
                    detailsView.showAddToCartFailure(product);
                }
            }
        };

        // add product to cart
        this.cartRepository.addProductToCart(product, callback);
    }
}
