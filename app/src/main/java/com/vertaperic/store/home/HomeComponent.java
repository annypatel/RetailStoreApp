/*
 * Project    : RetailStoreApp
 * File       : HomeComponent
 * Created on : 11/11/16 12:20 PM
 */
package com.vertaperic.store.home;

import dagger.Component;

/**
 * The dagger component for home screen.
 *
 * @author Anny Patel
 */
@Component(modules = HomeModule.class)
interface HomeComponent {

    /**
     * To inject the variables and methods of {@link HomeFragment}.
     *
     * @param fragment The fragment.
     */
    void inject(HomeFragment fragment);
}
