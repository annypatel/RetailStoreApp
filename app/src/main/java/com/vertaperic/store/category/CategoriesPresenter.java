/*
 * Project    : RetailStoreApp
 * File       : CategoriesPresenter
 * Created on : 7/11/16 5:55 PM
 */
package com.vertaperic.store.category;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import java.util.List;

import javax.inject.Inject;

/**
 * The presenter for categories view, listens to the user actions from UI({@link CategoriesFragment})
 * and update the UI if required.
 *
 * @author Anny Patel
 */
class CategoriesPresenter implements CategoriesContract.Presenter {

    /**
     * The view attached with this presenter.
     */
    private CategoriesContract.View categoriesView;
    /**
     * The repository for loading categories.
     */
    private CategoryRepository repository;

    /**
     * Constructs new CategoriesPresenter.
     *
     * @param categoriesView The view attached with this presenter.
     * @param repository     The repository for loading categories.
     */
    @Inject
    CategoriesPresenter(@NonNull CategoriesContract.View categoriesView, @NonNull CategoryRepository repository) {
        this.categoriesView = categoriesView;
        this.repository = repository;
    }

    @Override
    public void onToolbarNavigationClick() {
        // do nothing
    }

    @Override
    public boolean onMenuItemClick(@NonNull MenuItem item) {
        // do nothing
        return false;
    }

    @Override
    public void loadCategories(@Nullable Category mainCategory) {
        this.categoriesView.setLoadingIndicator(true);

        // create the loading callback
        CategoryRepository.LoadCategoriesCallback callback = new CategoryRepository.LoadCategoriesCallback() {

            @Override
            public void onCategoriesLoaded(@NonNull List<Category> categories) {
                if (categoriesView.isActive()) {
                    categoriesView.setLoadingIndicator(false);
                    categoriesView.showCategories(categories);
                }
            }

            @Override
            public void onDataNotAvailable() {
                if (categoriesView.isActive()) {
                    categoriesView.setLoadingIndicator(false);
                    categoriesView.showCategoriesNotAvailable();
                }
            }
        };

        // no main category given the load main categories
        if (mainCategory == null) {
            this.repository.getMainCategories(callback);
        } else {
            this.repository.getSubCategories(mainCategory, callback);
        }
    }

    @Override
    public void selectCategory(@NonNull Category category) {
        if (category.hasSubCategories()) {
            this.categoriesView.showSubCategoriesScreen(category);
        } else {
            this.categoriesView.showProductsScreen(category);
        }
    }
}
