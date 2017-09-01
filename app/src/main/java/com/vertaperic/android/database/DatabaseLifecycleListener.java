/*
 * Project    : RetailStoreApp
 * File       : DatabaseLifecycleListener
 * Created on : 11/6/2016 2:28 PM
 */
package com.vertaperic.android.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

/**
 * The listener for database lifecycle callbacks. Use this listener to create or update the
 * database.
 *
 * @author Anny Patel
 */
public interface DatabaseLifecycleListener {

    /**
     * Called to create the database.
     *
     * @param wrapper The SQLite database wrapper.
     * @see SQLiteOpenHelper#onCreate(SQLiteDatabase)
     */
    void onCreate(@NonNull SQLiteDatabaseWrapper wrapper);

    /**
     * Called to upgrade the database.
     *
     * @param wrapper    The SQLite database wrapper.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     * @see SQLiteOpenHelper#onUpgrade(SQLiteDatabase, int, int)
     */
    void onUpgrade(@NonNull SQLiteDatabaseWrapper wrapper, int oldVersion, int newVersion);

    /**
     * Called when the database is opened.
     *
     * @param wrapper The SQLite database wrapper.
     * @see SQLiteOpenHelper#onOpen(SQLiteDatabase)
     */
    void onOpen(@NonNull SQLiteDatabaseWrapper wrapper);

    /**
     * Called when the database is configured.
     *
     * @param wrapper The SQLite database wrapper.
     * @see SQLiteOpenHelper#onConfigure(SQLiteDatabase)
     */
    void onConfigure(@NonNull SQLiteDatabaseWrapper wrapper);
}
