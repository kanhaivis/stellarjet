package com.ns.model.FoodDay;

public class CabPersonalizeRequest {

    private String token;
    private int booking_id;
    private int pick_address;
    private int drop_address;

    public String getToken() {
        return token;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public int getPick_address() {
        return pick_address;
    }

    public int getDrop_address() {
        return drop_address;
    }

    public CabPersonalizeRequest(String token, int booking_id, int pick_address, int drop_address) {

        this.token = token;
        this.booking_id = booking_id;
        this.pick_address = pick_address;
        this.drop_address = drop_address;
    }
}
