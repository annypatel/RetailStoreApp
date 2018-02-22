/*
 * Project    : RetailStoreApp
 * File       : NavigationItemSelectionCallback
 * Created on : 20/10/16, 1:18 PM
 */
package com.vertaperic.android.support;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

/**
 * The callback listener for handling events on navigation items.
 *
 * @author Anny Patel
 */
public interface NavigationItemSelectionCallback {

    /**
     * Called when an item in the navigation menu is clicked.
     *
     * @param item The selected item.
     * @return true to display the item as selected item.
     */
    boolean shouldCheckedNavigationItem(@NonNull MenuItem item);

    /**
     * Called when an item in the navigation menu is selected.
     *
     * @param item The selected item.
     */
    void onNavigationItemSelected(@NonNull MenuItem item);

    /**
     * The wrapper class that wraps {@link NavigationItemSelectionCallback} into
     * {@link NavigationView.OnNavigationItemSelectedListener}.
     */
    class Wrapper {

        /**
         * To wrap the {@link NavigationItemSelectionCallback} into
         * {@link NavigationView.OnNavigationItemSelectedListener}. The default implementation
         * dispatch the method {@link #shouldCheckedNavigationItem(MenuItem)} as soon as item is clicked,
         * but the method {@link #onNavigationItemSelected(MenuItem)} will be dispatched after the
         * delay of 300 milliseconds. This delay will allow sliding drawer to close with smooth
         * animation without any zigzag.
         *
         * @param listener The inner listener.
         * @return The on navigation item selection listener.
         */
        static NavigationView.OnNavigationItemSelectedListener wrap(NavigationItemSelectionCallback listener) {
            return new OnNavigationItemSelectedListenerImpl(listener);
        }

        /**
         * The internal implementation of OnNavigationItemSelectedListener.
         */
        static class OnNavigationItemSelectedListenerImpl implements NavigationView.OnNavigationItemSelectedListener {

            /**
             * The inner listener.
             */
            private final NavigationItemSelectionCallback listener;
            /**
             * The handler to post selection event with delay.
             */
            private final Handler handler;

            OnNavigationItemSelectedListenerImpl(NavigationItemSelectionCallback listener) {
                this.listener = listener;
                this.handler = new Handler();
            }

            @Override
            public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
                boolean checked = this.listener.shouldCheckedNavigationItem(item);
                this.handler.postDelayed(() -> listener.onNavigationItemSelected(item), 300);
                return checked;
            }
        }
    }
}
