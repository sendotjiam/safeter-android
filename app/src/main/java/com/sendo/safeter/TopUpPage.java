package com.sendo.safeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sendo.safeter.database.UserDB;
import com.sendo.safeter.models.User;

public class TopUpPage extends AppCompatActivity {
    TextView balance;
    RadioGroup amountgroup;
    RadioButton amount;
    RadioGroup paymentgroup;
    RadioButton payment;
    EditText password;
    Button topup;
    ImageButton back;
    int userid;
    UserDB userDB;
    User user = new User();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up_page);
        balance = findViewById(R.id.balanceuser);
        amountgroup = findViewById(R.id.rg_amount);
        paymentgroup = findViewById(R.id.rg_method);
        password = findViewById(R.id.et_password);
        topup = findViewById(R.id.btn_topup);
        back = findViewById(R.id.btn_back);

        userDB = new UserDB(this);


        Intent intent = getIntent();
        userid = intent.getIntExtra("USER_ID_TOPUP", 0);

        user = userDB.getUser(userid);
        balance.setText("Rp." + user.getBalance() + ",00");


    }

    public void btn_amount(View view) {
        int amountradiogroup = amountgroup.getCheckedRadioButtonId();
        amount = findViewById(amountradiogroup);
    }

    public void btn_method(View view) {
        int methodradiogroup = paymentgroup.getCheckedRadioButtonId();
        payment = findViewById(methodradiogroup);
    }

    public void topup(View view) {
        int check = 0;
        String passwordtopup = password.getText().toString();
        if(amount == null || amount.equals("")){
            Toast.makeText(this,"Top Up Nominal must be selected!", Toast.LENGTH_SHORT).show();
        }
        else{
            check++;
        }
        if(payment == null || payment.equals("")){
            Toast.makeText(this,"Payment method must be selected!", Toast.LENGTH_SHORT).show();
        }
        else{
            check++;
        }
        if(!passwordtopup.equals(user.getPassword())){
            Toast.makeText(this,"Input the right password!",Toast.LENGTH_SHORT).show();
        }
        else{
            check++;
        }
        if(check == 3) {
            Toast.makeText(this, "Top Up Successfully", Toast.LENGTH_SHORT).show();
            if (amount.getText().toString().equals("Rp 50.000")) {
                userDB.updateNominal(user, userid, 50000);
            } else if (amount.getText().toString().equals("Rp 100.000")) {
                userDB.updateNominal(user, userid, 100000);
            } else if (amount.getText().toString().equals("Rp 150.000")){
                userDB.updateNominal(user, userid, 150000);
            } else{
                userDB.updateNominal(user, userid, 200000);
            }
            finish();
        }
    }


    public void back(View view) {
        finish();
    }
}