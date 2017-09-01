/*
 * Project    : RetailStoreApp
 * File       : SupportFragment
 * Created on : 17/10/16, 1:13 PM
 */
package com.vertaperic.android.support;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.animation.Animation;

/**
 * The fragment sub class that provides access to fragment controller API.
 *
 * @author Anny Patel
 */
public class SupportFragment extends Fragment implements BackPressListener {

    /**
     * The fragment controller of host(activity or fragment). If this fragment is hosted by activity
     * then it is fragment controller of host activity, and if this fragment is hosted by another
     * fragment then it is fragment controller of parent fragment.
     */
    private FragmentController fragmentController;
    /**
     * The fragment controller for this fragment.
     */
    private FragmentController childFragmentController;

    /**
     * To get the fragment controller of host(activity of fragment). If this fragment is hosted by
     * activity then it is fragment controller of host activity, and if this fragment is hosted by
     * another fragment then it is fragment controller of parent fragment.
     *
     * @return The fragment controller.
     */
    public FragmentController getFragmentController() {
        if (this.fragmentController == null) {

            Fragment fragment = getParentFragment();
            if (fragment instanceof SupportFragment) {
                this.fragmentController = ((SupportFragment) fragment)
                        .getChildFragmentController();
            } else {
                this.fragmentController = ((SupportAppCompatActivity) getActivity())
                        .getFragmentController();
            }
        }
        return fragmentController;
    }

    /**
     * To get the fragment controller for this fragment.
     *
     * @return The child fragment controller.
     */
    public FragmentController getChildFragmentController() {
        if (this.childFragmentController == null) {
            this.childFragmentController = SupportFactory.fragmentController(createBackStackManager());
        }
        return childFragmentController;
    }

    /**
     * To create the back stack manager.
     *
     * @return The back stack manager.
     */
    @NonNull
    protected BackStackManager createBackStackManager() {
        return SupportFactory.backStackManager(getContext(), getChildFragmentManager());
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return getFragmentController().getBackStackManager()
                .getAnimator()
                .createAnimation();
    }

    @Override
    public void onStart() {
        super.onStart();

        // register back-press listener if back press support enabled
        if (isBackPressSupportEnabled()) {
            getFragmentController().getBackPressHandler()
                    .addBackPressListener(this);
        }
    }

    @Override
    public void onStop() {
        // unregister back-press listener if back press support enabled
        if (isBackPressSupportEnabled()) {
            getFragmentController().getBackPressHandler()
                    .removeBackPressListener(this);
        }

        super.onStop();
    }

    /**
     * To check if back-press support is enabled.
     *
     * @return true if enabled otherwise false.
     */
    protected boolean isBackPressSupportEnabled() {
        return false;
    }

    @Override
    public boolean onBackPressed() {
        // dispatch back press to child fragment controller
        return getChildFragmentController().handleOnBackPressed();
    }
}
