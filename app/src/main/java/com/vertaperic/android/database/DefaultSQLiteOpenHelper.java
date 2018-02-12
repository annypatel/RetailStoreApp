/*
 * Project    : RetailStoreApp
 * File       : DefaultSQLiteOpenHelper
 * Created on : 11/6/2016 2:30 PM
 */
package com.vertaperic.android.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

/**
 * Simple implementation of {@link BaseSQLiteOpenHelper} with database lifecycle listener.
 *
 * @author Anny Patel
 */
class DefaultSQLiteOpenHelper extends BaseSQLiteOpenHelper {

    /**
     * The context attached with this helper.
     */
    private final Context context;
    /**
     * The SQLite callbacks.
     */
    private DatabaseLifecycleListener listener;

    /**
     * Constructs new DefaultSQLiteOpenHelper.
     *
     * @param context The host context.
     * @param name    The name of database.
     * @param factory The cursor factory.
     * @param version The database version.
     */
    DefaultSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    /**
     * Constructs new DefaultSQLiteOpenHelper.
     *
     * @param context      The host context.
     * @param name         The name of database.
     * @param factory      The cursor factory.
     * @param version      The database version.
     * @param errorHandler The database error handler.
     */
    DefaultSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
        this.context = context;
    }

    @Override
    public Context getContext() {
        return this.context;
    }

    @Override
    public void setDatabaseLifecycleListener(@NonNull DatabaseLifecycleListener listener) {
        this.listener = listener;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (this.listener != null) {
            this.listener.onCreate(new CachedWrapper(this, db));
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (this.listener != null) {
            this.listener.onUpgrade(new CachedWrapper(this, db), oldVersion, newVersion);
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (this.listener != null) {
            this.listener.onOpen(new CachedWrapper(this, db));
        }
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        if (this.listener != null) {
            this.listener.onConfigure(new CachedWrapper(this, db));
        }
    }

    /**
     * The SQLite database wrapper that will cache the {@link SQLiteDatabase} and return the same
     * for {@link #getReadableDatabase()} and {@link #getWritableDatabase()} method.
     */
    private static class CachedWrapper implements SQLiteDatabaseWrapper {

        /**
         * The SQLite open helper.
         */
        private final BaseSQLiteOpenHelper openHelper;
        /**
         * The SQLite database.
         */
        private final SQLiteDatabase database;

        CachedWrapper(BaseSQLiteOpenHelper openHelper, SQLiteDatabase database) {
            this.openHelper = openHelper;
            this.database = database;
        }

        @NonNull
        @Override
        public Context getContext() {
            return this.openHelper.getContext();
        }

        @NonNull
        @Override
        public SQLiteDatabase getWritableDatabase() {
            return this.database;
        }

        @NonNull
        @Override
        public SQLiteDatabase getReadableDatabase() {
            return this.database;
        }
    }
}
