package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bloodbank.Adapter.MyPostReqRecyclerAdapter;
import com.example.bloodbank.Api.Api;
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
    private Api api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_posts);


        myPostToolbar = findViewById(R.id.my_all_post_toolbar_id);
        myPostBackBtn = findViewById(R.id.savings_account_back_button);
        setSupportActionBar(myPostToolbar);

        myPostBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        Paper.init(this);
        myPostsRecycler = findViewById(R.id.my_posts_recyclerview);
        myPostReqList = new ArrayList<>();

        Call<List<MyPostRequestsModel>> call = api.getMyPosts(Paper.book().read(Permanent.uid));

        call.enqueue(new Callback<List<MyPostRequestsModel>>() {
            @Override
            public void onResponse(Call<List<MyPostRequestsModel>> call, Response<List<MyPostRequestsModel>> response) {

                if(response.body()!=null){

                    myPostReqList = response.body();

                }else {

                    Toast.makeText(MyPostsActivity.this, "No response found", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<List<MyPostRequestsModel>> call, Throwable t) {

                Toast.makeText(MyPostsActivity.this, t.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        myPostAdapter = new MyPostReqRecyclerAdapter(myPostReqList,this);

        myPostsRecycler.setLayoutManager(new LinearLayoutManager(this));
        myPostsRecycler.setAdapter(myPostAdapter);
    }
}