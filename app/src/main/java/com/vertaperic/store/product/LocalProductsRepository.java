/*
 * Project    : RetailStoreApp
 * File       : LocalProductsRepository
 * Created on : 8/11/16 5:40 PM
 */
package com.vertaperic.store.product;

import android.support.annotation.NonNull;

import com.vertaperic.store.category.Category;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Concrete implementation of {@link ProductsRepository} which uses local database as data source.
 *
 * @author Anny Patel
 */
class LocalProductsRepository implements ProductsRepository {

    /**
     * The product DAO.
     */
    private final ProductDao productDao;

    /**
     * Constructs new LocalProductsRepository.
     *
     * @param productDao The product DAO.
     */
    @Inject
    LocalProductsRepository(@NonNull ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Single<List<Product>> getProducts(@NonNull Category category) {
        return this.productDao.getProducts(category.getId());
    }
}
