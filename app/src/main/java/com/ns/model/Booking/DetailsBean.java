package com.ns.model.Booking;

import com.ns.model.ReUseModel.CustomerSeatBean;
import com.ns.model.ReUseModel.FromCityInfoBean;
import com.ns.model.ReUseModel.GuestSeatsBean;
import com.ns.model.ReUseModel.GuestsBean;
import com.ns.model.ReUseModel.PrefsBean;
import com.ns.model.ReUseModel.SeatsBean;
import com.ns.model.ReUseModel.ToCityInfoBean;

import java.io.Serializable;
import java.util.List;

public class DetailsBean implements Serializable {
    /**
     * booking_id : 130
     * trip_id : 76
     * schedule_id : 5
     * flight_id : 2
     * flight_no : STLR604
     * flight : CHALLENGER 604
     * from_city : 1
     * from_city_info : {"name":"Bengaluru","short_name":"BLR","airport":"BLR"}
     * to_city : 2
     * to_city_info : {"name":"Delhi","short_name":"DEL","airport":"DELHI"}
     * user : 6
     * feedback_available : false
     * customer : Mr Krishan
     * travelling_self : 0
     * service : Usual
     * pick_address :
     * drop_address :
     * pick_address_main :
     * drop_address_main :
     * arrival_time : 11:00:00
     * journey_time : 07:45:00
     * journey_date : 2019-05-16
     * departure_datetime : 2019-05-16 07:45:00
     * journey_datetime : 1557972900000
     * booking_created_at : 2019-05-03 15:56:47
     * transaction_id : 405
     * booking_status : 12
     * status : Confirmed
     * booked_by : Krishan
     * booked_by_user_type : Primary
     * customer_seat : {}
     * seats : [{"seat_id":9,"seat_code":"Indigo"},{"seat_id":10,"seat_code":"Kilo"}]
     * guests : [{"guest_id":20,"name":"Krishan Guest","phone":"9874563201","email":null},{"guest_id":22,"name":"Krishan Guest2","phone":"9874563202","email":null}]
     * guest_seats : [{"guest_id":20,"name":"Krishan Guest","phone":"9874563201","email":null,"seat_id":9,"seat_code":"Indigo"},{"guest_id":22,"name":"Krishan Guest2","phone":"9874563202","email":null,"seat_id":10,"seat_code":"Kilo"}]
     * prefs : {"main_passenger":{},"co_passengers":[{"booking_extra_id":219,"passenger":20,"name":"Krishan Guest","phone":"9874563201","membership":"","seats_info":{"seat_id":9,"seat_code":"Indigo"},"pick_address":null,"drop_address":null,"boarding_pass_url":"http://dev.stellarjet.com/v1/storage/uploads/boardingPasses/ST-20-Krishan_Guest-20190510123609.pdf","boarding_pass_data":{"seat_name":"Indigo","seat_short_code":"I","traveller":"Krishan Guest","aircraft":"STLR604","date":"16 05 2019","time":"0745","reportTime":"07.35am","departure":"Thursday 16 May 07.45am","from":"Bengaluru","to":"Delhi","from_code":"BLR","to_code":"DELHI","stellar_club_code":20,"flight":"S5"},"status":"Confirmed","last_modified_by":"Krishan","modified_user_type":"Primary","check_in_status":"N","check_in_time":null,"pickup_vehicle":null,"pickup_driver":null,"drop_vehicle":null,"drop_driver":null,"food_items":[]},{"booking_extra_id":220,"passenger":22,"name":"Krishan Guest2","phone":"9874563202","membership":"","seats_info":{"seat_id":10,"seat_code":"Kilo"},"pick_address":null,"drop_address":null,"boarding_pass_url":"http://dev.stellarjet.com/v1/storage/uploads/boardingPasses/ST-22-Krishan_Guest2-20190510123610.pdf","boarding_pass_data":{"seat_name":"Kilo","seat_short_code":"K","traveller":"Krishan Guest2","aircraft":"STLR604","date":"16 05 2019","time":"0745","reportTime":"07.35am","departure":"Thursday 16 May 07.45am","from":"Bengaluru","to":"Delhi","from_code":"BLR","to_code":"DELHI","stellar_club_code":22,"flight":"S5"},"status":"Confirmed","last_modified_by":"Krishan","modified_user_type":"Primary","check_in_status":"N","check_in_time":null,"pickup_vehicle":null,"pickup_driver":null,"drop_vehicle":null,"drop_driver":null,"food_items":[]}]}
     * assigned_drivers : []
     */

