/*
 * Project    : RetailStoreApp
 * File       : MyCartComponent
 * Created on : 10/11/16 7:30 PM
 */
package com.vertaperic.store.cart;

import com.vertaperic.store.app.AppComponent;
import com.vertaperic.store.util.FragmentScoped;

import dagger.Component;

/**
 * The dagger component for the my cart, annotated with {@link FragmentScoped} because it
 * depend on {@link AppComponent} which is singleton.
 *
 * @author Anny Patel
 */
@FragmentScoped
@Component(dependencies = AppComponent.class, modules = {MyCartModule.class, MyCartRepositoryModule.class})
interface MyCartComponent {

    /**
     * To inject the variables and methods of {@link MyCartFragment}.
     *
     * @param fragment The fragment.
     */
    void inject(MyCartFragment fragment);
}
