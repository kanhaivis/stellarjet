package com.ns.model.LoginResponse;

import java.io.Serializable;
import java.util.Objects;

public class CitiesBean implements Serializable {
    /**
     * id : 1
     * name : Bengaluru
     * short_name : BLR
     * airport : BLR
     * status : 1
     */

    private int id;
    private String name;
    private String short_name;
    private String airport;
    private int status;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof CitiesBean))
            return false;
        if (obj == this)
            return true;
        return this.getId() == ((CitiesBean) obj).getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }
}