package com.example.fabhotels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.fabhotels.Database.Data;

public class SplashScreenActivity extends AppCompatActivity {

    TextView tvSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int loading_time = 2000;

        setContentView(R.layout.activity_splash_screen);

        Data.initBooking();
        Data.initHotel();
        Data.initMember();

        tvSplash = (TextView) findViewById(R.id.activityOpen_txtView_name);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        }, loading_time);
    }
}
