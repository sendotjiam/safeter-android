package com.sendo.safeter.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sendo.safeter.models.User;

public class UserDB {

    private final DBHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    public UserDB(Context ctx) {
        dbHelper = new DBHelper(ctx);
    }

    public void storeUser(User user) {
        sqLiteDatabase = dbHelper.getWritableDatabase();

        ContentValues userCV = new ContentValues();
        userCV.put(DBHelper.USER_NAME, user.getName());
        userCV.put(DBHelper.USER_PASSWORD, user.getPassword());
        userCV.put(DBHelper.USER_GENDER, user.getGender());
        userCV.put(DBHelper.USER_EMAIL, user.getEmail());
        userCV.put(DBHelper.USER_PHONE_NUMBER, user.getPhoneNumber());
        userCV.put(DBHelper.USER_ADDRESS, user.getAddress());
        userCV.put(DBHelper.USER_BALANCE, user.getBalance());

        sqLiteDatabase.insert(DBHelper.USER_TABLE, null, userCV);

        sqLiteDatabase.close();
    }

    public User getUser(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = "id=?";
        String[] selectionArgs = {"" + id};

        Cursor cursor = db.query(DBHelper.USER_TABLE, null, selection, selectionArgs, null, null, null);

        User user = null;

        if(cursor.moveToFirst()){
            user = new User();
            user.setId(cursor.getLong(cursor.getColumnIndex(DBHelper.USER_ID)));
            user.setName(cursor.getString(cursor.getColumnIndex(DBHelper.USER_NAME)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(DBHelper.USER_PASSWORD)));
            user.setGender(cursor.getString(cursor.getColumnIndex(DBHelper.USER_GENDER)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(DBHelper.USER_EMAIL)));
            user.setPhoneNumber(cursor.getString(cursor.getColumnIndex(DBHelper.USER_PHONE_NUMBER)));
            user.setAddress(cursor.getString(cursor.getColumnIndex(DBHelper.USER_ADDRESS)));
            user.setBalance(cursor.getInt(cursor.getColumnIndex(DBHelper.USER_BALANCE)));
        }

        cursor.close();
        db.close();
        return user;
    }

    public int countTableSize(){
        String count = "SELECT * FROM " + DBHelper.USER_TABLE;
        sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(count, null);
        int count_size = cursor.getCount();

        cursor.close();
        sqLiteDatabase.close();
        return count_size;
    }

    public void updateNominal(User user, int id, int nominal){
        sqLiteDatabase = dbHelper.getWritableDatabase();

        String whereClause = "id=?";
        String[] whereClauseArgs = {"" + id};

        ContentValues cv = new ContentValues();
        int temp = user.getBalance() + nominal;
        cv.put(DBHelper.USER_BALANCE, temp);

        sqLiteDatabase.update(DBHelper.USER_TABLE, cv, whereClause, whereClauseArgs);

        sqLiteDatabase.close();
    }

    public void minusNominal(User user, int id, int nominal){
        sqLiteDatabase = dbHelper.getWritableDatabase();

        String whereClause = "id=?";
        String[] whereClauseArgs = {"" + id};

        ContentValues cv = new ContentValues();
        int temp = user.getBalance() - nominal;
        cv.put(DBHelper.USER_BALANCE, temp);

        sqLiteDatabase.update(DBHelper.USER_TABLE, cv, whereClause, whereClauseArgs);

        sqLiteDatabase.close();
    }

}
