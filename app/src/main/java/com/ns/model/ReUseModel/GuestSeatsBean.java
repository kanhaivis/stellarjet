package com.ns.model.ReUseModel;

import java.io.Serializable;

public class GuestSeatsBean implements Serializable {
    /**
     * guest_id : 20
     * name : Krishan Guest
     * phone : 9874563201
     * email : null
     * seat_id : 9
     * seat_code : Indigo
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
