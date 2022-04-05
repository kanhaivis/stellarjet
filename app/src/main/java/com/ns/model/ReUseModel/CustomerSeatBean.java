package com.ns.model.ReUseModel;

import java.io.Serializable;

public class CustomerSeatBean  implements Serializable {
    /**
     * seat_id : 11
     * seat_code : Juliet
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
