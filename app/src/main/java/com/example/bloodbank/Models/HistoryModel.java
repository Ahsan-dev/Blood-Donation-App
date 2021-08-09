package com.example.bloodbank.Models;

public class HistoryModel {

    private String year, month, date, location, hospital, bloodGrp, daysAgo;

    public HistoryModel(String year, String month, String date, String location, String hospital, String bloodGrp, String daysAgo) {
        this.year = year;
        this.month = month;
        this.date = date;
        this.location = location;
        this.hospital = hospital;
        this.bloodGrp = bloodGrp;
        this.daysAgo = daysAgo;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getBloodGrp() {
        return bloodGrp;
    }

    public void setBloodGrp(String bloodGrp) {
        this.bloodGrp = bloodGrp;
    }

    public String getDaysAgo() {
        return daysAgo;
    }

    public void setDaysAgo(String daysAgo) {
        this.daysAgo = daysAgo;
    }
}
