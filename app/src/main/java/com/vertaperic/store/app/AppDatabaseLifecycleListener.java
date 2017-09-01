/*
 * Project    : RetailStoreApp
 * File       : AppDatabaseLifecycleListener
 * Created on : 7/11/16 3:06 PM
 */
package com.vertaperic.store.app;

import android.support.annotation.NonNull;

import com.vertaperic.android.database.DatabaseController;
import com.vertaperic.android.database.DatabaseLifecycleListener;
import com.vertaperic.android.database.DatabaseManager;
import com.vertaperic.android.database.SQLiteDatabaseWrapper;
import com.vertaperic.store.cart.CartItemDao;
import com.vertaperic.store.category.CategoryDao;
import com.vertaperic.store.product.ProductDao;

/**
 * The database lifecycle listener for this app.
 *
 * @author Anny Patel
 */
class AppDatabaseLifecycleListener implements DatabaseLifecycleListener {

    /**
     * The name of database.
     * <p>
     * Value: app.sqlite
     * </p>
     * <p>
     * NOTE - Do not change the name of database(Recommended). In case name needed to be changed,
     * handle the changes in onUpgrade.
     * </p>
     */
    static final String DATABASE_NAME = "app.sqlite";
    /**
     * The database version.
     * <p>
     * Update the version of database with each release if there are any changes in database.
     * </p>
     */
    static final int DATABASE_VERSION = 1;

    @Override
    public void onCreate(@NonNull SQLiteDatabaseWrapper wrapper) {
        DatabaseController controller = DatabaseManager.newDatabaseController(wrapper);
        try {
            controller.beginTransaction();

            // create tables
            CategoryDao.createTable(controller);
            ProductDao.createTable(controller);
            CartItemDao.createTable(controller);
            // import data
            DataProvider.importData(controller);

            controller.setTransactionSuccessful();
        } finally {
            controller.endTransaction();
        }
    }

    @Override
    public void onUpgrade(@NonNull SQLiteDatabaseWrapper wrapper, int oldVersion, int newVersion) {
        // handle database upgrade based on changes in current version,
        // for now just drop all the tables and recreate them
        DatabaseController controller = DatabaseManager.newDatabaseController(wrapper);
        try {
            controller.beginTransaction();

            CategoryDao.dropTable(controller);
            ProductDao.dropTable(controller);
            CartItemDao.dropTable(controller);
            onCreate(wrapper);

            controller.setTransactionSuccessful();
        } finally {
            controller.endTransaction();
        }
    }

    @Override
    public void onOpen(@NonNull SQLiteDatabaseWrapper wrapper) {
    }

    @Override
    public void onConfigure(@NonNull SQLiteDatabaseWrapper wrapper) {
    }
}
