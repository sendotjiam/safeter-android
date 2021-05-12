package com.sendo.safeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;

public class ProfilePage extends AppCompatActivity {

    TextView balance;
    Button btn_topup;
    TextView prof_name, prof_address, prof_email, prof_number;
    Spinner spin_emergency;
    TextView cont_name, cont_number;
    ImageButton add_contact;
    ArrayList<EmergencyContact> listcontact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        getSupportActionBar().hide();

        balance = findViewById(R.id.balance);
        btn_topup = findViewById(R.id.btn_topup);
        prof_name = findViewById(R.id.name);
        prof_address = findViewById(R.id.prof_address);
        prof_email = findViewById(R.id.prof_email);
        prof_number = findViewById(R.id.prof_number);
        spin_emergency = findViewById(R.id.spin_emergency);
        cont_name = findViewById(R.id.cont_name);
        cont_number = findViewById(R.id.cont_number);
        add_contact = findViewById(R.id.add_contact);

        listcontact = new ArrayList<>();
        EmergencyContact contact = new EmergencyContact("Daddy", "081261234567");
        listcontact.add(contact);
        EmergencyContact contact1 = new EmergencyContact("Mommy", "081261234567");
        listcontact.add(contact1);

        ArrayAdapter<EmergencyContact> adapter = new ArrayAdapter<EmergencyContact>(this,
                R.layout.spinner, listcontact);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_emergency.setAdapter(adapter);

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

    public void topup(View view) {
        Intent intent = new Intent(this, TopUpPage.class);
        startActivity(intent);
    }

    public void addcontact(View view) {

    }

}