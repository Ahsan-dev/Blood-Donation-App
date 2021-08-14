package com.example.bloodbank.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("user_id")
    private Integer userId;
    @SerializedName("days")
    private Integer days;
    @SerializedName("same_blood")
    private Integer sameBlood;
    @SerializedName("blood_group")
    private String bloodGroup;
    @SerializedName("user_name")
    private String userName;
    @SerializedName("gender")
    private String gender;
    @SerializedName("image")
    private String image;
    @SerializedName("details")
    private String details;
    @SerializedName("police_station")
    private String policeStation;
    @SerializedName("district")
    private String district;

    public LoginResponse(Integer userId, Integer days, Integer sameBlood, String bloodGroup, String userName, String gender, String image, String details, String policeStation, String district) {
        super();
        this.userId = userId;
        this.days = days;
        this.sameBlood = sameBlood;
        this.bloodGroup = bloodGroup;
        this.userName = userName;
        this.gender = gender;
        this.image = image;
        this.details = details;
        this.policeStation = policeStation;
        this.district = district;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getSameBlood() {
        return sameBlood;
    }

    public void setSameBlood(Integer sameBlood) {
        this.sameBlood = sameBlood;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPoliceStation() {
        return policeStation;
    }

    public void setPoliceStation(String policeStation) {
        this.policeStation = policeStation;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
