/*
 * Project    : RetailStoreApp
 * File       : AppDatabaseCallback
 * Created on : 7/11/16 3:06 PM
 */
package com.vertaperic.store.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.RoomDatabase;
import android.support.annotation.NonNull;

import javax.inject.Inject;

/**
 * The database lifecycle callback for this app.
 *
 * @author Anny Patel
 */
class AppDatabaseCallback extends RoomDatabase.Callback {

    private final DataProvider dataProvider;

    /**
     * Constructs new AppDatabaseCallback.
     *
     * @param dataProvider The data provider.
     */
    @Inject
    AppDatabaseCallback(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase database) {
        super.onCreate(database);

        try {
            database.beginTransaction();

            // import data
            this.dataProvider.importData(database);

            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
    }
}
