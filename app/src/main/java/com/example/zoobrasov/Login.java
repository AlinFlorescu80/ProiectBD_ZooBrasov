package com.example.zoobrasov;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Login extends AppCompatActivity {

    EditText emailBox,passwordBox;
    Button loginButton;
    FirebaseAuth mAuth;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailBox=findViewById(R.id.login_email);
        passwordBox=findViewById(R.id.login_password);
        loginButton=findViewById(R.id.login_button);

        textView = findViewById(R.id.signUpRedirectText);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(!validateUserName() | !validatePassword())
                {

                } else
                {
                    checkUser();
                }

            }
        });
    }

    public Boolean validateUserName()
    {
        String val= emailBox.getText().toString();
        if(val.isEmpty()){
            emailBox.setError("Username cannot be empty");
            return false;
        } else {
            emailBox.setError(null);
            return true;
        }
    }

    public Boolean validatePassword()
    {
        String val= passwordBox.getText().toString();
        if(val.isEmpty()){
            passwordBox.setError("Password cannot be empty");
            return false;
        } else {
            passwordBox.setError(null);
            return true;
        }
    }

    public void checkUser()
    {
        String userUsername=emailBox.getText().toString();
        String userPassword=passwordBox.getText().toString();

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("users");
        Query chekUserDatabase=reference.orderByChild("username").equalTo(userUsername);

        chekUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists())
                {
                    emailBox.setError(null);
                    String passwordFromDb=snapshot.child(userPassword).child("password").getValue(String.class);

                    if(!Objects.equals(passwordFromDb,userPassword))
                    {
                        emailBox.setError(null);
                        Intent intent=new Intent(Login.this,MainActivity.class);
                        startActivity(intent);
                    }else{
                        passwordBox.setError("Invalid password");
                        passwordBox.requestFocus();
                    }
                } else{
                    emailBox.setError("User does not exist");
                    emailBox.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}