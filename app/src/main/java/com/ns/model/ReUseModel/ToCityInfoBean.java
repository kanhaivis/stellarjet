package com.ns.model.ReUseModel;

import java.io.Serializable;

public class ToCityInfoBean implements Serializable {
    /**
     * name : Mumbai
     * short_name : MUM
     * airport : BOM
     */

    private String name;
    private String short_name;
    private String airport;

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
}