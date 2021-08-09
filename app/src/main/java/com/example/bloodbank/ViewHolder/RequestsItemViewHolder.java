package com.example.bloodbank.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bloodbank.Models.RequestsItemsModel;
import com.example.bloodbank.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RequestsItemViewHolder extends RecyclerView.ViewHolder {

    public ImageView reqProPicImg, reqEmergencyImg;
    public TextView reqProNameTxt, reqBloodGrpTxt, reqLocationTxt, reqHospitalTxt, reqDetailsTxt, reqPhnNmbrTxt, reqCallNowTxtBtn,reqYesImBtn;
    public View reqWhiteLine;
    public LinearLayout visibleLinear;

    public RequestsItemViewHolder(@NonNull View itemView) {
        super(itemView);

        reqProPicImg = itemView.findViewById(R.id.requests_item_profile_img_id);
        reqEmergencyImg = itemView.findViewById(R.id.requests_item_emergency_img_id);
        reqProNameTxt = itemView.findViewById(R.id.requests_item_profile_name_id);
        reqBloodGrpTxt = itemView.findViewById(R.id.requests_item_blood_grp_id);
        reqLocationTxt = itemView.findViewById(R.id.requests_item_location_id);
        reqHospitalTxt = itemView.findViewById(R.id.requests_item_hospital_id);
        reqDetailsTxt = itemView.findViewById(R.id.requests_item_details_id);
        reqPhnNmbrTxt = itemView.findViewById(R.id.requests_item_phn_nmbr_id);
        reqCallNowTxtBtn = itemView.findViewById(R.id.requests_item_call_now_btn_id);
        reqYesImBtn = itemView.findViewById(R.id.requests_item_yes_im_btn_id);
        reqWhiteLine = itemView.findViewById(R.id.requests_item_white_line_id);
        visibleLinear = itemView.findViewById(R.id.requests_item_visible_details_id);


        visibleLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
