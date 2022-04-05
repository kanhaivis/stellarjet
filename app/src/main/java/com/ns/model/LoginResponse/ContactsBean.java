package com.ns.model.LoginResponse;

public class ContactsBean {
    /**
     * id : 20
     * name : Krishan Guest
     * nick_name : null
     * relationship : Friend
     * email : null
     * user : 6
     * phone : 9874563201
     * user_info : {"id":6,"name":"Krishan"}
     */

    private int id;
    private String name;
    private Object nick_name;
    private String relationship;
    private Object email;
    private int user;
    private String phone;
    private UserInfoBean user_info;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getNick_name() {
        return nick_name;
    }

    public void setNick_name(Object nick_name) {
        this.nick_name = nick_name;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserInfoBean getUser_info() {
        return user_info;
    }

    public void setUser_info(UserInfoBean user_info) {
        this.user_info = user_info;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof ContactsBean))
            return false;
        if (obj == this)
            return true;
        return this.getName() == ((ContactsBean) obj).getName();
    }

    @Override
    public int hashCode() {
        return getId();
    }
}