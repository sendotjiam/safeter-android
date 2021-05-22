package com.sendo.safeter.models;

public class Subscription {
    int subscription_id;
    int user_id;
    String type;
    String created_date;
    String expired_date;

    public Subscription(int subscription_id, int user_id, String type, String created_date, String expired_date) {
        this.subscription_id = subscription_id;
        this.user_id = user_id;
        this.type = type;
        this.created_date = created_date;
        this.expired_date = expired_date;
    }

    public Subscription(){

    }

    public int getSubscription_id() {
        return subscription_id;
    }

    public void setSubscription_id(int subscription_id) {
        this.subscription_id = subscription_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getExpired_date() {
        return expired_date;
    }

    public void setExpired_date(String expired_date) {
        this.expired_date = expired_date;
    }
}
