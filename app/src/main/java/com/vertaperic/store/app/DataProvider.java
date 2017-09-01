/*
 * Project    : RetailStoreApp
 * File       : DataProvider
 * Created on : 7/11/16 3:51 PM
 */
package com.vertaperic.store.app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.vertaperic.android.database.DatabaseController;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Anny Patel
 */
class DataProvider {

    // keep constructor private
    private DataProvider() {
    }

    /**
     * To import the data from sql file to database.
     *
     * @param controller The database controller.
     */
    static void importData(@NonNull DatabaseController controller) {
        Context context = controller.getContext();

        // import category data
        try {
            InputStream is = context.getAssets().open("sql/categories.sql");
            controller.importSQLStatements(is);
        } catch (IOException e) {
            Log.e("DataProvider", "unable to import category data", e);
        }


        // import product data
        try {
            InputStream is = context.getAssets().open("sql/products.sql");
            controller.importSQLStatements(is);
        } catch (IOException e) {
            Log.e("DataProvider", "unable to import product data", e);
        }
    }
}
