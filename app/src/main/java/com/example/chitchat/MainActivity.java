package com.example.chitchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //spalsh screen delay and go another screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               startActivity(new Intent(MainActivity.this,login.class));//from which activity to which
            }
        },2000);
    }
}