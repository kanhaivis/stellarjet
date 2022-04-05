package com.ns.model.BoardingList;

import com.ns.model.ReUseModel.DropAddress;
import com.ns.model.ReUseModel.PickAddress;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainPassengerBean implements Serializable {
    /**
     * booking_extra_id : 106
     * passenger : 6
     * name : Mr Krishan
     * phone : 8277027575
     * membership : Platinum
     * seats_info : {"seat_id":9,"seat_code":"Indigo"}
     * pick_address : null
     * drop_address : null
     * boarding_pass_url :
     * boarding_pass_data : {"seat_name":"Indigo","seat_short_code":"I","traveller":"Krishan","aircraft":"STLR604","date":"02 05 2019","time":"0745","reportTime":"07.35am","departure":"Thursday 02 May 07.45am","from":"Bengaluru","to":"Delhi","from_code":"BLR","to_code":"DEL","stellar_club_code":6,"flight":"S5"}
     * status : Confirmed
     * last_modified_by : Krishan
     * modified_user_type : Primary
     * check_in_status : N
     * check_in_time : null
     * pickup_vehicle : null
     * pickup_driver : null
     * drop_vehicle : null
     * drop_driver : null
     */

    private int booking_extra_id;
    private int passenger;
    private String name;
    private String phone;
    private String membership;
    private SeatsInfoBean seats_info;
    private PickAddress pick_address;
    private DropAddress drop_address;
    private String boarding_pass_url;
    private BoardingPassDataBean boarding_pass_data;
    private String status;
    private String last_modified_by;
    private String modified_user_type;
    private String check_in_status;
    private Object check_in_time;
    private Object pickup_vehicle;
    private Object pickup_driver;
    private Object drop_vehicle;
    private Object drop_driver;
    private ArrayList<FoodItems> food_items;

    public ArrayList<FoodItems> getFood_items() {
        return food_items;
    }

    public void setFood_items(ArrayList<FoodItems> food_items) {
        this.food_items = food_items;
    }

    public int getBooking_extra_id() {
        return booking_extra_id;
    }

    public void setBooking_extra_id(int booking_extra_id) {
        this.booking_extra_id = booking_extra_id;
    }

    public int getPassenger() {
        return passenger;
    }

    public void setPassenger(int passenger) {
        this.passenger = passenger;
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

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    public SeatsInfoBean getSeats_info() {
        return seats_info;
    }

    public void setSeats_info(SeatsInfoBean seats_info) {
        this.seats_info = seats_info;
    }

    public PickAddress getPick_address() {
        return pick_address;
    }

    public void setPick_address(PickAddress pick_address) {
        this.pick_address = pick_address;
    }

    public DropAddress getDrop_address() {
        return drop_address;
    }

    public void setDrop_address(DropAddress drop_address) {
        this.drop_address = drop_address;
    }

    public String getBoarding_pass_url() {
        return boarding_pass_url;
    }

    public void setBoarding_pass_url(String boarding_pass_url) {
        this.boarding_pass_url = boarding_pass_url;
    }

    public BoardingPassDataBean getBoarding_pass_data() {
        return boarding_pass_data;
    }

    public void setBoarding_pass_data(BoardingPassDataBean boarding_pass_data) {
        this.boarding_pass_data = boarding_pass_data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLast_modified_by() {
        return last_modified_by;
    }

    public void setLast_modified_by(String last_modified_by) {
        this.last_modified_by = last_modified_by;
    }

    public String getModified_user_type() {
        return modified_user_type;
    }

    public void setModified_user_type(String modified_user_type) {
        this.modified_user_type = modified_user_type;
    }

    public String getCheck_in_status() {
        return check_in_status;
    }

    public void setCheck_in_status(String check_in_status) {
        this.check_in_status = check_in_status;
    }

    public Object getCheck_in_time() {
        return check_in_time;
    }

    public void setCheck_in_time(Object check_in_time) {
        this.check_in_time = check_in_time;
    }

    public Object getPickup_vehicle() {
        return pickup_vehicle;
    }

    public void setPickup_vehicle(Object pickup_vehicle) {
        this.pickup_vehicle = pickup_vehicle;
    }

    public Object getPickup_driver() {
        return pickup_driver;
    }

    public void setPickup_driver(Object pickup_driver) {
        this.pickup_driver = pickup_driver;
    }

    public Object getDrop_vehicle() {
        return drop_vehicle;
    }

    public void setDrop_vehicle(Object drop_vehicle) {
        this.drop_vehicle = drop_vehicle;
    }

    public Object getDrop_driver() {
        return drop_driver;
    }

    public void setDrop_driver(Object drop_driver) {
        this.drop_driver = drop_driver;
    }

    public static class SeatsInfoBean implements Serializable{
        /**
         * seat_id : 9
         * seat_code : Indigo
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

    public static class BoardingPassDataBean implements Serializable{
        /**
         * seat_name : Indigo
         * seat_short_code : I
         * traveller : Krishan
         * aircraft : STLR604
         * date : 02 05 2019
         * time : 0745
         * reportTime : 07.35am
         * departure : Thursday 02 May 07.45am
         * from : Bengaluru
         * to : Delhi
         * from_code : BLR
         * to_code : DEL
         * stellar_club_code : 6
         * flight : S5
         */

        private String seat_name;
        private String seat_short_code;
        private String traveller;
        private String aircraft;
        private String date;
        private String time;
        private String reportTime;
        private String departure;
        private String from;
        private String to;
        private String from_code;
        private String to_code;
        private int stellar_club_code;
        private String flight;

        public String getSeat_name() {
            return seat_name;
        }

        public void setSeat_name(String seat_name) {
            this.seat_name = seat_name;
        }

        public String getSeat_short_code() {
            return seat_short_code;
        }

        public void setSeat_short_code(String seat_short_code) {
            this.seat_short_code = seat_short_code;
        }

        public String getTraveller() {
            return traveller;
        }

        public void setTraveller(String traveller) {
            this.traveller = traveller;
        }

        public String getAircraft() {
            return aircraft;
        }

        public void setAircraft(String aircraft) {
            this.aircraft = aircraft;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getReportTime() {
            return reportTime;
        }

        public void setReportTime(String reportTime) {
            this.reportTime = reportTime;
        }

        public String getDeparture() {
            return departure;
        }

        public void setDeparture(String departure) {
            this.departure = departure;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getFrom_code() {
            return from_code;
        }

        public void setFrom_code(String from_code) {
            this.from_code = from_code;
        }

        public String getTo_code() {
            return to_code;
        }

        public void setTo_code(String to_code) {
            this.to_code = to_code;
        }

        public int getStellar_club_code() {
            return stellar_club_code;
        }

        public void setStellar_club_code(int stellar_club_code) {
            this.stellar_club_code = stellar_club_code;
        }

        public String getFlight() {
            return flight;
        }

        public void setFlight(String flight) {
            this.flight = flight;
        }
    }
}
