package com.sendo.safeter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SubscriptionPage extends AppCompatActivity {
    LinearLayout weekly;
    LinearLayout monthly;
    LinearLayout yearly;
    TextView judul;
    TextView price;
    TextView startdate;
    TextView enddate;
    Button call;
    Button profile;
    int user_id, useridprofile, useridhome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription_page);

        weekly = findViewById(R.id.btn_weekly);
        monthly = findViewById(R.id.btn_monthly);
        yearly = findViewById(R.id.btn_yearly);

        Intent intent = getIntent();
        useridprofile = intent.getIntExtra("PROFILETOSUBSCRIPT", 0);
        useridhome = intent.getIntExtra("HOMETOSUBSCRIP", 0);

        user_id = 0;
        if(useridprofile > user_id){
            user_id = useridprofile;
        }
        if(useridhome > user_id){
            user_id = useridhome;
        }
    }

    public void subscription(View view) {
    }

    public void call(View view) {
        Intent intent = new Intent(this, HomePage.class);
        intent.putExtra("USERIDFROMSUBSCRIPT", user_id);
        startActivity(intent);
        finish();
    }

    public void profile(View view) {
        Intent intent = new Intent(this, ProfilePage.class);
        intent.putExtra("SUBSCRIPTOPROFILE", user_id);
        startActivity(intent);
        finish();
    }

    public void weekly(View view) {
        try {
            showdialogweekly();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this, HomePage.class);
        intent.putExtra("USERIDFROMSUBSCRIPT", user_id);
        startActivity(intent);
        finish();
    }

    public void monthly(View view) {
        try {
            showdialogmonthly();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this, HomePage.class);
        intent.putExtra("USERIDFROMSUBSCRIPT", user_id);
        startActivity(intent);
        finish();
    }

    public void yearly(View view) {
        try {
            showdialogyearly();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //kurangi nominal user
        //pas mau insert subscription, cek dulu database udh ada data belum
        //kalau belum tgl lsng insert aja
        //kalau sudah, ambil dlu data databasenya masukkin k arraylist lalu
        //ambil expired date terakhir dari arraylistnya (arraylist.size() -  1)
        //baru deh expired date terakhir itu lu tmbhin sesuai dia beli weekly,monthly, atau yearly
        //baru diinsert ke databasenya
        Intent intent = new Intent(this, HomePage.class);
        intent.putExtra("USERIDFROMSUBSCRIPT", user_id);
        startActivity(intent);
        finish();
    }

    private void showdialogweekly() throws ParseException {
        Dialog dialog = new Dialog(SubscriptionPage.this);
        dialog.setContentView(R.layout.diaglog_box);

        LayoutInflater layoutInflater = this.getLayoutInflater();

        judul = dialog.findViewById(R.id.db_title);
        price = dialog.findViewById(R.id.db_price2);
        startdate = dialog.findViewById(R.id.db_stdt2);
        enddate = dialog.findViewById(R.id.db_edt2);
        Button buysubscribe = dialog.findViewById(R.id.btn_buysubscribe);

        judul.setText("Weekly Subscription");
        price.setText("Rp15.000");

        String startdate_temp = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(new Date());
        startdate.setText(startdate_temp);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateFormat.parse(startdate_temp));

        calendar.add(Calendar.DATE, 7);
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date result = new Date(calendar.getTimeInMillis());
        String enddate_temp = dateFormat.format(result);
        enddate.setText(enddate_temp);


        buysubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    private void showdialogmonthly() throws ParseException {
        Dialog dialog = new Dialog(SubscriptionPage.this);
        dialog.setContentView(R.layout.diaglog_box);

        LayoutInflater layoutInflater = this.getLayoutInflater();

        judul = dialog.findViewById(R.id.db_title);
        price = dialog.findViewById(R.id.db_price2);
        startdate = dialog.findViewById(R.id.db_stdt2);
        enddate = dialog.findViewById(R.id.db_edt2);
        Button buysubscribe = dialog.findViewById(R.id.btn_buysubscribe);

        judul.setText("Monthly Subscription");
        price.setText("Rp50.000");

        String startdate_temp = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(new Date());
        startdate.setText(startdate_temp);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateFormat.parse(startdate_temp));

        calendar.add(Calendar.MONTH, 1);
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date result = new Date(calendar.getTimeInMillis());
        String enddate_temp = dateFormat.format(result);
        enddate.setText(enddate_temp);


        buysubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    private void showdialogyearly() throws ParseException {
        Dialog dialog = new Dialog(SubscriptionPage.this);
        dialog.setContentView(R.layout.diaglog_box);

        LayoutInflater layoutInflater = this.getLayoutInflater();

        judul = dialog.findViewById(R.id.db_title);
        price = dialog.findViewById(R.id.db_price2);
        startdate = dialog.findViewById(R.id.db_stdt2);
        enddate = dialog.findViewById(R.id.db_edt2);
        Button buysubscribe = dialog.findViewById(R.id.btn_buysubscribe);

        judul.setText("Yearly Subscription");
        price.setText("Rp550.000");

        String startdate_temp = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(new Date());
        startdate.setText(startdate_temp);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateFormat.parse(startdate_temp));

        calendar.add(Calendar.YEAR, 1);
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date result = new Date(calendar.getTimeInMillis());
        String enddate_temp = dateFormat.format(result);
        enddate.setText(enddate_temp);


        buysubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

}