/*
 * Project    : RetailStoreApp
 * File       : AppInjectionModule
 * Created on : 29/10/17 11:12 PM
 */
package com.vertaperic.store.app;

import com.vertaperic.store.about.AboutInjectionModule;

import dagger.Module;

/**
 * Application level injection module which includes all feature level injection modules.
 *
 * @author Anny Patel
 */
@Module(includes = {
        AboutInjectionModule.class,
})
class AppInjectionModule {
}
