/*
 * Project    : RetailStoreApp
 * File       : MyCartFragment
 * Created on : 11/4/2016 3:39 PM
 */
package com.vertaperic.store.cart;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vertaperic.store.R;
import com.vertaperic.store.mvp.BaseFragment;
import com.vertaperic.store.product.detail.ProductDetailsActivity;
import com.vertaperic.store.widget.ListMarginDecoration;

/**
 * Fragment for displaying the products added in the cart.
 *
 * @author Anny Patel
 */
public class MyCartFragment extends BaseFragment<MyCartContract.Presenter>
        implements MyCartContract.View {

    /**
     * The binding instance for this view.
     */
    private MyCartFragmentBinding binding;
    /**
     * Adapter for cart product items.
     */
    private CartProductItemsAdapter adapter;

    /**
     * Constructs new MyCartFragment.
     */
    public MyCartFragment() {
        // fragment must have public no-argument constructor
    }

    /**
     * To create the new instance of MyCartFragment.
     *
     * @return The newly created instance.
     */
    public static MyCartFragment newInstance() {
        return new MyCartFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_cart, container, false);
        this.binding.setPresenter(presenter());

        // configure RecyclerView
        this.binding.listProducts.setHasFixedSize(true);
        this.binding.listProducts.setLayoutManager(new LinearLayoutManager(getContext()));
        final int margin = getResources().getDimensionPixelSize(R.dimen.widget_inner_margin_small);
        this.binding.listProducts.addItemDecoration(new ListMarginDecoration(margin));

        // create adapter and set it to recycle view
        this.adapter = new CartProductItemsAdapter(getContext(), presenter());
        this.binding.listProducts.setAdapter(this.adapter);

        // load products
        presenter().loadCartProductItems();

        return this.binding.getRoot();
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        int visibility = active ? View.VISIBLE : View.GONE;
        this.binding.progressBar.setVisibility(visibility);
    }

    @Override
    public void showCartProductItems(@NonNull CartProductItems cartProductItems) {
        // add items to adapter
        this.adapter.setCartProductItems(cartProductItems.getCartProductItems());
        this.adapter.notifyDataSetChanged();

        // bind product items to view
        this.binding.setCartProductItems(cartProductItems);
    }

    @Override
    public void showCartIsEmpty() {
        // appbar may be hidden, expand it if required
        this.binding.appbarLayout.setExpanded(true, false);

        // remove bounded item from view
        this.binding.setCartProductItems(null);
        // show error message
        this.binding.textMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProductDetailsScreen(@NonNull CartProductItem cartProductItem) {
        // launch product details activity
        Intent intent = new Intent(getContext(), ProductDetailsActivity.class);
        intent.putExtra(ProductDetailsActivity.EXTRA_PRODUCT, cartProductItem.getProduct());
        intent.putExtra(ProductDetailsActivity.EXTRA_CART_ITEM, cartProductItem.getCartItem());
        startActivity(intent);
    }

    @Override
    public void showProductRemovalConfirmation(@NonNull final CartProductItem cartProductItem) {
        // show confirmation dialog
        new AlertDialog.Builder(getContext())
                .setTitle(R.string.my_cart_remove_confirm_title)
                .setMessage(R.string.my_cart_remove_confirm_message)
                .setPositiveButton(R.string.my_cart_button_remove, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // remove now
                        CartProductItems cartProductItems = binding.getCartProductItems();
                        presenter().removeProductFromCart(cartProductItems, cartProductItem);
                    }
                })
                .setNegativeButton(R.string.my_cart_button_cancel, null)
                .create()
                .show();
    }

    @Override
    public void showProductRemovedFromCart(@NonNull CartProductItem cartProductItem) {
        // show confirmation message
        Snackbar.make(this.binding.coordinatorLayout, R.string.my_cart_item_removed_from_cart, Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void showRemovalFromCartFailed(@NonNull CartProductItem cartProductItem) {
        // show confirmation message
        Snackbar.make(this.binding.coordinatorLayout, R.string.my_cart_unable_to_remove_item_from_cart, Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void handleBackPress() {
        ActivityCompat.finishAfterTransition(getActivity());
    }
}
