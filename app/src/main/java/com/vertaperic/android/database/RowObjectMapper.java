/*
 * Project    : RetailStoreApp
 * File       : RowObjectMapper
 * Created on : 11/6/2016 2:22 PM
 */
package com.vertaperic.android.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

/**
 * The mapper interface that provides JAVA object to database row mapping and vice versa.
 * <p>
 * {@link #setId(Object, Long)} method will called when new row is inserted using the
 * {@link DatabaseController}.
 * <p/>
 * For example some SampleModel class with table "sample_table" which has two columns(id INTEGER
 * PRIMARY KEY AUTO INCREMENT and name VARCHAR) will look like below:
 * <p/>
 * <pre>
 * public class SampleModel {
 *
 *      Long id;
 *      String name;
 *
 * }
 * </pre>
 * <p>
 * The mapper class for SampleModel will look like below:
 * <pre>
 * public class SampleMapper implements RowObjectMapper<SampleModel> {
 *
 *      //@Override
 *      public void setId(SampleModel sampleModel, Long id) {
 *          sampleModel.id = id;
 *      }
 *
 *      //@Override
 *      public SampleModel map(ContentValues cv) {
 *          SampleModel sampleModel = new SampleModel();
 *          sampleModel.id = cv.getAsLong("id");
 *          sampleModel.name = cv.getAsString("name");
 *          return sampleModel;
 *      }
 *
 *      //@Override
 *      public ContentValues map(SampleModel sampleModel) {
 *          ContentValues cv = new ContentValues();
 *          cv.put("name", sampleModel.name);
 *          return cv;
 *      }
 * }
 * </pre>
 *
 * @author Anny Patel
 */
public interface RowObjectMapper<T> {

    /**
     * Called to set the auto increment primary key returned by
     * {@link SQLiteDatabase#insert(String, String, ContentValues)} method.
     * If insertion failed in any case this method will not be called.
     * <p/>
     * If table does not have auto increment primary key then ROWID will be returned. For more
     * details refer https://www.sqlite.org/autoinc.html.
     *
     * @param t  The object to which the auto incremented id will be set.
     * @param id The generated id.
     */
    void setId(T t, Long id);

    /**
     * To map {@link ContentValues} to subclass of {@link RowObjectMapper}.
     *
     * @param cv The content values.
     */
    T map(@NonNull ContentValues cv);

    /**
     * To map subclass of {@link RowObjectMapper} to {@link ContentValues}.
     *
     * @param t The object to map to content values.
     */
    @NonNull
    ContentValues map(T t);
}
