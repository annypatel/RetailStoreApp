/*
 * Project    : RetailStoreApp
 * File       : BaseSQLiteOpenHelper
 * Created on : 7/11/2016 12:06 PM
 */
package com.vertaperic.android.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

/**
 * The base SQLite open helper with {@link DatabaseLifecycleListener}.
 *
 * @author Anny Patel
 */
public abstract class BaseSQLiteOpenHelper extends SQLiteOpenHelper {

    /**
     * Constructs new BaseSQLiteOpenHelper.
     *
     * @param context The host context.
     * @param name    The name of database.
     * @param factory The cursor factory.
     * @param version The database version.
     */
    public BaseSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * Constructs new BaseSQLiteOpenHelper.
     *
     * @param context      The host context.
     * @param name         The name of database.
     * @param factory      The cursor factory.
     * @param version      The database version.
     * @param errorHandler The database error handler.
     */
    public BaseSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    /**
     * To get the context attached with SQLite open helper.
     *
     * @return The context.
     */
    public abstract Context getContext();

    /**
     * To set the database lifecycle listener.
     *
     * @param listener The lifecycle listener.
     */
    public abstract void setDatabaseLifecycleListener(@NonNull DatabaseLifecycleListener listener);
}
