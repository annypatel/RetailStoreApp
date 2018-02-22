/*
 * Project    : RetailStoreApp
 * File       : AppRxSchedulers
 * Created on : 2/22/16 7:23 PM
 */
package com.vertaperic.store.app;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Simple implementation on {@link RxSchedulers} that provides schedulers for app.
 */
@Singleton
class AppRxSchedulers implements RxSchedulers {

    @Inject
    AppRxSchedulers() {
    }

    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler mainThread() {
        return AndroidSchedulers.mainThread();
    }
}
