package com.example.zoobrasov;

import static android.app.ProgressDialog.show;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.provider.FirebaseInitProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Register extends AppCompatActivity {
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView textView;
    EditText editTextEmail,editTextUserName,editTextName, editTextPassword;
    Button buttonReg;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth= FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.register_email);
        editTextPassword = findViewById(R.id.register_password);
        editTextName=findViewById(R.id.register_name);
        editTextUserName=findViewById(R.id.register_username);
        buttonReg = findViewById(R.id.register_button);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.loginRedirectText);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
        buttonReg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                FirebaseInitProvider.isCurrentlyInitializing();
                FirebaseApp app=FirebaseApp.initializeApp(Register.this);
                database=FirebaseDatabase.getInstance();
                reference=database.getReference("users");

                String name=editTextName.getText().toString();
                String email=editTextEmail.getText().toString();
                String username=editTextUserName.getText().toString();
                String password=editTextPassword.getText().toString();

                HelperClass helperClass=new HelperClass(name,email,username,password);
                reference.child(name).setValue(helperClass);

                Toast.makeText(Register.this,"You have signup successful!", Toast.LENGTH_LONG).show();
                Intent intent =new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });
    }
}