package com.sendo.safeter.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.sendo.safeter.R;
import com.sendo.safeter.database.UserDB;
import com.sendo.safeter.models.User;

public class RegisterActivity extends AppCompatActivity {

    UserDB userDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initialize();
        userDB = new UserDB(this);
    }

    private void initialize() {
    }

    private void storeUser() {}

    private void validation() {}

    public void onGenderRadioClicked(View view) {}
}