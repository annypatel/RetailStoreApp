/*
 * Project    : RetailStoreApp
 * File       : ToolbarMenuDataBinding
 * Created on : 11/4/2016 2:37 PM
 */
package com.vertaperic.store.binding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.vertaperic.store.app.BasePresenter;

/**
 * Binding adapter for the Toolbar menu item click listener.
 *
 * @author Anny Patel
 */
public class ToolbarMenuDataBinding {

    /**
     * Set the menu item click listener to toolbar.
     * <p>
     * Creates the {@code app:menuItemClickListener} attribute for a {@link Toolbar} that takes a
     * {@link BasePresenter}.
     *
     * @param toolbar   The toolbar to which menu item click listener will be set.
     * @param presenter The presenter to which click event will be propagated.
     */
    @BindingAdapter("menuItemClickListener")
    public static void setOnMenuItemClickListener(Toolbar toolbar, final BasePresenter presenter) {

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return presenter != null && presenter.onMenuItemClick(item);
            }
        });
    }
}
