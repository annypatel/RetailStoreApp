/*
 * Project    : RetailStoreApp
 * File       : CategoryDao
 * Created on : 7/11/16 3:16 PM
 */
package com.vertaperic.store.category;

import android.support.annotation.NonNull;

import com.vertaperic.android.database.BaseDao;
import com.vertaperic.android.database.DatabaseController;
import com.vertaperic.android.database.RowObjectMapper;

import java.util.List;

import javax.inject.Inject;

/**
 * The data access object for the {@link Category}.
 *
 * @author Anny Patel
 */
public class CategoryDao extends BaseDao<Category> {

    /**
     * Constructs new CategoryDao.
     *
     * @param controller The database controller.
     */
    @Inject
    CategoryDao(@NonNull DatabaseController controller) {
        super(controller, "categories");
    }

    @NonNull
    @Override
    protected RowObjectMapper<Category> onCreateMapper() {
        return new CategoryMapper();
    }

    /**
     * To create table in database.
     *
     * @param controller The database controller.
     */
    public static void createTable(DatabaseController controller) {
        String query = "CREATE TABLE categories (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name VARCHAR NOT NULL, " +
                "hasSubCategories BOOLEAN NOT NULL DEFAULT 0, " +
                "parentCategoryId INTEGER, " +
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
        String query = "DROP TABLE IF EXISTS categories";

        controller.execSQL(query);
    }

    /**
     * To get main categories(categories with no parent has -1 as parentCategoryId).
     *
     * @return The list of main categories.
     */
    @NonNull
    public List<Category> getMainCategories() {
        return query(null, "parentCategoryId = -1", null, null, null, null);
    }

    /**
     * To get the sub categories.
     *
     * @param mainCategory The main category.
     * @return The list of sub categories.
     */
    @NonNull
    public List<Category> getSubCategories(Category mainCategory) {
        return query(null, "parentCategoryId = ?", new String[]{Long.toString(mainCategory.getId())}, null, null, null);
    }
}
