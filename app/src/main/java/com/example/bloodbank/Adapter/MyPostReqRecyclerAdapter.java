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

public class MyPostReqRecyclerAdapter extends RecyclerView.Adapter<MyPostReqViewHolder> {

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

        holder.postDetailTxt.setText(myPostModel.getPostDetails());
        holder.postLocationTxt.setText(myPostModel.getPostLocation());
        holder.postHospitalTxt.setText(myPostModel.getPostHospital());
        holder.postBloodGrpTxt.setText(myPostModel.getPostBloodGrp());

        String status = myPostModel.getPostStatus();
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

        List<PostResponseModel> prList = new ArrayList<>();
        prList.add(new PostResponseModel(R.drawable.ahsan_job,"Md Ahsanul Haque","Kalmakanda, Netrakona","01775794472"));
        prList.add(new PostResponseModel(R.drawable.ahsan_job,"Md Monir Hossain","Kalmakanda, Netrakona","01775794472"));
        prList.add(new PostResponseModel(R.drawable.ahsan_job,"Md Haque","Kalmakanda, Netrakona","01775794472"));

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
