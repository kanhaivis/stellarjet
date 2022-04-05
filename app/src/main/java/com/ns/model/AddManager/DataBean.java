package com.ns.model.AddManager;

import java.util.List;

public class DataBean {
    /**
     * primary_user : Krishan
     * secondary_users : [{"su_id":9,"su_name":"kumar","su_email":"kumar@ns.com","su_phone":"1236547896","status":1}]
     */

    private String primary_user;
    private List<SecondaryUsersBean> secondary_users;

    public String getPrimary_user() {
        return primary_user;
    }

    public void setPrimary_user(String primary_user) {
        this.primary_user = primary_user;
    }

    public List<SecondaryUsersBean> getSecondary_users() {
        return secondary_users;
    }

    public void setSecondary_users(List<SecondaryUsersBean> secondary_users) {
        this.secondary_users = secondary_users;
    }

}
