package com.example.bloodbank.Models;

public class ActivityLogModel {

    private String date, time, action;

    public ActivityLogModel(String date, String time, String action) {
        this.date = date;
        this.time = time;
        this.action = action;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
