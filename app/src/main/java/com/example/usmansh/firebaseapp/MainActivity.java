package com.example.usmansh.firebaseapp;

import android.*;
import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 10 ;
    private EditText name,contact,address;
    public Button sendsdata,retreivedata,loginAct,uploadbt,camerabt,audioUp;
    FirebaseDatabase database;
    DatabaseReference myref;
    TextView textView1,textView2,textView3 ;
    ArrayList<person> arrperson = new ArrayList<person>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendsdata= (Button)findViewById(R.id.sendsdata);
        name = (EditText)findViewById(R.id.name);
        contact = (EditText)findViewById(R.id.contact1);
        address = (EditText)findViewById(R.id.address);
        retreivedata= (Button)findViewById(R.id.retdata);
        loginAct = (Button)findViewById(R.id.loginAct);
        uploadbt = (Button)findViewById(R.id.uploadbt);
        camerabt = (Button)findViewById(R.id.camerabt);
        audioUp = (Button)findViewById(R.id.audioUp);

        database = FirebaseDatabase.getInstance();
        myref = database.getInstance().getReference();


        //PERSON OBJECT
        final person per = new person();


        sendsdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String namee =  name.getText().toString().trim();
                String contactt = contact.getText().toString().trim();
                String addresss = address.getText().toString().trim();

                per.setName(namee);
                per.setContact(contactt);
                per.setAddress(addresss);

                arrperson.add(per);

                myref.push().setValue(per).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });





                /*HashMap<String,String> hashMap = new HashMap<String, String>();

                hashMap.put("name",namee);
                hashMap.put("contact",contactt);
                hashMap.put("address",addresss);

                myref.push().setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Inserted...!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "Error...!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });*/

            }
        });






        retreivedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),retdata.class);
                startActivity(intent);
            }
        });



       loginAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent loginactt = new Intent(getApplicationContext(),loginact.class);
                startActivity(loginactt);
            }
        });



        uploadbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( getApplicationContext(),uploadpic.class);
                startActivity(intent);
            }
        });


        camerabt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent cameraclass = new Intent(getApplicationContext(),cameraa.class);
                startActivity(cameraclass);
            }
        });


        audioUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent audioclass = new Intent(getApplicationContext(),audioo.class);
                startActivity(audioclass);
            }
        });



    }










    }









