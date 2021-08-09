package com.example.bloodbank;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bloodbank.Adapter.HistoryRecyclerAdapter;
import com.example.bloodbank.Models.HistoryModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment implements View.OnClickListener {

    private RecyclerView hisRecycler;
    private HistoryRecyclerAdapter hisResAdapter;
    private List<HistoryModel> donateList, serviceTakenList;
    private LinearLayout donateLinear, serviceTakenLinear;
    private TextView donateText, serviceTakenTxt;
    private View donateLine, serviceTakenLine;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        donateLinear = view.findViewById(R.id.history_fragment_donation_linear_id);
        donateText = view.findViewById(R.id.history_fragment_donation_text_id);
        donateLine = view.findViewById(R.id.history_fragment_donation_line_id);

        serviceTakenLinear = view.findViewById(R.id.history_fragment_service_taken_linear_id);
        serviceTakenTxt = view.findViewById(R.id.history_fragment_service_taken_text_id);
        serviceTakenLine = view.findViewById(R.id.history_fragment_service_taken_line_id);



        donateList = new ArrayList<>();
        serviceTakenList = new ArrayList<>();


        donateList.add(new HistoryModel("2021","January","17","Netrakona","Netrakona Sadar Hospital","Ö(Positive)","107"));
        donateList.add(new HistoryModel("2021","January","17","Netrakona","Netrakona Sadar Hospital","Ö(Positive)","107"));
        donateList.add(new HistoryModel("2021","January","17","Netrakona","Netrakona Sadar Hospital","Ö(Positive)","107"));

        serviceTakenList.add(new HistoryModel("2020","December","17","Netrakona","Netrakona Sadar Hospital","Ö(Positive)","107"));
        serviceTakenList.add(new HistoryModel("2020","Jun","17","Mymensingh","Mymensingh Medical College Hospital","Ö(Positive)","105"));
        serviceTakenList.add(new HistoryModel("2020","January","17","Netrakona","Netrakona Sadar Hospital","Ö(Positive)","107"));

        hisRecycler = view.findViewById(R.id.history_fragment_recycler_id);
        hisRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        hisResAdapter = new HistoryRecyclerAdapter(donateList,view.getContext());
        hisRecycler.setAdapter(hisResAdapter);

        donateLinear.setOnClickListener(this);
        serviceTakenLinear.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.history_fragment_donation_linear_id){

            donateText.setTextSize(20);
            donateText.setTextColor(getResources().getColor(R.color.pink));
            donateLine.setVisibility(View.VISIBLE);

            serviceTakenTxt.setTextSize(12);
            serviceTakenTxt.setTextColor(getResources().getColor(R.color.black));
            serviceTakenLine.setVisibility(View.GONE);

            hisResAdapter = new HistoryRecyclerAdapter(donateList,v.getContext());
            hisRecycler.setAdapter(hisResAdapter);



        }

        if(v.getId()==R.id.history_fragment_service_taken_linear_id){

            donateText.setTextSize(12);
            donateText.setTextColor(getResources().getColor(R.color.black));
            donateLine.setVisibility(View.GONE);

            serviceTakenTxt.setTextSize(20);
            serviceTakenTxt.setTextColor(getResources().getColor(R.color.pink));
            serviceTakenLine.setVisibility(View.VISIBLE);

            hisResAdapter = new HistoryRecyclerAdapter(serviceTakenList,v.getContext());
            hisRecycler.setAdapter(hisResAdapter);

        }

    }
}