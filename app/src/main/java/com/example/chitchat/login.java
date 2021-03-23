package com.example.chitchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    EditText emailbox ,passwordbox;
    Button loginbutton,createaccountbutton;

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth=FirebaseAuth.getInstance();

        emailbox=findViewById(R.id.EmailBox);
        passwordbox=findViewById(R.id.PasswordBox);
        loginbutton=findViewById(R.id.LoginButton);
        createaccountbutton=findViewById(R.id.CreateAccountButton);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String password,email;

                email=emailbox.getText().toString();
                password=passwordbox.getText().toString();


                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                          startActivity(new Intent(login.this,Dashboard.class));
                        }
                        else {
                            Toast.makeText(login.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });

        //run create account activity when click on create account
        createaccountbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,SignUp.class));
            }
        });

    }
}