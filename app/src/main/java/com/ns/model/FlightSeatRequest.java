package com.ns.model;

import java.util.List;

public class FlightSeatRequest {

    private String token;
    private String journey_date;
    private String journey_time;
    private int flight_id;
    private int from_city;
    private int to_city;
    private List<Integer> seats_for_unlock;

    public FlightSeatRequest(String token, String journey_date, String journey_time, int flight_id, int from_city, int to_city, List<Integer> seats_for_unlock, List<Integer> seats_for_lock) {
        this.token = token;
        this.journey_date = journey_date;
        this.journey_time = journey_time;
        this.flight_id = flight_id;
        this.from_city = from_city;
        this.to_city = to_city;
        this.seats_for_unlock = seats_for_unlock;
        this.seats_for_lock = seats_for_lock;
    }

    private List<Integer> seats_for_lock;

    public FlightSeatRequest(String token, String journey_date, String journey_time, int flight_id, int from_city, int to_city) {
        this.token = token;
        this.journey_date = journey_date;
        this.journey_time = journey_time;
        this.flight_id = flight_id;
        this.from_city = from_city;
        this.to_city = to_city;
    }

    public String getToken() {
        return token;
    }

    public String getJourney_date() {
        return journey_date;
    }

    public String getJourney_time() {
        return journey_time;
    }

    public int getFlight_id() {
        return flight_id;
    }

    public int getFrom_city() {
        return from_city;
    }

    public int getTo_city() {
        return to_city;
    }
}
