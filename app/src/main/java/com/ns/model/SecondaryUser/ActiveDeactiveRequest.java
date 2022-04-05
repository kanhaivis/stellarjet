package com.ns.model.SecondaryUser;

public class ActiveDeactiveRequest {

    private String token;
    private int secondary_user_id;
    private int status;

    public String getToken() {
        return token;
    }

    public int getSecondary_user_id() {
        return secondary_user_id;
    }

    public int getStatus() {
        return status;
    }

    public ActiveDeactiveRequest(String token, int secondary_user_id, int status) {

        this.token = token;
        this.secondary_user_id = secondary_user_id;
        this.status = status;
    }
}
