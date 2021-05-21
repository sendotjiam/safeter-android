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
    ArrayList<EmergencyContact> listcontact;
    ArrayList<Contact> contacts = new ArrayList<>();
    int userid;
    int tampung;
    UserDB userDB;
    ContactDB contactDB;
    User user = new User();
    Contact contact = new Contact();

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

        listcontact = new ArrayList<>();
        EmergencyContact contact = new EmergencyContact("Daddy", "081261234567");
        listcontact.add(contact);
        EmergencyContact contact1 = new EmergencyContact("Mommy", "081261234567");
        listcontact.add(contact1);

        tampung = contactDB.countTableSize();
        Toast.makeText(this, tampung + "", Toast.LENGTH_SHORT).show();
        contact = contactDB.getContact1(1);

//        if(tampung != 0){
//            for(int i = 1; i <= tampung; i++){
//                contact = contactDB.getContact1(i);
//                if(contact.getUser_id() == userid){
//                    EmergencyContact contact2 = new EmergencyContact(contact.getEmergency_contact_name(), String.valueOf(contact.getEmergency_contact_phonenumber()));
//                    listcontact.add(contact2);
//                }
//            }
//        }


//        Toast.makeText(this, contacts.get(0).getEmergency_contact_name(), Toast.LENGTH_SHORT).show();
//        if(contacts != null){
//            for(int i = 0; i <= contacts.size(); i++){
////                Toast.makeText(this, contacts.get(i).getEmergency_contact_name(), Toast.LENGTH_SHORT).show();
//                EmergencyContact contact2 = new EmergencyContact(contacts.get(i).getEmergency_contact_name(), String.valueOf(contacts.get(i).getEmergency_contact_phonenumber()));
//                listcontact.add(contact2);
//            }
//        }

        ArrayAdapter<EmergencyContact> adapter = new ArrayAdapter<EmergencyContact>(this,
                R.layout.spinner, listcontact);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_emergency.setAdapter(adapter);

        Intent intent = getIntent();
        userid = intent.getIntExtra("USER_ID_PROFILE", 0);

        user = userDB.getUser(userid);

        prof_name.setText(user.getName());
        prof_address.setText(user.getAddress());
        prof_email.setText(user.getEmail());
        prof_number.setText(user.getPhoneNumber());
        balance.setText("Rp." + user.getBalance() + ",00");

    }

    @Override
    protected void onResume() {
        super.onResume();
        user = userDB.getUser(userid);

        prof_name.setText(user.getName());
        prof_address.setText(user.getAddress());
        prof_email.setText(user.getEmail());
        prof_number.setText(user.getPhoneNumber());
        balance.setText("Rp." + user.getBalance() + ",00");
    }

    public void subscription(View view) {
        Intent intent = new Intent(this, SubscriptionPage.class);
        intent.putExtra("USER_ID_PROFILE", userid);
        startActivity(intent);
        finish();
    }

    public void call(View view) {
        Intent intent = new Intent(this, HomePage.class);
        intent.putExtra("USER_ID_PROFILE", userid);
        startActivity(intent);
        finish();
    }

    public void profile(View view) {

    }

    public void topup(View view) {
        Intent intent = new Intent(this, TopUpPage.class);
        intent.putExtra("USER_ID_TOPUP", userid);
        startActivity(intent);
    }

    public void addcontact(View view) {
        Contact contact = new Contact();
        contact.setUser_id(userid);
        contact.setEmergency_contact_name(cont_name.getText().toString());
        contact.setEmergency_contact_phonenumber(Long.parseLong(cont_number.getText().toString()));

        contactDB.insertContact(contact);
    }

}