/*
 * Project    : RetailStoreApp
 * File       : RxSchedulers
 * Created on : 2/22/16 7:23 PM
 */
package com.vertaperic.store.app;

import io.reactivex.Scheduler;

/**
 * Base interface for providing reactive schedulers.
 */
public interface RxSchedulers {

    /**
     * To get the scheduler for IO related work.
     *
     * @return A scheduler for IO work.
     */
    Scheduler io();

    /**
     * To get the scheduler for main thread on android.
     *
     * @return A scheduler that executes action on android main thread.
     */
    Scheduler mainThread();
}
