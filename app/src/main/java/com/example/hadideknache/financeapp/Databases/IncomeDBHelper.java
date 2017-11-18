package com.example.hadideknache.financeapp.Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * This class handles the database of the income which holds all incomes for each user
 * Every user saves its own information of incomes object information
 * Created by Hadi Deknache on 2017-09-17.
 */

public class IncomeDBHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "income";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_EARN = "earn";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_USERID = "userid";
    private static final String DATABASE_NAME = "income.db";
    private static final int DATABASE_VERSION = 1;

    /**
     * Creating the datatable with following structure:
     */
    private static final String DATATABLE_CREATE =
            "create table " + TABLE_NAME + " (" +
                    COLUMN_ID + " integer primary key autoincrement, " +
                    COLUMN_USERID + " integer not null, " +
                    COLUMN_CATEGORY + " text not null, " +
                    COLUMN_TIME + " text not null, " +
                    COLUMN_DATE + " text not null, " +
                    COLUMN_EARN + " text not null, " +
                    COLUMN_TITLE + " text not null);";

    /**
     * Constructor for creating tableinstance
     */
    public IncomeDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Overridden method for creating the database if not already created on first time started
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATATABLE_CREATE);
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
