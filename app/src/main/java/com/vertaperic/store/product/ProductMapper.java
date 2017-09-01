/*
 * Project    : RetailStoreApp
 * File       : ProductMapper
 * Created on : 7/11/16 3:23 PM
 */
package com.vertaperic.store.product;

import android.content.ContentValues;
import android.support.annotation.NonNull;

import com.vertaperic.android.database.RowObjectMapper;

/**
 * Row-object mapper for {@link Product} model.
 *
 * @author Anny Patel
 */
public class ProductMapper implements RowObjectMapper<Product> {

    @Override
    public void setId(Product p, Long id) {
        p.setId(id);
    }

    @Override
    public Product map(@NonNull ContentValues cv) {
        Product p = new Product();
        p.setId(cv.getAsLong("id"));
        p.setCategoryId(cv.getAsLong("categoryId"));
        p.setName(cv.getAsString("name"));
        p.setPrice(cv.getAsDouble("price"));
        p.setImageUri(cv.getAsString("imageUri"));
        return p;
    }

    @NonNull
    @Override
    public ContentValues map(Product p) {
        ContentValues cv = new ContentValues();
        cv.put("categoryId", p.getCategoryId());
        cv.put("name", p.getName());
        cv.put("price", p.getPrice());
        cv.put("imageUri", p.getImageUri());
        return cv;
    }
}
