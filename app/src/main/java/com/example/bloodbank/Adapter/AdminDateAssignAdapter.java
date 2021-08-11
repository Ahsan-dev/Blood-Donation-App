package com.example.bloodbank.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloodbank.Models.AdminDateAssignModel;
import com.example.bloodbank.Models.PostResponseModel;
import com.example.bloodbank.R;
import com.example.bloodbank.ViewHolder.AdminAssignDateViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdminDateAssignAdapter extends RecyclerView.Adapter<AdminAssignDateViewHolder> {


    private List<AdminDateAssignModel> dateAssignList;
    private Context context;

    public AdminDateAssignAdapter(List<AdminDateAssignModel> dateAssignList, Context context) {
        this.dateAssignList = dateAssignList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdminAssignDateViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.admid_assign_date_item_layout,parent,false);
        return new AdminAssignDateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminAssignDateViewHolder holder, int position) {

        AdminDateAssignModel dateAssignModel = dateAssignList.get(position);

        holder.donorDetailsText.setText(dateAssignModel.getPostDetails());
        Picasso.get().load(dateAssignModel.getDonorImageResource());
        holder.donorNameTxt.setText(dateAssignModel.getDonorName());
        holder.donorDonateDateTxt.setText(dateAssignModel.getDonationDate());

        holder.assignDateTxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = holder.donateDateEdt.getText().toString();
            }
        });


    }

    @Override
    public int getItemCount() {
        return dateAssignList.size();
    }
}
