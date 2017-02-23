package com.example.samirferraz.myapplication.database;

import android.provider.BaseColumns;

public final class AltitudeDatabaseContract {
    private AltitudeDatabaseContract() {
    }

    public static class LocationEntry implements BaseColumns {
        public static final String TABLE_NAME = "location";
        public static final String COLUMN_NAME_LATITUDE = "latitude";
        public static final String COLUMN_NAME_LONGITUDE = "longitude";
        public static final String COLUMN_NAME_ALTITUDE = "altitude";
    }

    private static final String TYPE = " FLOAT";
    private static final String COMMA_SEP = ",";
    protected static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + LocationEntry.TABLE_NAME + " (" +
                    LocationEntry._ID + " INTEGER PRIMARY KEY," +
                    LocationEntry.COLUMN_NAME_LATITUDE + TYPE + COMMA_SEP +
                    LocationEntry.COLUMN_NAME_LONGITUDE + TYPE + COMMA_SEP +
                    LocationEntry.COLUMN_NAME_ALTITUDE + TYPE + " )";

    protected static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + LocationEntry.TABLE_NAME;
}