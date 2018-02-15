/*
 * Project    : RetailStoreApp
 * File       : App
 * Created on : 10/30/2016 8:54 PM
 */
package com.vertaperic.store.app;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * Base application class for this app. Use this class to do one time initialization.
 *
 * @author Anny Patel
 */
public class App extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder()
                .create(this);
    }
}
