/*
 * Project    : RetailStoreApp
 * File       : BrowseComponent
 * Created on : 11/11/16 12:16 PM
 */
package com.vertaperic.store.browse;

import dagger.Component;

/**
 * The dagger component for browse screen.
 *
 * @author Anny Patel
 */
@Component(modules = BrowseModule.class)
interface BrowseComponent {

    /**
     * To inject the variables and methods of {@link BrowseFragment}.
     *
     * @param fragment The fragment.
     */
    void inject(BrowseFragment fragment);
}
