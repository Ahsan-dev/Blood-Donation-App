package com.example.bloodbank;

import android.content.Context;

import com.example.bloodbank.Api.Api;
import com.example.bloodbank.NatworkTool.NetworkConnectionInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {

    private static final String BASE_URL = "http://192.168.43.86/BloodDonate/BloodDonor/public/";
    private static RetroClient mInstance;
    private static Retrofit retrofit;

    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();



    private RetroClient(){

    }



    public static Retrofit getClient(){


        if(retrofit==null){

            synchronized (RetroClient.class){
                if(retrofit==null){
                    retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();

                }
            }
        }
        return retrofit;
    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }


}
