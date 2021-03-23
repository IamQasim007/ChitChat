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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUp extends AppCompatActivity {

    FirebaseAuth auth;
    EditText emailbox ,passwordbox,namebox;
    Button loginbutton,createaccountbutton;

    FirebaseFirestore database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth=FirebaseAuth.getInstance();

        database=FirebaseFirestore.getInstance();

        emailbox=findViewById(R.id.EmailBox);
        passwordbox=findViewById(R.id.PasswordBox);
        loginbutton=findViewById(R.id.LoginButton);
        createaccountbutton=findViewById(R.id.CreateAccountButton);
        namebox=findViewById(R.id.PersonName);



        createaccountbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name,password,email;
                name=namebox.getText().toString();
                email=emailbox.getText().toString();
                password=passwordbox.getText().toString();

                User user =new User();
                user.setEmail(email);
                user.setName(name);
                user.setPass(password);
                database.collection("user")
                        .document().set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        startActivity(new Intent(SignUp.this,login.class));
                    }
                });

             auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {

                     if (task.isSuccessful()){
                         Toast.makeText(SignUp.this, "Account is created", Toast.LENGTH_SHORT).show();
                     }
                     else {
                         Toast.makeText(SignUp.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                     }

                 }
             });
            }
        });

    }
}