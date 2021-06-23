package com.sendo.safeter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.sendo.safeter.database.ContactDB;
import com.sendo.safeter.database.SubscriptionDB;
import com.sendo.safeter.database.UserDB;
import com.sendo.safeter.models.Contact;
import com.sendo.safeter.models.Subscription;
import com.sendo.safeter.models.User;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class HomePage extends AppCompatActivity {

    TextView name, expired;
    ImageButton btn_oneclick;
//    Button btn_oneclick;
    UserDB userDB;
    User user = new User();
    int user_id, useridprofile, useridsubscription, useridlogin;
    SubscriptionDB subscriptionDB = new SubscriptionDB(this);
    ArrayList<Subscription> arraySub = new ArrayList<>();

    SmsManager smsManager;
    int sendSmsPermission;
    int locationPermission;
    ContactDB contactDB = new ContactDB(this);
    ArrayList<Contact> emergencyContact = new ArrayList<>();

    FusedLocationProviderClient fusedLocationProviderClient;
    Double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        userDB = new UserDB(this);

        name = findViewById(R.id.name);
        btn_oneclick = findViewById(R.id.btn_oneclick);
        expired = findViewById(R.id.expired);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        smsManager = SmsManager.getDefault();
        requestSmsPermission();

        Intent homeintent = getIntent();
        useridlogin = homeintent.getIntExtra("USER_ID", 0);
        useridprofile = homeintent.getIntExtra("USERIDFROMPROFILE", 0);
        useridsubscription = homeintent.getIntExtra("USERIDFROMSUBSCRIPT", 0);

        user_id = 0;
        if(useridlogin > user_id){
            user_id = useridlogin;
        }
        if(useridprofile > user_id){
            user_id = useridprofile;
        }
        if(useridsubscription > user_id){
            user_id = useridsubscription;
        }

        user = userDB.getUser(user_id);
        name.setText(user.getName());

        Toast.makeText(this, user_id + "", Toast.LENGTH_SHORT).show();

        arraySub = subscriptionDB.getSubscription(user_id);
        int array = subscriptionDB.countTableSize(user_id);
        if(array != 0){
            arraySub = subscriptionDB.getSubscription(user_id);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Date now = new Date();
            Date date1 = null;
            try {
                date1 = simpleDateFormat.parse(arraySub.get(array-1).getExpired_date());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long temp = date1.getTime() - now.getTime() ;
            temp = temp/ 86400000;
            expired.setText("Expired in " + String.valueOf(temp+1) + " days");
        }
    }

    public void subscription(View view) {
        Intent intent = new Intent(this, SubscriptionPage.class);
        intent.putExtra("HOMETOSUBSCRIP", user_id);
        startActivity(intent);
        finish();
    }

    public void profile(View view) {
        Intent intent = new Intent(this, ProfilePage.class);
        intent.putExtra("HOMETOPROFILE", user_id);
        startActivity(intent);
        finish();
    }

    public void oneClickCall(View view) {
        requestLocationPermission();
        if (latitude != null && longitude != null) {
            String message = convertLatLngToString(latitude, longitude);
            emergencyContact = contactDB.getContact(user_id);
            for (Contact contact : emergencyContact) {
                smsManager.sendTextMessage(contact.getEmergency_contact_phonenumber(), null, message, null, null);
            }
        }
    }

    private String convertLatLngToString(Double latitude, Double longitude) {
        String BASE_TEXT = "HELP!!!\n" + "My last location is in coordinates\n";
        String latLngText = "Lat " + latitude + "\nLong" + longitude;
        return BASE_TEXT + latLngText;
    }

    private void requestSmsPermission() {
        sendSmsPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if (sendSmsPermission == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);
        }
    }

    private void requestLocationPermission() {
        locationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (locationPermission == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    if (location != null) {
                        try {
                            Geocoder geocoder = new Geocoder(HomePage.this, Locale.getDefault());
                            List<Address> addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            latitude = addressList.get(0).getLatitude();
                            longitude = addressList.get(0).getLongitude();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2);
        }
    }

}