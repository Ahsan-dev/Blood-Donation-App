package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText userNameEdt, mobileEdt, altMobileEdt, emailEdt, cityEdt, districtEdt, policeStationEdt;
    private Spinner bloodGrpSpinner, religionSpinner;
    private ArrayAdapter<String> bloodGrpAdapter, religionAdapter;
    private String[] bloodGrparray, religionArray;
    private String userName, mobile, altMobile, email, city, district, policeStation, bloodGrp, religion;
    private Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userNameEdt = findViewById(R.id.register_user_name_edt);
        mobileEdt = findViewById(R.id.register_mobile_edt);
        altMobileEdt = findViewById(R.id.register_alt_mobile_edt);
        emailEdt = findViewById(R.id.register_email_edt);
        cityEdt = findViewById(R.id.register_state_edt);
        districtEdt = findViewById(R.id.register_district_edt);
        policeStationEdt = findViewById(R.id.register_police_station_edt);
        bloodGrpSpinner = findViewById(R.id.register_bloodgrp_spinner_id);
        religionSpinner = findViewById(R.id.register_religion_spinner_id);
        nextBtn = findViewById(R.id.register_next_btn_id);

        bloodGrparray = getResources().getStringArray(R.array.blood_grp);
        religionArray = getResources().getStringArray(R.array.religion);

        bloodGrpAdapter = new ArrayAdapter<String>(this,R.layout.bloodgrp_spinner_item_layout,R.id.blood_grp_spinner_item_txt,bloodGrparray);
        religionAdapter = new ArrayAdapter<String>(this,R.layout.bloodgrp_spinner_item_layout,R.id.blood_grp_spinner_item_txt,religionArray);

        bloodGrpSpinner.setAdapter(bloodGrpAdapter);
        religionSpinner.setAdapter(religionAdapter);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateFields();
            }
        });


    }

    private void validateFields(){

        userName = userNameEdt.getText().toString();
        mobile = mobileEdt.getText().toString();
        altMobile = altMobileEdt.getText().toString();
        email = emailEdt.getText().toString();
        city = cityEdt.getText().toString();
        district = districtEdt.getText().toString();
        policeStation = policeStationEdt.getText().toString();
        bloodGrp = bloodGrpSpinner.getSelectedItem().toString();
        religion = religionSpinner.getSelectedItem().toString();

        if(userName.equals("")){
            userNameEdt.setError("Enter a details");
            userNameEdt.requestFocus();
            return;
        }else if(mobile.equals("") || mobile.length()<11){
            mobileEdt.setError("Enter mobile number of 11 digits");
            mobileEdt.requestFocus();
            return;
        }else if(altMobile.equals("")|| altMobile.length()<11){
            altMobileEdt.setError("Enter alternative mobile number of 11 digits");
            altMobileEdt.requestFocus();
            return;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEdt.setError("Enter your email");
            emailEdt.requestFocus();
            return;
        }else if(bloodGrp.equals("--Select Your Blood Group--")) {
            Toast.makeText(this, "Select blood group", Toast.LENGTH_SHORT).show();
            bloodGrpSpinner.requestFocus();
            return;
        }else if(religion.equals("--Select Your Religion--")){
            Toast.makeText(this, "Select religion", Toast.LENGTH_SHORT).show();
            religionSpinner.requestFocus();
            return;
        }else if(city.equals("")){
            cityEdt.setError("Enter your city/division");
            cityEdt.requestFocus();
            return;
        }else if(district.equals("")){
            districtEdt.setError("Enter your district");
            districtEdt.requestFocus();
            return;
        }else if(policeStation.equals("")){
            policeStationEdt.setError("Enter your police station");
            policeStationEdt.requestFocus();
            return;
        }else{

            Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
            Intent registerImgIntent = new Intent(getApplicationContext(),RegisterImageActivity.class);
            startActivity(registerImgIntent);

        }


    }
}