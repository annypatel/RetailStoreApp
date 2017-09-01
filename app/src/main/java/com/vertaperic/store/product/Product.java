/*
 * Project    : RetailStoreApp
 * File       : Product
 * Created on : 7/11/16 3:22 PM
 */
package com.vertaperic.store.product;

import java.io.Serializable;

/**
 * The model for product.
 *
 * @author Anny Patel
 */
public class Product implements Serializable {

    /**
     * The unique id for product.
     * <p/>
     * INTEGER AUTO INCREMENT PRIMARY KEY
     */
    private Long id;
    /**
     * The category id to which the product belongs to.
     * <p/>
     * INTEGER FOREIGN KEY categories(id) NOT NULL
     */
    private Long categoryId;
    /**
     * Name of product.
     * <p/>
     * VARCHAR NOT NULL
     */
    private String name;
    /**
     * The price of product.
     * <p/>
     * DOUBLE NOT NULL
     */
    private Double price;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
