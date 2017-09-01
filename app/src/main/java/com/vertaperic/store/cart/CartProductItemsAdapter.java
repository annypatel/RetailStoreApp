/*
 * Project    : RetailStoreApp
 * File       : CartProductItemsAdapter
 * Created on : 9/11/16 6:32 PM
 */
package com.vertaperic.store.cart;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.vertaperic.store.R;

import java.util.List;

/**
 * The recycler adapter for the cart product items.
 *
 * @author Anny Patel
 */
class CartProductItemsAdapter extends RecyclerView.Adapter<CartProductItemsAdapter.ViewHolder> {

    /**
     * The list of cart product items to display.
     */
    private List<CartProductItem> cartProductItems;
    /**
     * The layout inflater.
     */
    private LayoutInflater inflater;
    /**
     * The presenter for my cart screen {@link MyCartFragment}.
     */
    private MyCartContract.Presenter presenter;

    /**
     * Constructs new CartProductItemsAdapter.
     *
     * @param context   The host context.
     * @param presenter The presenter for my cart screen.
     */
    CartProductItemsAdapter(Context context, MyCartContract.Presenter presenter) {
        this.inflater = LayoutInflater.from(context);
        this.presenter = presenter;
    }

    /**
     * To set the cart product items to display.
     *
     * @param cartProductItems The cart product items to set.
     */
    public void setCartProductItems(List<CartProductItem> cartProductItems) {
        this.cartProductItems = cartProductItems;
    }

    @Override
    public int getItemCount() {
        return this.cartProductItems == null ? 0 : this.cartProductItems.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.create(this.inflater, parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(this.cartProductItems.get(position), this.presenter);
    }

    /**
     * The view holder for cart product list item.
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * The binding instance for cart product list item.
         */
        private CartProductListItemBinding binding;

        /**
         * @param binding The binding for cart product list item.
         */
        private ViewHolder(CartProductListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        /**
         * To create the view holder.
         *
         * @param inflater The layout inflater.
         * @param parent   The view parent.
         * @return The created view holder.
         */
        public static ViewHolder create(LayoutInflater inflater, ViewGroup parent) {
            CartProductListItemBinding binding = DataBindingUtil
                    .inflate(inflater, R.layout.list_item_cart_product_item, parent, false);

            return new ViewHolder(binding);
        }

        /**
         * To bind cart product item to view.
         *
         * @param cartProductItem The product to bind.
         * @param presenter       The presenter for my cart screen.
         */
        public void bind(CartProductItem cartProductItem, MyCartContract.Presenter presenter) {
            this.binding.setCartProductItem(cartProductItem);
            this.binding.setActionHandler(presenter);
            this.binding.executePendingBindings();
        }
    }
}
