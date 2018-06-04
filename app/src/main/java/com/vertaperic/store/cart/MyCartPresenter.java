/*
 * Project    : RetailStoreApp
 * File       : MyCartPresenter
 * Created on : 11/4/2016 4:16 PM
 */
package com.vertaperic.store.cart;

import android.support.annotation.NonNull;

import com.vertaperic.store.app.RxSchedulers;
import com.vertaperic.store.mvp.RxBasePresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * The presenter for My Cart view, listens to the user actions from UI(@link {@link MyCartFragment})
 * and update the UI if required.
 *
 * @author Anny Patel
 */
class MyCartPresenter extends RxBasePresenter<MyCartContract.View>
        implements MyCartContract.Presenter {

    /**
     * Provider for the reactive schedulers.
     */
    private final RxSchedulers schedulers;
    /**
     * The repository for accessing cart data.
     */
    private final CartRepository repository;

    /**
     * Constructs new MyCartPresenter.
     *
     * @param schedulers Provider for the reactive schedulers.
     * @param repository The repository for accessing cart data.
     */
    @Inject
    MyCartPresenter(@NonNull RxSchedulers schedulers,
                    @NonNull CartRepository repository) {
        this.schedulers = schedulers;
        this.repository = repository;
    }

    @Override
    public void onToolbarNavigationClick() {
        view().handleBackPress();
    }

    @Override
    public void loadCartProductItems() {
        view().setLoadingIndicator(true);

        // get cart product items
        this.disposables.add(this.repository
                .getCartProductItems()
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.mainThread())
                .subscribe(cartProductItems ->
                        {
                            view().setLoadingIndicator(false);
                            if (cartProductItems.getCartProductItems().isEmpty()) {
                                view().showCartIsEmpty();
                            } else {
                                view().showCartProductItems(cartProductItems);
                            }
                        },
                        t -> {
                            view().setLoadingIndicator(false);
                            view().showCartIsEmpty();
                        }
                ));
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
        // remove product from cart
        this.disposables.add(this.repository
                .removeProductFromCart(cartProductItem)
                .map(count -> count > 0)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.mainThread())
                .subscribe(
                        deleted -> {
                            if (deleted) {
                                view().showProductRemovedFromCart(cartProductItem);
                                refreshCartProductItems(cartProductItems, cartProductItem);
                            } else {
                                view().showRemovalFromCartFailed(cartProductItem);
                            }
                        },
                        t -> view().showRemovalFromCartFailed(cartProductItem)
                ));
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
