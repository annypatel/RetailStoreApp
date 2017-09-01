/*
 * Project    : RetailStoreApp
 * File       : FragmentTransactionInfo
 * Created on : 17/10/16, 1:14 PM
 */
package com.vertaperic.android.support;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Simple POJO that holds the information for the fragment transaction.
 *
 * @author Anny Patel
 */
public class FragmentTransactionInfo {

    /**
     * The fragment to commit in transaction.
     */
    final SupportFragment fragment;
    /**
     * The container view id for fragment.
     */
    @IdRes
    final int containerViewId;
    /**
     * The tag for the fragment, by default it is fully qualified name of class.
     */
    String tag;
    /**
     * Flag for the back stack.
     */
    boolean addToBackStack;
    /**
     * Flag for animation of fragment transaction.
     */
    boolean animate;

    /**
     * Constructs new FragmentTransactionInfo.
     *
     * @param fragment        The fragment to commit.
     * @param containerViewId The container view id for fragment.
     */
    public FragmentTransactionInfo(@NonNull SupportFragment fragment, @IdRes int containerViewId) {
        this.fragment = fragment;
        this.containerViewId = containerViewId;
        this.tag = defaultTag(fragment.getClass());
    }

    /**
     * To get the default tag for the fragment transaction.
     *
     * @param clazz The class to get tag for.
     * @return The tag that will be used with fragment transaction.
     */
    public static String defaultTag(Class<? extends SupportFragment> clazz) {
        return clazz.getName();
    }

    /**
     * @param tag The fragment tag.
     * @return The same fragment transaction info.
     */
    public FragmentTransactionInfo tag(@Nullable String tag) {
        this.tag = tag;
        return this;
    }

    /**
     * @param addToBackStack true to add fragment to back stack else false.
     * @return The same fragment transaction info.
     */
    public FragmentTransactionInfo addToBackStack(boolean addToBackStack) {
        this.addToBackStack = addToBackStack;
        return this;
    }

    /**
     * @param animate true to animate fragment transaction.
     * @return The same fragment transaction info.
     */
    public FragmentTransactionInfo animate(boolean animate) {
        this.animate = animate;
        return this;
    }
}
