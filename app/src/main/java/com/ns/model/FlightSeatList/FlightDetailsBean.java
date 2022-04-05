package com.ns.model.FlightSeatList;

public class FlightDetailsBean {
    /**
     * id : 3
     * name : Stellar Jet
     * flight_no : SF17
     * model : 17 Seat Model
     * layout : 1549442998.jpg
     * layout_path : uploads/flights
     * no_of_seats : 17
     */

    private int id;
    private String name;
    private String flight_no;
    private String model;
    private String layout;
    private String layout_path;
    private int no_of_seats;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlight_no() {
        return flight_no;
    }

    public void setFlight_no(String flight_no) {
        this.flight_no = flight_no;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getLayout_path() {
        return layout_path;
    }

    public void setLayout_path(String layout_path) {
        this.layout_path = layout_path;
    }

    public int getNo_of_seats() {
        return no_of_seats;
    }

    public void setNo_of_seats(int no_of_seats) {
        this.no_of_seats = no_of_seats;
    }
}
