package com.example.finalappandroid;

public final class DatabaseContact {

    public DatabaseContact(){};

    public static final String DB_NAME = "finalApp.db";


    public static abstract class DriverTable{
        public static final String TABLE_NAME = "DriverDetails";
        public static final String COL_NAME_DRIVER_NAME = "driverName";
        public static final String COL_NAME_DRIVER_CAR_TYPE= "driverCarType";
        public static final String COL_NAME_DRIVER_CAR_NUMBER= "driverCarNumber";
    }

    public static abstract class ParkingHistoryTable{
        public static final String TABLE_NAME = "ParkingHistory";

    }

}
