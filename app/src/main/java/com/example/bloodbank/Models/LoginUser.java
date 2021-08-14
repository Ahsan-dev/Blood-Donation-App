package com.example.bloodbank.Models;

import com.google.gson.annotations.SerializedName;

public class LoginUser {

    @SerializedName("mobile")

    private String mobile;
    @SerializedName("password")

    private String password;

    /**
     * No args constructor for use in serialization
     *
     */
    public LoginUser() {
    }

    public LoginUser(String mobile, String password) {
        super();
        this.mobile = mobile;
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
