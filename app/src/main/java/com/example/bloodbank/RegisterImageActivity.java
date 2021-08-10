package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class RegisterImageActivity extends AppCompatActivity {

    private ImageView capturedImg;
    private ImageView captureBtn, cancelBtn;
    private TextView fromStorageTxtBtn, skipTxtBtn, nextBtn;
    private Bitmap photo;

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


    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST) {
            photo = (Bitmap) data.getExtras().get("data");
            capturedImg.setImageBitmap(photo);
        }
    }
}