package com.ns.model.LoginResponse;

import java.io.Serializable;
import java.util.List;

public class CustomerPrefsBean implements Serializable {
    /**
     * seats_available : 495
     * member_type : P
     * member_type_text : Personal
     * membership_details : {"id":2,"title":"Plaitinum","prepaid_terms":"true","seat_cost":"100000"}
     * gstin :
     * subscription_expiry : 1573378967000
     * subscription_expiry_datetime : 2019-11-10 15:12:47
     * food_prefs : [{"food_category":"veg","days":["Monday","Tuesday"]},{"food_category":"non-veg","days":["Monday","Tuesday","Friday","Saturday"]},{"food_category":"continental","days":["Monday","Tuesday"]}]
     * food_categories : [{"cat_key":"veg","cat_text":"Vegetarian","show_days":false},{"cat_key":"non-veg","cat_text":"Non - Vegetarian","show_days":true},{"cat_key":"continental","cat_text":"Continental","show_days":true}]
     * flight_days : ["Monday","Tuesday","Wednesday","Thursday","Friday"]
     */

    private int seats_available;
    private String member_type;
    private String member_type_text;
    private MembershipDetailsBean membership_details;
    private String gstin;
    private long subscription_expiry;
    private String subscription_expiry_datetime;
    private List<FoodPrefsBean> food_prefs;
    private List<FoodCategoriesBean> food_categories;
    private List<String> flight_days;

    public int getSeats_available() {
        return seats_available;
    }

    public void setSeats_available(int seats_available) {
        this.seats_available = seats_available;
    }

    public String getMember_type() {
        return member_type;
    }

    public void setMember_type(String member_type) {
        this.member_type = member_type;
    }

    public String getMember_type_text() {
        return member_type_text;
    }

    public void setMember_type_text(String member_type_text) {
        this.member_type_text = member_type_text;
    }

    public MembershipDetailsBean getMembership_details() {
        return membership_details;
    }

    public void setMembership_details(MembershipDetailsBean membership_details) {
        this.membership_details = membership_details;
    }

    public String getGstin() {
        return gstin;
    }

    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    public long getSubscription_expiry() {
        return subscription_expiry;
    }

    public void setSubscription_expiry(long subscription_expiry) {
        this.subscription_expiry = subscription_expiry;
    }

    public String getSubscription_expiry_datetime() {
        return subscription_expiry_datetime;
    }

    public void setSubscription_expiry_datetime(String subscription_expiry_datetime) {
        this.subscription_expiry_datetime = subscription_expiry_datetime;
    }

    public List<FoodPrefsBean> getFood_prefs() {
        return food_prefs;
    }

    public void setFood_prefs(List<FoodPrefsBean> food_prefs) {
        this.food_prefs = food_prefs;
    }

    public List<FoodCategoriesBean> getFood_categories() {
        return food_categories;
    }

    public void setFood_categories(List<FoodCategoriesBean> food_categories) {
        this.food_categories = food_categories;
    }

    public List<String> getFlight_days() {
        return flight_days;
    }

    public void setFlight_days(List<String> flight_days) {
        this.flight_days = flight_days;
    }
}