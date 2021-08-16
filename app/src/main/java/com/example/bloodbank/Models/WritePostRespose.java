package com.example.bloodbank.Models;

import com.google.gson.annotations.SerializedName;

public class WritePostRespose {

    @SerializedName("status")

    private String status;

    /**
     * No args constructor for use in serialization
     *
     */
    public WritePostRespose() {
    }

    public WritePostRespose(String status) {
        super();
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
