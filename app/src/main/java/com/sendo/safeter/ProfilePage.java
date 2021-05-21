package com.sendo.safeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sendo.safeter.database.ContactDB;
import com.sendo.safeter.database.UserDB;
import com.sendo.safeter.models.Contact;
import com.sendo.safeter.models.User;

import java.sql.Array;
import java.util.ArrayList;

public class ProfilePage extends AppCompatActivity {

    TextView balance;
    Button btn_topup;
    TextView prof_name, prof_address, prof_email, prof_number;
    Spinner spin_emergency;
    EditText cont_name, cont_number;
    ImageButton add_contact;
    ArrayList<Contact> contacts = new ArrayList<>();
    int user_id, useridprofile, useridsubscription, useridlogin;;
    int tampung;
    UserDB userDB;
    ContactDB contactDB;
    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        balance = findViewById(R.id.balance);
        btn_topup = findViewById(R.id.btn_topup);
        prof_name = findViewById(R.id.prof_name);
        prof_address = findViewById(R.id.prof_address);
        prof_email = findViewById(R.id.prof_email);
        prof_number = findViewById(R.id.prof_number);
        spin_emergency = findViewById(R.id.spin_emergency);
        cont_name = findViewById(R.id.cont_name);
        cont_number = findViewById(R.id.cont_number);
        add_contact = findViewById(R.id.add_contact);

        userDB = new UserDB(this);
        contactDB = new ContactDB(this);

        Intent intent = getIntent();
        useridprofile = intent.getIntExtra("HOMETOPROFILE", 0);
        useridsubscription = intent.getIntExtra("SUBSCRIPTOPROFILE", 0);

        user_id = 0;
        if(useridprofile > user_id){
            user_id = useridprofile;
        }
        if(useridsubscription > user_id){
            user_id = useridsubscription;
        }

        contacts = contactDB.getContact(user_id);


        ArrayAdapter<Contact> adapter = new ArrayAdapter<Contact>(this,
                R.layout.spinner, contacts);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_emergency.setAdapter(adapter);

        user = userDB.getUser(user_id);

        prof_name.setText(user.getName());
        prof_address.setText(user.getAddress());
        prof_email.setText(user.getEmail());
        prof_number.setText(user.getPhoneNumber());
        balance.setText("Rp." + user.getBalance() + ",00");

    }

    @Override
    protected void onResume() {
        super.onResume();
        user = userDB.getUser(user_id);

        prof_name.setText(user.getName());
        prof_address.setText(user.getAddress());
        prof_email.setText(user.getEmail());
        prof_number.setText(user.getPhoneNumber());
        balance.setText("Rp." + user.getBalance() + ",00");
    }

    public void subscription(View view) {
        Intent intent = new Intent(this, SubscriptionPage.class);
        intent.putExtra("PROFILETOSUBSCRIPT", user_id);
        startActivity(intent);
        finish();
    }

    public void call(View view) {
        Intent intent = new Intent(this, HomePage.class);
        intent.putExtra("USERIDFROMPROFILE", user_id);
        startActivity(intent);
        finish();
    }

    public void profile(View view) {

    }

    public void topup(View view) {
        Intent intent = new Intent(this, TopUpPage.class);
        intent.putExtra("USER_ID_TOPUP", user_id);
        startActivity(intent);
    }

    public void addcontact(View view) {
        Contact contact = new Contact();
        contact.setUser_id(user_id);
        contact.setEmergency_contact_name(cont_name.getText().toString());
        contact.setEmergency_contact_phonenumber(cont_number.getText().toString());

        contactDB.insertContact(contact);
    }

}