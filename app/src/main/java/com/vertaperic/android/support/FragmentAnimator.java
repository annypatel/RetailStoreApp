/*
 * Project    : RetailStoreApp
 * File       : FragmentAnimator
 * Created on : 17/10/16, 2:37 PM
 */
package com.vertaperic.android.support;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.animation.Animation;

/**
 * This class used to control the fragment transition animations.
 *
 * @author Anny Patel
 */
class FragmentAnimator implements FragmentManager.OnBackStackChangedListener {

    /**
     * The flag for pending fragment transition animation. By default all transitions are animated
     * so default value is set to true.
     */
    private boolean animate;

    /**
     * Constructs new fragment animator.
     *
     * @param fragmentManager The fragment manager.
     */
    FragmentAnimator(@NonNull FragmentManager fragmentManager) {
        this.animate = true;
        fragmentManager.addOnBackStackChangedListener(this);
    }

    /**
     * To override the pending transaction.
     *
     * @param animate true to animate, otherwise false.
     */
    void animatePendingTransition(boolean animate) {
        this.animate = animate;
    }

    /**
     * To create the animation for fragment.
     *
     * @return The animation or null.
     */
    Animation createAnimation() {
        if (this.animate) {
            // fragment manager will animate the fragment transition if null is returned
            return null;
        } else {
            // return empty animation, so fragment transition will not be animated
            return new Animation() {
            };
        }
    }

    /**
     * To apply animation to fragment transaction.
     *
     * @param transaction The fragment transaction.
     * @param animation   The animation to apply.
     */
    void applyAnimation(@NonNull FragmentTransaction transaction, @NonNull FragmentAnimation animation) {
        transaction.setCustomAnimations(
                animation.enter,
                animation.exit,
                animation.popEnter,
                animation.popExit);
    }

    @Override
    public void onBackStackChanged() {
        // reset the override flag to true as by default all transitions are animated
        animatePendingTransition(true);
    }
}
