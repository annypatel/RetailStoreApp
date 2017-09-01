/*
 * Project    : RetailStoreApp
 * File       : CategoriesContract
 * Created on : 7/11/16 5:53 PM
 */
package com.vertaperic.store.category;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.vertaperic.store.app.BasePresenter;
import com.vertaperic.store.app.BaseView;

import java.util.List;

/**
 * This interface specifies the contract between the Categories screen view and the presenter.
 *
 * @author Anny Patel
 */
public interface CategoriesContract {

    /**
     * The view interface for Categories screen functionality.
     */
    interface View extends BaseView {

        /**
         * To set the visibility of loading indicator.
         *
         * @param active true to show the indicator, false otherwise.
         */
        void setLoadingIndicator(boolean active);

        /**
         * To show the categories to user.
         *
         * @param categories The categories to display.
         */
        void showCategories(@NonNull List<Category> categories);

        /**
         * To show error if no categories are available.
         */
        void showCategoriesNotAvailable();

        /**
         * To show sub categories screen for given main category.
         *
         * @param mainCategory The main category.
         */
        void showSubCategoriesScreen(@NonNull Category mainCategory);

        /**
         * To show products screen for given category.
         *
         * @param category The category.
         */
        void showProductsScreen(@NonNull Category category);

        /**
         * To check if view is active or not.
         *
         * @return true if view is still active, otherwise false.
         */
        boolean isActive();
    }

    /**
     * The presenter interface for Categories screen functionality.
     */
    interface Presenter extends BasePresenter {

        /**
         * To load categories.
         *
         * @param mainCategory The main category if available, null otherwise.
         */
        void loadCategories(@Nullable Category mainCategory);

        /**
         * To select the category, called by data binding library when category card is clicked.
         *
         * @param category The category that was clicked.
         */
        void selectCategory(@NonNull Category category);
    }
}
