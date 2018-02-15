/*
 * Project    : RetailStoreApp
 * File       : DefaultDatabaseController
 * Created on : 7/11/2016 1:21 PM
 */
package com.vertaperic.android.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteQuery;
import android.arch.persistence.db.SupportSQLiteQueryBuilder;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * The default implementation of database controller.
 *
 * @author Anny Patel
 */
class DefaultDatabaseController implements DatabaseController {

    /**
     * The SQLite database wrapper.
     */
    private final SQLiteDatabaseWrapper wrapper;

    /**
     * Constructs new DefaultDatabaseController.
     *
     * @param openHelper The SQLite database helper.
     */
    DefaultDatabaseController(@NonNull SupportSQLiteOpenHelper openHelper) {
        this.wrapper = new LazyWrapper(openHelper);
    }

    /**
     * Constructs new DefaultDatabaseController.
     *
     * @param database The SQLite database.
     */
    DefaultDatabaseController(@NonNull SupportSQLiteDatabase database) {
        this.wrapper = new CachedWrapper(database);
    }

    @Override
    public SQLiteDatabaseWrapper getSQLiteDatabaseWrapper() {
        return this.wrapper;
    }

    @Override
    public void open() {
        // open database calling getWritableDatabase
        this.wrapper.getWritableDatabase();
    }

    @Override
    public void openAsync(@Nullable final Runnable runnable) {
        new Thread() {

            @Override
            public void run() {
                open();
                // run runnable after open
                if (runnable != null)
                    runnable.run();
            }
        }.start();
    }

    @Override
    public void beginTransaction() {
        this.wrapper.getWritableDatabase().beginTransaction();
    }

    @Override
    public void endTransaction() {
        this.wrapper.getWritableDatabase().endTransaction();
    }

    @Override
    public void setTransactionSuccessful() {
        this.wrapper.getWritableDatabase().setTransactionSuccessful();
    }

    @Override
    public void execSQL(String sql) {
        this.wrapper.getWritableDatabase().execSQL(sql);
    }

    @Override
    public <T, M extends RowObjectMapper<T>> long insert(M mapper, T item, String table, String nullColumnHack) {
        ContentValues cv = mapper.map(item);

        long id = this.wrapper.getWritableDatabase().insert(table, SQLiteDatabase.CONFLICT_NONE, cv);
        if (id != -1) {
            mapper.setId(item, id);
        }
        return id;
    }

    @Override
    public int delete(String table, String whereClause, String[] whereArgs) {
        return this.wrapper.getWritableDatabase().delete(table, whereClause, whereArgs);
    }

    @Override
    public <T, M extends RowObjectMapper<T>> int update(M mapper, T item, String table, String whereClause, String[] whereArgs) {
        ContentValues cv = mapper.map(item);
        return this.wrapper.getWritableDatabase().update(table, SQLiteDatabase.CONFLICT_NONE, cv, whereClause, whereArgs);
    }

    @Override
    public <T, M extends RowObjectMapper<T>> List<T> rawQuery(M mapper, String sql, String[] selectionArg) {
        Cursor cursor = this.wrapper.getReadableDatabase().query(sql, selectionArg);
        return convertCursorToList(cursor, mapper);
    }

    @Override
    public <T, M extends RowObjectMapper<T>> List<T> query(M mapper, String table, String[] columns, String whereSelection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        SupportSQLiteQuery query = SupportSQLiteQueryBuilder.builder(table)
                .columns(columns)
                .selection(whereSelection, selectionArgs)
                .groupBy(groupBy)
                .having(having)
                .orderBy(orderBy)
                .limit(limit)
                .create();

        Cursor cursor = this.wrapper.getReadableDatabase().query(query);
        return convertCursorToList(cursor, mapper);
    }

    @Override
    public <T, M extends RowObjectMapper<T>> List<T> query(M mapper, String table, String[] columns, String whereSelection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        SupportSQLiteQuery query = SupportSQLiteQueryBuilder.builder(table)
                .columns(columns)
                .selection(whereSelection, selectionArgs)
                .groupBy(groupBy)
                .having(having)
                .orderBy(orderBy)
                .create();

        Cursor cursor = this.wrapper.getReadableDatabase().query(query);
        return convertCursorToList(cursor, mapper);
    }

    @Override
    public void importSQLStatements(InputStream is) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(is);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        byte[] chunk = new byte[1024];
        int count;
        while ((count = bis.read(chunk)) >= 0) {
            bos.write(chunk, 0, count);
        }

        importSQLStatements(bos.toString());
    }

    @Override
    public void importSQLStatements(String sqlStatements) {
        String[] statements = TextUtils.split(sqlStatements, ";\n");

        try {
            beginTransaction();

            for (String statement : statements) {
                if (statement != null) {

                    statement = statement.trim();
                    if (!statement.isEmpty() && !statement.startsWith("--")) {
                        this.wrapper.getWritableDatabase().execSQL(statement);
                    } else {
                        Log.e("DatabaseController", "Skipped: '" + statement + "'");
                    }
                }
            }

            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    /**
     * To convert cursor to list.
     *
     * @param cursor The cursor to convert.
     * @param mapper The row-object mapper.
     * @return The array list of converted row-object mappers.
     */
    private <T, M extends RowObjectMapper<T>> List<T> convertCursorToList(Cursor cursor, M mapper) {
        List<T> list = new ArrayList<>();

        if (cursor != null) {
            if (cursor.moveToFirst()) {

                do {

                    ContentValues cv = new ContentValues();
                    DatabaseUtils.cursorRowToContentValues(cursor, cv);
                    T item = mapper.map(cv);
                    list.add(item);

                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return list;
    }

    /**
     * The SQLite database wrapper, that will return readable and writable database from
     * {@link SupportSQLiteDatabase}.
     */
    private static class LazyWrapper implements SQLiteDatabaseWrapper {

        /**
         * The SQLite open helper.
         */
        private final SupportSQLiteOpenHelper openHelper;

        LazyWrapper(SupportSQLiteOpenHelper openHelper) {
            this.openHelper = openHelper;
        }

        @NonNull
        @Override
        public SupportSQLiteDatabase getWritableDatabase() {
            return this.openHelper.getWritableDatabase();
        }

        @NonNull
        @Override
        public SupportSQLiteDatabase getReadableDatabase() {
            return this.openHelper.getReadableDatabase();
        }
    }

    /**
     * The SQLite database wrapper that will cache the {@link SupportSQLiteDatabase} and return the
     * same for {@link #getReadableDatabase()} and {@link #getWritableDatabase()} method.
     */
    private static class CachedWrapper implements SQLiteDatabaseWrapper {

        /**
         * The SQLite database.
         */
        private final SupportSQLiteDatabase database;

        CachedWrapper(SupportSQLiteDatabase openHelper) {
            this.database = openHelper;
        }

        @NonNull
        @Override
        public SupportSQLiteDatabase getWritableDatabase() {
            return this.database;
        }

        @NonNull
        @Override
        public SupportSQLiteDatabase getReadableDatabase() {
            return this.database;
        }
    }
}
