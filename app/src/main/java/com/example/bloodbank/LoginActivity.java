package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodbank.Api.Api;
import com.example.bloodbank.Models.LoginResponse;
import com.example.bloodbank.Models.LoginUser;
import com.rey.material.widget.CheckBox;

public class LoginActivity extends AppCompatActivity {

    private EditText mobileEdt, passEdt;
    private CheckBox rememberChecker;
    private TextView loginBtn;
    private ProgressDialog loadingBar;
    private boolean rememberChecked;
    private TextView adminText, notAdminText;
    private TextView adminLoginBtn;
    private String adminUserString = "user";
    private Api api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        api = RetroClient.getClient().create(Api.class);

        mobileEdt = findViewById(R.id.login_phn_numberEdtId);
        passEdt = findViewById(R.id.login_passwordrEdtId);
        rememberChecker = findViewById(R.id.logIn_remember_checkboxId);
        loginBtn = findViewById(R.id.login_buttonId);
        adminText = findViewById(R.id.admin_panel_text);
        notAdminText = findViewById(R.id.not_admin_panel_text);
        adminLoginBtn = findViewById(R.id.login_as_an_admin_buttonId);

        adminText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminText.setVisibility(View.GONE);
                loginBtn.setVisibility(View.GONE);
                notAdminText.setVisibility(View.VISIBLE);
                adminLoginBtn.setVisibility(View.VISIBLE);
                adminUserString = "admin";
            }
        });

        notAdminText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminText.setVisibility(View.VISIBLE);
                loginBtn.setVisibility(View.VISIBLE);
                notAdminText.setVisibility(View.GONE);
                adminLoginBtn.setVisibility(View.GONE);
                adminUserString = "user";
            }
        });


        loadingBar = new ProgressDialog(this);
        Paper.init(this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginaccess();
            }
        });

        adminLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginaccess();
            }
        });



    }

    private void loginaccess() {
        String number = mobileEdt.getText().toString().trim();
        String password = passEdt.getText().toString().trim();
        rememberChecked = rememberChecker.isChecked();

        mobileEdt.setText("");
        passEdt.setText("");


        if(TextUtils.isEmpty(number)){
            mobileEdt.setError("Plz enter your number.");
            mobileEdt.requestFocus();
            return;
        }

        else if(TextUtils.isEmpty(password)){
            passEdt.setError("Plz enter your password.");
            passEdt.requestFocus();
            return;
        }

        else{
            loadingBar.setTitle("Login to your Account");
            loadingBar.setMessage("Plz wait, while we are checking the credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            if(adminUserString.equals("user"))
                login(number,password);
            else
                loginAdmin(number,password);
        }
    }

    private void login( final String number, final  String password) {

        if (rememberChecker.isChecked()) {
            Paper.book().write(Prevalent.userPhnKey, "+88"+number);
            Paper.book().write(Prevalent.userPassKey, password);

        }

        LoginUser user = new LoginUser();

        user.setMobile("+88"+number);
        user.setPassword(password);

        Call<LoginResponse>  loginCall = api.loginUser(user);

        loginCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                LoginResponse loginResponse = response.body();

                if(loginResponse!=null){
                    if(!loginResponse.toString().equals("failed")){
                        loadingBar.dismiss();

                        String bg = loginResponse.getBloodGroup();
                        Paper.book().write(Permanent.uid,loginResponse.getUserId());
                        Paper.book().write(Permanent.userName,loginResponse.getUserName());
                        Paper.book().write(Permanent.days,loginResponse.getDays());
                        Paper.book().write(Permanent.bloodGrp,bg);
                        Paper.book().write(Permanent.sameBlood,loginResponse.getSameBlood());
                        Paper.book().write(Permanent.image,loginResponse.getImage());
                        Paper.book().write(Permanent.gender,loginResponse.getGender());
                        Paper.book().write(Permanent.policeStation,loginResponse.getPoliceStation());
                        Paper.book().write(Permanent.district,loginResponse.getDistrict());
                        Paper.book().write(Permanent.detailsAbout,loginResponse.getDetails());

                        Log.d("bg",bg);

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }else{
                        loadingBar.dismiss();
                        Toast.makeText(LoginActivity.this, "Incorrect mobile or password", Toast.LENGTH_SHORT).show();

                    }


                }else {
                    loadingBar.dismiss();
                    Toast.makeText(getApplicationContext(),"Response is null !!",Toast.LENGTH_LONG).show();

                }



            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loadingBar.dismiss();
                Toast.makeText(LoginActivity.this, t.toString(), Toast.LENGTH_SHORT).show();

            }
        });


    }


    private void loginAdmin( final String number, final  String password) {

        String userMobile = "01775794472";
        String userPass = "mymensingh";

        if (userMobile.equals(number)) {

            if (userPass.equals(password)) {

                Toast.makeText(getApplicationContext(), "Login Successful.", Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();

                Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }else {

                Toast.makeText(getApplicationContext(),"Password is incorrect.",Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();

            }
        } else {

            Toast.makeText(getApplicationContext(),"Account with this number "+number+" no admin exist",Toast.LENGTH_SHORT).show();
            loadingBar.dismiss();

        }

    }





}