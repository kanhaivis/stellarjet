package com.ns.model.Booking;

import com.ns.model.ReUseModel.PrefsBean;

import java.io.Serializable;
import java.util.List;

public class BookingIdResponse implements Serializable {

    /**
     * resultcode : 1
     * message : Booking Details
     * data : {"details":[{"booking_id":130,"trip_id":76,"schedule_id":5,"flight_id":2,"flight_no":"STLR604","flight":"CHALLENGER 604","from_city":1,"from_city_info":{"name":"Bengaluru","short_name":"BLR","airport":"BLR"},"to_city":2,"to_city_info":{"name":"Delhi","short_name":"DEL","airport":"DELHI"},"user":6,"feedback_available":false,"customer":"Mr Krishan","travelling_self":0,"service":"Usual","pick_address":"","drop_address":"","pick_address_main":"","drop_address_main":"","arrival_time":"11:00:00","journey_time":"07:45:00","journey_date":"2019-05-16","departure_datetime":"2019-05-16 07:45:00","journey_datetime":1557972900000,"booking_created_at":"2019-05-03 15:56:47","transaction_id":405,"booking_status":12,"status":"Confirmed","booked_by":"Krishan","booked_by_user_type":"Primary","customer_seat":{},"seats":[{"seat_id":9,"seat_code":"Indigo"},{"seat_id":10,"seat_code":"Kilo"}],"guests":[{"guest_id":20,"name":"Krishan Guest","phone":"9874563201","email":null},{"guest_id":22,"name":"Krishan Guest2","phone":"9874563202","email":null}],"guest_seats":[{"guest_id":20,"name":"Krishan Guest","phone":"9874563201","email":null,"seat_id":9,"seat_code":"Indigo"},{"guest_id":22,"name":"Krishan Guest2","phone":"9874563202","email":null,"seat_id":10,"seat_code":"Kilo"}],"prefs":{"main_passenger":{},"co_passengers":[{"booking_extra_id":219,"passenger":20,"name":"Krishan Guest","phone":"9874563201","membership":"","seats_info":{"seat_id":9,"seat_code":"Indigo"},"pick_address":null,"drop_address":null,"boarding_pass_url":"http://dev.stellarjet.com/v1/storage/uploads/boardingPasses/ST-20-Krishan_Guest-20190510123609.pdf","boarding_pass_data":{"seat_name":"Indigo","seat_short_code":"I","traveller":"Krishan Guest","aircraft":"STLR604","date":"16 05 2019","time":"0745","reportTime":"07.35am","departure":"Thursday 16 May 07.45am","from":"Bengaluru","to":"Delhi","from_code":"BLR","to_code":"DELHI","stellar_club_code":20,"flight":"S5"},"status":"Confirmed","last_modified_by":"Krishan","modified_user_type":"Primary","check_in_status":"N","check_in_time":null,"pickup_vehicle":null,"pickup_driver":null,"drop_vehicle":null,"drop_driver":null,"food_items":[]},{"booking_extra_id":220,"passenger":22,"name":"Krishan Guest2","phone":"9874563202","membership":"","seats_info":{"seat_id":10,"seat_code":"Kilo"},"pick_address":null,"drop_address":null,"boarding_pass_url":"http://dev.stellarjet.com/v1/storage/uploads/boardingPasses/ST-22-Krishan_Guest2-20190510123610.pdf","boarding_pass_data":{"seat_name":"Kilo","seat_short_code":"K","traveller":"Krishan Guest2","aircraft":"STLR604","date":"16 05 2019","time":"0745","reportTime":"07.35am","departure":"Thursday 16 May 07.45am","from":"Bengaluru","to":"Delhi","from_code":"BLR","to_code":"DELHI","stellar_club_code":22,"flight":"S5"},"status":"Confirmed","last_modified_by":"Krishan","modified_user_type":"Primary","check_in_status":"N","check_in_time":null,"pickup_vehicle":null,"pickup_driver":null,"drop_vehicle":null,"drop_driver":null,"food_items":[]}]},"assigned_drivers":[]}]}
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
