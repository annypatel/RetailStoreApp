/*
 * Project    : RetailStoreApp
 * File       : Category
 * Created on : 7/11/16 3:10 PM
 */
package com.vertaperic.store.category;

import java.io.Serializable;

/**
 * The model for category.
 *
 * @author Anny Patel
 */
public class Category implements Serializable {

    /**
     * The unique id for category.
     * <p/>
     * INTEGER AUTO INCREMENT PRIMARY KEY
     */
    private Long id;
    /**
     * Name of category.
     * <p/>
     * VARCHAR NOT NULL
     */
    private String name;
    /**
     * Flag to indicate whether this category has sub categories or not.
     * <p/>
     * BOOLEAN - 0/1
     */
    private boolean hasSubCategories;
    /**
     * The id of parent category.
     * <p/>
     * INTEGER FOREIGN KEY products(id) NULL
     * <p/>
     * -1 for main categories, otherwise as id
     */
    private Long parentCategoryId;
    /**
     * The category image URI.
     * <p/>
     * VARCHAR NULL
     */
    private String imageUri;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
