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
import com.example.bloodbank.Models.PostResponseModel;
import com.example.bloodbank.R;
import com.example.bloodbank.ViewHolder.PostResponseViewHolder;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostResponseRecyclerAdapter extends RecyclerView.Adapter<PostResponseViewHolder> {

    private Api api;
    private int donateId;

    private List<PostResponseModel> postResponseList;
    private Context context;

    public PostResponseRecyclerAdapter(List<PostResponseModel> postResponseList, Context context) {
        this.postResponseList = postResponseList;
        this.context = context;
    }

    @NonNull
    @Override
    public PostResponseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.post_response_item_layout,parent,false);
        return new PostResponseViewHolder(view);

    }

    @Override
    public void onBindViewHolder( PostResponseViewHolder holder, int position) {

        PostResponseModel prModel = postResponseList.get(position);

        holder.responseProPicImg.setImageURI(Uri.fromFile(new File(prModel.getImage())));
        holder.responseNameTxt.setText(prModel.getUserName());
        holder.responseMobileTxt.setText(prModel.getMobile());

        String phn = "tel:"+prModel.getMobile();
        holder.responseCallTxtBtn.setOnClickListener(new View.OnClickListener() {
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

        holder.responseDonationCompletionTxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<ResponseBody> call = api.donateConfirm(donateId,"donated");

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response!=null){
                            if(response.body().toString().equals("donated")){

                                holder.responseDonationCompletionTxtBtn.setEnabled(false);
                            }

                            Toast.makeText(context, response.body().toString(), Toast.LENGTH_SHORT).show();

                        }else{

                            Toast.makeText(context, "No Response", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });



            }


        });


    }

    @Override
    public int getItemCount() {
        return postResponseList.size();
    }
}
