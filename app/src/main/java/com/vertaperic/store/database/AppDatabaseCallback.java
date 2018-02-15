/*
 * Project    : RetailStoreApp
 * File       : AppDatabaseCallback
 * Created on : 7/11/16 3:06 PM
 */
package com.vertaperic.store.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.RoomDatabase;
import android.support.annotation.NonNull;

import com.vertaperic.android.database.DatabaseController;
import com.vertaperic.android.database.DatabaseManager;
import com.vertaperic.store.cart.CartItemDao;

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

        DatabaseController controller = DatabaseManager.newDatabaseController(database);
        try {
            controller.beginTransaction();

            // create tables
            CartItemDao.createTable(controller);
            // import data
            this.dataProvider.importData(controller);

            controller.setTransactionSuccessful();
        } finally {
            controller.endTransaction();
        }
    }
}
