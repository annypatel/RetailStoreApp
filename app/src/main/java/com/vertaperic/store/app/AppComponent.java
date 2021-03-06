/*
 * Project    : RetailStoreApp
 * File       : AppComponent
 * Created on : 10/11/16 7:23 PM
 */
package com.vertaperic.store.app;

import com.vertaperic.store.database.DatabaseModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * The dagger component for the app, manages application level dependencies, annotated as singleton
 * and {@link App} will ensure that only one instance of the class is created.
 *
 * @author Anny Patel
 */
@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppInjectionModule.class,
        AppModule.class,
        DatabaseModule.class
})
interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<App> {
    }
}
