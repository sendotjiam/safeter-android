package com.sendo.safeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HomePage extends AppCompatActivity {

    ImageButton btn_oneclick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        getSupportActionBar().hide();

        btn_oneclick = findViewById(R.id.btn_oneclick);

        btn_oneclick.setOnClickListener(view -> {
            Intent intent = new Intent(this, SubscriptionPage.class);
            startActivity(intent);
        });


    }

    public void subscription(View view) {
        Intent intent = new Intent(this, SubscriptionPage.class);
        startActivity(intent);
    }

    public void call(View view) {
    }

    public void profile(View view) {
        Intent intent = new Intent(this, ProfilePage.class);
        startActivity(intent);
    }
}