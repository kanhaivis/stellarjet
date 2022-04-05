package com.ns.model.SeatResponse;

import com.ns.model.FlightSeatList.FlightSeatAvailabilityBean;

import java.util.List;

public class ConformSeatRequest {


    /**
     * resultcode : 1
     * message : Seats availability.
     * data : {"flight_seat_availability":{"total_seats":12,"available_seats":11,"booked":[],"locked":[],"locked_by_current_user":[9]}}
     */

    private int resultcode;
    private String message;
    private DataBean data;

    public int getResultcode() {
        return resultcode;
    }

    public void setResultcode(int resultcode) {
        this.resultcode = resultcode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * flight_seat_availability : {"total_seats":12,"available_seats":11,"booked":[],"locked":[],"locked_by_current_user":[9]}
         */

        private FlightSeatAvailabilityBean flight_seat_availability;

        public FlightSeatAvailabilityBean getFlight_seat_availability() {
            return flight_seat_availability;
        }

        public void setFlight_seat_availability(FlightSeatAvailabilityBean flight_seat_availability) {
            this.flight_seat_availability = flight_seat_availability;
        }


    }
}
