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
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodbank.Api.Api;
import com.example.bloodbank.Models.LoginResponse;
import com.example.bloodbank.Models.LoginUser;

public class WelcomeActivity extends AppCompatActivity {

    private TextView registerBtn, loginBtn;
    private ProgressDialog loadingBar;
    private Api api;

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


        LoginUser user = new LoginUser();
        user.setMobile("+88"+number);
        user.setPassword(password);
        Call<LoginResponse> loginCall = api.loginUser(user);

        loginCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                LoginResponse loginResponse = response.body();
                if(loginResponse!=null){
                    if(!loginResponse.toString().equals("failed")){
                        loadingBar.dismiss();
                        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }else{
                        loadingBar.dismiss();
                        Toast.makeText(WelcomeActivity.this, "Incorrect mobile or password!", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    loadingBar.dismiss();
                    Toast.makeText(getApplicationContext(),"Response is null !!",Toast.LENGTH_LONG).show();

                }



            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loadingBar.dismiss();
                Toast.makeText(WelcomeActivity.this, t.toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}