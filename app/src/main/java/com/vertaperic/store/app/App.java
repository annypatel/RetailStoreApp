/*
 * Project    : RetailStoreApp
 * File       : App
 * Created on : 10/30/2016 8:54 PM
 */
package com.vertaperic.store.app;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.vertaperic.android.database.BaseSQLiteOpenHelper;
import com.vertaperic.android.database.DatabaseController;
import com.vertaperic.android.database.DatabaseManager;

/**
 * Base application class for this app. Use this class to do one time initialization.
 *
 * @author Anny Patel
 */
public class App extends Application {

    /**
     * The dagger component for the app.
     */
    private AppComponent appComponent;

    /**
     * To get the single instance of AppComponent managed by {@link App} class.
     *
     * @param context The host context.
     * @return The app component.
     */
    public static AppComponent getAppComponent(@NonNull Context context) {
        App app = (App) context.getApplicationContext();
        return app.appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // create SQLite helper with lifecycle listener
        BaseSQLiteOpenHelper openHelper = DatabaseManager.newSQLiteOpenHelper(
                this, AppDatabaseLifecycleListener.DATABASE_NAME,
                null, AppDatabaseLifecycleListener.DATABASE_VERSION);
        openHelper.setDatabaseLifecycleListener(new AppDatabaseLifecycleListener());

        // create database controller and open the database
        DatabaseController databaseController = DatabaseManager.newDatabaseController(openHelper);
        databaseController.openAsync(null);

        // create the app component
        this.appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, databaseController))
                .build();
    }
}
