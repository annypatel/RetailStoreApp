/*
 * Project    : RetailStoreApp
 * File       : DatabaseController
 * Created on : 11/6/2016 3:33 PM
 */
package com.vertaperic.android.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Database controller is entry point for accessing the database API. Automates the mapping of JAVA
 * object and database rows by using the {@link RowObjectMapper}.
 *
 * @author Anny Patel
 */
public interface DatabaseController {

    /**
     * To get the SQLite database wrapper.
     *
     * @return The SQLite database wrapper.
     */
    SQLiteDatabaseWrapper getSQLiteDatabaseWrapper();

    /**
     * To open the database.
     */
    void open();

    /**
     * To open the database asynchronously.
     *
     * @param runnable The runnable to run(in background thread) after database is opened.
     */
    void openAsync(@Nullable Runnable runnable);

    /**
     * To begin transaction.
     *
     * @see SQLiteDatabase#beginTransaction()
     */
    void beginTransaction();

    /**
     * To end transaction.
     *
     * @see SQLiteDatabase#endTransaction()
     */
    void endTransaction();

    /**
     * To set transaction successful.
     *
     * @see SQLiteDatabase#setTransactionSuccessful()
     */
    void setTransactionSuccessful();

    /**
     * To execute SQL statement.
     *
     * @param sql The sql to execute.
     * @see SQLiteDatabase#execSQL(String)
     */
    void execSQL(String sql);

    /**
     * To insert row in database.
     *
     * @param mapper         The row-object mapper.
     * @param item           The item to insert.
     * @param table          The table name.
     * @param nullColumnHack The name of nullable column.
     * @return Row id of newly inserted record or -1 if an error occurred.
     * @see SQLiteDatabase#insert(String, String, ContentValues)
     */
    <T, M extends RowObjectMapper<T>> long insert(M mapper, T item, String table, String nullColumnHack);

    /**
     * To delete rows from database.
     *
     * @param table       The table name.
     * @param whereClause The where clause.
     * @param whereArgs   The where arguments.
     * @return The number of rows affected if a whereClause is passed in, 0 otherwise. To remove
     * all rows and get a count pass "1" as the whereClause.
     * @see SQLiteDatabase#delete(String, String, String[])
     */
    int delete(String table, String whereClause, String[] whereArgs);

    /**
     * To update row in database.
     *
     * @param mapper      The row-object mapper.
     * @param item        The item to update.
     * @param table       The table name.
     * @param whereClause The where clause.
     * @param whereArgs   The where arguments.
     * @return The number of rows affected.
     * @see SQLiteDatabase#update(String, ContentValues, String, String[])
     */
    <T, M extends RowObjectMapper<T>> int update(M mapper, T item, String table, String whereClause, String[] whereArgs);

    /**
     * To get all rows from database.
     *
     * @param mapper       The row-object mapper.
     * @param sql          The SQL query to execute.
     * @param selectionArg The selection arguments.
     * @return The list of models.
     * @see SQLiteDatabase#rawQuery(String, String[])
     */
    <T, M extends RowObjectMapper<T>> List<T> rawQuery(M mapper, String sql, String[] selectionArg);

    /**
     * To query the database.
     *
     * @param mapper         The row-object mapper.
     * @param table          The mame of table.
     * @param columns        The name of columns to select.
     * @param whereSelection The selection filter.
     * @param selectionArgs  The selection arguments.
     * @param groupBy        The group by filter.
     * @param having         The having filter.
     * @param orderBy        The order by filter.
     * @param limit          The number of rows to be returned by the query.
     * @return The list of models.
     * @see SQLiteDatabase#query(String, String[], String, String[], String, String, String, String)
     */
    <T, M extends RowObjectMapper<T>> List<T> query(M mapper, String table, String[] columns, String whereSelection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit);

    /**
     * To query database database.
     *
     * @param mapper         The row-object mapper.
     * @param table          The mame of table.
     * @param columns        The name of columns to select.
     * @param whereSelection The selection filter.
     * @param selectionArgs  The selection arguments.
     * @param groupBy        The group by filter.
     * @param having         The having filter.
     * @param orderBy        The order by filter.
     * @return The list of models.
     * @see SQLiteDatabase#query(String, String[], String, String[], String, String, String)
     */
    <T, M extends RowObjectMapper<T>> List<T> query(M mapper, String table, String[] columns, String whereSelection, String[] selectionArgs, String groupBy, String having, String orderBy);

    /**
     * To import database from file in SQLite3 format.
     * <p/>
     * Note: Each SQL statement separated by "<code>;\n</code>". Single line comment that starts with
     * <code>--</code> and ends with <code>;\n</code> are supported.
     *
     * @param is The input stream to read statements from.
     * @throws IOException If fails to read input stream.
     */
    void importSQLStatements(InputStream is) throws IOException;

    /**
     * To import database from file in SQLite3 format.
     * <p/>
     * Note: Each SQL statement separated by "<code>;\n</code>". Single line comment that starts with
     * <code>--</code> and ends with <code>;\n</code> are supported.
     *
     * @param sqlStatements The SQL statement in string format.
     */
    void importSQLStatements(String sqlStatements);
}
