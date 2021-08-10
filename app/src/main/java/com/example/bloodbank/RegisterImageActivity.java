package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class RegisterImageActivity extends AppCompatActivity {

    private ImageView capturedImg;
    private ImageView captureBtn, cancelBtn;
    private TextView fromStorageTxtBtn, skipTxtBtn, nextBtn;
    private Bitmap photo;
    //private Uri imageUri;

    private String filePath;
    private static final int PICK_PHOTO = 1958;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private static final int CAMERA_REQUEST = 1888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_image);

        capturedImg = findViewById(R.id.register_img_picture_img);
        captureBtn = findViewById(R.id.register_img_picture_capture);
        cancelBtn = findViewById(R.id.register_img_picture_cancel);
        fromStorageTxtBtn = findViewById(R.id.register_img_select_from_storage_txt_btnId);
        skipTxtBtn = findViewById(R.id.register_img_skip_txt_btnId);
        nextBtn = findViewById(R.id.register_img_next_btnId);


        captureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int permissionCheck = ContextCompat.checkSelfPermission(v.getContext(), Manifest.permission.CAMERA);

                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            (Activity) v.getContext(),
                            new String[]{Manifest.permission.CAMERA},
                            1);
                }else {

                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);

                }

            }
        });

        fromStorageTxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imagePickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(imagePickIntent,PICK_PHOTO);
            }
        });


        skipTxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerDetailsIntent = new Intent(getApplicationContext(),RegisterDetailsActivity.class);
                startActivity(registerDetailsIntent);
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerDetailsIntent = new Intent(getApplicationContext(),RegisterDetailsActivity.class);
                startActivity(registerDetailsIntent);
            }
        });


    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST) {
            photo = (Bitmap) data.getExtras().get("data");
            Uri imageUri1 = data.getData();
            filePath = getPath(imageUri1);
            Picasso.get().load(imageUri1).into(capturedImg);
        }

        if (resultCode == RESULT_OK && requestCode == PICK_PHOTO) {
            Uri imageUri = data.getData();
            filePath = getPath(imageUri);
            Picasso.get().load(imageUri).into(capturedImg);
        }
    }

    private String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


}