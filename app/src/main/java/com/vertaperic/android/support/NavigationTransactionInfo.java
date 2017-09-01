/*
 * Project    : RetailStoreApp
 * File       : NavigationTransactionInfo
 * Created on : 21/10/16, 4:17 PM
 */
package com.vertaperic.android.support;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Simple POJO that holds the information for the fragment transaction with navigation drawer item.
 *
 * @author Anny Patel
 */
public class NavigationTransactionInfo {

    /**
     * The fragment to commit in transaction.
     */
    final SupportFragment fragment;
    /**
     * The navigation drawer item id.
     */
    @IdRes
    final Integer itemId;
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
     * Constructs new NavigationTransactionInfo.
     *
     * @param fragment        The fragment to commit.
     * @param itemId          The navigation drawer item id.
     * @param containerViewId The container view id for fragment.
     */
    public NavigationTransactionInfo(@NonNull SupportFragment fragment, @IdRes int itemId, @IdRes int containerViewId) {
        this.fragment = fragment;
        this.itemId = itemId;
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
     * @param tag The fragment tag. By default the tag is set to fully qualified name of class.
     * @return The same fragment transaction info.
     */
    public NavigationTransactionInfo tag(@Nullable String tag) {
        this.tag = tag;
        return this;
    }

    /**
     * @param addToBackStack true to add fragment to back stack else false.
     * @return The same fragment transaction info.
     */
    public NavigationTransactionInfo addToBackStack(boolean addToBackStack) {
        this.addToBackStack = addToBackStack;
        return this;
    }

    /**
     * @param animate true to animate fragment transaction.
     * @return The same fragment transaction info.
     */
    public NavigationTransactionInfo animate(boolean animate) {
        this.animate = animate;
        return this;
    }
}
