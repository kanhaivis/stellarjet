package com.ns.model.LoginResponse;

import java.util.ArrayList;
import java.util.List;

public class UserDataBean {
    /**
     * name : Krishan
     * email : krishan.k@ninestars.in
     * phone : 8277027575
     * status_id : 1
     * status : Active
     * created_at : 2019-03-21 15:12:16
     * addresses : []
     * customer_prefs : {"seats_available":495,"member_type":"P","member_type_text":"Personal","membership_details":{"id":2,"title":"Plaitinum","prepaid_terms":"true","seat_cost":"100000"},"gstin":"","subscription_expiry":1573378967000,"subscription_expiry_datetime":"2019-11-10 15:12:47","food_prefs":[{"food_category":"veg","days":["Monday","Tuesday"]},{"food_category":"non-veg","days":["Monday","Tuesday","Friday","Saturday"]},{"food_category":"continental","days":["Monday","Tuesday"]}],"food_categories":[{"cat_key":"veg","cat_text":"Vegetarian","show_days":false},{"cat_key":"non-veg","cat_text":"Non - Vegetarian","show_days":true},{"cat_key":"continental","cat_text":"Continental","show_days":true}],"flight_days":["Monday","Tuesday","Wednesday","Thursday","Friday"]}
     * contacts : [{"id":20,"name":"Krishan Guest","nick_name":null,"relationship":"Friend","email":null,"user":6,"phone":"9874563201","user_info":{"id":6,"name":"Krishan"}}]
     * primary_users : []
     * subscriptions : {"created_at":"2019-03-21 15:12:16","expiry_date":"2019-11-10 15:12:47","no_of_seats":500,"status":{"id":6,"status_text":"Completed"}}
     * locked_seats : []
     * cities : [{"id":1,"name":"Bengaluru","short_name":"BLR","airport":"BLR","status":1},{"id":2,"name":"Delhi","short_name":"DEL","airport":"DEL","status":1},{"id":3,"name":"Mumbai","short_name":"MUM","airport":"BOM","status":1}]
     * customer_care_info : {"email":"contact@stellarjet.com","phone":"180042577777"}
     */

    private String name;
    private String email;
    private String phone;
    private int status_id;
    private String status;
    private String created_at;
    private CustomerPrefsBean customer_prefs;
//    private SubscriptionsBean subscriptions;
    private CustomerCareInfoBean customer_care_info;
    private List<AddressBean> addresses;
    private List<ContactsBean> contacts;
    private List<?> primary_users;
    private List<?> locked_seats;
    private ArrayList<CitiesBean> cities;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public CustomerPrefsBean getCustomer_prefs() {
        return customer_prefs;
    }

    public void setCustomer_prefs(CustomerPrefsBean customer_prefs) {
        this.customer_prefs = customer_prefs;
    }

   /* public SubscriptionsBean getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(SubscriptionsBean subscriptions) {
        this.subscriptions = subscriptions;
    }*/

    public CustomerCareInfoBean getCustomer_care_info() {
        return customer_care_info;
    }

    public void setCustomer_care_info(CustomerCareInfoBean customer_care_info) {
        this.customer_care_info = customer_care_info;
    }

    public List<AddressBean> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressBean> addresses) {
        this.addresses = addresses;
    }

    public List<ContactsBean> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactsBean> contacts) {
        this.contacts = contacts;
    }

    public List<?> getPrimary_users() {
        return primary_users;
    }

    public void setPrimary_users(List<?> primary_users) {
        this.primary_users = primary_users;
    }

    public List<?> getLocked_seats() {
        return locked_seats;
    }

    public void setLocked_seats(List<?> locked_seats) {
        this.locked_seats = locked_seats;
    }

    public ArrayList<CitiesBean> getCities() {
        return cities;
    }

    public void setCities(ArrayList<CitiesBean> cities) {
        this.cities = cities;
    }
}
