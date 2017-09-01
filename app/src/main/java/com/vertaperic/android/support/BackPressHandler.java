/*
 * Project    : RetailStoreApp
 * File       : BackPressHandler
 * Created on : 10/30/2016 11:02 PM
 */
package com.vertaperic.android.support;

import android.support.annotation.NonNull;

import java.util.LinkedList;
import java.util.List;

/**
 * Simple handler class that manages the entries of back press listeners and dispatch the back press
 * event when required.
 *
 * @author Anny Patel
 */
class BackPressHandler {

    /**
     * The list of back press listeners.
     */
    private List<BackPressListener> backPressListeners;

    /**
     * To add listener to list.
     *
     * @param listener The back press listener.
     */
    void addBackPressListener(@NonNull BackPressListener listener) {
        initBackPressListeners();
        this.backPressListeners.add(listener);
    }

    /**
     * To remove listener from list.
     *
     * @param listener The back press listener.
     */
    void removeBackPressListener(@NonNull BackPressListener listener) {
        initBackPressListeners();
        this.backPressListeners.remove(listener);
    }

    /**
     * To initialize the back press listener list.
     */
    private void initBackPressListeners() {
        if (this.backPressListeners == null) {
            this.backPressListeners = new LinkedList<>();
        }
    }

    /**
     * To handle the back press event.
     *
     * @return true if event is consumed otherwise false.
     */
    boolean handleOnBackPressed() {
        // dispatch back press event to listeners
        if (this.backPressListeners != null) {
            for (BackPressListener listener : this.backPressListeners) {
                if (listener.onBackPressed())
                    return true;
            }
        }
        return false;
    }
}