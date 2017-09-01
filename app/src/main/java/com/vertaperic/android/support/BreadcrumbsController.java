/*
 * Project    : RetailStoreApp
 * File       : BreadcrumbsController
 * Created on : 11/5/2016 7:23 PM
 */
package com.vertaperic.android.support;

import android.support.annotation.NonNull;

/**
 * Controller to manage the {@link com.vertaperic.store.widget.BreadcrumbsView} with the fragments
 * in back stack.
 *
 * @author Anny Patel
 */
public abstract class BreadcrumbsController {

    /**
     * To add breadcrumb section with fragment transaction.
     *
     * @param transactionInfo The transaction info.
     */
    public abstract void add(@NonNull BreadcrumbTransactionInfo transactionInfo);

    /**
     * To handle the back press event.
     *
     * @return true if event is consumed otherwise false.
     */
    public abstract boolean handleOnBackPressed();
}
