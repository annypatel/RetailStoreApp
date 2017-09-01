/*
 * Project    : RetailStoreApp
 * File       : ProductsComponent
 * Created on : 11/11/16 10:41 AM
 */
package com.vertaperic.store.product;

import com.vertaperic.store.app.AppComponent;
import com.vertaperic.store.util.FragmentScoped;

import dagger.Component;

/**
 * The dagger component for the product browsing, annotated with {@link FragmentScoped} because it
 * depend on {@link AppComponent} which is singleton.
 *
 * @author Anny Patel
 */
@FragmentScoped
@Component(dependencies = AppComponent.class, modules = ProductsModule.class)
interface ProductsComponent {

    /**
     * To inject the variables and methods of {@link ProductsFragment}.
     *
     * @param fragment The fragment.
     */
    void inject(ProductsFragment fragment);
}
