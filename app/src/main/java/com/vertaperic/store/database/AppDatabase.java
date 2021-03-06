/*
 * Project    : RetailStoreApp
 * File       : AppDatabase
 * Created on : 2/13/2018 8:54 PM
 */
package com.vertaperic.store.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.vertaperic.store.cart.CartItem;
import com.vertaperic.store.cart.CartItemDao;
import com.vertaperic.store.category.Category;
import com.vertaperic.store.category.CategoryDao;
import com.vertaperic.store.product.Product;
import com.vertaperic.store.product.ProductDao;

/**
 * Room database class for this app.
 *
 * @author Anny Patel
 */
@Database(version = AppDatabase.VERSION, entities = {
        Category.class,
        Product.class,
        CartItem.class
})
public abstract class AppDatabase extends RoomDatabase {

    /**
     * The name of database.
     * <p>
     * Value: app.sqlite
     * </p>
     */
    static final String NAME = "app.sqlite";
    /**
     * The database version.
     * <p>
     * Update the version of database with each release if there are any changes in database.
     * </p>
     */
    static final int VERSION = 1;


    /**
     * @return The category dao.
     */
    public abstract CategoryDao categoryDao();

    /**
     * @return The product dao.
     */
    public abstract ProductDao productDao();

    /**
     * @return The cart item dao.
     */
    public abstract CartItemDao cartItemDao();
}
