/*
 * Project    : RetailStoreApp
 * File       : MyCartActivity
 * Created on : 11/4/2016 3:39 PM
 */
package com.vertaperic.store.cart;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.vertaperic.android.support.FragmentTransactionInfo;
import com.vertaperic.android.support.SupportAppCompatActivity;
import com.vertaperic.store.R;

/**
 * Activity for hosting the {@link MyCartFragment}, displays the products added into the cart.
 *
 * @author Anny Patel
 */
public class MyCartActivity extends SupportAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_fragment_host);

        // create the fragment
        MyCartFragment fragment = (MyCartFragment) getSupportFragmentManager()
                .findFragmentById(R.id.container);
        if (fragment == null) {
            fragment = MyCartFragment.newInstance();

            // add fragment to this activity
            getFragmentController().getBackStackManager().add(
                    new FragmentTransactionInfo(fragment, R.id.container)
            );
        }
    }
}
