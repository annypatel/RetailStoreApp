package com.vertaperic.store.mvp;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Base presenter with RxJava support. Provides {@link CompositeDisposable} for holding multiple
 * disposables, which will be cleared when view is detached from presenter.
 *
 * @author Anny Patel
 */
public class RxBasePresenter<V extends IView> extends BasePresenter<V> {

    /**
     * Container for holding multiple disposables.
     */
    protected final CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onViewDetached() {
        // clear all disposable when view is detached
        this.disposables.clear();
        super.onViewDetached();
    }
}
