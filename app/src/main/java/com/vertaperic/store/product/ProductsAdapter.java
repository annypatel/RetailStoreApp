/*
 * Project    : RetailStoreApp
 * File       : ProductsAdapter
 * Created on : 8/11/16 5:29 PM
 */
package com.vertaperic.store.product;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.vertaperic.store.R;

import java.util.List;

/**
 * The recycler adapter for the products.
 *
 * @author Anny Patel
 */
class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    /**
     * The list of products to display.
     */
    private List<Product> products;
    /**
     * The layout inflater.
     */
    private final LayoutInflater inflater;
    /**
     * The presenter for products screen {@link ProductsFragment}.
     */
    private final ProductsContract.Presenter presenter;

    /**
     * Constructs new ProductsAdapter.
     *
     * @param context   The host context.
     * @param presenter The presenter for products screen.
     */
    ProductsAdapter(Context context, ProductsContract.Presenter presenter) {
        this.inflater = LayoutInflater.from(context);
        this.presenter = presenter;
    }

    /**
     * To set the products to show in adapter.
     *
     * @param products The list of products.
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public int getItemCount() {
        return this.products == null ? 0 : this.products.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.create(this.inflater, parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(this.products.get(position), this.presenter);
    }

    /**
     * The view holder for product list item.
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * The binding instance for product list item.
         */
        private final ProductListItemBinding binding;

        /**
         * @param binding The binding for product list item.
         */
        private ViewHolder(ProductListItemBinding binding) {
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
            ProductListItemBinding binding = DataBindingUtil
                    .inflate(inflater, R.layout.list_item_product, parent, false);

            return new ViewHolder(binding);
        }

        /**
         * To bind product to view.
         *
         * @param product   The product to bind.
         * @param presenter The presenter for products screen.
         */
        public void bind(Product product, ProductsContract.Presenter presenter) {
            this.binding.setProduct(product);
            this.binding.setPresenter(presenter);
            this.binding.executePendingBindings();
        }
    }
}
