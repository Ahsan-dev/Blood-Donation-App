package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout dashBoardLinear, historyLinear, profileLinear, fragLinear;
    private TextView dashBoardTextToggle, historyTextToggle, profileTextToggle;
    private ImageView dashBoardIcon, historyIcon, profileIcon;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dashBoardLinear = findViewById(R.id.dashboard_linear_btn_id);
        historyLinear = findViewById(R.id.history_linear_btn_id);
        profileLinear = findViewById(R.id.profile_linear_btn_id);
        dashBoardTextToggle = findViewById(R.id.dashboard_text_toggle_id);
        historyTextToggle = findViewById(R.id.history_text_toggle_id);
        profileTextToggle = findViewById(R.id.profile_text_toggle_id);
        dashBoardIcon = findViewById(R.id.dashboard_img_id);
        historyIcon = findViewById(R.id.history_img_id);
        profileIcon = findViewById(R.id.profile_img_id);

        fragLinear = findViewById(R.id.home_frag_linear_id);

        dashBoardLinear.setOnClickListener(this);
        historyLinear.setOnClickListener(this);
        profileLinear.setOnClickListener(this);


        if(savedInstanceState == null){

            DashboardFragment dashFragment= new DashboardFragment();

            getSupportFragmentManager().beginTransaction().replace(R.id.home_frag_linear_id,dashFragment).commit();

        }else {

            getSupportFragmentManager().beginTransaction().replace(R.id.home_frag_linear_id, fragment).commit();

        }


    }

    @Override
    public void onClick(View v) {

        fragment = null;

        if(v.getId()==R.id.dashboard_linear_btn_id){
            fragment = new DashboardFragment();
            dashBoardLinear.setBackgroundResource(R.drawable.bottom_menu_shape_pink);
            dashBoardTextToggle.setVisibility(View.VISIBLE);
            dashBoardIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_dashboard_white));

            historyLinear.setBackgroundResource(R.drawable.bottom_menu_shape);
            historyTextToggle.setVisibility(View.GONE);
            historyIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_history));

            profileLinear.setBackgroundResource(R.drawable.bottom_menu_shape);
            profileTextToggle.setVisibility(View.GONE);
            profileIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_profile));

        }

        if(v.getId()==R.id.history_linear_btn_id){
            fragment = new HistoryFragment();
            dashBoardLinear.setBackgroundResource(R.drawable.bottom_menu_shape);
            dashBoardTextToggle.setVisibility(View.GONE);
            dashBoardIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_dashboard));

            historyLinear.setBackgroundResource(R.drawable.bottom_menu_shape_pink);
            historyTextToggle.setVisibility(View.VISIBLE);
            historyIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_history_white));

            profileLinear.setBackgroundResource(R.drawable.bottom_menu_shape);
            profileTextToggle.setVisibility(View.GONE);
            profileIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_profile));

        }

        if(v.getId()==R.id.profile_linear_btn_id){
            fragment = new ProfileFragment();
            dashBoardLinear.setBackgroundResource(R.drawable.bottom_menu_shape);
            dashBoardTextToggle.setVisibility(View.GONE);
            dashBoardIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_dashboard));

            historyLinear.setBackgroundResource(R.drawable.bottom_menu_shape);
            historyTextToggle.setVisibility(View.GONE);
            historyIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_history));

            profileLinear.setBackgroundResource(R.drawable.bottom_menu_shape_pink);
            profileTextToggle.setVisibility(View.VISIBLE);
            profileIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_profile_white));

        }

        getSupportFragmentManager().beginTransaction().replace(R.id.home_frag_linear_id,fragment).commit();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        TextView message = (TextView) dialog.findViewById(R.id.alertDialogMessageId);
        message.setText("Do you want to exit ?");

        TextView yesBtn = dialog.findViewById(R.id.alert_positive_btn_id);
        TextView noBtn = dialog.findViewById(R.id.alert_negative_btn_id);
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
               // System.exit(0);

            }
        });

        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
}