/*
 * Project    : RetailStoreApp
 * File       : ProductDao
 * Created on : 7/11/16 3:26 PM
 */
package com.vertaperic.store.product;

import android.support.annotation.NonNull;

import com.vertaperic.android.database.BaseDao;
import com.vertaperic.android.database.DatabaseController;
import com.vertaperic.android.database.RowObjectMapper;
import com.vertaperic.store.category.Category;

import java.util.List;

import javax.inject.Inject;

/**
 * The data access object for the {@link Product}.
 *
 * @author Anny Patel
 */
public class ProductDao extends BaseDao<Product> {

    /**
     * Constructs new ProductDao.
     *
     * @param controller The database controller.
     */
    @Inject
    ProductDao(@NonNull DatabaseController controller) {
        super(controller, "products");
    }

    /**
     * To create table in database.
     *
     * @param controller The database controller.
     */
    public static void createTable(DatabaseController controller) {
        String query = "CREATE TABLE products (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "categoryId INTEGER NOT NULL, " +
                "name VARCHAR NOT NULL, " +
                "price DOUBLE NOT NULL, " +
                "imageUri VARCHAR NULL" +
                ");";

        controller.execSQL(query);
    }

    /**
     * To drop table from database.
     *
     * @param controller The database controller.
     */
    public static void dropTable(DatabaseController controller) {
        String query = "DROP TABLE IF EXISTS products";

        controller.execSQL(query);
    }

    @NonNull
    @Override
    protected RowObjectMapper<Product> onCreateMapper() {
        return new ProductMapper();
    }

    /**
     * To get products for category.
     *
     * @param category The category to get product for.
     * @return The list of products.
     */
    public List<Product> getProducts(Category category) {
        return query(null, "categoryId = ?", new String[]{Long.toString(category.getId())}, null, null, null);
    }
}
