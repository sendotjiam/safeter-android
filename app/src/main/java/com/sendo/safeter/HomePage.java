package com.sendo.safeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {

    TextView name;
    ImageButton btn_oneclick;
    int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        name = findViewById(R.id.name);
        btn_oneclick = findViewById(R.id.btn_oneclick);

        Intent homeintent = getIntent();
        user_id = homeintent.getIntExtra("USER_ID", 0);
        Toast.makeText(this, user_id + "", Toast.LENGTH_SHORT).show();

    }

    public void subscription(View view) {
        Intent intent = new Intent(this, SubscriptionPage.class);
        intent.putExtra("USER_ID_PROFILE", user_id);
        startActivity(intent);
        finish();
    }

    public void call(View view) {

    }

    public void profile(View view) {
        Intent intent = new Intent(this, ProfilePage.class);
        intent.putExtra("USER_ID_PROFILE", user_id);
        startActivity(intent);
        finish();
    }

    public void oneclick(View view) {

    }

}