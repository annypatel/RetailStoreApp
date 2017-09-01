/*
 * Project    : RetailStoreApp
 * File       : CartItemMapper
 * Created on : 7/11/16 3:29 PM
 */
package com.vertaperic.store.cart;

import android.content.ContentValues;
import android.support.annotation.NonNull;

import com.vertaperic.android.database.RowObjectMapper;

/**
 * @author Anny Patel
 */
class CartItemMapper implements RowObjectMapper<CartItem> {

    @Override
    public void setId(CartItem ci, Long id) {
        ci.setCartItemId(id);
    }

    @Override
    public CartItem map(@NonNull ContentValues cv) {
        CartItem ci = new CartItem();
        ci.setCartItemId(cv.getAsLong("cartItemId"));
        ci.setProductId(cv.getAsLong("productId"));
        return ci;
    }

    @NonNull
    @Override
    public ContentValues map(CartItem ci) {
        ContentValues cv = new ContentValues();
        cv.put("productId", ci.getProductId());
        return cv;
    }
}
