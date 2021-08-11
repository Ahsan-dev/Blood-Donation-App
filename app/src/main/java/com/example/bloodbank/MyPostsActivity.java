package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.bloodbank.Adapter.MyPostReqRecyclerAdapter;
import com.example.bloodbank.Models.MyPostRequestsModel;

import java.util.ArrayList;
import java.util.List;

public class MyPostsActivity extends AppCompatActivity {

    private RecyclerView myPostsRecycler;
    private MyPostReqRecyclerAdapter myPostAdapter;
    private List<MyPostRequestsModel> myPostReqList;
    private String details = "";
    private Toolbar myPostToolbar;
    private ImageView myPostBackBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_posts);

        myPostsRecycler = findViewById(R.id.my_posts_recyclerview);
        myPostToolbar = findViewById(R.id.my_all_post_toolbar_id);
        myPostBackBtn = findViewById(R.id.savings_account_back_button);
        setSupportActionBar(myPostToolbar);

        myPostBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        myPostReqList = new ArrayList<>();




        myPostReqList.add(new MyPostRequestsModel(1,getString(R.string.details_text),"Sankipara, Mymensingh", "Mymensingh Medical College Hospital","O(Positive)","Pending"));
        myPostReqList.add(new MyPostRequestsModel(2,getString(R.string.details_text),"Charpara, Mymensingh", "Mymensingh Medical College Hospital","B(Positive)","donated"));
        myPostReqList.add(new MyPostRequestsModel(3,getString(R.string.details_text),"Charpara, Mymensingh", "Mymensingh Medical College Hospital","AB(Positive)","pending"));
        myPostReqList.add(new MyPostRequestsModel(4,getString(R.string.details_text),"Kalmakanda, Netrakona", "Netrakona Sadar Hospital","O(Positive)","Pending"));
        myPostReqList.add(new MyPostRequestsModel(5,getString(R.string.details_text),"Charpara, Mymensingh", "Mymensingh Medical College Hospital","B(Positive)","donated"));
        myPostReqList.add(new MyPostRequestsModel(6,getString(R.string.details_text),"Charpara, Mymensingh", "Mymensingh Medical College Hospital","AB(Positive)","pending"));

        myPostAdapter = new MyPostReqRecyclerAdapter(myPostReqList,this);

        myPostsRecycler.setLayoutManager(new LinearLayoutManager(this));
        myPostsRecycler.setAdapter(myPostAdapter);


    }
}