/*
 * Project    : RetailStoreApp
 * File       : BackStackManagerImpl
 * Created on : 20/10/16, 3:24 PM
 */
package com.vertaperic.android.support;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * The default implementation of BackStackManager.
 *
 * @author Anny Patel
 */
class BackStackManagerImpl extends BackStackManager {

    /**
     * The host activity context.
     */
    private final Context context;
    /**
     * The fragment manager.
     */
    private final FragmentManager fragmentManager;
    /**
     * The fragment animator.
     */
    private final FragmentAnimator animator;
    /**
     * The fragment animation.
     */
    private FragmentAnimation animation;

    /**
     * Constructs new BackStackManagerImpl.
     *
     * @param context         The host context.
     * @param fragmentManager The fragment manager.
     */
    BackStackManagerImpl(@NonNull Context context, @NonNull FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.animator = new FragmentAnimator(fragmentManager);
        // by default transactions are not animated
        this.animation = new FragmentAnimation(0, 0, 0, 0);
    }

    @NonNull
    @Override
    public FragmentManager getFragmentManager() {
        return this.fragmentManager;
    }

    @NonNull
    @Override
    public Context getContext() {
        return this.context;
    }

    @NonNull
    @Override
    FragmentAnimator getAnimator() {
        return this.animator;
    }

    @Override
    public void setAnimation(@NonNull FragmentAnimation animation) {
        this.animation = animation;
    }

    @Override
    public void add(@NonNull FragmentTransactionInfo transactionInfo) {
        FragmentTransaction transaction = this.fragmentManager.beginTransaction();
        this.animator.animatePendingTransition(transactionInfo.animate);
        this.animator.applyAnimation(transaction, this.animation);
        if (transactionInfo.addToBackStack) {
            transaction.addToBackStack(transactionInfo.tag);
        }
        transaction.replace(transactionInfo.containerViewId, transactionInfo.fragment, transactionInfo.tag)
                .commit();
    }

    @Override
    public boolean remove(boolean animate) {
        if (this.fragmentManager.getBackStackEntryCount() > 1) {

            this.animator.animatePendingTransition(animate);
            return this.fragmentManager.popBackStackImmediate();
        }
        return false;
    }

    @Override
    public boolean remove(String tag, boolean animate) {
        this.animator.animatePendingTransition(animate);
        return this.fragmentManager
                .popBackStackImmediate(tag, 0);
    }
}
