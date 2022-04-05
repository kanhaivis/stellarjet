package com.ns.model.Booking;

import org.json.JSONArray;

import java.util.List;

public class ConfirmSeatBookingResponse {


    private int from_city;
    private int to_city;
    private int flight_id;
    private String token;
    private int travelling_self;
    private String journey_date;
    private String journey_time;
    private String arrival_time;
    private int schedule_id;
    private List<Integer> seat_ids;
    private List<Integer> guests;
    private String seat_passenger_relation;

    public ConfirmSeatBookingResponse(int from_city, int to_city, int flight_id, String token, int travelling_self, String journey_date, String journey_time, String arrival_time, int schedule_id, List<Integer> seat_ids, List<Integer> guest_id, String seat_passenger_relation) {
        this.from_city = from_city;
        this.to_city = to_city;
        this.flight_id = flight_id;
        this.token = token;
        this.travelling_self = travelling_self;
        this.journey_date = journey_date;
        this.journey_time = journey_time;
        this.arrival_time = arrival_time;
        this.schedule_id = schedule_id;
        this.seat_ids = seat_ids;
        this.guests = guest_id;
        this.seat_passenger_relation = seat_passenger_relation;
    }

    public int getFrom_city() {
        return from_city;
    }

    public void setFrom_city(int from_city) {
        this.from_city = from_city;
    }

    public int getTo_city() {
        return to_city;
    }

    public void setTo_city(int to_city) {
        this.to_city = to_city;
    }

    public int getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(int flight_id) {
        this.flight_id = flight_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getTravelling_self() {
        return travelling_self;
    }

    public void setTravelling_self(int travelling_self) {
        this.travelling_self = travelling_self;
    }

    public String getJourney_date() {
        return journey_date;
    }

    public void setJourney_date(String journey_date) {
        this.journey_date = journey_date;
    }

    public String getJourney_time() {
        return journey_time;
    }

    public void setJourney_time(String journey_time) {
        this.journey_time = journey_time;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public int getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
    }

    public List<Integer> getSeat_ids() {
        return seat_ids;
    }

    public void setSeat_ids(List<Integer> seat_ids) {
        this.seat_ids = seat_ids;
    }

    public List<Integer> getGuests() {
        return guests;
    }

    public void setGuests(List<Integer> guests) {
        this.guests = guests;
    }

    public String  getSeat_passenger_relation() {
        return seat_passenger_relation;
    }

    public void setSeat_passenger_relation(String seat_passenger_relation) {
        this.seat_passenger_relation = seat_passenger_relation;
    }
}
