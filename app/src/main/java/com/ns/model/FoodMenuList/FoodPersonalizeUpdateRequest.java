package com.ns.model.FoodMenuList;

import java.util.List;

import retrofit2.http.Field;

public class FoodPersonalizeUpdateRequest {

    private String token;
    private String booking_id;
    private List<String> foods_taken;

    public String getToken() {
        return token;
    }

    public String getBooking_id() {
        return booking_id;
    }

    public List<String> getFoods_taken() {
        return foods_taken;
    }

    public FoodPersonalizeUpdateRequest(String token, String booking_id, List<String> foods_taken) {
        this.token = token;
        this.booking_id = booking_id;
        this.foods_taken = foods_taken;
    }
}
