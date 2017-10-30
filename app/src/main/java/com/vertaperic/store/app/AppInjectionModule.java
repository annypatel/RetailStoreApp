/*
 * Project    : RetailStoreApp
 * File       : AppInjectionModule
 * Created on : 29/10/17 11:12 PM
 */
package com.vertaperic.store.app;

import com.vertaperic.store.about.AboutInjectionModule;
import com.vertaperic.store.browse.BrowseInjectionModule;
import com.vertaperic.store.cart.MyCartInjectionModule;
import com.vertaperic.store.category.CategoryInjectionModule;
import com.vertaperic.store.home.HomeInjectionModule;
import com.vertaperic.store.product.ProductInjectionModule;
import com.vertaperic.store.product.detail.ProductDetailsInjectionModule;
import com.vertaperic.store.search.SearchInjectionModule;

import dagger.Module;

/**
 * Application level injection module which includes all feature level injection modules.
 *
 * @author Anny Patel
 */
@Module(includes = {
        HomeInjectionModule.class,
        BrowseInjectionModule.class,
        CategoryInjectionModule.class,
        ProductInjectionModule.class,
        ProductDetailsInjectionModule.class,
        MyCartInjectionModule.class,
        SearchInjectionModule.class,
        AboutInjectionModule.class,
})
class AppInjectionModule {
}
