/*
 * Project    : RetailStoreApp
 * File       : BaseDao
 * Created on : 7/11/2016 1:05 PM
 */
package com.vertaperic.android.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * The class that provides support for DAO pattern. The subclass DAOs must extends this class.
 * <p>
 * For example the SampleDao for SampleModel with table "sample_table" will look like below:
 * <pre>
 * public class SampleDao extends BaseDao&lt;SampleModel&gt; {
 *
 *      public SampleDao(@NonNull DatabaseController controller) {
 *          super(controller, "sample_table");
 *      }
 *
 *      //@NonNull
 *      //@Override
 *      protected RowObjectMapper<SampleModel> onCreateMapper() {
 *          return new SampleMapper();
 *      }
 *
 *      //...
 * }
 * </pre>
 *
 * @author Anny Patel
 */
public abstract class BaseDao<T> {

    /**
     * The database controller.
     */
    protected final DatabaseController controller;
    /**
     * The name of table.
     */
    protected final String tableName;
    /**
     * The row-object mapper.
     */
    protected final RowObjectMapper<T> mapper;

    /**
     * Constructs new BaseDao.
     *
     * @param controller The database controller.
     * @param tableName  The name of table.
     */
    public BaseDao(@NonNull DatabaseController controller, @NonNull String tableName) {
        this.controller = controller;
        this.tableName = tableName;
        this.mapper = onCreateMapper();
    }

    /**
     * Called to create the mapper.
     *
     * @return The row-object mapper.
     */
    @NonNull
    protected abstract RowObjectMapper<T> onCreateMapper();


    /**
     * To insert item into database.
     *
     * @param item           The item to insert.
     * @param nullColumnHack The name of nullable column.
     * @return Row id of newly inserted record or -1 if an error occurred.
     * @see SQLiteDatabase#insert(String, String, ContentValues)
     */
    public long insert(T item, String nullColumnHack) {
        return this.controller.insert(this.mapper, item, this.tableName, nullColumnHack);
    }

    /**
     * To insert list of items into database.
     *
     * @param items          The list of items.
     * @param nullColumnHack The name of nullable column.
     * @see SQLiteDatabase#insert(String, String, ContentValues)
     */
    public void insert(List<T> items, String nullColumnHack) {
        try {
            this.controller.beginTransaction();
            for (T item : items) {
                this.controller.insert(this.mapper, item, this.tableName, nullColumnHack);
            }
            this.controller.setTransactionSuccessful();
        } finally {
            this.controller.endTransaction();
        }
    }

    /**
     * To delete rows from database.
     *
     * @param whereClause The where clause.
     * @param whereArgs   The where arguments.
     * @return The number of rows affected if a whereClause is passed in, 0 otherwise. To remove
     * all rows and get a count pass "1" as the whereClause.
     * @see SQLiteDatabase#delete(String, String, String[])
     */
    public int delete(String whereClause, String[] whereArgs) {
        return this.controller.delete(this.tableName, whereClause, whereArgs);
    }

    /**
     * To delete all rows from database.
     *
     * @return The number of rows affected.
     * @see SQLiteDatabase#delete(String, String, String[])
     */
    public int delete() {
        return this.controller.delete(this.tableName, null, null);
    }

    /**
     * To update row in database.
     *
     * @param item        The item  to update.
     * @param whereClause The where clause.
     * @param whereArgs   The where arguments.
     * @return The number of rows affected.
     * @see SQLiteDatabase#update(String, ContentValues, String, String[])
     */
    public int update(T item, String whereClause, String[] whereArgs) {
        return this.controller.update(this.mapper, item, this.tableName, whereClause, whereArgs);
    }

    /**
     * To update rows in database.
     *
     * @param items       The list of items to update.
     * @param whereClause The where clause.
     * @param whereArgs   The where arguments.
     * @see SQLiteDatabase#update(String, ContentValues, String, String[])
     */
    public void update(List<T> items, String whereClause, String[] whereArgs) {
        try {
            this.controller.beginTransaction();
            for (T item : items) {
                this.controller.update(this.mapper, item, this.tableName, whereClause, whereArgs);
            }
            this.controller.setTransactionSuccessful();
        } finally {
            this.controller.endTransaction();
        }
    }

    /**
     * To get all rows from database.
     *
     * @param sql          The SQL query to execute.
     * @param selectionArg The selection arguments.
     * @return The list of models.
     * @see SQLiteDatabase#rawQuery(String, String[])
     */
    public List<T> rawQuery(String sql, String[] selectionArg) {
        return this.controller.rawQuery(this.mapper, sql, selectionArg);
    }

    /**
     * To query the database.
     *
     * @return The list of models.
     * @see SQLiteDatabase#query(String, String[], String, String[], String, String, String)
     */
    public List<T> query() {
        return this.controller.query(this.mapper, this.tableName, null, null, null, null, null, null);
    }

    /**
     * To query database database.
     *
     * @param columns        The name of columns to select.
     * @param whereSelection The selection filter.
     * @param selectionArgs  The selection arguments.
     * @param groupBy        The group by filter.
     * @param having         The having filter.
     * @param orderBy        The order by filter.
     * @return The list of models.
     * @see SQLiteDatabase#query(String, String[], String, String[], String, String, String)
     */
    public List<T> query(String[] columns, String whereSelection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return this.controller.query(this.mapper, this.tableName, columns, whereSelection, selectionArgs, groupBy, having, orderBy);
    }

    /**
     * To query the database.
     *
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
    public List<T> query(String[] columns, String whereSelection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        return this.controller.query(this.mapper, this.tableName, columns, whereSelection, selectionArgs, groupBy, having, orderBy, limit);
    }
}
