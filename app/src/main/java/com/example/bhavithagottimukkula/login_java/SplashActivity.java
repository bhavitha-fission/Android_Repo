package com.example.bhavithagottimukkula.login_java;

import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.concurrent.TimeUnit;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 500);
    }





}
