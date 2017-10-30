/*
 * Project    : RetailStoreApp
 * File       : CategoriesFragment
 * Created on : 11/5/2016 5:31 PM
 */
package com.vertaperic.store.category;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vertaperic.android.support.BreadcrumbTransactionInfo;
import com.vertaperic.android.support.BreadcrumbsController;
import com.vertaperic.store.R;
import com.vertaperic.store.browse.BrowseFragment;
import com.vertaperic.store.mvp.BaseFragment;
import com.vertaperic.store.product.ProductsFragment;
import com.vertaperic.store.widget.GridMarginDecoration;

import java.util.List;

/**
 * CategoriesFragment displays the grid of category or subcategory. If main category is passed
 * to this fragment using {@link #EXTRA_MAIN_CATEGORY} key then subcategories of that category will
 * be displayed otherwise all main categories will be displayed.
 *
 * @author Anny Patel
 */
public class CategoriesFragment extends BaseFragment<CategoriesContract.Presenter>
        implements CategoriesContract.View {

    /**
     * The extra key for providing main category to {@link CategoriesFragment}.
     * <p/>
     * This is optional. If main category is provided then its sub categories will be loaded
     * otherwise all main categories will be loaded.
     */
    private static final String EXTRA_MAIN_CATEGORY = "com.vertaperic.store.MainCategory";

    /**
     * The binding instance for this view.
     */
    private CategoriesFragmentBinding binding;
    /**
     * Adapter for the categories.
     */
    private CategoriesAdapter adapter;

    /**
     * Constructs new CategoriesFragment.
     */
    public CategoriesFragment() {
        // fragment must have public no-argument constructor
    }

    /**
     * To create the new instance of CategoriesFragment.
     *
     * @return The newly created instance.
     */
    public static CategoriesFragment newInstance() {
        return new CategoriesFragment();
    }

    /**
     * To create the new instance of CategoriesFragment with main category.
     *
     * @param mainCategory The main category.
     * @return The newly created instance.
     */
    public static CategoriesFragment newInstance(@NonNull Category mainCategory) {
        Bundle arguments = new Bundle();
        arguments.putSerializable(EXTRA_MAIN_CATEGORY, mainCategory);

        CategoriesFragment fragment = newInstance();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = DataBindingUtil.inflate(inflater, R.layout.fragment_categories, container, false);

        // configure RecyclerView
        this.binding.gridCategories.setHasFixedSize(true);
        this.binding.gridCategories.setLayoutManager(new GridLayoutManager(getContext(), 2));
        final int margin = getResources().getDimensionPixelSize(R.dimen.widget_inner_margin_small);
        this.binding.gridCategories.addItemDecoration(new GridMarginDecoration(margin));

        // create adapter and set it to recycle view
        this.adapter = new CategoriesAdapter(getContext(), presenter());
        this.binding.gridCategories.setAdapter(this.adapter);

        // load categories
        presenter().loadCategories(getMainCategoryFromArguments());

        return this.binding.getRoot();
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        int visibility = active ? View.VISIBLE : View.GONE;
        this.binding.progressBar.setVisibility(visibility);
    }

    @Override
    public void showCategories(@NonNull List<Category> categories) {
        this.binding.gridCategories.setVisibility(View.VISIBLE);
        this.adapter.setCategories(categories);
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public void showCategoriesNotAvailable() {
        // keep list visible so appbar can expand if collapsed
        this.binding.gridCategories.setVisibility(View.VISIBLE);
        this.binding.textMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSubCategoriesScreen(@NonNull Category mainCategory) {
        // create new category fragment
        CategoriesFragment fragment = CategoriesFragment.newInstance(mainCategory);

        // add it to breadcrumbs view and back stack
        getBreadcrumbsController().add(
                new BreadcrumbTransactionInfo(mainCategory.getName(), fragment, R.id.childContainer)
                        .showSeparator(true)
                        .animate(true)
                        .addToBackStack(true)
        );
    }

    @Override
    public void showProductsScreen(@NonNull Category category) {
        // create new product fragment
        ProductsFragment fragment = ProductsFragment.newInstance(category);

        // add it to breadcrumbs view and back stack
        getBreadcrumbsController().add(
                new BreadcrumbTransactionInfo(category.getName(), fragment, R.id.childContainer)
                        .showSeparator(true)
                        .animate(true)
                        .addToBackStack(true)
        );
    }

    /**
     * To get the breadcrumbs controller.
     *
     * @return The breadcrumbs controller.
     */
    private BreadcrumbsController getBreadcrumbsController() {
        return ((BrowseFragment) getParentFragment())
                .getBreadcrumbsController();
    }

    /**
     * To get the main category supplied with the arguments.
     *
     * @return The category if provided, null otherwise.
     */
    @Nullable
    private Category getMainCategoryFromArguments() {
        Bundle arguments = getArguments();

        Category mainCategory = null;
        if (arguments != null) {
            mainCategory = (Category) arguments.getSerializable(EXTRA_MAIN_CATEGORY);
        }
        return mainCategory;
    }
}
