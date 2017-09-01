/*
 * Project    : RetailStoreApp
 * File       : MyCartPresenter
 * Created on : 11/4/2016 4:16 PM
 */
package com.vertaperic.store.cart;

import android.support.annotation.NonNull;
import android.view.MenuItem;

import java.util.List;

import javax.inject.Inject;

/**
 * The presenter for My Cart view, listens to the user actions from UI(@link {@link MyCartFragment})
 * and update the UI if required.
 *
 * @author Anny Patel
 */
class MyCartPresenter implements MyCartContract.Presenter {

    /**
     * The view attached with this presenter.
     */
    private MyCartContract.View myCartView;
    /**
     * The repository for accessing cart data.
     */
    private CartRepository repository;

    /**
     * Constructs new MyCartPresenter.
     *
     * @param myCartView The view attached with this presenter.
     * @param repository The repository for accessing cart data.
     */
    @Inject
    MyCartPresenter(@NonNull MyCartContract.View myCartView, @NonNull CartRepository repository) {
        this.myCartView = myCartView;
        this.repository = repository;
   }

    @Override
    public void onToolbarNavigationClick() {
        this.myCartView.handleBackPress();
    }

    @Override
    public boolean onMenuItemClick(@NonNull MenuItem item) {
        // do nothing
        return false;
    }

    @Override
    public void loadCartProductItems() {
        this.myCartView.setLoadingIndicator(true);

        // create a callback
        CartRepository.GetCartProductItemsCallback callback = new CartRepository.GetCartProductItemsCallback() {

            @Override
            public void onCartProductItemsLoaded(@NonNull CartProductItems cartProductItems) {
                if (myCartView.isActive()) {
                    myCartView.setLoadingIndicator(false);
                    myCartView.showCartProductItems(cartProductItems);
                }
            }

            @Override
            public void onDataNotAvailable() {
                if (myCartView.isActive()) {
                    myCartView.setLoadingIndicator(false);
                    myCartView.showCartIsEmpty();
                }
            }
        };

        // get cart product items
        this.repository.getCartProductItems(callback);
    }

    @Override
    public void selectCartProductItem(@NonNull CartProductItem cartProductItem) {
        this.myCartView.showProductDetailsScreen(cartProductItem);
    }

    @Override
    public void confirmProductRemoval(@NonNull CartProductItem cartProductItem) {
        this.myCartView.showProductRemovalConfirmation(cartProductItem);
    }

    @Override
    public void removeProductFromCart(@NonNull final CartProductItems cartProductItems, @NonNull CartProductItem cartProductItem) {
        // create the callback
        CartRepository.RemoveProductFromCartCallback cartCallback = new CartRepository.RemoveProductFromCartCallback() {

            @Override
            public void onProductRemoved(@NonNull CartProductItem cartProductItem) {
                if (myCartView.isActive()) {
                    myCartView.showProductRemovedFromCart(cartProductItem);
                    refreshCartProductItems(cartProductItems, cartProductItem);
                }

            }

            @Override
            public void onFailure(@NonNull CartProductItem cartProductItem) {
                if (myCartView.isActive()) {
                    myCartView.showRemovalFromCartFailed(cartProductItem);
                }
            }
        };

        // remove product from cart
        this.repository.removeProductFromCart(cartProductItem, cartCallback);
    }

    /**
     * To refresh cart products after item removal.
     *
     * @param cartProductItems The wrapper for cart products items with total price.
     * @param cartProductItem  The cart product item that was removed.
     */
    private void refreshCartProductItems(@NonNull CartProductItems cartProductItems, @NonNull CartProductItem cartProductItem) {
        // remove the item from list
        List<CartProductItem> items = cartProductItems.getCartProductItems();
        items.remove(cartProductItem);

        // if list is empty that means no product left in cart
        if (items.isEmpty()) {
            this.myCartView.showCartIsEmpty();
            return;
        }

        // recalculate price and show items
        double price = cartProductItems.getTotalPrice() - cartProductItem.getProduct().getPrice();
        this.myCartView.showCartProductItems(new CartProductItems(items, price));
    }
}
