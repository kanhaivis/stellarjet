package com.ns.model;

import com.ns.model.ReUseModel.BookingDataBean;

public class ConfirmFlightBookingResponse {


    /**
     * resultcode : 1
     * message : Booking Confirmed
     * data : {"booking_id":41,"booking_details":[{"booking_id":41,"trip_id":16,"schedule_id":5,"flight_id":2,"flight_no":"STLR604","flight":"CHALLENGER 604","from_city":1,"from_city_info":{"name":"Bengaluru","short_name":"BLR","airport":"BLR"},"to_city":2,"to_city_info":{"name":"Delhi","short_name":"DEL","airport":"DEL"},"user":6,"feedback_available":false,"customer":"Mr Krishan","travelling_self":1,"service":"Usual","pick_address":"","drop_address":"","pick_address_main":"","drop_address_main":"","arrival_time":"11:00:00","journey_time":"07:45:00","journey_date":"2019-04-30","departure_datetime":"2019-04-30 07:45:00","journey_datetime":1556590500000,"booking_created_at":"2019-04-08 17:48:41","transaction_id":210,"booking_status":12,"status":"Confirmed","booked_by":"Krishan","booked_by_user_type":"Primary","customer_seat":{"seat_id":9,"seat_code":"Indigo"},"seats":[{"seat_id":9,"seat_code":"Indigo"}],"guests":[],"guest_seats":[],"prefs":{"main_passenger":{"booking_extra_id":48,"passenger":6,"name":"Mr Krishan","phone":"8277027575","membership":"Platinum","seats_info":{"seat_id":9,"seat_code":"Indigo"},"boarding_pass_url":"","boarding_pass_data":{"seat_name":"Indigo","seat_short_code":"I","traveller":"Krishan","aircraft":"STLR604","date":"30 04 2019","time":"0745","reportTime":"07.35am","departure":"Tuesday 30 April 07.45am","from":"Bengaluru","to":"Delhi","from_code":"BLR","to_code":"DEL","stellar_club_code":6,"flight":"S5"},"status":"Confirmed","last_modified_by":"Krishan","modified_user_type":"Primary","check_in_status":"N","check_in_time":null,"food_items":[],"user_meta_data":[{"meta_key":"food_prefs","meta_value":[{"food_category":"veg","days":["Monday","Tuesday"]},{"food_category":"non-veg","days":["Sunday","Monday","Tuesday","Friday","Saturday"]},{"food_category":"continental","days":["Monday","Tuesday","Wednesday","Thursday"]}]},{"meta_key":"food_spl_instruction","meta_value":"undefined"},{"meta_key":"billing_address","meta_value":"Ninestars"},{"meta_key":"discount_offered_membership","meta_value":"10.00"},{"meta_key":"discount_offered_seat","meta_value":"10.00"}]},"co_passengers":[]},"assigned_drivers":[]}]}
     */

    private int resultcode;
    private String message;
    private BookingDataBean data;

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

    public BookingDataBean getData() {
        return data;
    }

    public void setData(BookingDataBean data) {
        this.data = data;
    }


}
