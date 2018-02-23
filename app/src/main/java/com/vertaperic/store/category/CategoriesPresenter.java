/*
 * Project    : RetailStoreApp
 * File       : CategoriesPresenter
 * Created on : 7/11/16 5:55 PM
 */
package com.vertaperic.store.category;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.vertaperic.store.app.RxSchedulers;
import com.vertaperic.store.mvp.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * The presenter for categories view, listens to the user actions from UI({@link CategoriesFragment})
 * and update the UI if required.
 *
 * @author Anny Patel
 */
class CategoriesPresenter extends BasePresenter<CategoriesContract.View>
        implements CategoriesContract.Presenter {

    /**
     * Provider for reactive schedulers.
     */
    private final RxSchedulers schedulers;
    /**
     * The repository for loading categories.
     */
    private final CategoryRepository repository;

    /**
     * Constructs new CategoriesPresenter.
     *
     * @param schedulers Provider for reactive schedulers.
     * @param repository The repository for loading categories.
     */
    @Inject
    CategoriesPresenter(@NonNull RxSchedulers schedulers,
                        @NonNull CategoryRepository repository) {
        this.schedulers = schedulers;
        this.repository = repository;
    }

    @Override
    public void loadCategories(@Nullable Category mainCategory) {
        view().setLoadingIndicator(true);

        // no main category given the load main categories
        final Single<List<Category>> categoriesSingle;
        if (mainCategory == null) {
            categoriesSingle = this.repository.getMainCategories();
        } else {
            categoriesSingle = this.repository.getSubCategories(mainCategory);
        }

        categoriesSingle
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.mainThread())
                .subscribe(categories -> {

                    view().setLoadingIndicator(false);
                    if (categories.isEmpty()) {
                        view().showCategoriesNotAvailable();
                    } else {
                        view().showCategories(categories);
                    }
                });
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
