/*
 * Project    : RetailStoreApp
 * File       : SimpleRowObjectMapper
 * Created on : 9/11/16 5:58 PM
 */
package com.vertaperic.android.database;

import android.content.ContentValues;
import android.support.annotation.NonNull;

/**
 * The basic implementation of {@link RowObjectMapper} with all empty method.
 *
 * @author Anny Patel
 */
public class SimpleRowObjectMapper<T> implements RowObjectMapper<T> {

    @Override
    public void setId(T t, Long id) {
        // do nothing
    }

    @Override
    public T map(@NonNull ContentValues cv) {
        // do nothing
        return null;
    }

    @NonNull
    @Override
    public ContentValues map(T t) {
        // do nothing
        //noinspection ConstantConditions
        return null;
    }
}
