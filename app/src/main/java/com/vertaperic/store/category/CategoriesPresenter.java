/*
 * Project    : RetailStoreApp
 * File       : CategoriesPresenter
 * Created on : 7/11/16 5:55 PM
 */
package com.vertaperic.store.category;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.vertaperic.store.mvp.BasePresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * The presenter for categories view, listens to the user actions from UI({@link CategoriesFragment})
 * and update the UI if required.
 *
 * @author Anny Patel
 */
class CategoriesPresenter extends BasePresenter<CategoriesContract.View>
        implements CategoriesContract.Presenter {

    /**
     * The repository for loading categories.
     */
    private CategoryRepository repository;

    /**
     * Constructs new CategoriesPresenter.
     *
     * @param repository The repository for loading categories.
     */
    @Inject
    CategoriesPresenter(@NonNull CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void loadCategories(@Nullable Category mainCategory) {
        view().setLoadingIndicator(true);

        // create the loading callback
        CategoryRepository.LoadCategoriesCallback callback = new CategoryRepository.LoadCategoriesCallback() {

            @Override
            public void onCategoriesLoaded(@NonNull List<Category> categories) {
                if (isAttached()) {
                    view().setLoadingIndicator(false);
                    view().showCategories(categories);
                }
            }

            @Override
            public void onDataNotAvailable() {
                if (isAttached()) {
                    view().setLoadingIndicator(false);
                    view().showCategoriesNotAvailable();
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
            view().showSubCategoriesScreen(category);
        } else {
            view().showProductsScreen(category);
        }
    }
}
