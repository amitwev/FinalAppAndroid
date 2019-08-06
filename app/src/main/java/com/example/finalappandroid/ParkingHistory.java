package com.example.finalappandroid;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ParkingHistory extends AppCompatActivity {
    private MyDatabaseHelper myDatabaseHelper;
    private SQLiteDatabase dbRead;
    private String sortLocationString;
    private String[] returnCulomnsFromDB;
    private Cursor allData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_history);
        //For Db Connection
        initDB();
        allData = getAllDataFromDB();
        writeDataToTable();

    }

    private void writeDataToTable() {
        //TODO add here function to insert the data to table
    }

    private Cursor getAllDataFromDB() {
        return dbRead.query(
                DatabaseContact.LocationHistory.TABLE_NAME,
                returnCulomnsFromDB,
                null, null, null, null,
                sortLocationString, "5");
//        Log.d(this.toString(), "the data = " + allData +
//                ", count = " + allData.getCount() +
//                ", column names = " + allData.getColumnNames().toString() +
//                ", first item = " + allData.moveToFirst());
//        for(int i = 0 ; i < allData.getCount(); i++){
//            Log.d(this.toString(), "first row = " +
//                    ",column 1 = " + allData.getInt(0 ) +
//                    ",column 2 = " + allData.getString(1) +
//                    ",column 3 = " + allData.getString(2));
//            allData.moveToNext();
//        }
    }

    private void initDB(){
        myDatabaseHelper = new MyDatabaseHelper(this);
        dbRead = myDatabaseHelper.getReadableDatabase();
        sortLocationString = DatabaseContact.LocationHistory.COLUMN_NAME_LOCATION_ID ;
        returnCulomnsFromDB = new String[]{
                DatabaseContact.LocationHistory.COLUMN_NAME_LOCATION_ID,
                DatabaseContact.LocationHistory.COLUMN_NAME_LATITUDE,
                DatabaseContact.LocationHistory.COLUMN_NAME_LONGITUDE
        };
    }
}
