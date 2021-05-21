package com.sendo.safeter.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sendo.safeter.HomePage;
import com.sendo.safeter.R;
import com.sendo.safeter.database.UserDB;
import com.sendo.safeter.models.User;

public class LoginActivity extends AppCompatActivity {

    UserDB userDB;
    EditText etEmail, etPassword;
    String email, password;
    Button btnLogin, btnRegis;

    TextView appName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();
        setAppNameColor();
        userDB = new UserDB(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int check = validation();

                if (check == 1) {
                    int nopass = 0;
                    int count = userDB.countTableSize();
                    if(count == 0){
                        Toast.makeText(getApplicationContext(), "You must register first!", Toast.LENGTH_SHORT).show();
                    }
                    else if(count != 0){
                        for(int i = 1; i <= count; i++) {
                            User user = userDB.getUser(i);
                            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                                Toast.makeText(getApplicationContext(), "Login Successfully!", Toast.LENGTH_SHORT).show();
                                int tampung_user_id = i;
                                Intent homeintent = new Intent(getApplicationContext(), HomePage.class);
                                homeintent.putExtra("USER_ID", tampung_user_id);
                                startActivity(homeintent);
                                finish();
                            }
                            else{
                                nopass++;
                            }
                        }
                    }
                    if(nopass == count){
                        Toast.makeText(getApplicationContext(), "You must register first!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setAppNameColor() {
        String safeterName = "Safeter";
        SpannableString appNameSpannableString = new SpannableString(safeterName);
        ForegroundColorSpan colorWhite = new ForegroundColorSpan(Color.WHITE);
        ForegroundColorSpan colorRed = new ForegroundColorSpan(Color.RED);

        appNameSpannableString.setSpan(colorWhite, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        appNameSpannableString.setSpan(colorRed, 4, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        appName.setText(appNameSpannableString);
    }

    private void initialize() {
        etEmail = findViewById(R.id.et_login_email);
        etPassword = findViewById(R.id.et_login_password);
        btnLogin = findViewById(R.id.btn_login_sign_in);
        btnRegis = findViewById(R.id.btn_login_sign_up);

        appName = findViewById(R.id.app_name);
    }

    private int validation() {
        int check = 1;
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();

        if (email.isEmpty()) {
            etEmail.setError("Email must be filled");
            check = 0;
        }
        else {
            etEmail.setError(null);
        }

        if (password.isEmpty()) {
            etPassword.setError("Password shouldn't be empty");
            check = 0;
        }
        else {
            etPassword.setError(null);
        }

        return check;
    }
}