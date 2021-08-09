package com.example.bloodbank.ViewHolder;

import android.view.View;
import android.widget.TextView;

import com.example.bloodbank.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ActivityLogViewHolder extends RecyclerView.ViewHolder {

    public TextView activityLogDateTxt, activityLogTimeTxt, activityLogActionTxt;

    public ActivityLogViewHolder(@NonNull View itemView) {
        super(itemView);

        activityLogDateTxt = itemView.findViewById(R.id.activity_log_date_txt_id);
        activityLogTimeTxt = itemView.findViewById(R.id.activity_log_time_txt_id);
        activityLogActionTxt = itemView.findViewById(R.id.activity_log_action_txt_id);

    }
}
