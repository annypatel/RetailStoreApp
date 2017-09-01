/*
 * Project    : RetailStoreApp
 * File       : Simulation
 * Created on : 8/11/16 3:37 PM
 */
package com.vertaperic.store.util;

/**
 * The helper class that provides helper method for simulation.
 *
 * @author Anny Patel
 */
public final class Simulation {

    // keep constructor private
    private Simulation() {
    }

    /**
     * To put thread to sleep for 1 seconds.
     */
    public static void sleep() {
        sleep(1000);
    }

    /**
     * To put thread to sleep for specified time.
     *
     * @param time Time to sleep in milliseconds.
     */
    public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ignore) {
        }
    }
}
