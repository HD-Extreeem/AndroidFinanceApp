package com.example.hadideknache.financeapp.Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * This class handles the database for the barcodes that is being scanned
 * Also used for getting already scanned before items
 * Created by hadideknache on 2017-09-20.
 */

public class BarCodeDBHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "barcode";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_COST = "cost";
    public static final String COLUMN_ITEMNAME = "itemname";
    public static final String COLUMN_ITEMID = "itemid";
    private static final String DATABASE_NAME = "barcode.db";
    private static final int DATABASE_VERSION = 1;

    /**
     * Creating the datatable
     */
    private static final String DATATABLE_CREATE =
            "create table " + TABLE_NAME + " (" +
                    COLUMN_ID + " integer primary key autoincrement, " +
                    COLUMN_ITEMID + " integer not null, " +
                    COLUMN_CATEGORY + " text not null, " +
                    COLUMN_COST + " text not null, " +
                    COLUMN_ITEMNAME + " text not null);";

    /**
     * Constructor for creating tableinstance
     */
    public BarCodeDBHelper(Context context) {
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
