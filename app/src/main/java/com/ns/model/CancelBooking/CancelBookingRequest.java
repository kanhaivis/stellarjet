package com.ns.model.CancelBooking;

import java.util.List;

import retrofit2.http.Field;

public class CancelBookingRequest {

    private String token;
    private int booking_id;
    private List<Integer> seat_ids;

    public CancelBookingRequest(String token, int booking_id, List<Integer> seat_ids) {
        this.token = token;
        this.booking_id = booking_id;
        this.seat_ids = seat_ids;
    }

    public String getToken() {
        return token;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public List<Integer> getSeat_ids() {
        return seat_ids;
    }
}
