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
import android.widget.RadioButton;
import android.widget.TextView;

import com.sendo.safeter.R;
import com.sendo.safeter.database.UserDB;
import com.sendo.safeter.models.User;

public class RegisterActivity extends AppCompatActivity {

    UserDB userDB;

    EditText etName, etPassword, etConfirmPassword, etEmail, etPhone, etAddress;
    String name, password, confirmPassword, email, phone, address, gender;
    Button btnLogin, btnRegis;

    TextView appName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initialize();
        setAppNameColor();
        userDB = new UserDB(this);

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int check = validation();
                if (check == 1) {
                    storeUser();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initialize() {
        etName = findViewById(R.id.et_regis_name);
        etPassword = findViewById(R.id.et_regis_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        etPhone = findViewById(R.id.et_phone);
        etEmail = findViewById(R.id.et_email);
        etAddress = findViewById(R.id.et_address);
        btnRegis = (Button) findViewById(R.id.btn_regis_sign_up);
        btnLogin = (Button) findViewById(R.id.btn_regis_sign_in);

        appName = findViewById(R.id.app_name);
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

    private void storeUser() {}

    private boolean isAplhanumeric(String password) {
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (!(ch >= '0' && ch <= '9') && !(ch >= 'a' && ch <= 'z') && !(ch >= 'A' && ch <= 'Z')) return false;
        }
        return true;
    }

    private int validation() {
        int check = 1;
        name = etName.getText().toString();
        password = etPassword.getText().toString();
        confirmPassword = etConfirmPassword.getText().toString();
        phone = etPhone.getText().toString();
        email = etEmail.getText().toString();
        address = etAddress.getText().toString();

        if (!name.isEmpty()) {
            if (name.length() < 6 || name.length() > 12) {
                etName.setError("Username must be between 6 and 12 characters");
                name = null;
                check = 0;
            }
        }
        else {
            etName.setError("Username can't be empty");
            check = 0;
        }

        if (!phone.isEmpty()) {
            if (!phone.matches("^(\\d{10}|\\d{11}|\\d{12})$")) {
                phone = null;
                etPhone.setError("Phone number must be between 10 and 12 digits");
                check = 0;
            }
        }
        else {
            etPhone.setError("Phone number can't be empty");
            check = 0;
        }

        if (!password.isEmpty()) {
            if (password.length() < 8 || !isAplhanumeric(password)) {
                password = null;
                etPassword.setError("Password must be more than or equals 8 aplhanumeric characters");
                check = 0;
            }
        }
        else {
            etPassword.setError("Password can't be empty");
            check = 0;
        }

        if (!confirmPassword.isEmpty()) {
            if (!confirmPassword.equals(password)) {
                etConfirmPassword.setError("Password should match each other");
                confirmPassword = null;
                check = 0;
            }
        }
        else {
            etConfirmPassword.setError("Confirm password can't be empty");
            check = 0;
        }

        if (email.isEmpty()) {
            etEmail.setError("Email must be filled");
            check = 0;
        }
        else {
            etEmail.setError(null);
        }

        if (address.isEmpty()) {
            etAddress.setError("Address must be filled");
            check = 0;
        }
        else {
            etAddress.setError(null);
        }

        return check;
    }

    public void onGenderRadioClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radio_male:
                if (checked) {
                    gender = "Male";
                }
                break;
            case R.id.radio_female:
                if (checked) {
                    gender = "Female";
                }
                break;
            default:
                ((RadioButton) view).setError("Gender must be selected");
                break;
        }
    }
}