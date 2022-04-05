package com.ns.model.LoginResponse;

public class SubscriptionsBean {
    /**
     * created_at : 2019-03-21 15:12:16
     * expiry_date : 2019-11-10 15:12:47
     * no_of_seats : 500
     * status : {"id":6,"status_text":"Completed"}
     */

    private String created_at;
    private String expiry_date;
    private int no_of_seats;
    private StatusBean status;

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    public int getNo_of_seats() {
        return no_of_seats;
    }

    public void setNo_of_seats(int no_of_seats) {
        this.no_of_seats = no_of_seats;
    }

    public StatusBean getStatus() {
        return status;
    }

    public void setStatus(StatusBean status) {
        this.status = status;
    }
}