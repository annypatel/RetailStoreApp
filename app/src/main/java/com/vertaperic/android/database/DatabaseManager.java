/*
 * Project    : RetailStoreApp
 * File       : DatabaseManager
 * Created on : 7/11/16 2:55 PM
 */
package com.vertaperic.android.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;

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
     * @param wrapper The SQLite database wrapper.
     * @return The database controller.
     */
    public static DatabaseController newDatabaseController(SQLiteDatabaseWrapper wrapper) {
        return new DefaultDatabaseController(wrapper);
    }

    /**
     * To create new database controller.
     *
     * @param openHelper The SQLite open helper.
     * @return The database controller.
     */
    public static DatabaseController newDatabaseController(BaseSQLiteOpenHelper openHelper) {
        return new DefaultDatabaseController(openHelper);
    }

    /**
     * To create the SQLite open helper.
     *
     * @param context The host context.
     * @param name    The name of database.
     * @param factory The cursor factory.
     * @param version The database version.
     * @return The SQLite open helper.
     */
    public static BaseSQLiteOpenHelper newSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        return new DefaultSQLiteOpenHelper(context, name, factory, version);
    }

    /**
     * To create the SQLite open helper.
     *
     * @param context      The host context.
     * @param name         The name of database.
     * @param factory      The cursor factory.
     * @param version      The database version.
     * @param errorHandler The database error handler.
     * @return The SQLite open helper.
     */
    public static BaseSQLiteOpenHelper newSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        return new DefaultSQLiteOpenHelper(context, name, factory, version, errorHandler);
    }
}
