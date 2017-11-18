package com.example.hadideknache.financeapp.Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * This class holds all user information
 * Whenever a user registers, its information then gets saved in the database
 * Used also when logging in and checking whether a user already is registered
 * Created by Hadi Deknache on 2017-09-16.
 */

public class UserDBHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "user";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASS = "pass";
    private static final String DATABASE_NAME = "user.db";
    private static final int DATABASE_VERSION = 1;

    /**
     * Creating the datatable with following structure:
     */
    private static final String DATABASE_CREATE =
            "create table " + TABLE_NAME + " (" +
            COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_NAME + " text not null, " +
            COLUMN_SURNAME + " text not null, " +
            COLUMN_EMAIL + " text not null, " +
            COLUMN_PASS + " text not null);";

    /**
     * Constructor for creating tableinstance
     */
    public UserDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Overridden method for creating the database if not already created on first time started
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    /**
     * Method is used when wanting to upgrade database and destroy old one
     * @param db instance to database
     * @param oldVersion the version of the database
     * @param newVersion the new version of the database that is wanted to  be upgraded to
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(UserDBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
