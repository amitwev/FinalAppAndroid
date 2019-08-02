package com.example.finalappandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = DatabaseContact.DB_NAME;
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    //Declare the String to Create Tables
    private static final String SQL_CREATE_DRIVER_TABLE =
            "CREATE TABLE " + DatabaseContact.DriverTable.TABLE_NAME + " (" +
            DatabaseContact.DriverTable.COL_NAME_DRIVER_NAME + TEXT_TYPE + COMMA_SEP +
            DatabaseContact.DriverTable.COL_NAME_DRIVER_CAR_TYPE+ TEXT_TYPE + COMMA_SEP +
            DatabaseContact.DriverTable.COL_NAME_DRIVER_CAR_NUMBER + TEXT_TYPE  + ")";
    private static final String SQL_DELETE_DRIVER_TABLE =
            "DROP TABLE IF EXISTS " + DatabaseContact.DriverTable.TABLE_NAME;


    MyDatabaseHelper(Context context){
        super(context,DATABASE_NAME , null,DATABASE_VERSION);
    }

    @Override
    //onCreate - create on first time -> put here the table that need to create
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_DRIVER_TABLE);
    }

    @Override
    //upgrade will called when we cahnge the VERSION
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_DRIVER_TABLE);
    }
}
