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

import android.util.Log;
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
import com.example.bloodbank.Models.WritePostRespose;
import com.example.bloodbank.NatworkTool.NoConnectivityException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

     List<RequestsItemsModel> reqLists;
     private List<GetPostFeed> postList;
     private RecyclerView reqRecycler;
     private RequestsRecyclerAdapter reqRecyclerAdapter;
     private TextView postNowBtn, checkPostsBtn, daysTxt, bloodGrpTxt, sameBloodTxt, daysAgoTxt, donateAdviceTxt;
     private View view;
     private Api api, api2;
     private ProgressDialog loadingBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        loadingBar = new ProgressDialog(view.getContext());
        api = RetroClient.getClient().create(Api.class);

        daysTxt = view.findViewById(R.id.dashboard_days_txt_id);
        bloodGrpTxt = view.findViewById(R.id.dashboard_blood_group_txt_id);
        sameBloodTxt = view.findViewById(R.id.dashboard_same_blood_txt_id);
        daysAgoTxt = view.findViewById(R.id.dashboard_days_ago_text_id);
        donateAdviceTxt = view.findViewById(R.id.dashboard_donate_advice_txt_id);
        reqRecycler = view.findViewById(R.id.requests_recycler_id);
        Paper.init(view.getContext());


        int daysAgo = Paper.book().read(Permanent.days);

        if(daysAgo < 0){

            daysTxt.setText("No Donation History");
            daysAgoTxt.setVisibility(View.GONE);


        }else if(daysAgo==0){

            daysTxt.setText("Donated very recently");
            daysAgoTxt.setVisibility(View.GONE);

        }else{

            daysTxt.setText(String.valueOf(daysAgo));
            daysAgoTxt.setVisibility(View.VISIBLE);
        }

        if(daysAgo>=0 && daysAgo<92 ){
            donateAdviceTxt.setText("You can not donate now");
            donateAdviceTxt.setTextColor(getResources().getColor(R.color.light_red));

        }else{
            donateAdviceTxt.setText("You can donate now");
            donateAdviceTxt.setTextColor(getResources().getColor(R.color.light_green));

        }


        String bg = Paper.book().read(Permanent.bloodGrp);
        bloodGrpTxt.setText(bg);
        sameBloodTxt.setText(Paper.book().read(Permanent.sameBlood).toString());


        reqLists = new ArrayList<>();



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



        postList = new ArrayList<>();

        api.getPostFeed().enqueue(new Callback<List<GetPostFeed>>() {
            @Override
            public void onResponse(Call<List<GetPostFeed>> call, Response<List<GetPostFeed>> response) {
                if(response.isSuccessful()){

                    postList = response.body();
                    reqRecyclerAdapter = new RequestsRecyclerAdapter(postList,view.getContext());
                    reqRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
                    //reqRecycler.hasFixedSize();
                    reqRecycler.setAdapter(reqRecyclerAdapter);
                    reqRecyclerAdapter.notifyDataSetChanged();
                    Log.d("postresponse",response.body().toString());
                }else{
                    Toast.makeText(view.getContext(), response.code()+"00", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<GetPostFeed>> call, Throwable t) {
                Toast.makeText(view.getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();




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

                    int id = Paper.book().read(Permanent.uid);
                    post.setUserId(id);
                    post.setDetails(details);
                    post.setBloodGrp(bloodGrp);
                    post.setRelation(relation);
                    post.setPoliceStation(policeStation);
                    post.setHospital(hospital);
                    post.setDistrict(district);
                    post.setMobile("+88"+mobile);
                    post.setDate(date);
                    post.setTimeFrame(hours);
                    post.setStatus("pending");



                 api.writePost(post).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(response.isSuccessful()){


                                try {
                                    if(response.body().string().equals("posted")){
                                        loadingBar.dismiss();
                                        Toast.makeText(view.getContext(), "Posted Successfully", Toast.LENGTH_SHORT).show();
                                    }else{
                                        loadingBar.dismiss();
                                        Toast.makeText(view.getContext(), "Failed! Try again..", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (Exception e) {
                                    loadingBar.dismiss();
                                    e.printStackTrace();
                                }

                            }else {
                                Log.d("check",response.code()+" ");
                                loadingBar.dismiss();
                                Toast.makeText(view.getContext(), "Response not found!!", Toast.LENGTH_SHORT).show();


                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                            loadingBar.dismiss();
                            Toast.makeText(view.getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();


                        }
                    });

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