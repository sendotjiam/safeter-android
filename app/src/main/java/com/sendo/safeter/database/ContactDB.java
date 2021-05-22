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

    public ArrayList<Contact> getContact(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String get_contact = "SELECT *" +
                " FROM " + DBHelper.CONTACT_TABLE + " t " +
                " WHERE t. " + DBHelper.CT_USER_ID + " = " + id;

        Cursor cursor = db.rawQuery(get_contact, null);

        ArrayList<Contact> contacts = new ArrayList<>();

        if (cursor.moveToFirst() == true) {
            do {
                int contactid = cursor.getInt(0);
                int userid = cursor.getInt(1);
                String name = cursor.getString(2);
                String number = cursor.getString(3);

                Contact contact1 = new Contact();
                contact1.setUser_id(contactid);
                contact1.setContact_id(userid);
                contact1.setEmergency_contact_name(name);
                contact1.setEmergency_contact_phonenumber(number);

                contacts.add(contact1);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return contacts;
    }

}
