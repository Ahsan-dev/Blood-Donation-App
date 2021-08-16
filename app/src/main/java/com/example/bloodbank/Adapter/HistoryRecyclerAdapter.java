package com.example.bloodbank.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloodbank.Models.HistoryModel;
import com.example.bloodbank.Permanent;
import com.example.bloodbank.R;
import com.example.bloodbank.ViewHolder.HistoryViewHolder;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.paperdb.Paper;

public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryViewHolder> {

    private List<HistoryModel> hisList;
    private Context context;

    public HistoryRecyclerAdapter(List<HistoryModel> hisList, Context context) {
        this.hisList = hisList;
        this.context = context;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.history_items_layout,parent,false);
        return new HistoryViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {

        HistoryModel hisModel = hisList.get(position);

        holder.histYearTxt.setText(hisModel.getUpdatedAt().substring(0,4));

        int month = Integer.parseInt(hisModel.getUpdatedAt().substring(5,7));
        String mon = "";

                if(month==1) mon = "January";
                else if(month==2) mon = "February";
                else if(month==3) mon = "March";
                else if(month==4) mon = "Apeil";
                else if(month==5) mon = "May";
                else if(month==6) mon = "June";
                else if(month==7) mon = "July";
                else if(month==8) mon = "August";
                else if(month==9) mon = "September";
                else if(month==10) mon = "Otcober";
                else if(month==11) mon = "November";
                else if(month==12) mon = "December";

        holder.histMonthTxt.setText(mon);
        holder.histDateTxt.setText(hisModel.getUpdatedAt().substring(8,10));

        String loc = hisModel.getPoliceStation()+", "+hisModel.getDistrict();
        holder.histLocationTxt.setText(loc);
        holder.histHospitalTxt.setText(hisModel.getHospital());
        holder.histBloodGrpTxt.setText(hisModel.getBloodGrp());
        int days = 0;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        SimpleDateFormat sdf
                = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        long Hours = 0;

        try {
            Date d1 = sdf.parse(String.valueOf(timestamp));
            Date d2 = sdf.parse(hisModel.getUpdatedAt());

            long difference_In_Time = d2.getTime() - d1.getTime();
            long Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;
            days = (int) Days;

        } catch (ParseException e) {
            e.printStackTrace();
        }


        holder.histDaysAgoTxt.setText(days+" Days Ago");

    }

    @Override
    public int getItemCount() {
        return hisList.size();
    }
}
