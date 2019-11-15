package com.example.usmansh.firebaseapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginact extends AppCompatActivity {



    private EditText email,password;
    private Button login;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListner;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginact);




        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.loginbt);

        mAuth = FirebaseAuth.getInstance();








        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser() !=null){

                    Intent account = new Intent(getApplicationContext(),account.class);
                    startActivity(account);
                }
            }
        };





        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startSignIn();
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListner);
    }

    public void startSignIn() {

        String emaill = email.getText().toString();
        String passwordd = password.getText().toString();

        if (TextUtils.isEmpty(emaill) || TextUtils.isEmpty(passwordd)) {

            Toast.makeText(this, "Fields are Empty...!", Toast.LENGTH_SHORT).show();

        } else {
            mAuth.signInWithEmailAndPassword(emaill, passwordd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (!task.isSuccessful()) {

                        Toast.makeText(loginact.this, "Sign In Problem...!", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }

    }
}
