package com.example.bloodbank.ViewHolder;

import android.view.View;
import android.widget.TextView;

import com.example.bloodbank.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryViewHolder extends RecyclerView.ViewHolder {

    public TextView histYearTxt, histMonthTxt, histDateTxt, histLocationTxt, histHospitalTxt, histBloodGrpTxt, histDaysAgoTxt;

    public HistoryViewHolder(@NonNull View itemView) {
        super(itemView);

        histYearTxt = itemView.findViewById(R.id.history_items_year_txt_id);
        histMonthTxt = itemView.findViewById(R.id.history_items_month_txt_id);
        histDateTxt = itemView.findViewById(R.id.history_items_date_txt_id);
        histLocationTxt = itemView.findViewById(R.id.history_items_location_txt_id);
        histHospitalTxt = itemView.findViewById(R.id.history_items_hospital_txt_id);
        histBloodGrpTxt = itemView.findViewById(R.id.history_items_blood_group_txt_id);
        histDaysAgoTxt = itemView.findViewById(R.id.history_items_times_ago_txt_id);

    }
}
