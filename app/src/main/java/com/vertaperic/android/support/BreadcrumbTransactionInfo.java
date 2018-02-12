/*
 * Project    : RetailStoreApp
 * File       : BreadcrumbTransactionInfo
 * Created on : 11/5/2016 7:24 PM
 */
package com.vertaperic.android.support;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Simple POJO that holds the information for the breadcrumb fragment transaction.
 *
 * @author Anny Patel
 */
public class BreadcrumbTransactionInfo {

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
     * The breadcrumb section title.
     */
    final String title;
    /**
     * The tag for the fragment, by default it is fully qualified name of class appended with
     * {@link #title}.
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
     * Flag for visibility of separator.
     */
    boolean showSeparator;

    /**
     * Constructs new BreadcrumbTransactionInfo.
     *
     * @param title           The breadcrumb section title.
     * @param fragment        The fragment to commit.
     * @param containerViewId The container view id for fragment.
     */
    public BreadcrumbTransactionInfo(@NonNull String title, @NonNull SupportFragment fragment, @IdRes int containerViewId) {
        this.title = title;
        this.fragment = fragment;
        this.containerViewId = containerViewId;
        this.tag = defaultTag(title, fragment.getClass());
    }

    /**
     * To get the default tag for the fragment transaction.
     *
     * @param title The title of breadcrumb section.
     * @param clazz The class of the fragment to get tag for.
     * @return The tag for the fragment used with fragment transaction, fully qualified name of
     * class appended with title.
     */
    private static String defaultTag(String title, Class<? extends SupportFragment> clazz) {
        return clazz.getName() + "." + title;
    }

    /**
     * @param tag The fragment tag.
     * @return The same transaction info.
     */
    public BreadcrumbTransactionInfo tag(@Nullable String tag) {
        this.tag = tag;
        return this;
    }

    /**
     * @param addToBackStack true to add fragment to back stack else false.
     * @return The same transaction info.
     */
    public BreadcrumbTransactionInfo addToBackStack(boolean addToBackStack) {
        this.addToBackStack = addToBackStack;
        return this;
    }

    /**
     * @param animate true to animate fragment transaction.
     * @return The same transaction info.
     */
    public BreadcrumbTransactionInfo animate(boolean animate) {
        this.animate = animate;
        return this;
    }

    /**
     * @param showSeparator true to show the separator, false otherwise.
     * @return The same transaction info.
     */
    public BreadcrumbTransactionInfo showSeparator(boolean showSeparator) {
        this.showSeparator = showSeparator;
        return this;
    }
}

