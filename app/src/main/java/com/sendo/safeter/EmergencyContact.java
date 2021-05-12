package com.sendo.safeter;

public class EmergencyContact {
    private String contact_name;
    private String contact_number;

    public EmergencyContact(String contact_name, String contact_number) {
        this.contact_name = contact_name;
        this.contact_number = contact_number;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    @Override
    public String toString() {
        return contact_name + " - " + contact_number ;
    }
}
