package com.sendo.safeter.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sendo.safeter.models.Contact;

import java.util.ArrayList;

public class ContactDB {
    private DBHelper dbHelper;

    public ContactDB(Context ctx) {
        dbHelper = new DBHelper(ctx);
    }

    public void insertContact(Contact contact) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(DBHelper.CT_USER_ID, contact.getUser_id());
        cv.put(DBHelper.CONTACT_NAME, contact.getEmergency_contact_name());
        cv.put(DBHelper.CONTACT_NUMBER, contact.getEmergency_contact_phonenumber());


        db.insert(DBHelper.CONTACT_TABLE, "N/A", cv);

        db.close();
    }

//    public ArrayList<Contact> getContact(int id) {
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//
//        String get_history = "SELECT *" +
//                " FROM " + DBHelper.CONTACT_TABLE + " t " +
//                " WHERE t. " + DBHelper.CT_USER_ID + " = " + id;
//
//        Cursor cursor = db.rawQuery(get_history, null);
//
//        ArrayList<Contact> contacts = new ArrayList<>();
//
//        if (cursor.moveToFirst() == true) {
//            do {
//                int contactid = cursor.getInt(0);
//                int userid = cursor.getInt(1);
//                String name = cursor.getString(2);
//                int number = cursor.getInt(3);
//
//                Contact contact1 = new Contact();
//                contact1.setUser_id(contactid);
//                contact1.setContact_id(userid);
//                contact1.setEmergency_contact_name(name);
//                contact1.setEmergency_contact_phonenumber(number);
//
//                contacts.add(contact1);
//
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        return contacts;
//    }

    public Contact getContact1(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = "id=?";
        String[] selectionArgs = {"" + id};

        Cursor cursor = db.query(DBHelper.CONTACT_TABLE, null, selection, selectionArgs, null, null, null);

        Contact contact = null;

        if(cursor.moveToFirst()){
            contact = new Contact();
            contact.setContact_id(cursor.getInt(cursor.getColumnIndex(DBHelper.CONTACT_ID)));
            contact.setUser_id(cursor.getInt(cursor.getColumnIndex(DBHelper.CT_USER_ID)));
            contact.setEmergency_contact_name(cursor.getString(cursor.getColumnIndex(DBHelper.CONTACT_NAME)));
            contact.setEmergency_contact_phonenumber(cursor.getLong(cursor.getColumnIndex(DBHelper.CONTACT_NUMBER)));
        }

        cursor.close();
        db.close();
        return contact;
    }

    public int countTableSize(){
        String count = "SELECT * FROM " + DBHelper.CONTACT_TABLE;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(count, null);
        int count_size = cursor.getCount();

        cursor.close();
        db.close();
        return count_size;
    }

}
