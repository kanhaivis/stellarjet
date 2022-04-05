package com.ns.model.FlightSeatList;

public class SeatsLockedByUser {

    private int flight_seat_id;
    private String seat_reserved_at;
    private long seat_reserved_at_ms;

    public int getFlight_seat_id() {
        return flight_seat_id;
    }

    public void setFlight_seat_id(int flight_seat_id) {
        this.flight_seat_id = flight_seat_id;
    }

    public String getSeat_reserved_at() {
        return seat_reserved_at;
    }

    public void setSeat_reserved_at(String seat_reserved_at) {
        this.seat_reserved_at = seat_reserved_at;
    }

    public long getSeat_reserved_at_ms() {
        return seat_reserved_at_ms;
    }

    public void setSeat_reserved_at_ms(long seat_reserved_at_ms) {
        this.seat_reserved_at_ms = seat_reserved_at_ms;
    }
}
