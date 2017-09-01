/*
 * Project    : RetailStoreApp
 * File       : BackStackManager
 * Created on : 10/30/2016 11:06 PM
 */
package com.vertaperic.android.support;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

/**
 * The abstraction that manages the fragment back stack and transition animations.
 *
 * @author Anny Patel
 */
public abstract class BackStackManager {

    /**
     * To get the context to which this manager is attached.
     *
     * @return The host activity context.
     */
    @NonNull
    public abstract Context getContext();

    /**
     * To get the fragment manager to which this manager is attached.
     *
     * @return The fragment manager.
     */
    @NonNull
    public abstract FragmentManager getFragmentManager();

    /**
     * To get the fragment animator.
     *
     * @return The fragment animator.
     */
    @NonNull
    abstract FragmentAnimator getAnimator();

    /**
     * To set the fragment animation.
     *
     * @param animation The fragment animation.
     */
    public abstract void setAnimation(@NonNull FragmentAnimation animation);

    /**
     * To add fragment to given container.
     *
     * @param transactionInfo The fragment transaction info.
     */
    public abstract void add(@NonNull FragmentTransactionInfo transactionInfo);

    /**
     * To remove fragment from the top of the stack.
     *
     * @param animate true to animate the transaction else false. User {@link FragmentAnimation}
     *                for custom animations.
     * @return true if fragment removed otherwise false.
     */
    public abstract boolean remove(boolean animate);

    /**
     * To remove fragments from back stack.
     *
     * @param tag     The tag of the fragment to look for in back stack; if found, all
     *                states up to that state will be popped.
     * @param animate true to animate the transaction else false. User {@link FragmentAnimation}
     *                for custom animations.
     * @return true if any fragment removed otherwise false.
     */
    public abstract boolean remove(String tag, boolean animate);
}
