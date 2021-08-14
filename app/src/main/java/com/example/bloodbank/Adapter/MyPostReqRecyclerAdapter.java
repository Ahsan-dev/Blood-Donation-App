package com.example.bloodbank.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodbank.Api.Api;
import com.example.bloodbank.Models.MyPostRequestsModel;
import com.example.bloodbank.Models.PostResponseModel;
import com.example.bloodbank.R;
import com.example.bloodbank.ViewHolder.MyPostReqViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPostReqRecyclerAdapter extends RecyclerView.Adapter<MyPostReqViewHolder> {

    private Api api;
    private int postId;
    private List<PostResponseModel> prList;

    private List<MyPostRequestsModel> myReqList;
    private Context context;

    public MyPostReqRecyclerAdapter(List<MyPostRequestsModel> myReqList, Context context) {
        this.myReqList = myReqList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyPostReqViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.my_posts_request_item_layout,parent,false);
        return new MyPostReqViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyPostReqViewHolder holder, int position) {

        MyPostRequestsModel myPostModel = myReqList.get(position);

        holder.postDetailTxt.setText(myPostModel.getDetails());
        String loc = myPostModel.getPoliceStation()+", "+myPostModel.getDistrict();
        holder.postLocationTxt.setText(loc);
        holder.postHospitalTxt.setText(myPostModel.getHospital());
        holder.postBloodGrpTxt.setText(myPostModel.getBloodGrp());

        postId = myPostModel.getId();

        String status = myPostModel.getStatus();
        if(status.equals("donated")){
            holder.postStatusTxt.setText(status);
            holder.postStatusTxt.setTextColor(context.getResources().getColor(R.color.green));
        }else{

            holder.postStatusTxt.setText(status);
            holder.postStatusTxt.setTextColor(context.getResources().getColor(R.color.yellow));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showInAlertDialog();

            }
        });


    }

    @Override
    public int getItemCount() {
        return myReqList.size();
    }

    private void showInAlertDialog() {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setCancelable(false);
        dialog.setContentView(R.layout.post_response_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

         prList = new ArrayList<>();

        Call<List<PostResponseModel>> call = api.getMyPostAcceptors(postId);

        call.enqueue(new Callback<List<PostResponseModel>>() {
            @Override
            public void onResponse(Call<List<PostResponseModel>> call, Response<List<PostResponseModel>> response) {
                if(response.body()!=null){

                    prList = response.body();

                }else{

                    Toast.makeText(context, "No response found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<PostResponseModel>> call, Throwable t) {

                Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();

            }
        });


        ImageView cancelBtn = dialog.findViewById(R.id.post_response_cancel_btn_id);
        RecyclerView postRespRecycler = dialog.findViewById(R.id.post_response_recycler_view_id);
        TextView DonateCompltTxtBtn = dialog.findViewById(R.id.post_response_donation_complt_btn_id);
        TextView deleteTxtBtn = dialog.findViewById(R.id.post_response_delete_post_btn_id);
        PostResponseRecyclerAdapter postResponseRecyclerAdapter = new PostResponseRecyclerAdapter(prList,context);
        postRespRecycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        postRespRecycler.setAdapter(postResponseRecyclerAdapter);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;

        DonateCompltTxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<ResponseBody> call = api.donationCompletePost(postId,"donated");

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.body()!=null){

                            if(response.body().toString().equals("donated")){

                                DonateCompltTxtBtn.setEnabled(false);
                            }

                            Toast.makeText(context, response.body().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                        Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);

    }
}
