package com.sendo.safeter.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sendo.safeter.models.Contact;
import com.sendo.safeter.models.Subscription;

import java.util.ArrayList;

public class SubscriptionDB {
    private DBHelper dbHelper;


    public SubscriptionDB(Context ctx) {
        dbHelper = new DBHelper(ctx);
    }

    public void insertSubscription(Subscription subscription) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(DBHelper.ST_USER_ID, subscription.getUser_id());
        cv.put(DBHelper.SUBSCRIPTION_TYPE, subscription.getType());
        cv.put(DBHelper.SUBSCRIPTION_CREATED_DATE, subscription.getCreated_date());
        cv.put(DBHelper.SUBSCRIPTION_EXPIRED_DATE, subscription.getExpired_date());


        db.insert(DBHelper.SUBSCRIPTION_TABLE, "N/A", cv);

        db.close();
        dbHelper.close();
    }

    public ArrayList<Subscription> getSubscription(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String get_subscription = "SELECT *" +
                " FROM " + DBHelper.SUBSCRIPTION_TABLE + " t " +
                " WHERE t. " + DBHelper.ST_USER_ID + " = " + id;

        Cursor cursor = db.rawQuery(get_subscription, null);

        ArrayList<Subscription> subscriptions = new ArrayList<>();

        if (cursor.moveToFirst() == true) {
            do {
                int subscriptionid = cursor.getInt(0);
                int userid = cursor.getInt(1);
                String type = cursor.getString(2);
                String created_date = cursor.getString(3);
                String expired_date = cursor.getString(4);

                Subscription subscription1 = new Subscription();
                subscription1.setSubscription_id(subscriptionid);
                subscription1.setUser_id(userid);
                subscription1.setType(type);
                subscription1.setCreated_date(created_date);
                subscription1.setExpired_date(expired_date);

                subscriptions.add(subscription1);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return subscriptions;
    }

    public int countTableSize(int userid){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String count = "SELECT * FROM Subscriptions WHERE st_user_id=? ";
        Cursor cursor = db.rawQuery(count, new String[]{Integer.toString(userid)});
        int count_size =  cursor.getCount();

        cursor.close();
        db.close();
        return count_size;
    }


}
