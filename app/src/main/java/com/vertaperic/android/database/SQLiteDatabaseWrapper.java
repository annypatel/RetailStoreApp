/*
 * Project    : RetailStoreApp
 * File       : SQLiteDatabaseWrapper
 * Created on : 11/6/2016 4:15 PM
 */
package com.vertaperic.android.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

/**
 * The SQLite database wrapper which provides access to the {@link SQLiteDatabase}.
 *
 * @author Anny Patel
 */
public interface SQLiteDatabaseWrapper {

    /**
     * To get the context attached with SQLite database.
     *
     * @return The context.
     */
    @NonNull
    Context getContext();

    /**
     * To get the writable database.
     *
     * @return The SQLite database.
     * @see SQLiteOpenHelper#getWritableDatabase()
     */
    @NonNull
    SQLiteDatabase getWritableDatabase();

    /**
     * To get the readable database.
     *
     * @return The SQLite database.
     * @see SQLiteOpenHelper#getReadableDatabase()
     */
    @NonNull
    SQLiteDatabase getReadableDatabase();
}
