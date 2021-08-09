package com.example.bloodbank.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloodbank.Models.ActivityLogModel;
import com.example.bloodbank.R;
import com.example.bloodbank.ViewHolder.ActivityLogViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ActivityLogAdapter extends RecyclerView.Adapter<ActivityLogViewHolder> {

    private List<ActivityLogModel> actLogList;
    private Context context;

    public ActivityLogAdapter(List<ActivityLogModel> actLogList, Context context) {
        this.actLogList = actLogList;
        this.context = context;
    }

    @NonNull
    @Override
    public ActivityLogViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.activity_log_items_layout,parent,false);
        return new ActivityLogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityLogViewHolder holder, int position) {

        ActivityLogModel actModel = actLogList.get(position);

        holder.activityLogDateTxt.setText(actModel.getDate());
        holder.activityLogTimeTxt.setText(actModel.getTime());
        holder.activityLogActionTxt.setText(actModel.getAction());

    }

    @Override
    public int getItemCount() {
        return actLogList.size();
    }
}
