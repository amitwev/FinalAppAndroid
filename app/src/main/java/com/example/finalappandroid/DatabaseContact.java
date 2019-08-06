package com.example.finalappandroid;
import android.provider.BaseColumns;

public final class DatabaseContact {

    public DatabaseContact(){};
    public static final String DB_NAME = "finalApp.db";

    //Table for save location
    public static abstract class LocationHistory implements BaseColumns {
        public static final String TABLE_NAME = "LocationHistory";
        public static final String COLUMN_NAME_LOCATION_ID = "locationId";
        public static final String COLUMN_NAME_LATITUDE= "latitude";
        public static final String COLUMN_NAME_LONGITUDE= "longitude";

    }
}
