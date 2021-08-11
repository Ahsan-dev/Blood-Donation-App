package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.rey.material.widget.CheckBox;

public class RegisterDetailsActivity extends AppCompatActivity {

    private EditText detailsEdt, passEdt, confirmPassEdt;
    private CheckBox termsChecker;
    private String details;
    private Button confirmBtn;
    private boolean checked;
    private ImageView passEyeToggle, confirmPassEyeToggle;
    Boolean passEyeState = true, confirmPassEyeState = true;
    String pass, confirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_details);

        detailsEdt = findViewById(R.id.register_details_edt_id);
        termsChecker = findViewById(R.id.register_terms_policy_checker);
        confirmBtn = findViewById(R.id.register_details_confirm_btn_id);
        passEyeToggle = findViewById(R.id.register_details_pass_eye_id);
        confirmPassEyeToggle = findViewById(R.id.register_details_confirm_pass_eye_id);
        passEdt = findViewById(R.id.register_details_pass_edt_id);
        confirmPassEdt = findViewById(R.id.register_details_confirm_pass_edt_id);

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

                    Toast.makeText(RegisterDetailsActivity.this, "Confirm Registration", Toast.LENGTH_SHORT).show();
                    Intent successRegiIntent = new Intent(getApplicationContext(),SuccessfulRegiActivity.class);
                    successRegiIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(successRegiIntent);

                }


            }
        });



    }
}