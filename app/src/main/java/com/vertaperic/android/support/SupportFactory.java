/*
 * Project    : RetailStoreApp
 * File       : SupportFactory
 * Created on : 10/11/16 4:09 PM
 */
package com.vertaperic.android.support;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.vertaperic.store.widget.BreadcrumbsView;

/**
 * The factory class to create the support controllers.
 *
 * @author Anny Patel
 */
public final class SupportFactory {

    // keep constructor private
    private SupportFactory() {
    }

    /**
     * @param backStackManager The back stack manager.
     * @return The new fragment controller.
     */
    public static FragmentController fragmentController(@NonNull BackStackManager backStackManager) {
        return new FragmentControllerImpl(backStackManager);
    }

    /**
     * @param context         The host context.
     * @param fragmentManager The fragment manager.
     * @return The new back stack manager.
     */
    public static BackStackManager backStackManager(@NonNull Context context, @NonNull FragmentManager fragmentManager) {
        return new BackStackManagerImpl(context, fragmentManager);
    }

    /**
     * @param controller The fragment controller.
     * @return The new navigation controller.
     */
    public static NavigationDrawerController navigationDrawerController(@NonNull FragmentController controller) {
        return new NavigationDrawerControllerImpl(controller);
    }

    /**
     * @param controller      The fragment controller.
     * @param breadcrumbsView The breadcrumb view.
     * @return The new breadcrumbs controller.
     */
    public static BreadcrumbsController breadcrumbsController(@NonNull FragmentController controller, @NonNull BreadcrumbsView breadcrumbsView) {
        return new BreadcrumbsControllerImpl(controller, breadcrumbsView);
    }
}
