/*
 * Project    : RetailStoreApp
 * File       : CategoryMapper
 * Created on : 7/11/16 3:11 PM
 */
package com.vertaperic.store.category;

import android.content.ContentValues;
import android.support.annotation.NonNull;

import com.vertaperic.android.database.RowObjectMapper;

/**
 * Row-object mapper for {@link Category} model.
 *
 * @author Anny Patel
 */
class CategoryMapper implements RowObjectMapper<Category> {

    @Override
    public void setId(Category c, Long id) {
        c.setId(id);
    }

    @Override
    public Category map(@NonNull ContentValues cv) {
        Category c = new Category();
        c.setId(cv.getAsLong("id"));
        c.setName(cv.getAsString("name"));
        c.setHasSubCategories(cv.getAsInteger("hasSubCategories") == 1);
        c.setParentCategoryId(cv.getAsLong("parentCategoryId"));
        c.setImageUri(cv.getAsString("imageUri"));
        return c;
    }

    @NonNull
    @Override
    public ContentValues map(Category c) {
        ContentValues cv = new ContentValues();
        cv.put("name", c.getName());
        cv.put("hasSubCategories", c.hasSubCategories());
        cv.put("parentCategoryId", c.getParentCategoryId());
        cv.put("imageUri", c.getImageUri());
        return cv;
    }
}
