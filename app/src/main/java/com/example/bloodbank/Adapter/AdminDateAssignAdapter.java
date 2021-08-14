package com.example.bloodbank.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bloodbank.Api.Api;
import com.example.bloodbank.Models.AdminDateAssignModel;
import com.example.bloodbank.Models.PostResponseModel;
import com.example.bloodbank.R;
import com.example.bloodbank.ViewHolder.AdminAssignDateViewHolder;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminDateAssignAdapter extends RecyclerView.Adapter<AdminAssignDateViewHolder> {

    private Api api;
    private int donateId;


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

        holder.donorDetailsText.setText(dateAssignModel.getDetails());
        holder.donorImg.setImageURI(Uri.fromFile(new File(dateAssignModel.getImage())));
        holder.donorNameTxt.setText(dateAssignModel.getUserName());
        holder.donorDonateDateTxt.setText(dateAssignModel.getDate());

        donateId = dateAssignModel.getId();

        holder.assignDateTxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = holder.donateDateEdt.getText().toString();

                Call<ResponseBody> call = api.adminDateAssign(donateId,date);

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response!=null){
                            Toast.makeText(context, response.body().toString(), Toast.LENGTH_SHORT).show();
                        }else {

                            Toast.makeText(context, "Response Not Found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                        Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });


    }

    @Override
    public int getItemCount() {
        return dateAssignList.size();
    }
}
