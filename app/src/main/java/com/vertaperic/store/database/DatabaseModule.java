/*
 * Project    : RetailStoreApp
 * File       : DatabaseModule
 * Created on : 2/14/2018 8:54 PM
 */
package com.vertaperic.store.database;

import android.arch.persistence.room.Room;

import com.vertaperic.store.app.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * The dagger module for providing database dependencies.
 *
 * @author Anny Patel
 */
@Module
public class DatabaseModule {

    /**
     * @param app      The application instance.
     * @param listener The database lifecycle callback.
     * @return The application database instance.
     */
    @Provides
    @Singleton
    static AppDatabase providesAppDatabase(App app, AppDatabaseCallback listener) {
        return Room.databaseBuilder(app, AppDatabase.class, AppDatabase.NAME)
                .addCallback(listener)
                .build();
    }
}
