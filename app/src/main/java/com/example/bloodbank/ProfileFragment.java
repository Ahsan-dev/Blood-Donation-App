package com.example.bloodbank;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.paperdb.Paper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bloodbank.Adapter.ActivityLogAdapter;
import com.example.bloodbank.Models.ActivityLogModel;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    private RecyclerView actLogRecycler;
    private ActivityLogAdapter activityLogAdapter;
    private List<ActivityLogModel> actList;
    private TextView logoutBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        Paper.init(view.getContext());
        logoutBtn = view.findViewById(R.id.profile_fragment_logout_btn_id);
        actLogRecycler = view.findViewById(R.id.profile_fragment_activity_log_recycler_id);
        actList = new ArrayList<>();

        actList.add(new ActivityLogModel("2021/05/17","15:20","donation"));
        actList.add(new ActivityLogModel("2021/05/18","13:23","log in"));
        actList.add(new ActivityLogModel("2021/05/19","15:20","log in"));
        actList.add(new ActivityLogModel("2021/05/17","15:20","donation"));
        actList.add(new ActivityLogModel("2021/05/18","13:23","log in"));
        actList.add(new ActivityLogModel("2021/05/19","15:20","log in"));

        activityLogAdapter = new ActivityLogAdapter(actList,view.getContext());
        actLogRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        actLogRecycler.setAdapter(activityLogAdapter);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(v.getContext());
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
                        Intent welcomeIntent = new Intent(view.getContext(),WelcomeActivity.class);
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
        });

        return view;

    }
}