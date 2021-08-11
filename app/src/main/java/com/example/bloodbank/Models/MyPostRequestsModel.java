package com.example.bloodbank.Models;

public class MyPostRequestsModel {

    private int postReqId;
    private String postDetails, postLocation, postHospital, postBloodGrp, postStatus;

    public MyPostRequestsModel(int postReqId, String postDetails, String postLocation, String postHospital, String postBloodGrp, String postStatus) {
        this.postReqId = postReqId;
        this.postDetails = postDetails;
        this.postLocation = postLocation;
        this.postHospital = postHospital;
        this.postBloodGrp = postBloodGrp;
        this.postStatus = postStatus;
    }

    public int getPostReqId() {
        return postReqId;
    }

    public void setPostReqId(int postReqId) {
        this.postReqId = postReqId;
    }

    public String getPostDetails() {
        return postDetails;
    }

    public void setPostDetails(String postDetails) {
        this.postDetails = postDetails;
    }

    public String getPostLocation() {
        return postLocation;
    }

    public void setPostLocation(String postLocation) {
        this.postLocation = postLocation;
    }

    public String getPostHospital() {
        return postHospital;
    }

    public void setPostHospital(String postHospital) {
        this.postHospital = postHospital;
    }

    public String getPostBloodGrp() {
        return postBloodGrp;
    }

    public void setPostBloodGrp(String postBloodGrp) {
        this.postBloodGrp = postBloodGrp;
    }

    public String getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(String postStatus) {
        this.postStatus = postStatus;
    }
}
