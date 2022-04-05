package com.ns.model;

import java.io.Serializable;

public class FlightScheduleData implements Serializable {
    /**
     * schedule_id : 2
     * route_name : S2
     * flight_id : 1
     * journey_date : 2019-03-26
     * journey_time : 19:30:00
     * arrival_time : 22:00:00
     * direction : right
     * sun_rise_set : set
     * journey_datetime_ms : 1553608800000
     * flight_seat_availability : {"total_seats":8,"available_seats":5,"booked":[7,3,4],"locked":[]}
     */

    private int schedule_id;
    private String route_name;
    private int flight_id;
    private String journey_date;
    private String journey_time;
    private String arrival_time;
    private String direction;
    private String sun_rise_set;
    private long journey_datetime_ms;
    private FlightSeatAvailabilityBean flight_seat_availability;

    public int getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
    }

    public String getRoute_name() {
        return route_name;
    }

    public void setRoute_name(String route_name) {
        this.route_name = route_name;
    }

    public int getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(int flight_id) {
        this.flight_id = flight_id;
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getSun_rise_set() {
        return sun_rise_set;
    }

    public void setSun_rise_set(String sun_rise_set) {
        this.sun_rise_set = sun_rise_set;
    }

    public long getJourney_datetime_ms() {
        return journey_datetime_ms;
    }

    public void setJourney_datetime_ms(long journey_datetime_ms) {
        this.journey_datetime_ms = journey_datetime_ms;
    }

    public FlightSeatAvailabilityBean getFlight_seat_availability() {
        return flight_seat_availability;
    }

    public void setFlight_seat_availability(FlightSeatAvailabilityBean flight_seat_availability) {
        this.flight_seat_availability = flight_seat_availability;
    }


}