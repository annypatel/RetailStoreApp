/*
 * Project    : RetailStoreApp
 * File       : CategoriesComponent
 * Created on : 11/11/16 11:42 AM
 */
package com.vertaperic.store.category;

import com.vertaperic.store.app.AppComponent;
import com.vertaperic.store.util.FragmentScoped;

import dagger.Component;

/**
 * The dagger component for the category browsing, annotated with {@link FragmentScoped} because
 * it depend on {@link AppComponent} which is singleton.
 *
 * @author Anny Patel
 */
@FragmentScoped
@Component(dependencies = AppComponent.class, modules = CategoriesModule.class)
interface CategoriesComponent {

    /**
     * To inject the variables and methods of {@link CategoriesFragment}.
     *
     * @param fragment The fragment.
     */
    void inject(CategoriesFragment fragment);
}
