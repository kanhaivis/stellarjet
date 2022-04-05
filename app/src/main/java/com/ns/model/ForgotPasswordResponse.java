package com.ns.model;

public class ForgotPasswordResponse {

    /**
     * resultcode : 1
     * message : We have e-mailed your password reset link!
     */

    private int resultcode;
    private String message;

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
