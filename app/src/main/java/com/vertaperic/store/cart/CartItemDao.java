/*
 * Project    : RetailStoreApp
 * File       : CartItemDao
 * Created on : 7/11/16 3:31 PM
 */
package com.vertaperic.store.cart;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.vertaperic.android.database.BaseDao;
import com.vertaperic.android.database.DatabaseController;
import com.vertaperic.android.database.RowObjectMapper;
import com.vertaperic.android.database.SimpleRowObjectMapper;
import com.vertaperic.store.product.Product;
import com.vertaperic.store.product.ProductMapper;

import java.util.List;

import javax.inject.Inject;

/**
 * The data access object for cart items.
 *
 * @author Anny Patel
 */
public class CartItemDao extends BaseDao<CartItem> {

    /**
     * Constructs new CartItemDao.
     *
     * @param controller The database controller.
     */
    @Inject
    public CartItemDao(@NonNull DatabaseController controller) {
        super(controller, "cart");
    }

    /**
     * To create table in database.
     *
     * @param controller The database controller.
     */
    public static void createTable(DatabaseController controller) {
        String query = "CREATE TABLE cart (" +
                "cartItemId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "productId INTEGER NOT NULL" +
                ");";

        controller.execSQL(query);
    }

    /**
     * To drop table from database.
     *
     * @param controller The database controller.
     */
    public static void dropTable(DatabaseController controller) {
        String query = "DROP TABLE IF EXISTS cart";

        controller.execSQL(query);
    }

    @NonNull
    @Override
    protected RowObjectMapper<CartItem> onCreateMapper() {
        return new CartItemMapper();
    }

    /**
     * To add product to cart.
     *
     * @param product The product to add.
     * @return The added cart item, null if insert failed.
     */
    @Nullable
    public CartItem addProductToCart(Product product) {
        CartItem cartItem = new CartItem();
        cartItem.setProductId(product.getId());

        long id = insert(cartItem, null);
        if (id == -1) {
            return null;
        } else {
            return cartItem;
        }
    }

    /**
     * To get cart item for given product.
     *
     * @param product The product.
     * @return The cart item if found, null otherwise.
     */
    @Nullable
    public CartItem getCartItem(Product product) {

        List<CartItem> cartItems = query(null, "productId = ?", new String[]{Long.toString(product.getId())}, null, null, null, "1");
        if (cartItems.isEmpty()) {
            return null;
        } else {
            return cartItems.get(0);
        }
    }

    /**
     * To remove product from cart.
     *
     * @param cartProductItem The product to remove.
     * @return true if removed else false.
     */
    public boolean removeProductFromCart(CartProductItem cartProductItem) {
        int delete = delete("cartItemId = ?", new String[]{Long.toString(cartProductItem.getCartItem().getCartItemId())});
        return delete > 0;
    }

    /**
     * To get the products added in cart.
     *
     * @return The list of cart product items.
     */
    public List<CartProductItem> getProductsInCart() {
        // create new mapper for CartProductItem
        RowObjectMapper<CartProductItem> mapper = new SimpleRowObjectMapper<CartProductItem>() {

            final CartItemMapper cartItemMapper = new CartItemMapper();
            final ProductMapper productMapper = new ProductMapper();

            @Override
            public CartProductItem map(@NonNull ContentValues cv) {
                CartItem cartItem = cartItemMapper.map(cv);
                Product product = productMapper.map(cv);
                return new CartProductItem(cartItem, product);
            }
        };

        // query database
        String sql = "SELECT products.*, cart.* FROM products, cart WHERE products.id = cart.productId;";
        return this.controller.rawQuery(mapper, sql, null);
    }

    /**
     * To get the sum of price of products added in cart.
     *
     * @return The total price.
     */
    public double getTotalPrice() {
        // sql to select total price of items in cart
        String sql = "SELECT sum(products.price) FROM products, cart WHERE products.id = cart.productId;";

        // query database
        Cursor cursor = this.controller.getSQLiteDatabaseWrapper()
                .getReadableDatabase().query(sql);
        double totalPrice = 0;
        if (cursor.moveToFirst()) {
            totalPrice = cursor.getDouble(0);
        }
        cursor.close();
        return totalPrice;
    }
}
