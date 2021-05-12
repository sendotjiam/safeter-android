package com.sendo.safeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {

    TextView name;
    ImageButton btn_oneclick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        getSupportActionBar().hide();

        name = findViewById(R.id.name);
        btn_oneclick = findViewById(R.id.btn_oneclick);

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

    public void oneclick(View view) {

    }

}