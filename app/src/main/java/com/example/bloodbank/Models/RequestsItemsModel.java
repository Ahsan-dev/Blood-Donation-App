package com.example.bloodbank.Models;

public class RequestsItemsModel {

    private int proPicResource;
    private String proName;
    private String bloodGrp;
    private String location;
    private String hospital;
    private boolean emergency;
    private String details;
    private String phnNumbr;

    public RequestsItemsModel(int proPicResource, String proName, String bloodGrp, String location, String hospital, boolean emergency, String details, String phnNumbr) {
        this.proPicResource = proPicResource;
        this.proName = proName;
        this.bloodGrp = bloodGrp;
        this.location = location;
        this.hospital = hospital;
        this.emergency = emergency;
        this.details = details;
        this.phnNumbr = phnNumbr;
    }

    public int getProPicResource() {
        return proPicResource;
    }

    public void setProPicResource(int proPicResource) {
        this.proPicResource = proPicResource;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getBloodGrp() {
        return bloodGrp;
    }

    public void setBloodGrp(String bloodGrp) {
        this.bloodGrp = bloodGrp;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public boolean isEmergency() {
        return emergency;
    }

    public void setEmergency(boolean emergency) {
        this.emergency = emergency;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPhnNumbr() {
        return phnNumbr;
    }

    public void setPhnNumbr(String phnNumbr) {
        this.phnNumbr = phnNumbr;
    }
}
