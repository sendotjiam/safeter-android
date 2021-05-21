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

    public long storeUser(User user) {
        sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues userCV = new ContentValues();
        userCV.put(DBHelper.USER_NAME, user.getName());
        userCV.put(DBHelper.USER_PASSWORD, user.getPassword());
        userCV.put(DBHelper.USER_GENDER, user.getGender());
        userCV.put(DBHelper.USER_EMAIL, user.getEmail());
        userCV.put(DBHelper.USER_PHONE_NUMBER, user.getPhoneNumber());
        userCV.put(DBHelper.USER_ADDRESS, user.getAddress());
        long checkIfSuccess = sqLiteDatabase.insert(DBHelper.USER_TABLE, null, userCV);
        sqLiteDatabase.close();
        return checkIfSuccess;
    }

    public long updateUser(int id, User user) {
        long checkIfSuccess = -1;
        sqLiteDatabase = dbHelper.getWritableDatabase();
        String selectionId = "id=?";
        String[] selectionIdArgs = {"" + id};
        ContentValues userCV = new ContentValues();
        userCV.put(DBHelper.USER_NAME, user.getName());
        userCV.put(DBHelper.USER_PASSWORD, user.getPassword());
        userCV.put(DBHelper.USER_GENDER, user.getGender());
        userCV.put(DBHelper.USER_EMAIL, user.getEmail());
        userCV.put(DBHelper.USER_PHONE_NUMBER, user.getPhoneNumber());
        userCV.put(DBHelper.USER_ADDRESS, user.getAddress());
        Cursor userCursor = sqLiteDatabase.rawQuery(
                "SELECT * FROM " +
                        DBHelper.USER_TABLE + " WHERE " + selectionId, selectionIdArgs
        );
        if (userCursor.getCount() > 0) {
            checkIfSuccess = sqLiteDatabase.update(DBHelper.USER_TABLE, userCV, selectionId, selectionIdArgs);
        }
        userCursor.close();
        sqLiteDatabase.close();
        return checkIfSuccess;
    }

    public User getUserById(int id) {
        sqLiteDatabase = dbHelper.getReadableDatabase();
        String selectionId = "id=?";
        String[] selectionIdArgs = {"" + id};

        Cursor userCursor = sqLiteDatabase.query(
                DBHelper.USER_TABLE, null, selectionId, selectionIdArgs,
                null, null, null
        );
        User user = null;
        if (userCursor.moveToFirst()) {
            long userId = userCursor.getInt(userCursor.getColumnIndex(DBHelper.USER_ID));
            String name = userCursor.getString(userCursor.getColumnIndex(DBHelper.USER_NAME));
            String password = userCursor.getString(userCursor.getColumnIndex(DBHelper.USER_PASSWORD));
            String gender = userCursor.getString(userCursor.getColumnIndex(DBHelper.USER_GENDER));
            String email = userCursor.getString(userCursor.getColumnIndex(DBHelper.USER_EMAIL));
            String phoneNumber = userCursor.getString(userCursor.getColumnIndex(DBHelper.USER_PHONE_NUMBER));
            String address = userCursor.getString(userCursor.getColumnIndex(DBHelper.USER_ADDRESS));
            user = new User(userId, name, password, gender, email, phoneNumber, address);
        }
        userCursor.close();
        sqLiteDatabase.close();
        return user;
    }

}
