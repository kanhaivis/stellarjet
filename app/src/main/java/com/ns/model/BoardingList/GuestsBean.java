package com.ns.model.BoardingList;

import java.io.Serializable;

public class  GuestsBean implements Serializable {
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
