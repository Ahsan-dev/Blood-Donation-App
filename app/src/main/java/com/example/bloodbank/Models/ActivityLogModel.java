package com.example.bloodbank.Models;

import com.google.gson.annotations.SerializedName;

public class ActivityLogModel {

    @SerializedName("id")

    private Integer id;
    @SerializedName("user_id")

    private Integer userId;
    @SerializedName("type")

    private String type;
    @SerializedName("details")

    private String details;
    @SerializedName("created_at")

    private String createdAt;
    @SerializedName("updated_at")

    private String updatedAt;

    /**
     * No args constructor for use in serialization
     *
     */
    public ActivityLogModel() {
    }

    public ActivityLogModel(Integer id, Integer userId, String type, String details, String createdAt, String updatedAt) {
        super();
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.details = details;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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
