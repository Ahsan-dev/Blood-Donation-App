package com.example.bloodbank.Models;

public class PostResponseModel {

    private int imageResource;
    private String responserName, responserLocation, responserMobile;

    public PostResponseModel(int imageResource, String responserName, String responserLocation, String responserMobile) {
        this.imageResource = imageResource;
        this.responserName = responserName;
        this.responserLocation = responserLocation;
        this.responserMobile = responserMobile;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getResponserName() {
        return responserName;
    }

    public void setResponserName(String responserName) {
        this.responserName = responserName;
    }

    public String getResponserLocation() {
        return responserLocation;
    }

    public void setResponserLocation(String responserLocation) {
        this.responserLocation = responserLocation;
    }

    public String getResponserMobile() {
        return responserMobile;
    }

    public void setResponserMobile(String responserMobile) {
        this.responserMobile = responserMobile;
    }
}
