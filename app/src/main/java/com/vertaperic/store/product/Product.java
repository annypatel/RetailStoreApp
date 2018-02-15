/*
 * Project    : RetailStoreApp
 * File       : Product
 * Created on : 7/11/16 3:22 PM
 */
package com.vertaperic.store.product;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * The model for product.
 *
 * @author Anny Patel
 */
@Entity(tableName = "products")
public class Product implements Serializable {

    /**
     * The unique id for product.
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Long id;

    /**
     * The category id to which the product belongs to.
     * <p/>
     * INTEGER FOREIGN KEY categories(id) NOT NULL
     */
    @NonNull
    @ColumnInfo(name = "categoryId")
    private Long categoryId;

    /**
     * Name of product.
     */
    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    /**
     * The price of product.
     */
    @NonNull
    @ColumnInfo(name = "price")
    private Double price;

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
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(@NonNull Long categoryId) {
        this.categoryId = categoryId;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public Double getPrice() {
        return price;
    }

    public void setPrice(@NonNull Double price) {
        this.price = price;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
