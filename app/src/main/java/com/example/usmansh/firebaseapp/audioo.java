package com.example.usmansh.firebaseapp;

import android.*;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

public class audioo extends AppCompatActivity {

    private Button mRecordbt;

    private TextView mRecordLabel;

    private MediaRecorder mRecorder;

    private StorageReference mStorage;

    private String mFileName = null;

    private ProgressDialog Progressdialog;

    private static final String LOG_TAG = " Record_log";

    private static final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 2;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audioo);

        //permission calling function
        manageBtnClick();

        mRecordbt = (Button)findViewById(R.id.Recbt);
        mRecordLabel = (TextView)findViewById(R.id.Reclabel);
        mStorage = FirebaseStorage.getInstance().getReference();
        Progressdialog = new ProgressDialog(this);

        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/Recorded_audio.3gp";


        mRecordbt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == event.ACTION_DOWN){

                    startRecording();
                    mRecordLabel.setText("Audio Recording..!");
                }
                else if (event.getAction() == event.ACTION_UP){

                    stopRecording();
                    mRecordLabel.setText("Audio successfully Recorded..!");
                }


                return false;
            }
        });


    }




    private void startRecording() {

        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
    }


    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;

        UploadAudio();
    }


    private void UploadAudio(){


        Progressdialog.setMessage("Audio File Uploading..!");
        Progressdialog.show();

        StorageReference filepath = mStorage.child("Audio Files").child("new_audio.3gp");

        Uri audiofile = Uri.fromFile(new File(mFileName));

        filepath.putFile(audiofile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Progressdialog.dismiss();
                mRecordLabel.setText("Audio Successfully uploaded..!");
                Toast.makeText(audioo.this, "Audio Successfully uploaded..!", Toast.LENGTH_SHORT).show();
            }
        });

    }








//Recording Permission Code


    private void manageBtnClick() {

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,
                    new String [] {Manifest.permission.RECORD_AUDIO,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_RECORD_AUDIO);
        }
        else{
            readContacts();
        }
    }


    private void readContacts() {

        Toast.makeText(this, "Read Contacts features call", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode){


            case MY_PERMISSIONS_REQUEST_RECORD_AUDIO:
                if(grantResults .length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){

                    readContacts();
                }

                else{

                    if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_CONTACTS)){

                        new AlertDialog.Builder(this).setTitle("Read Contacts permission").
                                setMessage("You need to grant audio record permission to use audio"+
                                        "recorder feature. Retry and grant it..!").show();
                    }
                    else{

                        new AlertDialog.Builder(this).setTitle("Read Contacts permission denied").
                                setMessage("You denied audio record permission. So, the feature will be disabled." +
                                        "To Enable it, go on settings and grant audio record for the application");
                    }


                }

                break;

        }


    }








}
