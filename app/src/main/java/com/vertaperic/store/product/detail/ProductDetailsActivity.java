/*
 * Project    : RetailStoreApp
 * File       : ProductDetailsActivity
 * Created on : 8/11/16 7:19 PM
 */
package com.vertaperic.store.product.detail;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.vertaperic.android.support.FragmentTransactionInfo;
import com.vertaperic.android.support.SupportAppCompatActivity;
import com.vertaperic.store.R;
import com.vertaperic.store.app.App;
import com.vertaperic.store.cart.CartItem;
import com.vertaperic.store.product.Product;

/**
 * Activity for hosting the {@link ProductDetailsFragment}, displays product details.
 *
 * @author Anny Patel
 */
public class ProductDetailsActivity extends SupportAppCompatActivity {

    /**
     * The extra name for product to pass to ProductDetailsActivity. To display product details its
     * mandatory to pass {@link Product} with this key.
     */
    public static final String EXTRA_PRODUCT = "com.vertaperic.store.Product";

    /**
     * The extra name for cart item to pass to ProductDetailsActivity. Cart item is optional,
     * if passed that means product is already added in the cart otherwise not.
     */
    public static final String EXTRA_CART_ITEM = "com.vertaperic.store.CartItem";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_fragment_host);

        // create the fragment
        ProductDetailsFragment fragment = (ProductDetailsFragment) getSupportFragmentManager()
                .findFragmentById(R.id.container);
        if (fragment == null) {
            fragment = ProductDetailsFragment.newInstance(getProductFromIntent(), getCartItemFromIntent());

            // select navigation item for home
            getFragmentController().getBackStackManager().add(
                    new FragmentTransactionInfo(fragment, R.id.container)
            );
        }

        // inject dependencies with dagger
        DaggerProductDetailsComponent.builder()
                .appComponent(App.getAppComponent(this))
                .productDetailsModule(new ProductDetailsModule(fragment))
                .build()
                .inject(fragment);
    }

    /**
     * To get the product from the intent.
     *
     * @return The product.
     */
    @NonNull
    private Product getProductFromIntent() {
        Intent intent = getIntent();
        Product product = (Product) intent.getSerializableExtra(EXTRA_PRODUCT);
        if (product == null) {
            throw new IllegalArgumentException("product is null");
        }
        return product;
    }

    /**
     * To get the cart item from the intent.
     *
     * @return The cart item if found, null otherwise.
     */
    @Nullable
    private CartItem getCartItemFromIntent() {
        Intent intent = getIntent();
        return (CartItem) intent.getSerializableExtra(EXTRA_CART_ITEM);
    }
}
