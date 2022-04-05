package com.ns.model.FlightSeatList;

import java.util.List;

public class FlightSeatListResponse {


    /**
     * resultcode : 1
     * message : Flight Seats.
     * data : {"flight_id":"3","flight_details":{"id":3,"name":"Stellar Jet","flight_no":"SF17","model":"17 Seat Model","layout":"1549442998.jpg","layout_path":"uploads/flights","no_of_seats":17},"flight_seats":[{"id":21,"seat_code":"Alpha","seat_layout":"1551872301.png","layout_path":"uploads/seats","flight_id":3,"sort_order":1},{"id":22,"seat_code":"Bravo","seat_layout":"1551872522.png","layout_path":"uploads/seats","flight_id":3,"sort_order":2},{"id":23,"seat_code":"Charlie","seat_layout":"1551872545.png","layout_path":"uploads/seats","flight_id":3,"sort_order":3},{"id":24,"seat_code":"Delta","seat_layout":"1551872552.png","layout_path":"uploads/seats","flight_id":3,"sort_order":4},{"id":25,"seat_code":"Echo","seat_layout":"1551872558.png","layout_path":"uploads/seats","flight_id":3,"sort_order":5},{"id":26,"seat_code":"Foxtrot","seat_layout":"1551872567.png","layout_path":"uploads/seats","flight_id":3,"sort_order":6},{"id":27,"seat_code":"Golf","seat_layout":"1551872575.png","layout_path":"uploads/seats","flight_id":3,"sort_order":7},{"id":28,"seat_code":"Hotel","seat_layout":"1551872582.png","layout_path":"uploads/seats","flight_id":3,"sort_order":8},{"id":29,"seat_code":"India","seat_layout":"1551872589.png","layout_path":"uploads/seats","flight_id":3,"sort_order":9},{"id":30,"seat_code":"Juliett","seat_layout":"1551872596.png","layout_path":"uploads/seats","flight_id":3,"sort_order":10},{"id":31,"seat_code":"Kilo","seat_layout":"1551872603.png","layout_path":"uploads/seats","flight_id":3,"sort_order":11},{"id":32,"seat_code":"Lima","seat_layout":"1551872615.png","layout_path":"uploads/seats","flight_id":3,"sort_order":12},{"id":33,"seat_code":"Mike","seat_layout":"1551872621.png","layout_path":"uploads/seats","flight_id":3,"sort_order":13},{"id":34,"seat_code":"November","seat_layout":"1551872628.png","layout_path":"uploads/seats","flight_id":3,"sort_order":14},{"id":35,"seat_code":"Oscar","seat_layout":"1551872634.png","layout_path":"uploads/seats","flight_id":3,"sort_order":15},{"id":36,"seat_code":"Papa","seat_layout":"1551872641.png","layout_path":"uploads/seats","flight_id":3,"sort_order":16},{"id":37,"seat_code":"Quebec","seat_layout":"1551872682.png","layout_path":"uploads/seats","flight_id":3,"sort_order":17}],"flight_seat_availability":{"total_seats":17,"available_seats":17,"booked":[],"locked":[]},"seats_locked_by_user":[]}
     */

    private int resultcode;
    private String message;
    private FlightSeat data;

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

    public FlightSeat getData() {
        return data;
    }

    public void setData(FlightSeat data) {
        this.data = data;
    }
}
