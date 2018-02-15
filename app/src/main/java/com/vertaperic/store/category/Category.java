/*
 * Project    : RetailStoreApp
 * File       : Category
 * Created on : 7/11/16 3:10 PM
 */
package com.vertaperic.store.category;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * The model for category.
 *
 * @author Anny Patel
 */
@Entity(tableName = "categories")
public class Category implements Serializable {

    /**
     * The unique id for category.
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Long id;

    /**
     * Name of category.
     */
    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    /**
     * Flag to indicate whether this category has sub categories or not.
     */
    @ColumnInfo(name = "hasSubCategories")
    private boolean hasSubCategories;

    /**
     * The id of parent category.
     * <p/>
     * INTEGER FOREIGN KEY products(id) NULL
     * <p/>
     * -1 for main categories, otherwise as id
     */
    @ColumnInfo(name = "parentCategoryId")
    private Long parentCategoryId;

    /**
     * The category image URI.
     */
    @ColumnInfo(name = "imageUri")
    private String imageUri;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public boolean hasSubCategories() {
        return hasSubCategories;
    }

    public void setHasSubCategories(boolean hasSubCategories) {
        this.hasSubCategories = hasSubCategories;
    }

    public Long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
