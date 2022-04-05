package com.ns.model.AddManager;

import java.util.List;

public class ManagerResponse {

    /**
     * resultcode : 1
     * message : Secodary users.
     * data : {"primary_user":"Krishan","secondary_users":[{"su_id":9,"su_name":"kumar","su_email":"kumar@ns.com","su_phone":"1236547896","status":1}]}
     */

    private int resultcode;
    private String message;
    private DataBean data;

    public int getResultcode() {
        return resultcode;
    }

    public void setResultcode(int resultcode) {
        this.resultcode = resultcode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }
}
