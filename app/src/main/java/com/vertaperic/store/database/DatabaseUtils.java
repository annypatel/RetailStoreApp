/*
 * Project    : RetailStoreApp
 * File       : DatabaseUtils
 * Created on : 19/2/2018 3:33 PM
 */
package com.vertaperic.store.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Utility class for common database operations.
 *
 * @author Anny Patel
 */
class DatabaseUtils {

    /**
     * To import database from file in SQLite3 format.
     * <p/>
     * Note: Each SQL statement separated by "<code>;\n</code>". Single line comment that starts with
     * <code>--</code> and ends with <code>;\n</code> are supported.
     *
     * @param database The SQLite database instance.
     * @param is       The input stream to read statements from.
     * @throws IOException If fails to read input stream.
     */
    static void importSQLStatements(SupportSQLiteDatabase database, InputStream is) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(is);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        byte[] chunk = new byte[1024];
        int count;
        while ((count = bis.read(chunk)) >= 0) {
            bos.write(chunk, 0, count);
        }

        importSQLStatements(database, bos.toString());
    }

    /**
     * To import database from file in SQLite3 format.
     * <p/>
     * Note: Each SQL statement separated by "<code>;\n</code>". Single line comment that starts with
     * <code>--</code> and ends with <code>;\n</code> are supported.
     *
     * @param database      The SQLite database instance.
     * @param sqlStatements The SQL statement in string format.
     */
    static void importSQLStatements(SupportSQLiteDatabase database, String sqlStatements) {
        String[] statements = TextUtils.split(sqlStatements, ";\n");

        try {
            database.beginTransaction();

            for (String statement : statements) {
                if (statement != null) {

                    statement = statement.trim();
                    if (!statement.isEmpty() && !statement.startsWith("--")) {
                        database.execSQL(statement);
                    } else {
                        Log.i("DatabaseUtils", "Skipped: '" + statement + "'");
                    }
                }
            }

            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
    }
}
