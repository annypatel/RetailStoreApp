/*
 * Project    : RetailStoreApp
 * File       : SearchComponent
 * Created on : 11/11/16 12:23 PM
 */
package com.vertaperic.store.search;

import dagger.Component;

/**
 * The dagger component for search screen.
 *
 * @author Anny Patel
 */
@Component(modules = SearchModule.class)
interface SearchComponent {

    /**
     * To inject the variables and methods of {@link SearchFragment}.
     *
     * @param fragment The fragment.
     */
    void inject(SearchFragment fragment);
}
