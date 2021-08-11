package com.example.bloodbank.Models;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdminDateAssignModel {

    private int postId;
    private int donorImageResource;
    private String donorName, postDetails, donationDate;

    public AdminDateAssignModel(int postId, int donorImageResource, String donorName, String postDetails, String donationDate) {
        this.postId = postId;
        this.donorImageResource = donorImageResource;
        this.donorName = donorName;
        this.postDetails = postDetails;
        this.donationDate = donationDate;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getDonorImageResource() {
        return donorImageResource;
    }

    public void setDonorImageResource(int donorImageResource) {
        this.donorImageResource = donorImageResource;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getPostDetails() {
        return postDetails;
    }

    public void setPostDetails(String postDetails) {
        this.postDetails = postDetails;
    }

    public String getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(String donationDate) {
        this.donationDate = donationDate;
    }
}
