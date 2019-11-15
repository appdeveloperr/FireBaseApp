package com.example.usmansh.firebaseapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ui.email.SignInActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class account extends AppCompatActivity {

     Button logoutt;
        FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        fAuth = FirebaseAuth.getInstance();
        logoutt = (Button)findViewById(R.id.logoutt);

        logoutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // fAuth.signOut();
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(account.this, "Sign out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

    }


}
