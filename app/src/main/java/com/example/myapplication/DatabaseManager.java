package com.example.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class DatabaseManager {
        private DatabaseHandler dbHandler;
        private Context context;
        private SQLiteDatabase database;

        public DatabaseManager(Context c) {
            context = c;
        }

        public DatabaseManager open() throws SQLException {
            dbHandler = new DatabaseHandler(context);
            database = dbHandler.getWritableDatabase();
            return this;
        }

        public void close() {
            dbHandler.close();
        }

        public void insert(String title, String desc) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseHandler.KEY_TITLE, title);
            contentValues.put(DatabaseHandler.KEY_DESC, desc);
            database.insert(DatabaseHandler.TABLE_NAME, null, contentValues);
        }

        public Cursor fetch() {
            String[] columns = new String[] {DatabaseHandler.KEY_ID, DatabaseHandler.KEY_TITLE, DatabaseHandler.KEY_DESC};
            Cursor cursor = database.query(DatabaseHandler.TABLE_NAME, columns, null, null, null, null, null);
            if(cursor != null) {
                cursor.moveToFirst();
            }
            return cursor;
        }

        public int update(long id, String title, String desc) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseHandler.KEY_TITLE, title);
            contentValues.put(DatabaseHandler.KEY_DESC, desc);

            int i = database.update(DatabaseHandler.TABLE_NAME, contentValues, DatabaseHandler.KEY_ID + " = " + id, null);
            return i;
        }

        public void delete(long id) {
            database.delete(DatabaseHandler.TABLE_NAME, DatabaseHandler.KEY_ID + " = " + id, null);
        }
}
