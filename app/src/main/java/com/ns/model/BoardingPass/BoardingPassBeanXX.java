package com.ns.model.BoardingPass;

import com.ns.model.ReUseModel.PrefsBean;

import java.io.Serializable;
import java.util.List;

public class BoardingPassBeanXX implements Serializable {
    /**
     * booking_id : 39
     * trip_id : 16
     * schedule_id : 2
     * flight_id : 1
     * flight_no : VTARWE
     * flight : CHALLENGER 850
     * from_city : 2
     * from_city_info : {"name":"Delhi","short_name":"DEL","airport":"DEL"}
     * to_city : 3
     * to_city_info : {"name":"Mumbai","short_name":"MUM","airport":"BOM"}
     * user : 6
     * feedback_available : false
     * feedbacks : []
     * customer : Krishan
     * travelling_self : 1
     * service : Usual
     * pick_address :
     * drop_address :
     * pick_address_main :
     * drop_address_main :
     * arrival_time : 22:00:00
     * journey_time : 19:30:00
     * journey_date : 2019-03-29
     * departure_datetime : 2019-03-29 19:30:00
     * journey_datetime : 1553868000000
     * booking_created_at : 2019-03-26 16:29:15
     * transaction_id : 50
     * booking_status : 12
     * status : Confirmed
     * booked_by : Krishan
     * booked_by_user_type : Primary
     * customer_seat : {"seat_id":4,"seat_code":"Delta"}
     * seats : [{"seat_id":4,"seat_code":"Delta"},{"seat_id":3,"seat_code":"Charlie"}]
     * guests : [{"guest_id":20,"name":"Krishan Guest","phone":"9874563201","email":null}]
     * guest_seats : [{"guest_id":20,"name":"Krishan Guest","phone":"9874563201","email":null,"seat_id":3,"seat_code":"Charlie"}]
     * prefs : {"main_passenger":{"passenger":6,"name":"Krishan","phone":"8277027575","seats_info":{"seat_id":4,"seat_code":"Delta"},"boarding_pass_url":"http://dev.stellarjet.com/app/v2/storage/","boarding_pass":{"seat_name":"Delta","seat_short_code":"D","traveller":"Krishan","aircraft":"VTARWE","date":"29 03 2019","time":"1930","reportTime":"07.20pm","departure":"Friday 29 March 07.30pm","from":"Delhi","to":"Mumbai","from_code":"DEL","to_code":"BOM","stellar_club_code":6,"flight":"S2"},"status":"Confirmed","last_modified_by":"Krishan","modified_user_type":"Primary","food_items":[],"user_meta_data":[{"meta_key":"food_prefs","meta_value":[{"food_category":"veg","days":["Monday","Tuesday"]},{"food_category":"non-veg","days":["Monday","Tuesday","Friday","Saturday"]},{"food_category":"continental","days":["Monday","Tuesday"]}]},{"meta_key":"food_spl_instruction","meta_value":"undefined"},{"meta_key":"billing_address","meta_value":"Ninestars"},{"meta_key":"discount_offered_membership","meta_value":"10.00"},{"meta_key":"discount_offered_seat","meta_value":"10.00"}]},"co_passengers":[{"passenger":20,"name":"Krishan Guest","phone":"9874563201","seats_info":{"seat_id":3,"seat_code":"Charlie"},"boarding_pass_url":"http://dev.stellarjet.com/app/v2/storage/","boarding_pass":{"seat_name":"Charlie","seat_short_code":"C","traveller":"Krishan Guest","aircraft":"VTARWE","date":"29 03 2019","time":"1930","reportTime":"07.20pm","departure":"Friday 29 March 07.30pm","from":"Delhi","to":"Mumbai","from_code":"DEL","to_code":"BOM","stellar_club_code":20,"flight":"S2"},"status":"Confirmed","last_modified_by":"Krishan","modified_user_type":"Primary","food_items":[]}]}
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
    private String reportTime;

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

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
    private List<?> feedbacks;
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

    public List<?> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<?> feedbacks) {
        this.feedbacks = feedbacks;
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

    public static class FromCityInfoBean implements Serializable {
        /**
         * name : Delhi
         * short_name : DEL
         * airport : DEL
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

    public static class ToCityInfoBean implements Serializable {
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

    public static class CustomerSeatBean implements Serializable {
        /**
         * seat_id : 4
         * seat_code : Delta
         */

        private int seat_id;
        private String seat_code;

        public int getSeat_id() {
            return seat_id;
        }

        public void setSeat_id(int seat_id) {
            this.seat_id = seat_id;
        }

        public String getSeat_code() {
            return seat_code;
        }

        public void setSeat_code(String seat_code) {
            this.seat_code = seat_code;
        }
    }


    public static class SeatsBean implements Serializable {
        /**
         * seat_id : 4
         * seat_code : Delta
         */

        private int seat_id;
        private String seat_code;

        public int getSeat_id() {
            return seat_id;
        }

        public void setSeat_id(int seat_id) {
            this.seat_id = seat_id;
        }

        public String getSeat_code() {
            return seat_code;
        }

        public void setSeat_code(String seat_code) {
            this.seat_code = seat_code;
        }
    }

    public static class GuestsBean implements Serializable {
        /**
         * guest_id : 20
         * name : Krishan Guest
         * phone : 9874563201
         * email : null
         */

        private int guest_id;
        private String name;
        private String phone;
        private Object email;

        public int getGuest_id() {
            return guest_id;
        }

        public void setGuest_id(int guest_id) {
            this.guest_id = guest_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }
    }

    public static class GuestSeatsBean implements Serializable {
        /**
         * guest_id : 20
         * name : Krishan Guest
         * phone : 9874563201
         * email : null
         * seat_id : 3
         * seat_code : Charlie
         */

        private int guest_id;
        private String name;
        private String phone;
        private Object email;
        private int seat_id;
        private String seat_code;

        public int getGuest_id() {
            return guest_id;
        }

        public void setGuest_id(int guest_id) {
            this.guest_id = guest_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public int getSeat_id() {
            return seat_id;
        }

        public void setSeat_id(int seat_id) {
            this.seat_id = seat_id;
        }

        public String getSeat_code() {
            return seat_code;
        }

        public void setSeat_code(String seat_code) {
            this.seat_code = seat_code;
        }
    }
}
