package com.ns.model.City;

public class CitiesBean {
    /**
     * id : 1
     * name : Bengaluru
     * short_name : BLR
     * airport : BLR
     * terminal : Null
     * airport_lat : 12.9677022
     * airport_long : 77.6367125
     * airport_address : HAL Old Airport Rd, ISRO Colony, Domlur, Bengaluru, Karnataka, India
     * status : 1
     * status_title : Enabled
     */

    private int id;
    private String name;
    private String short_name;
    private String airport;
    private String terminal;
    private String airport_lat;
    private String airport_long;
    private String airport_address;
    private int status;
    private String status_title;

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

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getAirport_lat() {
        return airport_lat;
    }

    public void setAirport_lat(String airport_lat) {
        this.airport_lat = airport_lat;
    }

    public String getAirport_long() {
        return airport_long;
    }

    public void setAirport_long(String airport_long) {
        this.airport_long = airport_long;
    }

    public String getAirport_address() {
        return airport_address;
    }

    public void setAirport_address(String airport_address) {
        this.airport_address = airport_address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatus_title() {
        return status_title;
    }

    public void setStatus_title(String status_title) {
        this.status_title = status_title;
    }
}
