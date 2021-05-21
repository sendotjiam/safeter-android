package com.sendo.safeter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sendo.safeter.database.UserDB;
import com.sendo.safeter.models.User;

public class HomePage extends AppCompatActivity {

    TextView name;
    ImageButton btn_oneclick;
    UserDB userDB;
    User user = new User();
    int user_id, useridprofile, useridsubscription, useridlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        userDB = new UserDB(this);

        name = findViewById(R.id.name);
        btn_oneclick = findViewById(R.id.btn_oneclick);

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