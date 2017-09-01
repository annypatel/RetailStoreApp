/*
 * Project    : RetailStoreApp
 * File       : ProductDetailsComponent
 * Created on : 10/11/16 9:12 PM
 */
package com.vertaperic.store.product.detail;

import com.vertaperic.store.app.AppComponent;
import com.vertaperic.store.cart.MyCartRepositoryModule;
import com.vertaperic.store.util.FragmentScoped;

import dagger.Component;

/**
 * The dagger component for the product details, annotated with {@link FragmentScoped} because it
 * depend on {@link AppComponent} which is singleton.
 *
 * @author Anny Patel
 */
@FragmentScoped
@Component(dependencies = AppComponent.class, modules = {ProductDetailsModule.class, MyCartRepositoryModule.class})
interface ProductDetailsComponent {

    /**
     * To inject the variables and methods of {@link ProductDetailsFragment}.
     *
     * @param fragment The fragment.
     */
    void inject(ProductDetailsFragment fragment);
}
