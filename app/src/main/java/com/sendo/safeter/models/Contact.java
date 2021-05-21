package com.sendo.safeter.models;

public class Contact {
    int contact_id;
    int user_id;
    String emergency_contact_name;
    long emergency_contact_phonenumber;

    public Contact(int contact_id, int user_id, String emergency_contact_name, long emergency_contact_phonenumber) {
        this.contact_id = contact_id;
        this.user_id = user_id;
        this.emergency_contact_name = emergency_contact_name;
        this.emergency_contact_phonenumber = emergency_contact_phonenumber;
    }

    public Contact(){

    }

    public int getContact_id() {
        return contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getEmergency_contact_name() {
        return emergency_contact_name;
    }

    public void setEmergency_contact_name(String emergency_contact_name) {
        this.emergency_contact_name = emergency_contact_name;
    }

    public long getEmergency_contact_phonenumber() {
        return emergency_contact_phonenumber;
    }

    public void setEmergency_contact_phonenumber(long emergency_contact_phonenumber) {
        this.emergency_contact_phonenumber = emergency_contact_phonenumber;
    }
}
