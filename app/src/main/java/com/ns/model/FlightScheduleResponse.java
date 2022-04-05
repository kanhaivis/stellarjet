package com.ns.model;

import java.util.List;

public class FlightScheduleResponse {

    /**
     * resultcode : 1
     * message : Flight Schedules.
     * data : [{"schedule_id":2,"route_name":"S2","flight_id":1,"journey_date":"2019-03-26","journey_time":"19:30:00","arrival_time":"22:00:00","direction":"right","sun_rise_set":"set","journey_datetime_ms":1553608800000,"flight_seat_availability":{"total_seats":8,"available_seats":5,"booked":[7,3,4],"locked":[]}},{"schedule_id":2,"route_name":"S2","flight_id":1,"journey_date":"2019-03-27","journey_time":"19:30:00","arrival_time":"22:00:00","direction":"right","sun_rise_set":"set","journey_datetime_ms":1553695200000,"flight_seat_availability":{"total_seats":8,"available_seats":6,"booked":[2,7],"locked":[]}},{"schedule_id":2,"route_name":"S2","flight_id":1,"journey_date":"2019-03-28","journey_time":"19:30:00","arrival_time":"22:00:00","direction":"right","sun_rise_set":"set","journey_datetime_ms":1553781600000,"flight_seat_availability":{"total_seats":8,"available_seats":6,"booked":[5,7],"locked":[]}},{"schedule_id":2,"route_name":"S2","flight_id":1,"journey_date":"2019-03-29","journey_time":"19:30:00","arrival_time":"22:00:00","direction":"right","sun_rise_set":"set","journey_datetime_ms":1553868000000,"flight_seat_availability":{"total_seats":8,"available_seats":8,"booked":[],"locked":[]}},{"schedule_id":2,"route_name":"S2","flight_id":1,"journey_date":"2019-04-01","journey_time":"19:30:00","arrival_time":"22:00:00","direction":"right","sun_rise_set":"set","journey_datetime_ms":1554127200000,"flight_seat_availability":{"total_seats":8,"available_seats":8,"booked":[],"locked":[]}}
     */

    private int resultcode;
    private String message;
    private List<FlightScheduleData> data;

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

    public List<FlightScheduleData> getData() {
        return data;
    }

    public void setData(List<FlightScheduleData> data) {
        this.data = data;
    }


}
