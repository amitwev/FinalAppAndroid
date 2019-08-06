package com.example.finalappandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = DatabaseContact.DB_NAME;
    private static final String DOUBLE_TYPE = " DOUBLE";
    private static final String TEXT_TYPE = "TEXT";
    private static final String COMMA_SEP = ",";

    //Declare the String to Create Tables
    private static final String SQL_CREATE_LOCATION_HISTORY_TABLE =
            "CREATE TABLE IF NOT EXISTS " + DatabaseContact.LocationHistory.TABLE_NAME + "(" +
            DatabaseContact.LocationHistory.COLUMN_NAME_LOCATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
            DatabaseContact.LocationHistory.COLUMN_NAME_LATITUDE + DOUBLE_TYPE + COMMA_SEP +
            DatabaseContact.LocationHistory.COLUMN_NAME_LONGITUDE + DOUBLE_TYPE + ")";
    private static final String SQL_DELETE_LOCATION_HISTORY_TABLE =
            "DROP TABLE IF EXISTS " + DatabaseContact.LocationHistory.TABLE_NAME;

    MyDatabaseHelper(Context context){
        super(context,DATABASE_NAME , null,DATABASE_VERSION);
    }

    @Override
    //onCreate - create on first time -> put here the table that need to create
    public void onCreate(SQLiteDatabase db) {
        Log.d(this.toString(), "DB Created history table ");
        db.execSQL(SQL_CREATE_LOCATION_HISTORY_TABLE);
    }

    @Override
    //upgrade will called when we cahnge the VERSION
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_LOCATION_HISTORY_TABLE);
    }
}
