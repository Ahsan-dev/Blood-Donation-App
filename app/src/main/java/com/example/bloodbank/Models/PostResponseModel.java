package com.example.bloodbank.Models;

import com.google.gson.annotations.SerializedName;

public class PostResponseModel {

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
    @SerializedName("user_name")

    private String userName;
    @SerializedName("image")

    private String image;
    @SerializedName("police_station")

    private String policeStation;
    @SerializedName("district")

    private String district;
    @SerializedName("mobile")

    private String mobile;


    public PostResponseModel() {
    }


    public PostResponseModel(Integer id, Integer postId, Integer userId, String status, String admin, String createdAt, String updatedAt, String userName, String image, String policeStation, String district, String mobile) {
        super();
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.status = status;
        this.admin = admin;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userName = userName;
        this.image = image;
        this.policeStation = policeStation;
        this.district = district;
        this.mobile = mobile;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
