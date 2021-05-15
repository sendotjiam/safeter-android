package com.sendo.safeter.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "SafeterDB";
    private static final int DB_VERSION = 1;

    public static final String USER_TABLE = "Users";
    public static final String USER_ID = "id";
    public static final String USER_NAME = "name";
    public static final String USER_PASSWORD = "password";
    public static final String USER_GENDER = "gender";
    public static final String USER_EMAIL = "email";
    public static final String USER_PHONE_NUMBER = "phone_number";
    public static final String USER_ADDRESS = "address";

    private static final String CREATE_USER_TABLE =
            "CREATE TABLE IF NOT EXISTS " + USER_TABLE + " (" +
            USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            USER_NAME + " TEXT, " +
            USER_PASSWORD + " TEXT, " +
            USER_GENDER + " TEXT, " +
            USER_EMAIL + " TEXT, " +
            USER_PHONE_NUMBER + " TEXT, " +
            USER_ADDRESS + " TEXT)";

    private static final String DROP_USER_TABLE = "DROP TABLE IF EXISTS" + USER_TABLE;

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_USER_TABLE);
        onCreate(sqLiteDatabase);
    }
}
