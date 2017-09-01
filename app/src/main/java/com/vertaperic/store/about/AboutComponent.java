/*
 * Project    : RetailStoreApp
 * File       : AboutComponent
 * Created on : 11/11/16 12:08 PM
 */
package com.vertaperic.store.about;

import dagger.Component;

/**
 * The dagger component for about screen.
 *
 * @author Anny Patel
 */
@Component(modules = AboutModule.class)
interface AboutComponent {

    /**
     * To inject the variables and methods of {@link AboutFragment}.
     *
     * @param fragment The fragment.
     */
    void inject(AboutFragment fragment);
}
