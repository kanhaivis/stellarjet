package com.ns.model.Booking;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataBean implements Serializable {
    private ArrayList<DetailsBean> details;

    public ArrayList<DetailsBean> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<DetailsBean> details) {
        this.details = details;
    }

}