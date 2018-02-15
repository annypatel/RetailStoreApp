/*
 * Project    : RetailStoreApp
 * File       : DataProvider
 * Created on : 7/11/16 3:51 PM
 */
package com.vertaperic.store.database;

import android.support.annotation.NonNull;
import android.util.Log;

import com.vertaperic.android.database.DatabaseController;
import com.vertaperic.store.app.App;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;

/**
 * Temporary static data provider which imports data into SQLite database.
 *
 * @author Anny Patel
 */
class DataProvider {

    private final App app;

    /**
     * Constructs new DataProvider.
     *
     * @param app The application instance.
     */
    @Inject
    DataProvider(App app) {
        this.app = app;
    }

    /**
     * To import the data from sql file to database.
     *
     * @param controller The database controller.
     */
    void importData(@NonNull DatabaseController controller) {

        // import category data
        try {
            InputStream is = this.app.getAssets().open("sql/categories.sql");
            controller.importSQLStatements(is);
        } catch (IOException e) {
            Log.e("DataProvider", "unable to import category data", e);
        }


        // import product data
        try {
            InputStream is = this.app.getAssets().open("sql/products.sql");
            controller.importSQLStatements(is);
        } catch (IOException e) {
            Log.e("DataProvider", "unable to import product data", e);
        }
    }
}
