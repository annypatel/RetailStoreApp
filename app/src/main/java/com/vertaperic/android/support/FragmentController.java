/*
 * Project    : RetailStoreApp
 * File       : FragmentController
 * Created on : 17/10/16, 3:39 PM
 */
package com.vertaperic.android.support;

import android.support.annotation.NonNull;

/**
 * Fragment controller provides the entry point to control the fragment back stack and and back
 * press events.
 *
 * @author Anny Patel
 */
public abstract class FragmentController {

    /**
     * To get the fragment back stack manager.
     *
     * @return The back stack manager of this controller.
     */
    @NonNull
    public abstract BackStackManager getBackStackManager();

    /**
     * To get the back press handler.
     *
     * @return The back press handler.
     */
    @NonNull
    abstract BackPressHandler getBackPressHandler();

    /**
     * To handle the back press event.
     *
     * @return true if event is consumed otherwise false.
     */
    abstract boolean handleOnBackPressed();
}
