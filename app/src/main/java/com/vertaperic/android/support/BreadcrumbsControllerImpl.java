/*
 * Project    : RetailStoreApp
 * File       : BreadcrumbsControllerImpl
 * Created on : 11/5/2016 7:29 PM
 */
package com.vertaperic.android.support;

import android.support.annotation.NonNull;

import com.vertaperic.store.widget.BreadcrumbSection;
import com.vertaperic.store.widget.BreadcrumbSectionView;
import com.vertaperic.store.widget.BreadcrumbsView;

/**
 * The default implementation of BreadcrumbsController.
 *
 * @author Anny Patel
 */
class BreadcrumbsControllerImpl extends BreadcrumbsController {

    /**
     * The fragment controller.
     */
    private final FragmentController controller;
    /**
     * The breadcrumb view.
     */
    private final BreadcrumbsView breadcrumbsView;

    /**
     * Constructs new BreadcrumbsControllerImpl.
     *
     * @param controller      The fragment controller.
     * @param breadcrumbsView The breadcrumb view.
     */
    BreadcrumbsControllerImpl(@NonNull FragmentController controller, @NonNull BreadcrumbsView breadcrumbsView) {
        this.controller = controller;
        this.breadcrumbsView = breadcrumbsView;

        // add on breadcrumb click listener
        this.breadcrumbsView.setOnBreadcrumbClickListener(new BreadcrumbsView.OnBreadcrumbClickListener() {
            @Override
            public void onBreadcrumbClick(BreadcrumbSectionView sectionView) {
                handleBreadcrumbClick(sectionView);
            }
        });
    }

    @Override
    public void add(@NonNull BreadcrumbTransactionInfo transactionInfo) {
        // create breadcrumb section from transaction info
        BreadcrumbSection section = new BreadcrumbSection(transactionInfo.title);
        section.setSeparatorVisibility(transactionInfo.showSeparator);
        section.setTag(transactionInfo.tag);

        // add breadcrumb section and fragment to back stack
        this.breadcrumbsView.add(section);
        this.controller.getBackStackManager().add(
                new FragmentTransactionInfo(transactionInfo.fragment, transactionInfo.containerViewId)
                        .animate(transactionInfo.animate)
                        .addToBackStack(transactionInfo.addToBackStack)
                        .tag(transactionInfo.tag)
        );
    }

    /**
     * To handle the breadcrumb click event.
     *
     * @param sectionView The view that was clicked.
     */
    private void handleBreadcrumbClick(BreadcrumbSectionView sectionView) {
        // remove section from breadcrumb view
        this.breadcrumbsView.removeUpTo(sectionView);
        // remove fragments from back stack by tag
        BreadcrumbSection section = sectionView.getBreadcrumbSection();
        this.controller.getBackStackManager().remove(section.getTag(), true);
    }

    @Override
    public boolean handleOnBackPressed() {
        // if more than one breadcrumb is added then remove it first
        if (this.breadcrumbsView.getBreadcrumbSectionCount() > 1) {

            // remove fragments and breadcrumb section from top of stack
            this.breadcrumbsView.remove();
            this.controller.getBackStackManager().remove(true);
            return true;

        } else {
            return false;
        }
    }
}
