package com.example.bloodbank.ViewHolder;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bloodbank.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class AdminAssignDateViewHolder extends RecyclerView.ViewHolder {

    public CircleImageView donorImg;
    public TextView donorNameTxt, donorDetailsText, donorDonateDateTxt;
    public EditText donateDateEdt;
    public TextView assignDateTxtBtn;

    public AdminAssignDateViewHolder(@NonNull View itemView) {
        super(itemView);

        donorImg = itemView.findViewById(R.id.admin_date_assign_item_profile_img_id);
        donorNameTxt = itemView.findViewById(R.id.admin_date_assign_item_profile_name_id);
        donorDetailsText = itemView.findViewById(R.id.admin_date_assign_item_details_id);
        donorDonateDateTxt = itemView.findViewById(R.id.admin_date_assign_item_date_id);
        donateDateEdt = itemView.findViewById(R.id.admin_date_assign_item_date_edit_id);
        assignDateTxtBtn = itemView.findViewById(R.id.admin_date_assign_item_assign_txt_btn_id);


    }
}
