package com.sendo.safeter.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Safeter.db";
    private static final int DB_VERSION = 1;

    public static final String USER_TABLE = "Users";
    public static final String USER_ID = "id";
    public static final String USER_NAME = "name";
    public static final String USER_PASSWORD = "password";
    public static final String USER_GENDER = "gender";
    public static final String USER_EMAIL = "email";
    public static final String USER_PHONE_NUMBER = "phone_number";
    public static final String USER_ADDRESS = "address";
    public static final String USER_BALANCE = "balance";

    private static final String CREATE_USER_TABLE =
            "CREATE TABLE IF NOT EXISTS " + USER_TABLE + " (" +
            USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            USER_NAME + " TEXT, " +
            USER_PASSWORD + " TEXT, " +
            USER_GENDER + " TEXT, " +
            USER_EMAIL + " TEXT, " +
            USER_PHONE_NUMBER + " TEXT, " +
            USER_ADDRESS + " TEXT, " +
            USER_BALANCE + " INTEGER)";

    private static final String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + USER_TABLE;

    public static final String CONTACT_TABLE = "Contacts";
    public static final String CONTACT_ID = "contact_id";
    public static final String CT_USER_ID = "ct_user_id";
    public static final String CONTACT_NAME = "ct_name";
    public static final String CONTACT_NUMBER = "ct_number";

    private static final String CREATE_CONTACT_TABLE =
            "CREATE TABLE IF NOT EXISTS " + CONTACT_TABLE + " (" +
                    CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CT_USER_ID + " INTEGER NOT NULL REFERENCES " + CONTACT_TABLE + "(" + USER_ID + ") ON UPDATE CASCADE," +
                    CONTACT_NAME + " TEXT, " +
                    CONTACT_NUMBER + " INTEGER)";


    private static final String DROP_CONTACT_TABLE = "DROP TABLE IF EXISTS " + CONTACT_TABLE;

    public static final String SUBCRIPTION_TABLE = "Subscriptions";
    public static final String SUBSCRIPTION_ID = "subscription_id";
    public static final String ST_USER_ID = "st_user_id";
    public static final String SUBSCRIPTION_CREATED_DATE = "ct_created_date";
    public static final String SUBSCRIPTION_EXPIRED_DATE = "ct_expired_date";

    private static final String CREATE_SUBSCRIPTION_TABLE =
            "CREATE TABLE IF NOT EXISTS " + SUBCRIPTION_TABLE + " (" +
                    SUBSCRIPTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ST_USER_ID + " INTEGER NOT NULL REFERENCES " + CONTACT_TABLE + "(" + USER_ID + ") ON UPDATE CASCADE," +
                    SUBSCRIPTION_CREATED_DATE + " TEXT, " +
                    SUBSCRIPTION_EXPIRED_DATE + " TEXT)";


    private static final String DROP_SUBSCRIPTION_TABLE = "DROP TABLE IF EXISTS " + SUBCRIPTION_TABLE;

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
        sqLiteDatabase.execSQL(CREATE_CONTACT_TABLE);
        sqLiteDatabase.execSQL(CREATE_SUBSCRIPTION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_USER_TABLE);
        sqLiteDatabase.execSQL(DROP_CONTACT_TABLE);
        sqLiteDatabase.execSQL(DROP_SUBSCRIPTION_TABLE);
        onCreate(sqLiteDatabase);
    }
}
