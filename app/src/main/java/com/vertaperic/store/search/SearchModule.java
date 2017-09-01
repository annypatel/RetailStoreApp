/*
 * Project    : RetailStoreApp
 * File       : SearchModule
 * Created on : 11/11/16 12:23 PM
 */
package com.vertaperic.store.search;

import dagger.Module;
import dagger.Provides;

/**
 * The dagger module that provides dependencies for search screen.
 *
 * @author Anny Patel
 */
@Module
class SearchModule {

    /**
     * The view for search screen.
     */
    private final SearchContract.View view;

    /**
     * @param view The view for search screen.
     */
    SearchModule(SearchContract.View view) {
        this.view = view;
    }

    @Provides
    SearchContract.View provideSearchView() {
        return view;
    }

    @Provides
    SearchContract.Presenter provideSearchPresenter(SearchPresenter presenter) {
        return presenter;
    }
}
