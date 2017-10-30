/*
 * Project    : RetailStoreApp
 * File       : ProductsFragment
 * Created on : 8/11/16 5:21 PM
 */
package com.vertaperic.store.product;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vertaperic.store.R;
import com.vertaperic.store.category.Category;
import com.vertaperic.store.mvp.BaseFragment;
import com.vertaperic.store.product.detail.ProductDetailsActivity;
import com.vertaperic.store.widget.ListMarginDecoration;

import java.util.List;

/**
 * ProductFragment will displays the list of products by specified category. Pass category to this
 * fragment using the {@link #EXTRA_CATEGORY} key(mandatory).
 * <p/>
 * The UI of this fragment displays the list of product with the hardcoded star rating for demo
 * purpose.
 *
 * @author Anny Patel
 */
public class ProductsFragment extends BaseFragment<ProductsContract.Presenter>
        implements ProductsContract.View {

    /**
     * The extra name for category of products. To display products its mandatory to pass
     * {@link Category} with this key.
     */
    private static final String EXTRA_CATEGORY = "com.vertaperic.store.Category";

    /**
     * The binding instance for this view.
     */
    private ProductsFragmentBinding binding;
    /**
     * Adapter for the products.
     */
    private ProductsAdapter adapter;

    /**
     * Constructs new ProductsFragment.
     */
    public ProductsFragment() {
        // fragment must have public no-argument constructor
    }

    /**
     * To create the new instance of ProductsFragment.
     *
     * @param category The category of which products will be displayed.
     * @return The newly created instance.
     */
    public static ProductsFragment newInstance(@NonNull Category category) {
        Bundle arguments = new Bundle();
        arguments.putSerializable(EXTRA_CATEGORY, category);

        ProductsFragment fragment = new ProductsFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_products, container, false);

        // configure RecyclerView
        this.binding.listProducts.setHasFixedSize(true);
        this.binding.listProducts.setLayoutManager(new LinearLayoutManager(getContext()));
        final int margin = getResources().getDimensionPixelSize(R.dimen.widget_inner_margin_small);
        this.binding.listProducts.addItemDecoration(new ListMarginDecoration(margin));

        // create adapter and set it to recycle view
        this.adapter = new ProductsAdapter(getContext(), presenter());
        this.binding.listProducts.setAdapter(this.adapter);

        // load products
        presenter().loadProducts(getCategoryFromArguments());

        return this.binding.getRoot();
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        int visibility = active ? View.VISIBLE : View.GONE;
        this.binding.progressBar.setVisibility(visibility);
    }

    @Override
    public void showProducts(@NonNull List<Product> products) {
        this.binding.listProducts.setVisibility(View.VISIBLE);
        this.adapter.setProducts(products);
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public void showProductsNotAvailable() {
        // keep list visible so appbar can expand if collapsed
        this.binding.listProducts.setVisibility(View.VISIBLE);
        this.binding.textMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProductDetailsScreen(@NonNull Product product) {
        Intent intent = new Intent(getContext(), ProductDetailsActivity.class);
        intent.putExtra(ProductDetailsActivity.EXTRA_PRODUCT, product);
        startActivity(intent);
    }

    /**
     * To get the category supplied with the arguments.
     *
     * @return The category.
     */
    @NonNull
    private Category getCategoryFromArguments() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            throw new IllegalArgumentException("argument bundle is null");
        }

        Category category = (Category) arguments.getSerializable(EXTRA_CATEGORY);
        if (category == null) {
            throw new IllegalArgumentException("category is null");
        }
        return category;
    }
}
