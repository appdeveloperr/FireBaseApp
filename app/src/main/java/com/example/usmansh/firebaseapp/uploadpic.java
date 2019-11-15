package com.example.usmansh.firebaseapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class uploadpic extends AppCompatActivity {


    private Button selectpic;

    private StorageReference mStorage;

    private static final int GALLRY_INTENT = 2;

    private ProgressDialog mProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadpic);

        selectpic = (Button)findViewById(R.id.selectpic);

        mStorage = FirebaseStorage.getInstance().getReference();

        mProgress = new ProgressDialog(this);





        selectpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent goGallry = new Intent(Intent.ACTION_PICK);
                goGallry.setType("image/*");
                startActivityForResult(goGallry,GALLRY_INTENT);
            }
        });
    }



    //GEtting image result


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        if(requestCode == GALLRY_INTENT && resultCode == RESULT_OK){

            mProgress.setMessage("Uploading..!");
            mProgress.show();


            Uri uripic = data.getData();

            StorageReference filepath = mStorage.child("Photos").child(uripic.getLastPathSegment());

            filepath.putFile(uripic).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Toast.makeText(uploadpic.this, "Uploaded success..!!", Toast.LENGTH_SHORT).show();

                    mProgress.dismiss();
                }
            });
        }
    }
}
