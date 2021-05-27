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

import com.sendo.safeter.database.SubscriptionDB;
import com.sendo.safeter.database.UserDB;
import com.sendo.safeter.models.Subscription;
import com.sendo.safeter.models.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    SubscriptionDB subscriptionDB = new SubscriptionDB(this);
    UserDB userDB = new UserDB(this);
    int balance;
    ArrayList<Subscription> arraySub = new ArrayList<>();
    User user = new User();

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
        if (useridprofile > user_id) {
            user_id = useridprofile;
        }
        if (useridhome > user_id) {
            user_id = useridhome;
        }
        user = userDB.getUser(user_id);
        balance = user.getBalance();
        int test = subscriptionDB.countTableSize(user_id);
        Toast.makeText(this, test + "", Toast.LENGTH_SHORT).show();

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
    }

    public void monthly(View view) {
        try {
            showdialogmonthly();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void yearly(View view) throws ParseException {
        try {
            showdialogyearly();
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
                if (balance < 15000) {
                    Toast.makeText(SubscriptionPage.this, "Balance is insufficient", Toast.LENGTH_SHORT).show();
                } else {
                    String date, expired;
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                    int array = subscriptionDB.countTableSize(user_id);
                    if (array != 0) {
                        arraySub = subscriptionDB.getSubscription(user_id);
                        date = simpleDateFormat.format(calendar.getTime());
                        Date date1 = null;
                        try {
                            date1 = simpleDateFormat.parse(arraySub.get(array-1).getExpired_date());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        calendar.setTime(date1);
                        calendar.add(calendar.DATE, 7);
                        Date result = new Date(calendar.getTimeInMillis());
                        expired = simpleDateFormat.format(result);
                    } else {
                        date = simpleDateFormat.format(calendar.getTime());
                        calendar.add(calendar.DATE, 7);
                        Date result = new Date(calendar.getTimeInMillis());
                        expired = simpleDateFormat.format(result);
                    }
                    Subscription sub = new Subscription(0, user_id, "Yearly", date, expired);

                    subscriptionDB.insertSubscription(sub);
                    userDB.minusNominal(user, user_id, 15000);

                    Intent intent = new Intent(SubscriptionPage.this, HomePage.class);
                    intent.putExtra("USERIDFROMSUBSCRIPT", user_id);
                    startActivity(intent);
                    finish();
                }
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
                if (balance < 50000) {
                    Toast.makeText(SubscriptionPage.this, "Balance is insufficient", Toast.LENGTH_SHORT).show();
                } else {
                    String date, expired;
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                    int array = subscriptionDB.countTableSize(user_id);
                    if (array != 0) {
                        arraySub = subscriptionDB.getSubscription(user_id);
                        date = simpleDateFormat.format(calendar.getTime());
                        Date date1 = null;
                        try {
                            date1 = simpleDateFormat.parse(arraySub.get(array-1).getExpired_date());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        calendar.setTime(date1);
                        calendar.add(calendar.DATE, 30);
                        Date result = new Date(calendar.getTimeInMillis());
                        expired = simpleDateFormat.format(result);
                    } else {
                        date = simpleDateFormat.format(calendar.getTime());
                        calendar.add(calendar.DATE, 30);
                        Date result = new Date(calendar.getTimeInMillis());
                        expired = simpleDateFormat.format(result);
                    }
                    Subscription sub = new Subscription(0, user_id, "Yearly", date, expired);

                    subscriptionDB.insertSubscription(sub);
                    userDB.minusNominal(user, user_id, 50000);

                    Intent intent = new Intent(SubscriptionPage.this, HomePage.class);
                    intent.putExtra("USERIDFROMSUBSCRIPT", user_id);
                    startActivity(intent);
                    finish();
                }
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
                if (balance < 550000) {
                    Toast.makeText(SubscriptionPage.this, "Balance is insufficient", Toast.LENGTH_SHORT).show();
                } else {
                    String date, expired;
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                    int array = subscriptionDB.countTableSize(user_id);
                    if (array != 0) {
                        arraySub = subscriptionDB.getSubscription(user_id);
                        date = simpleDateFormat.format(calendar.getTime());
                        Date date1 = null;
                        try {
                            date1 = simpleDateFormat.parse(arraySub.get(array-1).getExpired_date());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        calendar.setTime(date1);
                        calendar.add(calendar.DATE, 365);
                        Date result = new Date(calendar.getTimeInMillis());
                        expired = simpleDateFormat.format(result);
                    } else {
                        date = simpleDateFormat.format(calendar.getTime());
                        calendar.add(calendar.DATE, 365);
                        Date result = new Date(calendar.getTimeInMillis());
                        expired = simpleDateFormat.format(result);
                    }
                    Subscription sub = new Subscription(0, user_id, "Yearly", date, expired);

                    subscriptionDB.insertSubscription(sub);
                    userDB.minusNominal(user, user_id, 550000);

                    Intent intent = new Intent(SubscriptionPage.this, HomePage.class);
                    intent.putExtra("USERIDFROMSUBSCRIPT", user_id);
                    startActivity(intent);
                    finish();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}