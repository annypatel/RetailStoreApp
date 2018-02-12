/*
 * Project    : RetailStoreApp
 * File       : MyCartPresenter
 * Created on : 11/4/2016 4:16 PM
 */
package com.vertaperic.store.cart;

import android.support.annotation.NonNull;

import com.vertaperic.store.mvp.BasePresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * The presenter for My Cart view, listens to the user actions from UI(@link {@link MyCartFragment})
 * and update the UI if required.
 *
 * @author Anny Patel
 */
class MyCartPresenter extends BasePresenter<MyCartContract.View>
        implements MyCartContract.Presenter {

    /**
     * The repository for accessing cart data.
     */
    private final CartRepository repository;

    /**
     * Constructs new MyCartPresenter.
     *
     * @param repository The repository for accessing cart data.
     */
    @Inject
    MyCartPresenter(@NonNull CartRepository repository) {
        this.repository = repository;
    }

    @Override
    public void onToolbarNavigationClick() {
        view().handleBackPress();
    }

    @Override
    public void loadCartProductItems() {
        view().setLoadingIndicator(true);

        // create a callback
        CartRepository.GetCartProductItemsCallback callback = new CartRepository.GetCartProductItemsCallback() {

            @Override
            public void onCartProductItemsLoaded(@NonNull CartProductItems cartProductItems) {
                if (isAttached()) {
                    view().setLoadingIndicator(false);
                    view().showCartProductItems(cartProductItems);
                }
            }

            @Override
            public void onDataNotAvailable() {
                if (isAttached()) {
                    view().setLoadingIndicator(false);
                    view().showCartIsEmpty();
                }
            }
        };

        // get cart product items
        this.repository.getCartProductItems(callback);
    }

    @Override
    public void selectCartProductItem(@NonNull CartProductItem cartProductItem) {
        view().showProductDetailsScreen(cartProductItem);
    }

    @Override
    public void confirmProductRemoval(@NonNull CartProductItem cartProductItem) {
        view().showProductRemovalConfirmation(cartProductItem);
    }

    @Override
    public void removeProductFromCart(@NonNull final CartProductItems cartProductItems, @NonNull CartProductItem cartProductItem) {
        // create the callback
        CartRepository.RemoveProductFromCartCallback cartCallback = new CartRepository.RemoveProductFromCartCallback() {

            @Override
            public void onProductRemoved(@NonNull CartProductItem cartProductItem) {
                if (isAttached()) {
                    view().showProductRemovedFromCart(cartProductItem);
                    refreshCartProductItems(cartProductItems, cartProductItem);
                }

            }

            @Override
            public void onFailure(@NonNull CartProductItem cartProductItem) {
                if (isAttached()) {
                    view().showRemovalFromCartFailed(cartProductItem);
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
            view().showCartIsEmpty();
            return;
        }

        // recalculate price and show items
        double price = cartProductItems.getTotalPrice() - cartProductItem.getProduct().getPrice();
        view().showCartProductItems(new CartProductItems(items, price));
    }
}
