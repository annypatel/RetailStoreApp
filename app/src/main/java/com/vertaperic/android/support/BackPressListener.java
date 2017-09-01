/*
 * Project    : RetailStoreApp
 * File       : BackPressListener
 * Created on : 10/30/2016 11:00 PM
 */
package com.vertaperic.android.support;

/**
 * The listener for back press event. Fragments must implement this interface to receive the back
 * press event.
 *
 * @author Anny Patel
 */
interface BackPressListener {

    /**
     * Called when back button is pressed.
     *
     * @return true if back pressed event is consumed otherwise false.
     */
    boolean onBackPressed();
}
