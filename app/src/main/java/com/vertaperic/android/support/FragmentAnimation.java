/*
 * Project    : RetailStoreApp
 * File       : FragmentAnimation
 * Created on : 17/10/16, 2:05 PM
 */
package com.vertaperic.android.support;

import android.support.annotation.AnimRes;

/**
 * Simple POJO that holds the fragment transition animations.
 *
 * @author Anny Patel
 */
public class FragmentAnimation {

    @AnimRes
    final int enter;
    @AnimRes
    final int exit;
    @AnimRes
    final int popEnter;
    @AnimRes
    final int popExit;

    /**
     * Specific animation resources to run for the fragments that are entering and exiting in
     * transaction. The popEnter and popExit animations will be played for enter/exit operations
     * specifically when popping the back stack
     */
    public FragmentAnimation(@AnimRes int enter, @AnimRes int exit, @AnimRes int popEnter, @AnimRes int popExit) {
        this.enter = enter;
        this.exit = exit;
        this.popEnter = popEnter;
        this.popExit = popExit;
    }
}
