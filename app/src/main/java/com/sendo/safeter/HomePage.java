package com.sendo.safeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sendo.safeter.database.SubscriptionDB;
import com.sendo.safeter.database.UserDB;
import com.sendo.safeter.models.Subscription;
import com.sendo.safeter.models.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class HomePage extends AppCompatActivity {

    TextView name, expired;
    ImageButton btn_oneclick;
    UserDB userDB;
    User user = new User();
    int user_id, useridprofile, useridsubscription, useridlogin;
    SubscriptionDB subscriptionDB = new SubscriptionDB(this);
    ArrayList<Subscription> arraySub = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        userDB = new UserDB(this);

        name = findViewById(R.id.name);
        btn_oneclick = findViewById(R.id.btn_oneclick);
        expired = findViewById(R.id.expired);

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

    public void call(View view) {

    }

    public void profile(View view) {
        Intent intent = new Intent(this, ProfilePage.class);
        intent.putExtra("HOMETOPROFILE", user_id);
        startActivity(intent);
        finish();
    }

    public void oneclick(View view) {

    }

}