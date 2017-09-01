/*
 * Project    : RetailStoreApp
 * File       : FragmentControllerImpl
 * Created on : 17/10/16, 1:14 PM
 */
package com.vertaperic.android.support;

import android.support.annotation.NonNull;

/**
 * The default implementation of fragment controller.
 *
 * @author Anny Patel
 */
class FragmentControllerImpl extends FragmentController {

    /**
     * The fragment back stack manager.
     */
    private final BackStackManager backStackManager;
    /**
     * The back press handler.
     */
    private final BackPressHandler backPressHandler;

    /**
     * Constructs new FragmentControllerImpl.
     */
    FragmentControllerImpl(@NonNull BackStackManager backStackManager) {
        this.backStackManager = backStackManager;
        this.backPressHandler = new BackPressHandler();
    }

    @NonNull
    @Override
    public BackStackManager getBackStackManager() {
        return this.backStackManager;
    }

    @NonNull
    @Override
    BackPressHandler getBackPressHandler() {
        return this.backPressHandler;
    }

    @Override
    boolean handleOnBackPressed() {
        // dispatch back press event to handler
        if (this.backPressHandler.handleOnBackPressed()) {
            return true;
        }

        // if fragment is removed then return
        if (this.backStackManager.remove(true)) {
            return true;
        }
        return false;
    }
}
