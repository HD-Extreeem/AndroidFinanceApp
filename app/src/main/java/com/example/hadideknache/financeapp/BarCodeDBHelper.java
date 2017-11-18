package com.example.hadideknache.financeapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
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

    private static final String DATATABLE_CREATE =
            "create table " + TABLE_NAME + " (" +
                    COLUMN_ID + " integer primary key autoincrement, " +
                    COLUMN_ITEMID + " integer not null, " +
                    COLUMN_CATEGORY + " text not null, " +
                    COLUMN_COST + " text not null, " +
                    COLUMN_ITEMNAME + " text not null);";

    public BarCodeDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATATABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(UserDBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
