/*
 * Project    : RetailStoreApp
 * File       : AppModule
 * Created on : 10/11/16 7:21 PM
 */
package com.vertaperic.store.app;

import android.content.Context;

import com.vertaperic.android.database.DatabaseController;

import dagger.Module;
import dagger.Provides;

/**
 * The module for providing application level dependencies.
 *
 * @author Anny Patel
 */
@Module
class AppModule {

    /**
     * The application context.
     */
    private final Context context;
    /**
     * The database controller.
     */
    private final DatabaseController databaseController;

    /**
     * @param context            The application context.
     * @param databaseController The database controller.
     */
    AppModule(Context context, DatabaseController databaseController) {
        this.context = context;
        this.databaseController = databaseController;
    }

    @Provides
    Context provideContext() {
        return this.context;
    }

    @Provides
    DatabaseController provideDatabaseController() {
        return this.databaseController;
    }
}
