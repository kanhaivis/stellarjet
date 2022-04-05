package com.ns.model.FlightSeatList;

public class FlightSeatsBean {
    /**
     * id : 21
     * seat_code : Alpha
     * seat_layout : 1551872301.png
     * layout_path : uploads/seats
     * flight_id : 3
     * sort_order : 1
     */

    private int id;
    private String seat_code;
    private String seat_layout;
    private String layout_path;
    private int flight_id;
    private int sort_order;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeat_code() {
        return seat_code;
    }

    public void setSeat_code(String seat_code) {
        this.seat_code = seat_code;
    }

    public String getSeat_layout() {
        return seat_layout;
    }

    public void setSeat_layout(String seat_layout) {
        this.seat_layout = seat_layout;
    }

    public String getLayout_path() {
        return layout_path;
    }

    public void setLayout_path(String layout_path) {
        this.layout_path = layout_path;
    }

    public int getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(int flight_id) {
        this.flight_id = flight_id;
    }

    public int getSort_order() {
        return sort_order;
    }

    public void setSort_order(int sort_order) {
        this.sort_order = sort_order;
    }
}