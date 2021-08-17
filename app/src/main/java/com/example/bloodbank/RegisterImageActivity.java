package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class RegisterImageActivity extends AppCompatActivity {

    private ImageView capturedImg;
    private ImageView captureBtn, cancelBtn;
    private TextView fromStorageTxtBtn, skipTxtBtn, nextBtn;
    private Bitmap photo;
    //private Uri imageUri;

    private String filePath, userName, mobile, altMobile, email, city, district, policeStation, bloodGrp, religion, gender, weight, birthDay;
    private static final int PICK_PHOTO = 1958;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE

    };
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 101;

    private static final int CAMERA_REQUEST = 1888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_image);

        userName = getIntent().getStringExtra("user_name");
        mobile = getIntent().getStringExtra("mobile");
        altMobile = getIntent().getStringExtra("alt_mobile");
        email = getIntent().getStringExtra("email");
        bloodGrp = getIntent().getStringExtra("blood_group");
        religion = getIntent().getStringExtra("religion");
        gender = getIntent().getStringExtra("gender");
        city = getIntent().getStringExtra("division");
        district = getIntent().getStringExtra("district");
        policeStation = getIntent().getStringExtra("police_station");
        weight = getIntent().getStringExtra("weight");
        birthDay = getIntent().getStringExtra("birth_date");

        capturedImg = findViewById(R.id.register_img_picture_img);
        captureBtn = findViewById(R.id.register_img_picture_capture);
        cancelBtn = findViewById(R.id.register_img_picture_cancel);
        fromStorageTxtBtn = findViewById(R.id.register_img_select_from_storage_txt_btnId);
        skipTxtBtn = findViewById(R.id.register_img_skip_txt_btnId);
        nextBtn = findViewById(R.id.register_img_next_btnId);


        captureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkAndRequestPermissions(RegisterImageActivity.this)){
                    selectImage(RegisterImageActivity.this);
                }

            }
        });



        skipTxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerDetailsIntent = new Intent(getApplicationContext(),RegisterDetailsActivity.class);

                registerDetailsIntent.putExtra("fromBtn","skip");

                registerDetailsIntent.putExtra("user_name",userName);
                registerDetailsIntent.putExtra("mobile",mobile);
                registerDetailsIntent.putExtra("alt_mobile",altMobile);
                registerDetailsIntent.putExtra("email",email);
                registerDetailsIntent.putExtra("blood_group",bloodGrp);
                registerDetailsIntent.putExtra("religion",religion);
                registerDetailsIntent.putExtra("gender",gender);
                registerDetailsIntent.putExtra("division",city);
                registerDetailsIntent.putExtra("district",district);
                registerDetailsIntent.putExtra("police_station",policeStation);
                registerDetailsIntent.putExtra("weight",weight);
                registerDetailsIntent.putExtra("birth_date",birthDay);


                startActivity(registerDetailsIntent);
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerDetailsIntent = new Intent(getApplicationContext(),RegisterDetailsActivity.class);

                registerDetailsIntent.putExtra("fromBtn","next");
                registerDetailsIntent.putExtra("user_name",userName);
                registerDetailsIntent.putExtra("mobile",mobile);
                registerDetailsIntent.putExtra("alt_mobile",altMobile);
                registerDetailsIntent.putExtra("email",email);
                registerDetailsIntent.putExtra("blood_group",bloodGrp);
                registerDetailsIntent.putExtra("religion",religion);
                registerDetailsIntent.putExtra("gender",gender);
                registerDetailsIntent.putExtra("division",city);
                registerDetailsIntent.putExtra("district",district);
                registerDetailsIntent.putExtra("police_station",policeStation);
                registerDetailsIntent.putExtra("weight",weight);
                registerDetailsIntent.putExtra("birth_date",birthDay);
                registerDetailsIntent.putExtra("image",filePath);

                startActivity(registerDetailsIntent);
            }
        });


    }

    public static boolean checkAndRequestPermissions(final Activity context) {
        int WExtstorePermission = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int cameraPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (WExtstorePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(context, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }
    // Handled permission Result
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_ID_MULTIPLE_PERMISSIONS:
                if (ContextCompat.checkSelfPermission(RegisterImageActivity.this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "FlagUp Requires Access to Camara.", Toast.LENGTH_SHORT).show();
                } else if (ContextCompat.checkSelfPermission(RegisterImageActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "FlagUp Requires Access to Your Storage.", Toast.LENGTH_SHORT).show();
                } else {
                    selectImage(RegisterImageActivity.this);
                }
                break;
        }
    }



    private void selectImage(Context context) {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                        {
                            requestPermissions(new String[]{Manifest.permission.CAMERA}, 0);
                        }
                        else
                        {
                            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, 0);
                        }
                    }

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        capturedImg.setImageBitmap(selectedImage);
                    }
                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();
                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                capturedImg.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                                cursor.close();
                            }
                        }
                    }
                    break;
            }
        }
    }



    private String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }



}