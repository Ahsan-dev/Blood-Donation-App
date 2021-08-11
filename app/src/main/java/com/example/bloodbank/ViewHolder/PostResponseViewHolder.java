package com.example.bloodbank.ViewHolder;

import android.view.View;
import android.widget.TextView;

import com.example.bloodbank.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class PostResponseViewHolder extends RecyclerView.ViewHolder {

    public CircleImageView responseProPicImg;
    public TextView responseNameTxt, responseLocationTxt, responseMobileTxt, responseCallTxtBtn, responseDonationCompletionTxtBtn;

    public PostResponseViewHolder(@NonNull View itemView) {
        super(itemView);

        responseProPicImg = itemView.findViewById(R.id.post_response_item_profile_img_id);
        responseNameTxt = itemView.findViewById(R.id.post_response_item_profile_name_id);
        responseLocationTxt = itemView.findViewById(R.id.post_response_item_location_id);
        responseMobileTxt = itemView.findViewById(R.id.post_response_item_phn_nmbr_id);
        responseCallTxtBtn = itemView.findViewById(R.id.post_response_item_call_now_btn_id);
        responseDonationCompletionTxtBtn = itemView.findViewById(R.id.post_response_item_yes_im_btn_id);

    }
}
