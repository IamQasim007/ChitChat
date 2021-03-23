package com.example.chitchat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class Dashboard extends AppCompatActivity {

    EditText secretcodeboc;
    Button joinbt,sharebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        secretcodeboc =findViewById(R.id.cidebox);
        joinbt =findViewById(R.id.joinbtn);
        sharebtn =findViewById(R.id.share);

        //integrate jitsi meet video calling sdk
        URL serverURl;
        try {
            serverURl =new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions deafultoption=
                    new  JitsiMeetConferenceOptions.Builder()
                            .setServerURL(serverURl)
                            .setWelcomePageEnabled(false)
                            .build();
            JitsiMeet.setDefaultConferenceOptions(deafultoption);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }



        joinbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JitsiMeetConferenceOptions options=new  JitsiMeetConferenceOptions.Builder()
                        .setRoom(secretcodeboc.getText().toString())
                        .setWelcomePageEnabled(false)
                        .build();

                JitsiMeetActivity.launch(Dashboard.this,options);

            }
        });
    }
}