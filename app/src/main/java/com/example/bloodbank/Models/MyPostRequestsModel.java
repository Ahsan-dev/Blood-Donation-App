package com.example.bloodbank.Models;

import com.google.gson.annotations.SerializedName;

public class MyPostRequestsModel {

    @SerializedName("id")

    private Integer id;
    @SerializedName("user_id")

    private Integer userId;
    @SerializedName("details")

    private String details;
    @SerializedName("blood_grp")

    private String bloodGrp;
    @SerializedName("relation")

    private String relation;
    @SerializedName("police_station")

    private String policeStation;
    @SerializedName("hospital")

    private String hospital;
    @SerializedName("district")

    private String district;
    @SerializedName("mobile")

    private String mobile;
    @SerializedName("date")

    private String date;
    @SerializedName("time_frame")

    private String timeFrame;
    @SerializedName("status")

    private String status;
    @SerializedName("created_at")

    private String createdAt;
    @SerializedName("updated_at")

    private String updatedAt;


    public MyPostRequestsModel() {
    }

    public MyPostRequestsModel(Integer id, Integer userId, String details, String bloodGrp, String relation, String policeStation, String hospital, String district, String mobile, String date, String timeFrame, String status, String createdAt, String updatedAt) {
        super();
        this.id = id;
        this.userId = userId;
        this.details = details;
        this.bloodGrp = bloodGrp;
        this.relation = relation;
        this.policeStation = policeStation;
        this.hospital = hospital;
        this.district = district;
        this.mobile = mobile;
        this.date = date;
        this.timeFrame = timeFrame;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getBloodGrp() {
        return bloodGrp;
    }

    public void setBloodGrp(String bloodGrp) {
        this.bloodGrp = bloodGrp;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getPoliceStation() {
        return policeStation;
    }

    public void setPoliceStation(String policeStation) {
        this.policeStation = policeStation;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(String timeFrame) {
        this.timeFrame = timeFrame;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
