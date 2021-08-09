package com.example.bloodbank;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloodbank.Adapter.ActivityLogAdapter;
import com.example.bloodbank.Models.ActivityLogModel;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    private RecyclerView actLogRecycler;
    private ActivityLogAdapter activityLogAdapter;
    private List<ActivityLogModel> actList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

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



        return view;

    }
}