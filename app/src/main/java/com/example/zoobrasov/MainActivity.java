package com.example.zoobrasov;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;



public class MainActivity extends AppCompatActivity {

    private Button button;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button logOut;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    Button logIn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button1);
        button.setOnClickListener(v -> openActivity2());

        button1 = findViewById(R.id.button2);
        button1.setOnClickListener(v -> openActivity3());


        button2 = findViewById(R.id.button3);
        button2.setOnClickListener(v -> openActivity4());


        button3 = findViewById(R.id.button4);
        button3.setOnClickListener(v -> openActivity5());

        logIn = findViewById(R.id.login);

        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(logIn.getContext(),gso);

        logOut = findViewById(R.id.logout);



        logIn.setOnClickListener(view -> logIn());

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null)
        {
            String personName = "Welcome, "+ acct.getDisplayName();
            logIn.setText(personName);
        }


        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });

        /*
        if (!logIn.getText().toString().equals("Log in"))
            logOut.setVisibility(View.VISIBLE);
        else
            logOut.setVisibility(View.INVISIBLE);
*/


        Button mailButton = findViewById(R.id.mail_button);
        mailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mailto = "mailto:gradinazoobv@yahoo.com";
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse(mailto));
                startActivity(intent);
            }
        });

        Button webButton = findViewById(R.id.site_button);
        webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.zoobrasov.ro";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

    }


    void logOut() {
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                finish();
                startActivity(new Intent(MainActivity.this, Login.class));
            }
        });
    }

    void logIn()
    {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000)
        {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                NavigateBack();


            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Eroare șefule", Toast.LENGTH_SHORT).show();
            }
        }
    }

    void NavigateBack(){
        finish();
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
    }






    public void openActivity2(){
        Intent intent = new Intent(this, ProgramSiBilete.class);
        startActivity(intent);
    }

    public void openActivity3(){
        Intent intent = new Intent(this, Animale.class);
        startActivity(intent);
    }


    public void openActivity4(){
        Intent intent = new Intent(this, Quiz.class);
        startActivity(intent);
    }


    public void openActivity5(){
        Intent intent = new Intent(this, Harta.class);
        startActivity(intent);
    }



}