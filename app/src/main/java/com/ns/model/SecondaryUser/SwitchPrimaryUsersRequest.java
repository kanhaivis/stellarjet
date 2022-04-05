package com.ns.model.SecondaryUser;

import retrofit2.http.Field;

public class SwitchPrimaryUsersRequest {
    private String token;
    private int primary_user;

    public String getToken() {
        return token;
    }

    public int getPrimary_user() {
        return primary_user;
    }

    public SwitchPrimaryUsersRequest(String token, int primary_user) {

        this.token = token;
        this.primary_user = primary_user;
    }
}
