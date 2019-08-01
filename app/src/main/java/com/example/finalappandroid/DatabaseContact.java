package com.example.finalappandroid;

public final class DatabaseContact {

    public DatabaseContact(){};

    public static final String DB_NAME = "finalApp.db";


    public static abstract class DriverTable{
        public static final String TABLE_NAME = "Driver";
        public static final String COL_NAME_DRIVER_NAME = "";
        public static final String COL_NAME_DRIVER_CAR_TYPE= "";
        public static final String COL_NAME_DRIVER_CAR_NUMBER= "";
    }

    public static abstract class ParkingHistoryTable{
        public static final String TABLE_NAME = "ParkingHistory";
        public static final String COL_NAME_PARKING_HISTORY_ARRAY = "";
    }

}