    private int booking_id;
    private int trip_id;
    private int schedule_id;
    private int flight_id;
    private String flight_no;
    private String flight;
    private int from_city;
    private FromCityInfoBean from_city_info;
    private int to_city;
    private ToCityInfoBean to_city_info;
    private int user;
    private boolean feedback_available;
    private String customer;
    private int travelling_self;
    private String service;
    private String pick_address;
    private String drop_address;
    private String pick_address_main;
    private String drop_address_main;
    private String arrival_time;
    private String journey_time;
    private String journey_date;
    private String departure_datetime;
    private long journey_datetime;
    private String booking_created_at;
    private int transaction_id;
    private int booking_status;
    private String status;
    private String booked_by;
    private String booked_by_user_type;
    private CustomerSeatBean customer_seat;
    private PrefsBean prefs;
    private List<SeatsBean> seats;
    private List<GuestsBean> guests;
    private List<GuestSeatsBean> guest_seats;
    private List<?> assigned_drivers;

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public int getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
    }

    public int getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(int flight_id) {
        this.flight_id = flight_id;
    }

    public String getFlight_no() {
        return flight_no;
    }

    public void setFlight_no(String flight_no) {
        this.flight_no = flight_no;
    }

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    public int getFrom_city() {
        return from_city;
    }

    public void setFrom_city(int from_city) {
        this.from_city = from_city;
    }

    public FromCityInfoBean getFrom_city_info() {
        return from_city_info;
    }

    public void setFrom_city_info(FromCityInfoBean from_city_info) {
        this.from_city_info = from_city_info;
    }

    public int getTo_city() {
        return to_city;
    }

    public void setTo_city(int to_city) {
        this.to_city = to_city;
    }

    public ToCityInfoBean getTo_city_info() {
        return to_city_info;
    }

    public void setTo_city_info(ToCityInfoBean to_city_info) {
        this.to_city_info = to_city_info;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public boolean isFeedback_available() {
        return feedback_available;
    }

    public void setFeedback_available(boolean feedback_available) {
        this.feedback_available = feedback_available;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getTravelling_self() {
        return travelling_self;
    }

    public void setTravelling_self(int travelling_self) {
        this.travelling_self = travelling_self;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getPick_address() {
        return pick_address;
    }

    public void setPick_address(String pick_address) {
        this.pick_address = pick_address;
    }

    public String getDrop_address() {
        return drop_address;
    }

    public void setDrop_address(String drop_address) {
        this.drop_address = drop_address;
    }

    public String getPick_address_main() {
        return pick_address_main;
    }

    public void setPick_address_main(String pick_address_main) {
        this.pick_address_main = pick_address_main;
    }

    public String getDrop_address_main() {
        return drop_address_main;
    }

    public void setDrop_address_main(String drop_address_main) {
        this.drop_address_main = drop_address_main;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getJourney_time() {
        return journey_time;
    }

    public void setJourney_time(String journey_time) {
        this.journey_time = journey_time;
    }

    public String getJourney_date() {
        return journey_date;
    }

    public void setJourney_date(String journey_date) {
        this.journey_date = journey_date;
    }

    public String getDeparture_datetime() {
        return departure_datetime;
    }

    public void setDeparture_datetime(String departure_datetime) {
        this.departure_datetime = departure_datetime;
    }

    public long getJourney_datetime() {
        return journey_datetime;
    }

    public void setJourney_datetime(long journey_datetime) {
        this.journey_datetime = journey_datetime;
    }

    public String getBooking_created_at() {
        return booking_created_at;
    }

    public void setBooking_created_at(String booking_created_at) {
        this.booking_created_at = booking_created_at;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public int getBooking_status() {
        return booking_status;
    }

    public void setBooking_status(int booking_status) {
        this.booking_status = booking_status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBooked_by() {
        return booked_by;
    }

    public void setBooked_by(String booked_by) {
        this.booked_by = booked_by;
    }

    public String getBooked_by_user_type() {
        return booked_by_user_type;
    }

    public void setBooked_by_user_type(String booked_by_user_type) {
        this.booked_by_user_type = booked_by_user_type;
    }

    public CustomerSeatBean getCustomer_seat() {
        return customer_seat;
    }

    public void setCustomer_seat(CustomerSeatBean customer_seat) {
        this.customer_seat = customer_seat;
    }

    public PrefsBean getPrefs() {
        return prefs;
    }

    public void setPrefs(PrefsBean prefs) {
        this.prefs = prefs;
    }

    public List<SeatsBean> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatsBean> seats) {
        this.seats = seats;
    }

    public List<GuestsBean> getGuests() {
        return guests;
    }

    public void setGuests(List<GuestsBean> guests) {
        this.guests = guests;
    }

    public List<GuestSeatsBean> getGuest_seats() {
        return guest_seats;
    }

    public void setGuest_seats(List<GuestSeatsBean> guest_seats) {
        this.guest_seats = guest_seats;
    }

    public List<?> getAssigned_drivers() {
        return assigned_drivers;
    }

    public void setAssigned_drivers(List<?> assigned_drivers) {
        this.assigned_drivers = assigned_drivers;
    }


}