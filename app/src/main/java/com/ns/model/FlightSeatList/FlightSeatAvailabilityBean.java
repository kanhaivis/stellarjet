package com.ns.model.FlightSeatList;

import java.util.List;

public class FlightSeatAvailabilityBean {
    /**
     * total_seats : 17
     * available_seats : 17
     * booked : []
     * locked : []
     */

    private int total_seats;
    private int available_seats;
    private List<Integer> booked;
    private List<Integer> locked;
    private List<Integer> locked_by_current_user;

    public int getTotal_seats() {
        return total_seats;
    }

    public void setTotal_seats(int total_seats) {
        this.total_seats = total_seats;
    }

    public int getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(int available_seats) {
        this.available_seats = available_seats;
    }

    public List<Integer> getBooked() {
        return booked;
    }

    public void setBooked(List<Integer> booked) {
        this.booked = booked;
    }

    public List<Integer> getLocked() {
        return locked;
    }

    public void setLocked(List<Integer> locked) {
        this.locked = locked;
    }

    public List<Integer> getLocked_by_current_user() {
        return locked_by_current_user;
    }

    public void setLocked_by_current_user(List<Integer> locked_by_current_user) {
        this.locked_by_current_user = locked_by_current_user;
    }
}
