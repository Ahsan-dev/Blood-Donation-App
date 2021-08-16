package com.example.bloodbank;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodbank.Adapter.ActivityLogAdapter;
import com.example.bloodbank.Adapter.HistoryRecyclerAdapter;
import com.example.bloodbank.Api.Api;
import com.example.bloodbank.Models.ActivityLogModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    private RecyclerView actLogRecycler;
    private ActivityLogAdapter activityLogAdapter;
    private List<ActivityLogModel> actList;
    private TextView logoutBtn;
    private TextView nameTxt, detailTxt, locationTxt, bloodGrpTxt;
    private ImageView proPicImg;
    private  Api api;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        api = RetroClient.getClient().create(Api.class);

        nameTxt = view.findViewById(R.id.profile_fragment_profile_name_id);
        detailTxt = view.findViewById(R.id.profile_fragment_details_id);
        locationTxt = view.findViewById(R.id.profile_fragment_location_id);
        bloodGrpTxt = view.findViewById(R.id.profile_fragment_blood_grp_txt_id);
        proPicImg = view.findViewById(R.id.profile_fragment_profile_img_id);

        Paper.init(view.getContext());

        proPicImg.setImageURI(Uri.fromFile(new File((String) Paper.book().read(Permanent.image))));

        nameTxt.setText(Paper.book().read(Permanent.userName));
        detailTxt.setText(Paper.book().read(Permanent.detailsAbout));
        String loc = Paper.book().read(Permanent.policeStation)+", "+Paper.book().read(Permanent.district);
        locationTxt.setText(loc);
        bloodGrpTxt.setText(Paper.book().read(Permanent.bloodGrp));

        Paper.init(view.getContext());
        logoutBtn = view.findViewById(R.id.profile_fragment_logout_btn_id);
        actLogRecycler = view.findViewById(R.id.profile_fragment_activity_log_recycler_id);
        actList = new ArrayList<>();

        int id = Paper.book().read(Permanent.uid);

        api.getActivity(id).enqueue(new Callback<List<ActivityLogModel>>() {
            @Override
            public void onResponse(Call<List<ActivityLogModel>> call, Response<List<ActivityLogModel>> response) {
                if(response.isSuccessful()){

                    actList = response.body();
                    Toast.makeText(view.getContext(), "Size:"+actList.size(), Toast.LENGTH_SHORT).show();
                    activityLogAdapter = new ActivityLogAdapter(actList,view.getContext());
                    actLogRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
                    actLogRecycler.hasFixedSize();
                    actLogRecycler.setAdapter(activityLogAdapter);
                    activityLogAdapter.notifyDataSetChanged();


                }else {

                    Toast.makeText(view.getContext(), "Response not found!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ActivityLogModel>> call, Throwable t) {

                Toast.makeText(view.getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });



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