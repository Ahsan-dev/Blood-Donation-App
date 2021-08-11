package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.paperdb.Paper;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bloodbank.Adapter.AdminDateAssignAdapter;
import com.example.bloodbank.Models.AdminDateAssignModel;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {

    private ImageView logOutBtn;
    private RecyclerView assignDateRecycler;
    private List<AdminDateAssignModel> dateAssList;
    private AdminDateAssignAdapter assignAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        logOutBtn = findViewById(R.id.admin_dashboard_logout_btn_id);

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logoutDialog();

            }
        });

        assignDateRecycler = findViewById(R.id.admin_dashboard_recycler_id);
        dateAssList = new ArrayList<>();

        dateAssList.add(new AdminDateAssignModel(1,R.drawable.ahsan_job,"Md Ahsanul",getString(R.string.details_text),"15/02/2021"));
        dateAssList.add(new AdminDateAssignModel(2,R.drawable.ahsan_job,"Md Ahsanul",getString(R.string.details_text),"15/02/2021"));
        dateAssList.add(new AdminDateAssignModel(3,R.drawable.ahsan_job,"Md Ahsanul",getString(R.string.details_text),"15/02/2021"));
        dateAssList.add(new AdminDateAssignModel(4,R.drawable.ahsan_job,"Md Ahsanul",getString(R.string.details_text),"15/02/2021"));
        dateAssList.add(new AdminDateAssignModel(5,R.drawable.ahsan_job,"Md Ahsanul",getString(R.string.details_text),"15/02/2021"));

        assignAdapter = new AdminDateAssignAdapter(dateAssList,this);
        assignDateRecycler.setLayoutManager(new LinearLayoutManager(this));
        assignDateRecycler.setAdapter(assignAdapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        logoutDialog();
    }

    private void logoutDialog(){

        final Dialog dialog = new Dialog(this);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        TextView message = (TextView) dialog.findViewById(R.id.alertDialogMessageId);
        message.setText("Do you want to logout?");

        TextView yesBtn = dialog.findViewById(R.id.alert_positive_btn_id);
        TextView noBtn = dialog.findViewById(R.id.alert_negative_btn_id);
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().destroy();
                Intent welcomeIntent = new Intent(getApplicationContext(),WelcomeActivity.class);
                welcomeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(welcomeIntent);

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