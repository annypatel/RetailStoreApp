/*
 * Project    : RetailStoreApp
 * File       : DatabaseManager
 * Created on : 7/11/16 2:55 PM
 */
package com.vertaperic.android.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;

/**
 * The factory for creating the SQLiteOpenHelper and DatabaseController.
 *
 * @author Anny Patel
 */
public class DatabaseManager {

    // keep constructor private
    private DatabaseManager() {
    }

    /**
     * To create new database controller.
     *
     * @param database The SQLite database.
     * @return The database controller.
     */
    public static DatabaseController newDatabaseController(SupportSQLiteDatabase database) {
        return new DefaultDatabaseController(database);
    }

    /**
     * To create new database controller.
     *
     * @param openHelper The SQLite open helper.
     * @return The database controller.
     */
    public static DatabaseController newDatabaseController(SupportSQLiteOpenHelper openHelper) {
        return new DefaultDatabaseController(openHelper);
    }
}
