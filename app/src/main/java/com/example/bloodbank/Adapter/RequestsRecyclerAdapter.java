package com.example.bloodbank.Adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloodbank.Models.RequestsItemsModel;
import com.example.bloodbank.R;
import com.example.bloodbank.ViewHolder.RequestsItemViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class RequestsRecyclerAdapter extends RecyclerView.Adapter<RequestsItemViewHolder> {

    private List<RequestsItemsModel> reqList;
    private Context context;

    public RequestsRecyclerAdapter(List<RequestsItemsModel> reqList, Context context) {
        this.reqList = reqList;
        this.context = context;
    }

    @NonNull
    @Override
    public RequestsItemViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.requests_item_layout,parent,false);
        return new RequestsItemViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull  RequestsItemViewHolder holder, int position) {

        RequestsItemsModel reqModel = reqList.get(position);

        Picasso.get().load(reqModel.getProPicResource()).into(holder.reqProPicImg);
        holder.reqProNameTxt.setText(reqModel.getProName());
        holder.reqBloodGrpTxt.setText(reqModel.getBloodGrp());
        holder.reqLocationTxt.setText(reqModel.getLocation());
        holder.reqHospitalTxt.setText(reqModel.getHospital());
        holder.reqDetailsTxt.setText(reqModel.getDetails());
        holder.reqPhnNmbrTxt.setText(reqModel.getPhnNumbr());
        holder.reqEmergencyImg.setVisibility(reqModel.isEmergency()? View.VISIBLE:View.GONE);
        holder.reqWhiteLine.setBackgroundResource(reqModel.isEmergency()? R.color.white: R.color.light_gray);

        CardView card = (CardView) holder.itemView;
        card.setCardBackgroundColor(reqModel.isEmergency()? context.getResources().getColor(R.color.light_light_red):context.getResources().getColor(R.color.white));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.visibleLinear.getVisibility()==View.VISIBLE){

                    holder.visibleLinear.setVisibility(View.GONE);
                }else
                    holder.visibleLinear.setVisibility(View.VISIBLE);
            }
        });
        String phn = "tel:"+reqModel.getPhnNumbr();
        holder.reqCallNowTxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE);

                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            (Activity) context,
                            new String[]{Manifest.permission.CALL_PHONE},
                            1);
                } else {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse(phn));
                    v.getContext().startActivity(callIntent);
                }
            }
        });


    }


    @Override
    public int getItemCount() {
        return reqList.size();
    }
}
