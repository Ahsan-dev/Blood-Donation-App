package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;
import io.paperdb.Paper;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rey.material.widget.CheckBox;

public class LoginActivity extends AppCompatActivity {

    private EditText mobileEdt, passEdt;
    private CheckBox rememberChecker;
    private TextView loginBtn;
    private ProgressDialog loadingBar;
    private boolean rememberChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mobileEdt = findViewById(R.id.login_phn_numberEdtId);
        passEdt = findViewById(R.id.login_passwordrEdtId);
        rememberChecker = findViewById(R.id.logIn_remember_checkboxId);
        loginBtn = findViewById(R.id.login_buttonId);



        loadingBar = new ProgressDialog(this);
        Paper.init(this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
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

            login(number,password);
        }
    }

    private void login( final String number, final  String password) {

        if (rememberChecker.isChecked()) {
            Paper.book().write(Prevalent.userPhnKey, number);
            Paper.book().write(Prevalent.userPassKey, password);

        }
        String userMobile = "01775794472";
        String userPass = "mymensingh";

        if (userMobile.equals(number)) {

            if (userPass.equals(password)) {

                Toast.makeText(getApplicationContext(), "Login Successful.", Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }else {

                Toast.makeText(getApplicationContext(),"Password is incorrect.",Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();

            }
        } else {

            Toast.makeText(getApplicationContext(),"Account with this number "+number+" does not exist",Toast.LENGTH_SHORT).show();
            loadingBar.dismiss();

        }

    }



}