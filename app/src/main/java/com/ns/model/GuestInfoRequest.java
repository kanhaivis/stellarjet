package com.ns.model;

import android.provider.Telephony;

import java.util.List;

public class GuestInfoRequest {
    private String token;
    private List<GuestBean> add;

    public String getToken() {
        return token;
    }

    public GuestInfoRequest(String token, List<GuestBean> add) {

        this.token = token;
        this.add = add;
    }

    public List<GuestBean> getAdd() {
        return add;
    }


}
