package com.ns.model;

public class CommonPojo {


    /**
     * resultcode : 1
     * message : Secondary user added successfully.
     * data : {"secondary_user_id":9}
     */

    private int resultcode;
    private String message;
    private String error_message;

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

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
}

