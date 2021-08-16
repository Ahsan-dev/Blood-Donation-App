package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bloodbank.Api.Api;
import com.rey.material.widget.CheckBox;

import java.io.IOException;

public class RegisterDetailsActivity extends AppCompatActivity {

    private EditText detailsEdt, passEdt, confirmPassEdt;
    private CheckBox termsChecker;
    private String details;
    private Button confirmBtn;
    private boolean checked;
    private ImageView passEyeToggle, confirmPassEyeToggle;
    private String filePath, userName, mobile, altMobile, email, city, district, policeStation, bloodGrp, religion, gender, weight, birthDay;
    Boolean passEyeState = true, confirmPassEyeState = true;
    String pass, confirmPass;
    private Api api;
    private ProgressDialog loadingBar;
    String r, fromBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_details);

        api = RetroClient.getClient().create(Api.class);

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

        fromBtn = getIntent().getStringExtra("fromBtn");

        if(fromBtn.equals("next"))
            filePath = getIntent().getStringExtra("image");
        else
            filePath = "";

        detailsEdt = findViewById(R.id.register_details_edt_id);
        termsChecker = findViewById(R.id.register_terms_policy_checker);
        confirmBtn = findViewById(R.id.register_details_confirm_btn_id);
        passEyeToggle = findViewById(R.id.register_details_pass_eye_id);
        confirmPassEyeToggle = findViewById(R.id.register_details_confirm_pass_eye_id);
        passEdt = findViewById(R.id.register_details_pass_edt_id);
        confirmPassEdt = findViewById(R.id.register_details_confirm_pass_edt_id);

        loadingBar = new ProgressDialog(this);

        passEyeToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(passEyeState==true){
                    passEyeState = false;
                    passEdt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    passEyeToggle.setImageResource(R.drawable.invisible);

                }else{
                    passEyeState = true;
                    passEdt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passEyeToggle.setImageResource(R.drawable.eye);
                }
            }
        });

        confirmPassEyeToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(confirmPassEyeState==true){
                    confirmPassEyeState = false;
                    confirmPassEdt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    confirmPassEyeToggle.setImageResource(R.drawable.invisible);

                }else{
                    confirmPassEyeState = true;
                    confirmPassEdt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    confirmPassEyeToggle.setImageResource(R.drawable.eye);
                }
            }
        });

        detailsEdt.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                if (detailsEdt.hasFocus()) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction() & MotionEvent.ACTION_MASK){
                        case MotionEvent.ACTION_SCROLL:
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                            return true;
                    }
                }
                return false;
            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                details = detailsEdt.getText().toString();
                checked = termsChecker.isChecked();
                pass = passEdt.getText().toString();
                confirmPass = confirmPassEdt.getText().toString();


                if(details.equals("")){

                    detailsEdt.setError("Enter something details");
                    detailsEdt.requestFocus();
                    return;

                }else if(pass.equals("") || pass.length()<6){

                    passEdt.setError("Enter password of 6 digit at least");
                    passEdt.requestFocus();
                    return;

                }else if(confirmPass.equals("") || confirmPass.length()<6 || !confirmPass.equals(pass)){

                    confirmPassEdt.setError("Password does not match!");
                    confirmPassEdt.requestFocus();
                    return;

                }else if(checked == false){

                    Toast.makeText(RegisterDetailsActivity.this, "Check terms and privacy checkbox", Toast.LENGTH_SHORT).show();
                    termsChecker.requestFocus();
                    return;

                }else{
                    loadingBar.setTitle("Registering your Account..");
                    loadingBar.setMessage("Plz wait, while we are checking the credentials..");
                    loadingBar.setCanceledOnTouchOutside(false);
                    loadingBar.show();

                    Call<ResponseBody> call =api.insertUser(userName, mobile, altMobile, email, bloodGrp, religion, gender, city, district, policeStation, weight, birthDay,filePath,details,pass);

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

//                            assert response.body() != null;
                            try {
                                r = response.body().string();

                                if(r.equals("Registered")){
                                    loadingBar.dismiss();
                                    Toast.makeText(getApplicationContext(),r,Toast.LENGTH_LONG).show();
                                    Toast.makeText(RegisterDetailsActivity.this, r, Toast.LENGTH_SHORT).show();
                                    Intent successRegiIntent = new Intent(getApplicationContext(),SuccessfulRegiActivity.class);
                                    successRegiIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(successRegiIntent);

                                }else if(r.equals("Failed")){
                                    loadingBar.dismiss();
                                    Toast.makeText(RegisterDetailsActivity.this, r+". Try Again!", Toast.LENGTH_SHORT).show();
                                }else{
                                    loadingBar.dismiss();
                                    Toast.makeText(RegisterDetailsActivity.this, r, Toast.LENGTH_SHORT).show();
                                    Intent regiIntent = new Intent(RegisterDetailsActivity.this,RegisterActivity.class);
                                    regiIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(regiIntent);
                                }
                            } catch (Exception e) {
                                loadingBar.dismiss();
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            loadingBar.dismiss();
                            Toast.makeText(RegisterDetailsActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }


            }
        });



    }
}