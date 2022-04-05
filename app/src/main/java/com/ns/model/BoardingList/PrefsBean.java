package com.ns.model.BoardingList;

import java.io.Serializable;
import java.util.List;

public class PrefsBean implements Serializable {
    /**
     * main_passenger : {"booking_extra_id":106,"passenger":6,"name":"Mr Krishan","phone":"8277027575","membership":"Platinum","seats_info":{"seat_id":9,"seat_code":"Indigo"},"pick_address":null,"drop_address":null,"boarding_pass_url":"","boarding_pass_data":{"seat_name":"Indigo","seat_short_code":"I","traveller":"Krishan","aircraft":"STLR604","date":"02 05 2019","time":"0745","reportTime":"07.35am","departure":"Thursday 02 May 07.45am","from":"Bengaluru","to":"Delhi","from_code":"BLR","to_code":"DEL","stellar_club_code":6,"flight":"S5"},"status":"Confirmed","last_modified_by":"Krishan","modified_user_type":"Primary","check_in_status":"N","check_in_time":null,"pickup_vehicle":null,"pickup_driver":null,"drop_vehicle":null,"drop_driver":null}
     * co_passengers : [{"booking_extra_id":107,"passenger":22,"name":"Krishan Guest2","phone":"9874563202","membership":"","seats_info":{"seat_id":11,"seat_code":"Juliet"},"pick_address":null,"drop_address":null,"boarding_pass_url":"","boarding_pass_data":{"seat_name":"Juliet","seat_short_code":"J","traveller":"Krishan Guest2","aircraft":"STLR604","date":"02 05 2019","time":"0745","reportTime":"07.35am","departure":"Thursday 02 May 07.45am","from":"Bengaluru","to":"Delhi","from_code":"BLR","to_code":"DEL","stellar_club_code":22,"flight":"S5"},"status":"Cancelled","last_modified_by":"Krishan","modified_user_type":"Primary","check_in_status":"N","check_in_time":null,"pickup_vehicle":null,"pickup_driver":null,"drop_vehicle":null,"drop_driver":null}]
     */

    private MainPassengerBean main_passenger;
    private List<CoPassengersBean> co_passengers;

    public MainPassengerBean getMain_passenger() {
        return main_passenger;
    }

    public void setMain_passenger(MainPassengerBean main_passenger) {
        this.main_passenger = main_passenger;
    }

    public List<CoPassengersBean> getCo_passengers() {
        return co_passengers;
    }

    public void setCo_passengers(List<CoPassengersBean> co_passengers) {
        this.co_passengers = co_passengers;
    }

}