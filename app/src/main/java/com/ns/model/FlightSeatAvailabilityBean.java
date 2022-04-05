package com.ns.model;

import java.io.Serializable;
import java.util.List;

public class FlightSeatAvailabilityBean implements Serializable {
    /**
     * total_seats : 8
     * available_seats : 5
     * booked : [7,3,4]
     * locked : []
     */

    private int total_seats;
    private int available_seats;
    private List<Integer> booked;
    private List<?> locked;

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

    public List<?> getLocked() {
        return locked;
    }

    public void setLocked(List<?> locked) {
        this.locked = locked;
    }
}
