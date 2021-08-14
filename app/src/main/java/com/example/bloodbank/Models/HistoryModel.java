package com.example.bloodbank.Models;

import com.google.gson.annotations.SerializedName;

public class HistoryModel {

    @SerializedName("id")

    private Integer id;
    @SerializedName("post_id")

    private Integer postId;
    @SerializedName("user_id")

    private Integer userId;
    @SerializedName("status")

    private String status;
    @SerializedName("admin")

    private String admin;
    @SerializedName("created_at")

    private String createdAt;
    @SerializedName("updated_at")

    private String updatedAt;
    @SerializedName("police_station")

    private String policeStation;
    @SerializedName("district")

    private String district;
    @SerializedName("hospital")

    private String hospital;
    @SerializedName("blood_grp")

    private String bloodGrp;

    /**
     * No args constructor for use in serialization
     *
     */
    public HistoryModel() {
    }

    /**
     *
     * @param policeStation
     * @param createdAt
     * @param bloodGrp
     * @param district
     * @param admin
     * @param id
     * @param postId
     * @param hospital
     * @param userId
     * @param status
     * @param updatedAt
     */
    public HistoryModel(Integer id, Integer postId, Integer userId, String status, String admin, String createdAt, String updatedAt, String policeStation, String district, String hospital, String bloodGrp) {
        super();
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.status = status;
        this.admin = admin;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.policeStation = policeStation;
        this.district = district;
        this.hospital = hospital;
        this.bloodGrp = bloodGrp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
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

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getBloodGrp() {
        return bloodGrp;
    }

    public void setBloodGrp(String bloodGrp) {
        this.bloodGrp = bloodGrp;
    }
}
