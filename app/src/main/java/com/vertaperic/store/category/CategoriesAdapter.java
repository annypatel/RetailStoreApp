/*
 * Project    : RetailStoreApp
 * File       : CategoriesAdapter
 * Created on : 8/11/16 10:23 AM
 */
package com.vertaperic.store.category;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.vertaperic.store.R;

import java.util.List;

/**
 * The recycler adapter for the categories.
 *
 * @author Anny Patel
 */
class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    /**
     * The list of categories to display.
     */
    private List<Category> categories;
    /**
     * The layout inflater.
     */
    private final LayoutInflater inflater;
    /**
     * The presenter for category screen {@link CategoriesFragment}.
     */
    private final CategoriesContract.Presenter presenter;

    /**
     * Constructs new CategoriesAdapter.
     *
     * @param context   The host context.
     * @param presenter The presenter for category screen.
     */
    CategoriesAdapter(Context context, CategoriesContract.Presenter presenter) {
        this.inflater = LayoutInflater.from(context);
        this.presenter = presenter;
    }

    /**
     * To set categories to display.
     *
     * @param categories The list of categories.
     */
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public int getItemCount() {
        return this.categories == null ? 0 : this.categories.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.create(this.inflater, parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(this.categories.get(position), this.presenter);
    }


    /**
     * The view holder for category grid item.
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * The binding instance for category grid item.
         */
        private final CategoryGridItemBinding binding;

        /**
         * @param binding The binding for category grid item.
         */
        private ViewHolder(CategoryGridItemBinding binding) {
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
            CategoryGridItemBinding binding = DataBindingUtil
                    .inflate(inflater, R.layout.grid_item_category, parent, false);

            return new ViewHolder(binding);
        }

        /**
         * To bind category to view.
         *
         * @param category  The category to bind.
         * @param presenter The presenter for categories screen.
         */
        public void bind(Category category, CategoriesContract.Presenter presenter) {
            this.binding.setCategory(category);
            this.binding.setPresenter(presenter);
            this.binding.executePendingBindings();
        }
    }
}
