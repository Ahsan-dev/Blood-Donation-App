package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;
import io.paperdb.Paper;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {

    private TextView registerBtn, loginBtn;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        registerBtn = findViewById(R.id.welcome_register_buttonId);
        loginBtn = findViewById(R.id.welcome_login_buttonId);

        Paper.init(this);
        loadingBar = new ProgressDialog(this);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent registerActivityIntent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(registerActivityIntent);

            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent loginActivityIntent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(loginActivityIntent);

            }
        });

        String userPhoneKey = Paper.book().read(Prevalent.userPhnKey);
        String userPassKey = Paper.book().read(Prevalent.userPassKey);

        if(userPhoneKey != "" && userPassKey != ""){
            if(!TextUtils.isEmpty(userPhoneKey) && !TextUtils.isEmpty(userPassKey)){
                allowDirectAccessToLogIn(userPhoneKey,userPassKey);

                loadingBar.setTitle("Already logged in...");
                loadingBar.setMessage("Plz wait, You will be redirected to your home page...");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();

            }
        }

    }

    private void allowDirectAccessToLogIn(final String number,final String password) {

        String userMobile = "01775794472";
        String userPass = "mymensingh";


        if(userMobile.equals(number)){

            if(userPass.equals(password)){

                Toast.makeText(getApplicationContext(),"Login Successful.",Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));

            }else {

                Toast.makeText(getApplicationContext(),"Password is incorrect.",Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
            }

        }else {
            Toast.makeText(getApplicationContext(),"Account with this number "+number+" does not exist",Toast.LENGTH_SHORT).show();
            loadingBar.dismiss();
        }

    }
}