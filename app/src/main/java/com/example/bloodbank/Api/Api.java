package com.example.bloodbank.Api;

import com.example.bloodbank.Models.AdminDateAssignModel;
import com.example.bloodbank.Models.GetPostFeed;
import com.example.bloodbank.Models.HistoryModel;
import com.example.bloodbank.Models.LoginResponse;
import com.example.bloodbank.Models.LoginUser;
import com.example.bloodbank.Models.MyPostRequestsModel;
import com.example.bloodbank.Models.PostResponseModel;
import com.example.bloodbank.Models.WritePost;


import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("register")
    Call<ResponseBody> insertUser(

            @Field("user_name") String user_name,
            @Field("mobile") String mobile,
            @Field("alt_mobile") String alt_mobile,
            @Field("email") String email,
            @Field("blood_group") String blood_group,
            @Field("religion") String religion,
            @Field("gender") String gender,
            @Field("division") String division,
            @Field("district") String district,
            @Field("police_station") String police_station,
            @Field("weight") String weight,
            @Field("birth_date") String birth_date,
            @Field("image") String image,
            @Field("details") String details,
            @Field("password") String password

            //Callback<ResponseBody> callback

    );


    @POST("login")
    Call<LoginResponse> loginUser(@Body LoginUser userCredentials);


    @GET("getpostfeed")
    Call<List<GetPostFeed>> getPostFeed();

    @POST("writepost")
    Call<ResponseBody> writePost(@Body WritePost postRequired);

    @POST("getmyposts")
    Call<List<MyPostRequestsModel>> getMyPosts(

            @Field("id") int id

    );

    @POST("getmyacceptors")
    Call<List<PostResponseModel>> getMyPostAcceptors(

            @Field("post_id") int post_id

    );

    @POST("donationcomplete")
    Call<ResponseBody> donationCompletePost(
            @Field("post_id") int post_id,
            @Field("status") String status
            );

    @POST("getdonationhistory")
    Call<List<HistoryModel>> getDonationHistory(

            @Field("user_id") int user_id

    );

    @POST("getservicetakenhistory")
    Call<List<HistoryModel>> getServiceTakenHistory(

            @Field("user_id") int user_id

    );

    @GET("postsadmintosign")
    Call<List<AdminDateAssignModel>> postsAdminToSign();

    @POST("admindateassign")
    Call<ResponseBody> adminDateAssign(
            @Field("donate_id") int donate_id,
            @Field("date") String date
    );

    @POST("donateconfirm")
    Call<ResponseBody> donateConfirm(
            @Field("donate_id") int donate_id,
            @Field("status") String status
    );

    @POST("acceptpost")
    Call<ResponseBody> acceptPost(
            @Field("post_id") int post_id,
            @Field("user_id") int user_id,
            @Field("status") String status,
            @Field("admin") String admin

    );




}
