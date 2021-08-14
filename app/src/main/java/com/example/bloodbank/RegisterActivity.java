package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText userNameEdt, mobileEdt, altMobileEdt, emailEdt, cityEdt, districtEdt, policeStationEdt,weightEdt;
    private Spinner bloodGrpSpinner, religionSpinner, genderSpinner;
    private ArrayAdapter<String> bloodGrpAdapter, religionAdapter, genderAdapter;
    private String[] bloodGrparray, religionArray, genderArray;
    private String userName, mobile, altMobile, email, city, district, policeStation, bloodGrp, religion, gender, weight;
    private Button nextBtn;
    private TextView dobTxt;
    private ImageView dobBtn;
    private DatePickerDialog dobPicker;
    private StringBuilder dobString;
    private int bDay, bMonth, bYear;

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
        genderSpinner = findViewById(R.id.register_gender_spinner_id);
        dobTxt = findViewById(R.id.personal_details_dob_text_edt);
        dobBtn = findViewById(R.id.personal_detail_dob_btn_id);
        nextBtn = findViewById(R.id.register_next_btn_id);
        weightEdt = findViewById(R.id.register_weight_edt);

        bloodGrparray = getResources().getStringArray(R.array.blood_grp);
        religionArray = getResources().getStringArray(R.array.religion);
        genderArray = getResources().getStringArray(R.array.gender);

        bloodGrpAdapter = new ArrayAdapter<String>(this,R.layout.bloodgrp_spinner_item_layout,R.id.blood_grp_spinner_item_txt,bloodGrparray);
        religionAdapter = new ArrayAdapter<String>(this,R.layout.bloodgrp_spinner_item_layout,R.id.blood_grp_spinner_item_txt,religionArray);
        genderAdapter = new ArrayAdapter<String>(this,R.layout.bloodgrp_spinner_item_layout,R.id.blood_grp_spinner_item_txt,genderArray);


        bloodGrpSpinner.setAdapter(bloodGrpAdapter);
        religionSpinner.setAdapter(religionAdapter);
        genderSpinner.setAdapter(genderAdapter);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateFields();
            }
        });

        dobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dobMaker();

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
        gender =  genderSpinner.getSelectedItem().toString();
        weight = weightEdt.getText().toString();

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
        }else if(gender.equals("--Select Your Gender--")){
            Toast.makeText(this, "Select Gender", Toast.LENGTH_SHORT).show();
            genderSpinner.requestFocus();
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

            registerImgIntent.putExtra("user_name",userName);
            registerImgIntent.putExtra("mobile","+88"+mobile);
            registerImgIntent.putExtra("alt_mobile",altMobile);
            registerImgIntent.putExtra("email",email);
            registerImgIntent.putExtra("blood_group",bloodGrp);
            registerImgIntent.putExtra("religion",religion);
            registerImgIntent.putExtra("gender",gender);
            registerImgIntent.putExtra("division",city);
            registerImgIntent.putExtra("district",district);
            registerImgIntent.putExtra("police_station",policeStation);
            registerImgIntent.putExtra("weight",weight);
            registerImgIntent.putExtra("birth_date",dobString.toString());

            startActivity(registerImgIntent);

        }


    }

    private void dobMaker() {

        DatePicker datePicker = new DatePicker(this);
        int bdyear = datePicker.getYear();
        final int bdMonth = (datePicker.getMonth())+1;
        final int bdDay = datePicker.getDayOfMonth();

        dobPicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                bYear = year;
                bMonth = month;
                bDay = dayOfMonth;

                dobString = new StringBuilder();
                dobString.append(dayOfMonth+"/");
                dobString.append((month+1)+"/");
                dobString.append(year);

                dobTxt.setText(dobString);


            }
        },bdyear,bdMonth,bdDay);
        dobPicker.getDatePicker().setMaxDate(System.currentTimeMillis() - 568025136000L);
        dobPicker.show();

    }
}