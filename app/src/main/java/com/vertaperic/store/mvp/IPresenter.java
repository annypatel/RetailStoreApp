/*
 * Project    : RetailStoreApp
 * File       : BasePresenter
 * Created on : 11/3/2016 7:43 PM
 */
package com.vertaperic.store.mvp;

/**
 * Base presenter interface with common methods.
 *
 * @author Anny Patel
 */
public interface IPresenter {

    /**
     * To bind the view with presenter.
     *
     * @param view The view to bind.
     */
    void bind(IView view);

    /**
     * To unbind the view from presenter.
     */
    void unbind();
}
