/*
 * Project    : RetailStoreApp
 * File       : ToolbarNavigationDataBinding
 * Created on : 11/4/2016 1:46 PM
 */
package com.vertaperic.store.binding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.vertaperic.store.app.BasePresenter;

/**
 * Binding adapter for the Toolbar navigation click listener.
 *
 * @author Anny Patel
 */
public class ToolbarNavigationDataBinding {

    /**
     * Set the navigation on-click listener to toolbar.
     * <p>
     * Creates the {@code app:navigationOnClickListener} attribute for a {@link Toolbar} that takes
     * a {@link BasePresenter}.
     *
     * @param toolbar   The toolbar to which navigation click listener will be set.
     * @param presenter The presenter to which click event will be propagated.
     */
    @BindingAdapter("navigationOnClickListener")
    public static void setOnToolbarNavigationClickListener(Toolbar toolbar, final BasePresenter presenter) {

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (presenter != null) {
                    presenter.onToolbarNavigationClick();
                }
            }
        });
    }
}
