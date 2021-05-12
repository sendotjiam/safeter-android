package com.sendo.safeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ProfilePage extends AppCompatActivity {

    Spinner spin_emergency;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        getSupportActionBar().hide();

        spin_emergency = findViewById(R.id.spin_emergency);

    }

    public void subscription(View view) {
        Intent intent = new Intent(this, SubscriptionPage.class);
        startActivity(intent);
    }

    public void call(View view) {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }

    public void profile(View view) {
    }
}