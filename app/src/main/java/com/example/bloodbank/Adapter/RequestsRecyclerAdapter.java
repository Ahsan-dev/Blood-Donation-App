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
import android.widget.Toast;

import com.example.bloodbank.Api.Api;
import com.example.bloodbank.Models.GetPostFeed;
import com.example.bloodbank.Models.RequestsItemsModel;
import com.example.bloodbank.Permanent;
import com.example.bloodbank.R;
import com.example.bloodbank.ViewHolder.RequestsItemViewHolder;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import io.paperdb.Paper;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestsRecyclerAdapter extends RecyclerView.Adapter<RequestsItemViewHolder> {

    private Api api;
    private int postId;

    private List<GetPostFeed> reqList;
    private Context context;

    public RequestsRecyclerAdapter(List<GetPostFeed> reqList, Context context) {
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

        GetPostFeed reqModel = reqList.get(position);

        holder.reqProPicImg.setImageURI(Uri.fromFile(new File(reqModel.getImage())));
        holder.reqProNameTxt.setText(reqModel.getUserName());
        holder.reqBloodGrpTxt.setText(reqModel.getBloodGrp());
        String loc = reqModel.getPoliceStation()+", "+reqModel.getDistrict();
        holder.reqLocationTxt.setText(loc);
        holder.reqHospitalTxt.setText(reqModel.getHospital());
        holder.reqDetailsTxt.setText(reqModel.getDetails());
        holder.reqPhnNmbrTxt.setText(reqModel.getMobile());


        Paper.init(context);
        int days = Paper.book().read(Permanent.days);

        if(days<92){

            holder.reqYesImBtn.setEnabled(false);
        }else {
            holder.reqYesImBtn.setEnabled(true);
        }

        String hours = reqModel.getTimeFrame();
        int hour = Integer.parseInt(hours);
        holder.reqEmergencyImg.setVisibility(hour<=24? View.VISIBLE:View.GONE);
        holder.reqWhiteLine.setBackgroundResource(hour<=24? R.color.white: R.color.light_gray);

        postId = reqModel.getId();

        CardView card = (CardView) holder.itemView;
        card.setCardBackgroundColor(hour<=24? context.getResources().getColor(R.color.light_light_red):context.getResources().getColor(R.color.white));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.visibleLinear.getVisibility()==View.VISIBLE){

                    holder.visibleLinear.setVisibility(View.GONE);
                }else
                    holder.visibleLinear.setVisibility(View.VISIBLE);
            }
        });
        String phn = "tel:"+reqModel.getMobile();
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

        holder.reqYesImBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Paper.init(v.getContext());
                Call<ResponseBody> call = api.acceptPost(postId, Paper.book().read(Permanent.uid),"accepted","");

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response!=null){

                            if(response.body().toString().equals("accepted")){

                                holder.reqYesImBtn.setEnabled(false);
                            }
                            Toast.makeText(v.getContext(), "Response Not Found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                        Toast.makeText(v.getContext(), t.toString(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });


    }


    @Override
    public int getItemCount() {
        return reqList.size();
    }
}
