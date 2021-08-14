package com.example.bloodbank;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.paperdb.Paper;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
import com.example.bloodbank.Api.Api;
import com.example.bloodbank.Models.GetPostFeed;
import com.example.bloodbank.Models.RequestsItemsModel;
import com.example.bloodbank.Models.WritePost;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

     List<RequestsItemsModel> reqLists;
     List<GetPostFeed> postList;
     private RecyclerView reqRecycler;
     private RequestsRecyclerAdapter reqRecyclerAdapter;
     private TextView postNowBtn, checkPostsBtn, daysTxt, bloodGrpTxt, sameBloodTxt;
     private View view;
     private Api api;
     private ProgressDialog loadingBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        loadingBar = new ProgressDialog(view.getContext());

        daysTxt = view.findViewById(R.id.dashboard_days_txt_id);
        bloodGrpTxt = view.findViewById(R.id.dashboard_blood_group_txt_id);
        sameBloodTxt = view.findViewById(R.id.dashboard_same_blood_txt_id);

        Paper.init(view.getContext());

        daysTxt.setText(Paper.book().read(Permanent.days));
        bloodGrpTxt.setText(Paper.book().read(Permanent.bloodGrp));
        sameBloodTxt.setText(Paper.book().read(Permanent.sameBlood));


        reqLists = new ArrayList<>();

        reqLists.add(new RequestsItemsModel(R.drawable.ahsan_job,"Md Ahsanul Haque","O(Positive)","Netrakona","Netrakona Hospital",true,"Blood is needed for a patient of operation. Please come and help.","+8801775794472"));
        reqLists.add(new RequestsItemsModel(R.drawable.ahsan_job,"Md Ahsanul Haque","O(Positive)","Netrakona","Netrakona Hospital",false,"Blood is needed for a patient of operation. Please come and help.","+8801775794472"));
        reqLists.add(new RequestsItemsModel(R.drawable.ahsan_job,"Md Ahsanul Haque","O(Positive)","Netrakona","Netrakona Hospital",true,"Blood is needed for a patient of operation. Please come and help.","+8801775794472"));
        reqLists.add(new RequestsItemsModel(R.drawable.ahsan_job,"Md Ahsanul Haque","O(Positive)","Netrakona","Netrakona Hospital",false,"Blood is needed for a patient of operation. Please come and help.","+8801775794472"));
        reqLists.add(new RequestsItemsModel(R.drawable.ahsan_job,"Md Ahsanul Haque","O(Positive)","Netrakona","Netrakona Hospital",true,"Blood is needed for a patient of operation. Please come and help.","+8801775794472"));
        reqLists.add(new RequestsItemsModel(R.drawable.ahsan_job,"Md Ahsanul Haque","O(Positive)","Netrakona","Netrakona Hospital",false,"Blood is needed for a patient of operation. Please come and help.","+8801775794472"));
        reqLists.add(new RequestsItemsModel(R.drawable.ahsan_job,"Md Ahsanul Haque","O(Positive)","Netrakona","Netrakona Hospital",true,"Blood is needed for a patient of operation. Please come and help.","+8801775794472"));
        reqLists.add(new RequestsItemsModel(R.drawable.ahsan_job,"Md Ahsanul Haque","O(Positive)","Netrakona","Netrakona Hospital",false,"Blood is needed for a patient of operation. Please come and help.","+8801775794472"));




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

    @Override
    public void onResume() {
        super.onResume();



        Call<List<GetPostFeed>> call = api.getPostFeed();

        call.enqueue(new Callback<List<GetPostFeed>>() {
            @Override
            public void onResponse(Call<List<GetPostFeed>> call, Response<List<GetPostFeed>> response) {

                if(response!=null){

                    postList = response.body();
                }else{
                    Toast.makeText(view.getContext(), "Response Empty", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<GetPostFeed>> call, Throwable t) {

                Toast.makeText(view.getContext(), t.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        reqRecycler = view.findViewById(R.id.requests_recycler_id);
        reqRecyclerAdapter = new RequestsRecyclerAdapter(postList,view.getContext());
        reqRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        reqRecycler.setAdapter(reqRecyclerAdapter);


    }

    private void showInAlertDialog() {

        final Dialog dialog = new Dialog(view.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setCancelable(false);
        dialog.setContentView(R.layout.blood_request_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        String[] bloodGrpArray = getResources().getStringArray(R.array.blood_grp);
        String[] relationArray = getResources().getStringArray(R.array.relation);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;

        EditText detailEdt = dialog.findViewById(R.id.blood_request_details_edt_id);
        Spinner bloodgrpSpinner = dialog.findViewById(R.id.blood_request_bloodgrp_spinner_id);
        Spinner relationSpinner = dialog.findViewById(R.id.blood_request_relation_spinner_id);
        EditText policeStationEdt =  dialog.findViewById(R.id.blood_request_police_station_edt_id);
        EditText districtEdt =  dialog.findViewById(R.id.blood_request_district_edt_id);
        EditText mobileEdt =  dialog.findViewById(R.id.blood_request_mobile_edt_id);
        EditText dateEdt =  dialog.findViewById(R.id.blood_request_date_edt_id);
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

        ArrayAdapter<String> relationAdapter = new ArrayAdapter<String>(view.getContext(),R.layout.bloodgrp_spinner_item_layout,R.id.blood_grp_spinner_item_txt,relationArray);
        relationSpinner.setAdapter(relationAdapter);




        postTxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String details = detailEdt.getText().toString();
                String bloodGrp = bloodgrpSpinner.getSelectedItem().toString();
                String relation = relationSpinner.getSelectedItem().toString();
                String policeStation = policeStationEdt.getText().toString();
                String district = districtEdt.getText().toString();
                String mobile = mobileEdt.getText().toString();
                String date = dateEdt.getText().toString();
                String hospital = hospitalEdt.getText().toString();
                String hours = hoursEdt.getText().toString();

                if(details.equals("")){
                    detailEdt.setError("Enter a details");
                    detailEdt.requestFocus();
                    return;
                }else if(bloodGrp.equals("--Select Your Blood Group--")) {
                    bloodgrpSpinner.requestFocus();
                    return;
                }else if(relation.equals("--Select Relation--")) {
                    relationSpinner.requestFocus();
                    return;
                }else if(policeStation.equals("")){
                    policeStationEdt.setError("Enter location's police station");
                    policeStationEdt.requestFocus();
                    return;
                }else if(district.equals("")){
                    districtEdt.setError("Enter District");
                    districtEdt.requestFocus();
                    return;
                }else if(mobile.equals("") || mobile.length()<11){
                    mobileEdt.setError("Enter valid contact mobile number");
                    mobileEdt.requestFocus();
                    return;
                }else if(date.equals("") ){
                    dateEdt.setError("Enter required date");
                    dateEdt.requestFocus();
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
                    loadingBar.setTitle("Processing your post for blood request..");
                    loadingBar.setMessage("Plz wait, while we are posting your request...");
                    loadingBar.setCanceledOnTouchOutside(false);
                    loadingBar.show();

                    WritePost post = new WritePost();

                    post.setUserId(Paper.book().read(Permanent.uid));
                    post.setDetails(details);
                    post.setBloodGrp(bloodGrp);
                    post.setPoliceStation(policeStation);
                    post.setDistrict(district);
                    post.setMobile("+88"+mobile);
                    post.setDate(date);
                    post.setTimeFrame(hours);
                    post.setStatus("pending");

                    Call<ResponseBody> call = api.writePost(post);

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(response.body()!=null){

                                if(response.body().toString().equals("posted")){
                                    dialog.dismiss();
                                    Toast.makeText(view.getContext(), "Posted Successfully", Toast.LENGTH_SHORT).show();
                                }else{
                                    dialog.dismiss();
                                    Toast.makeText(view.getContext(), "Failed! Try again..", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            dialog.dismiss();
                            Toast.makeText(view.getContext(), t.toString(), Toast.LENGTH_SHORT).show();

                        }
                    });


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