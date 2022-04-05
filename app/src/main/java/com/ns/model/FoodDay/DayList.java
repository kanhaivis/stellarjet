package com.ns.model.FoodDay;

public class DayList {
    private String day;
    private boolean status;

    public String getDay() {
        return day;
    }

    public boolean isStatus() {
        return status;
    }

    public DayList(String day, boolean status) {

        this.day = day;
        this.status = status;
    }
}