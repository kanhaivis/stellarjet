package com.ns.model.BoardingPass;

import java.util.List;

public class BoardingPassResponse {


    /**
     * resultcode : 1
     * message : Boarding Pass
     * data : {"boarding_pass":[{"booking_id":39,"trip_id":16,"schedule_id":2,"flight_id":1,"flight_no":"VTARWE","flight":"CHALLENGER 850","from_city":2,"from_city_info":{"name":"Delhi","short_name":"DEL","airport":"DEL"},"to_city":3,"to_city_info":{"name":"Mumbai","short_name":"MUM","airport":"BOM"},"user":6,"feedback_available":false,"feedbacks":[],"customer":"Krishan","travelling_self":1,"service":"Usual","pick_address":"","drop_address":"","pick_address_main":"","drop_address_main":"","arrival_time":"22:00:00","journey_time":"19:30:00","journey_date":"2019-03-29","departure_datetime":"2019-03-29 19:30:00","journey_datetime":1553868000000,"booking_created_at":"2019-03-26 16:29:15","transaction_id":50,"booking_status":12,"status":"Confirmed","booked_by":"Krishan","booked_by_user_type":"Primary","customer_seat":{"seat_id":4,"seat_code":"Delta"},"seats":[{"seat_id":4,"seat_code":"Delta"},{"seat_id":3,"seat_code":"Charlie"}],"guests":[{"guest_id":20,"name":"Krishan Guest","phone":"9874563201","email":null}],"guest_seats":[{"guest_id":20,"name":"Krishan Guest","phone":"9874563201","email":null,"seat_id":3,"seat_code":"Charlie"}],"prefs":{"main_passenger":{"passenger":6,"name":"Krishan","phone":"8277027575","seats_info":{"seat_id":4,"seat_code":"Delta"},"boarding_pass_url":"http://dev.stellarjet.com/app/v2/storage/","boarding_pass":{"seat_name":"Delta","seat_short_code":"D","traveller":"Krishan","aircraft":"VTARWE","date":"29 03 2019","time":"1930","reportTime":"07.20pm","departure":"Friday 29 March 07.30pm","from":"Delhi","to":"Mumbai","from_code":"DEL","to_code":"BOM","stellar_club_code":6,"flight":"S2"},"status":"Confirmed","last_modified_by":"Krishan","modified_user_type":"Primary","food_items":[],"user_meta_data":[{"meta_key":"food_prefs","meta_value":[{"food_category":"veg","days":["Monday","Tuesday"]},{"food_category":"non-veg","days":["Monday","Tuesday","Friday","Saturday"]},{"food_category":"continental","days":["Monday","Tuesday"]}]},{"meta_key":"food_spl_instruction","meta_value":"undefined"},{"meta_key":"billing_address","meta_value":"Ninestars"},{"meta_key":"discount_offered_membership","meta_value":"10.00"},{"meta_key":"discount_offered_seat","meta_value":"10.00"}]},"co_passengers":[{"passenger":20,"name":"Krishan Guest","phone":"9874563201","seats_info":{"seat_id":3,"seat_code":"Charlie"},"boarding_pass_url":"http://dev.stellarjet.com/app/v2/storage/","boarding_pass":{"seat_name":"Charlie","seat_short_code":"C","traveller":"Krishan Guest","aircraft":"VTARWE","date":"29 03 2019","time":"1930","reportTime":"07.20pm","departure":"Friday 29 March 07.30pm","from":"Delhi","to":"Mumbai","from_code":"DEL","to_code":"BOM","stellar_club_code":20,"flight":"S2"},"status":"Confirmed","last_modified_by":"Krishan","modified_user_type":"Primary","food_items":[]}]},"assigned_drivers":[]},{"booking_id":45,"trip_id":20,"schedule_id":2,"flight_id":1,"flight_no":"VTARWE","flight":"CHALLENGER 850","from_city":2,"from_city_info":{"name":"Delhi","short_name":"DEL","airport":"DEL"},"to_city":3,"to_city_info":{"name":"Mumbai","short_name":"MUM","airport":"BOM"},"user":6,"feedback_available":false,"feedbacks":[],"customer":"Krishan","travelling_self":1,"service":"Usual","pick_address":"","drop_address":"","pick_address_main":"","drop_address_main":"","arrival_time":"22:00:00","journey_time":"19:30:00","journey_date":"2019-04-01","departure_datetime":"2019-04-01 19:30:00","journey_datetime":1554127200000,"booking_created_at":"2019-03-27 12:31:23","transaction_id":60,"booking_status":12,"status":"Confirmed","booked_by":"Krishan","booked_by_user_type":"Primary","customer_seat":{"seat_id":3,"seat_code":"Charlie"},"seats":[{"seat_id":3,"seat_code":"Charlie"}],"guests":[],"guest_seats":[],"prefs":{"main_passenger":{"passenger":6,"name":"Krishan","phone":"8277027575","seats_info":{"seat_id":3,"seat_code":"Charlie"},"boarding_pass_url":"http://dev.stellarjet.com/app/v2/storage/","boarding_pass":{"seat_name":"Charlie","seat_short_code":"C","traveller":"Krishan","aircraft":"VTARWE","date":"01 04 2019","time":"1930","reportTime":"07.20pm","departure":"Monday 01 April 07.30pm","from":"Delhi","to":"Mumbai","from_code":"DEL","to_code":"BOM","stellar_club_code":6,"flight":"S2"},"status":"Confirmed","last_modified_by":"Krishan","modified_user_type":"Primary","food_items":[],"user_meta_data":[{"meta_key":"food_prefs","meta_value":[{"food_category":"veg","days":["Monday","Tuesday"]},{"food_category":"non-veg","days":["Monday","Tuesday","Friday","Saturday"]},{"food_category":"continental","days":["Monday","Tuesday"]}]},{"meta_key":"food_spl_instruction","meta_value":"undefined"},{"meta_key":"billing_address","meta_value":"Ninestars"},{"meta_key":"discount_offered_membership","meta_value":"10.00"},{"meta_key":"discount_offered_seat","meta_value":"10.00"}]},"co_passengers":[]},"assigned_drivers":[]}]}
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

    public static class DataBean {
        private List<BoardingPassBeanXX> boarding_pass;

        public List<BoardingPassBeanXX> getBoarding_pass() {
            return boarding_pass;
        }

        public void setBoarding_pass(List<BoardingPassBeanXX> boarding_pass) {
            this.boarding_pass = boarding_pass;
        }

    }
}