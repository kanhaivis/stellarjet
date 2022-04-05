package com.ns.model.AddManager;

public class SecondaryUsersBean {
    /**
     * su_id : 9
     * su_name : kumar
     * su_email : kumar@ns.com
     * su_phone : 1236547896
     * status : 1
     */

    private int su_id;
    private String su_name;
    private String su_email;
    private String su_phone;
    private int status;

    public int getSu_id() {
        return su_id;
    }

    public void setSu_id(int su_id) {
        this.su_id = su_id;
    }

    public String getSu_name() {
        return su_name;
    }

    public void setSu_name(String su_name) {
        this.su_name = su_name;
    }

    public String getSu_email() {
        return su_email;
    }

    public void setSu_email(String su_email) {
        this.su_email = su_email;
    }

    public String getSu_phone() {
        return su_phone;
    }

    public void setSu_phone(String su_phone) {
        this.su_phone = su_phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}