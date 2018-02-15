/*
 * Project    : RetailStoreApp
 * File       : SQLiteDatabaseWrapper
 * Created on : 11/6/2016 4:15 PM
 */
package com.vertaperic.android.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.support.annotation.NonNull;

/**
 * The SQLite database wrapper which provides access to the {@link SupportSQLiteDatabase}.
 *
 * @author Anny Patel
 */
public interface SQLiteDatabaseWrapper {

    /**
     * To get the writable database.
     *
     * @return The SQLite database.
     * @see SupportSQLiteOpenHelper#getWritableDatabase()
     */
    @NonNull
    SupportSQLiteDatabase getWritableDatabase();

    /**
     * To get the readable database.
     *
     * @return The SQLite database.
     * @see SupportSQLiteOpenHelper#getReadableDatabase()
     */
    @NonNull
    SupportSQLiteDatabase getReadableDatabase();
}
