package com.ns.model.LoginResponse;

import java.util.List;

public class LoginInfo {

    /**
     * resultcode : 1
     * message : Login Successful
     * data : {"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJsdW1lbi1qd3QiLCJzdWIiOjYsImlhdCI6MTU1MzY2ODI4MSwiZXhwIjoxNTU2MjYwMjgxfQ.hTk27Wubjey4uN3ZjjuO_57mRn6EoeU70pOhC1g-bMQ","refresh_token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJsdW1lbi1qd3QtcmVmcmVzaCIsInN1YiI6NiwiaWF0IjoxNTUzNjY4MjgxLCJleHAiOiIifQ.UFkuHBrAcBxDkkHMZ9JwyfzrPSdUoo2sCuhxUQk_K4E","user_data":{"name":"Krishan","email":"krishan.k@ninestars.in","phone":"8277027575","status_id":1,"status":"Active","created_at":"2019-03-21 15:12:16","addresses":[],"customer_prefs":{"seats_available":495,"member_type":"P","member_type_text":"Personal","membership_details":{"id":2,"title":"Plaitinum","prepaid_terms":"true","seat_cost":"100000"},"gstin":"","subscription_expiry":1573378967000,"subscription_expiry_datetime":"2019-11-10 15:12:47","food_prefs":[{"food_category":"veg","days":["Monday","Tuesday"]},{"food_category":"non-veg","days":["Monday","Tuesday","Friday","Saturday"]},{"food_category":"continental","days":["Monday","Tuesday"]}],"food_categories":[{"cat_key":"veg","cat_text":"Vegetarian","show_days":false},{"cat_key":"non-veg","cat_text":"Non - Vegetarian","show_days":true},{"cat_key":"continental","cat_text":"Continental","show_days":true}],"flight_days":["Monday","Tuesday","Wednesday","Thursday","Friday"]},"contacts":[{"id":20,"name":"Krishan Guest","nick_name":null,"relationship":"Friend","email":null,"user":6,"phone":"9874563201","user_info":{"id":6,"name":"Krishan"}}],"primary_users":[],"subscriptions":{"created_at":"2019-03-21 15:12:16","expiry_date":"2019-11-10 15:12:47","no_of_seats":500,"status":{"id":6,"status_text":"Completed"}},"locked_seats":[],"cities":[{"id":1,"name":"Bengaluru","short_name":"BLR","airport":"BLR","status":1},{"id":2,"name":"Delhi","short_name":"DEL","airport":"DEL","status":1},{"id":3,"name":"Mumbai","short_name":"MUM","airport":"BOM","status":1}],"customer_care_info":{"email":"contact@stellarjet.com","phone":"180042577777"}}}
     */

    private int resultcode;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }
}
