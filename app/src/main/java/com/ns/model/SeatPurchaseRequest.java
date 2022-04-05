package com.ns.model;

import retrofit2.http.Field;

public class SeatPurchaseRequest {

    private String token;
    private int no_of_seats;

    public SeatPurchaseRequest(String token, int no_of_seats) {
        this.token = token;
        this.no_of_seats = no_of_seats;
    }
}
