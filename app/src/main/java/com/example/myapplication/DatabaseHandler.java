package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {
        private static final String TAG = "DatabaseHandler";
        //Name of the table(SQL)
        public static final String TABLE_NAME = "data_notes";
        //Names of the three columns in the table
        public static final String KEY_ID = "_id";
        public static final String KEY_TITLE = "title";
        public static final String KEY_DESC = "description";
        //Name of the database
        static final String DATABASE_NAME = "notes_database";
        //Database version
        static final int DATABASE_VERSION = 1;

        private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_TITLE + " TEXT NOT NULL, " + KEY_DESC + " TEXT);";

        public DatabaseHandler(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
               db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
                onCreate(db);
        }
}
