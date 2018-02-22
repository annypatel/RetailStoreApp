/*
 * Project    : RetailStoreApp
 * File       : AppModule
 * Created on : 2/22/16 7:23 PM
 */
package com.vertaperic.store.app;

import dagger.Binds;
import dagger.Module;


/**
 * The module for providing application level dependencies.
 *
 * @author Anny Patel
 */
@Module(includes = {
        AppModule.Bindings.class
})
class AppModule {

    @Module
    static abstract class Bindings {

        @Binds
        abstract RxSchedulers bindRxSchedulers(AppRxSchedulers schedulers);
    }
}
