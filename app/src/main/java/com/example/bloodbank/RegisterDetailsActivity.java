package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rey.material.widget.CheckBox;

public class RegisterDetailsActivity extends AppCompatActivity {

    private EditText detailsEdt;
    private CheckBox termsChecker;
    private String details;
    private Button confirmBtn;
    private boolean checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_details);

        detailsEdt = findViewById(R.id.register_details_edt_id);
        termsChecker = findViewById(R.id.register_terms_policy_checker);
        confirmBtn = findViewById(R.id.register_details_confirm_btn_id);

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

                if(details.equals("")){

                    detailsEdt.setError("Enter something details");
                    detailsEdt.requestFocus();
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