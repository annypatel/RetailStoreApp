/*
 * Project    : RetailStoreApp
 * File       : MyCartContract
 * Created on : 11/4/2016 3:48 PM
 */
package com.vertaperic.store.cart;

import android.support.annotation.NonNull;

import com.vertaperic.store.app.IPresenter;
import com.vertaperic.store.app.IView;

/**
 * This interface specifies the contract between the My Cart screen view and the presenter.
 *
 * @author Anny Patel
 */
public interface MyCartContract {

    /**
     * The view interface for My Cart screen functionality.
     */
    interface View extends IView {

        /**
         * To set the visibility of loading indicator.
         *
         * @param active true to show the indicator, false otherwise.
         */
        void setLoadingIndicator(boolean active);

        /**
         * To show the products added in cart.
         *
         * @param cartProductItems The wrapper for products added in cart.
         */
        void showCartProductItems(@NonNull CartProductItems cartProductItems);

        /**
         * To show that no products are added in cart.
         */
        void showCartIsEmpty();

        /**
         * To show the product details screen.
         *
         * @param cartProductItem The product item whose details will be displayed.
         */
        void showProductDetailsScreen(@NonNull CartProductItem cartProductItem);

        /**
         * To show the confirmation to remove product from cart.
         *
         * @param cartProductItem The item for which confirmation is needed.
         */
        void showProductRemovalConfirmation(@NonNull CartProductItem cartProductItem);

        /**
         * To show that product was successfully removed from cart.
         *
         * @param cartProductItem The cart product item that was removed.
         */
        void showProductRemovedFromCart(@NonNull CartProductItem cartProductItem);

        /**
         * To show that product removal from cart is failed.
         *
         * @param cartProductItem The product to remove.
         */
        void showRemovalFromCartFailed(@NonNull CartProductItem cartProductItem);

        /**
         * To handle the toolbar back press event.
         */
        void handleBackPress();

        /**
         * To check if view is active or not.
         *
         * @return true if view is still active, otherwise false.
         */
        boolean isActive();
    }

    /**
     * The presenter interface for My Cart screen functionality.
     */
    interface Presenter extends IPresenter {

        /**
         * Called when the toolbar navigation icon is clicked.
         */
        void onToolbarNavigationClick();

        /**
         * To load the products added in cart.
         */
        void loadCartProductItems();

        /**
         * Called when product item is selected, called by data binding library.
         *
         * @param cartProductItem The selected item.
         */
        void selectCartProductItem(@NonNull CartProductItem cartProductItem);

        /**
         * Called when remove product is clicked, called by data binding library.
         *
         * @param cartProductItem The item for which confirmation is needed.
         */
        void confirmProductRemoval(@NonNull CartProductItem cartProductItem);

        /**
         * To remove product from cart.
         *
         * @param cartProductItems The wrapper for cart product items with total price(currently
         *                         shown on UI).
         * @param cartProductItem  The item to remove.
         */
        void removeProductFromCart(@NonNull CartProductItems cartProductItems, @NonNull CartProductItem cartProductItem);
    }
}
