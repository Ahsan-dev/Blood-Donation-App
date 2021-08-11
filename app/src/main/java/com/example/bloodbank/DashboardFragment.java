package com.example.bloodbank;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodbank.Adapter.RequestsRecyclerAdapter;
import com.example.bloodbank.Models.RequestsItemsModel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DashboardFragment extends Fragment {

     List<RequestsItemsModel> reqLists;
     private RecyclerView reqRecycler;
     private RequestsRecyclerAdapter reqRecyclerAdapter;
     private TextView postNowBtn, checkPostsBtn;
     private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        reqLists = new ArrayList<>();

        reqLists.add(new RequestsItemsModel(R.drawable.ahsan_job,"Md Ahsanul Haque","O(Positive)","Netrakona","Netrakona Hospital",true,"Blood is needed for a patient of operation. Please come and help.","+8801775794472"));
        reqLists.add(new RequestsItemsModel(R.drawable.ahsan_job,"Md Ahsanul Haque","O(Positive)","Netrakona","Netrakona Hospital",false,"Blood is needed for a patient of operation. Please come and help.","+8801775794472"));
        reqLists.add(new RequestsItemsModel(R.drawable.ahsan_job,"Md Ahsanul Haque","O(Positive)","Netrakona","Netrakona Hospital",true,"Blood is needed for a patient of operation. Please come and help.","+8801775794472"));
        reqLists.add(new RequestsItemsModel(R.drawable.ahsan_job,"Md Ahsanul Haque","O(Positive)","Netrakona","Netrakona Hospital",false,"Blood is needed for a patient of operation. Please come and help.","+8801775794472"));
        reqLists.add(new RequestsItemsModel(R.drawable.ahsan_job,"Md Ahsanul Haque","O(Positive)","Netrakona","Netrakona Hospital",true,"Blood is needed for a patient of operation. Please come and help.","+8801775794472"));
        reqLists.add(new RequestsItemsModel(R.drawable.ahsan_job,"Md Ahsanul Haque","O(Positive)","Netrakona","Netrakona Hospital",false,"Blood is needed for a patient of operation. Please come and help.","+8801775794472"));
        reqLists.add(new RequestsItemsModel(R.drawable.ahsan_job,"Md Ahsanul Haque","O(Positive)","Netrakona","Netrakona Hospital",true,"Blood is needed for a patient of operation. Please come and help.","+8801775794472"));
        reqLists.add(new RequestsItemsModel(R.drawable.ahsan_job,"Md Ahsanul Haque","O(Positive)","Netrakona","Netrakona Hospital",false,"Blood is needed for a patient of operation. Please come and help.","+8801775794472"));


        reqRecycler = view.findViewById(R.id.requests_recycler_id);
        reqRecyclerAdapter = new RequestsRecyclerAdapter(reqLists,view.getContext());
        reqRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        reqRecycler.setAdapter(reqRecyclerAdapter);

        postNowBtn = view.findViewById(R.id.post_now_txt_btn_id);
        checkPostsBtn = view.findViewById(R.id.checked_post_txt_btn_id);

        postNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInAlertDialog();
            }
        });

        checkPostsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(),MyPostsActivity.class));
            }
        });

        return view;
    }

    private void showInAlertDialog() {

        final Dialog dialog = new Dialog(view.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setCancelable(false);
        dialog.setContentView(R.layout.blood_request_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        String[] bloodGrpArray = getResources().getStringArray(R.array.blood_grp);


        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;

        EditText detailEdt = dialog.findViewById(R.id.blood_request_details_edt_id);
        Spinner bloodgrpSpinner = dialog.findViewById(R.id.blood_request_bloodgrp_spinner_id);
        EditText locationEdt =  dialog.findViewById(R.id.blood_request_location_edt_id);
        EditText hospitalEdt =  dialog.findViewById(R.id.blood_request_hospital_edt_id);
        EditText hoursEdt =  dialog.findViewById(R.id.blood_request_hours_edt_id);
        TextView postTxtBtn = dialog.findViewById(R.id.blood_request_post_btn_id);
        TextView cancelTxtBtn =  dialog.findViewById(R.id.blood_request_cancel_btn_id);

        detailEdt.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                if (detailEdt.hasFocus()) {
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

        ArrayAdapter<String> bloodGrpAdapter = new ArrayAdapter<String>(view.getContext(),R.layout.bloodgrp_spinner_item_layout,R.id.blood_grp_spinner_item_txt,bloodGrpArray);
        bloodgrpSpinner.setAdapter(bloodGrpAdapter);




        postTxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String details = detailEdt.getText().toString();
                String bloodGrp = bloodgrpSpinner.getSelectedItem().toString();
                String location = locationEdt.getText().toString();
                String hospital = hospitalEdt.getText().toString();
                String hours = hoursEdt.getText().toString();

                if(details.equals("")){
                    detailEdt.setError("Enter a details");
                    detailEdt.requestFocus();
                    return;
                }else if(bloodGrp.equals("")) {
                    bloodgrpSpinner.requestFocus();
                    return;
                }else if(location.equals("")){
                    locationEdt.setError("Enter a details");
                    locationEdt.requestFocus();
                    return;
                }else if(hospital.equals("")){
                    hospitalEdt.setError("Enter a details");
                    hospitalEdt.requestFocus();
                    return;
                }else if(hours.equals("")){
                    hoursEdt.setError("Enter a details");
                    hoursEdt.requestFocus();
                    return;
                }else{
                    Toast.makeText(view.getContext(), "Posted successfully", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }


            }
        });

        cancelTxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);

    }
}