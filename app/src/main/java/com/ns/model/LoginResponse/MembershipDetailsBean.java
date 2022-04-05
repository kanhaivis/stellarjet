package com.ns.model.LoginResponse;

public class MembershipDetailsBean {
    /**
     * id : 2
     * title : Plaitinum
     * prepaid_terms : true
     * seat_cost : 100000
     */

    private int id;
    private String title;
    private String prepaid_terms;
    private String seat_cost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrepaid_terms() {
        return prepaid_terms;
    }

    public void setPrepaid_terms(String prepaid_terms) {
        this.prepaid_terms = prepaid_terms;
    }

    public String getSeat_cost() {
        return seat_cost;
    }

    public void setSeat_cost(String seat_cost) {
        this.seat_cost = seat_cost;
    }
}