package com.example.bloodbank.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloodbank.Models.HistoryModel;
import com.example.bloodbank.R;
import com.example.bloodbank.ViewHolder.HistoryViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

        holder.histYearTxt.setText(hisModel.getYear());
        holder.histMonthTxt.setText(hisModel.getMonth());
        holder.histDateTxt.setText(hisModel.getDate());
        holder.histLocationTxt.setText(hisModel.getLocation());
        holder.histHospitalTxt.setText(hisModel.getHospital());
        holder.histBloodGrpTxt.setText(hisModel.getBloodGrp());
        holder.histDaysAgoTxt.setText(hisModel.getDaysAgo()+" Days Ago");

    }

    @Override
    public int getItemCount() {
        return hisList.size();
    }
}
