package com.example.usmansh.firebaseapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class cameraa extends AppCompatActivity {

    private Button mUploadbt;
    private ImageView imageView;
    private StorageReference mStorageRef;
    private ProgressDialog ProgressDialog;
    private static final int CAMERA_REQUEST_CODE = 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cameraa);

        mUploadbt = (Button)findViewById(R.id.CapUpload);
        imageView = (ImageView)findViewById(R.id.imageView);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        ProgressDialog = new ProgressDialog(this);

        mUploadbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent cameraa = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(cameraa,CAMERA_REQUEST_CODE);
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK){

            ProgressDialog.setMessage("Uploading..!");
            ProgressDialog.show();

            Uri img = data.getData();

            final StorageReference filepath = mStorageRef.child("CamPhotos").child(img.getLastPathSegment());

            filepath.putFile(img).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Toast.makeText(cameraa.this, "Successfull uploaded", Toast.LENGTH_SHORT).show();
                    ProgressDialog.dismiss();

                    Uri downloadpic = taskSnapshot.getDownloadUrl();
                    Picasso.with(getApplicationContext()).load(downloadpic).into(imageView);



                }
            });


        }
    }
}
