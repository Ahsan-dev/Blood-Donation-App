package com.example.bloodbank.ViewHolder;

import android.view.View;
import android.widget.TextView;

import com.example.bloodbank.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyPostReqViewHolder extends RecyclerView.ViewHolder {

    public TextView postDetailTxt, postLocationTxt, postHospitalTxt, postBloodGrpTxt, postStatusTxt;

    public MyPostReqViewHolder(@NonNull View itemView) {
        super(itemView);

        postDetailTxt = itemView.findViewById(R.id.my_post_requests_item_details_id);
        postLocationTxt = itemView.findViewById(R.id.my_post_requests_item_location_id);
        postHospitalTxt = itemView.findViewById(R.id.my_post_requests_item_hospital_id);
        postBloodGrpTxt = itemView.findViewById(R.id.my_post_requests_item_blood_grp_id);
        postStatusTxt = itemView.findViewById(R.id.my_post_requests_item_blood_donation_status_id);
    }
}
