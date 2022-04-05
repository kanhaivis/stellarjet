package com.ns.model.LoginResponse;



public class AddressBean  {
    /**
     * id : 1
     * address : Mumbai Office
     * address_tag : Lower Parel
     * lat : null
     * lng : null
     * city : 3
     * created_at : 2019-03-21 13:34:21
     * user : 2
     * user_type : 1
     * city_info : {"id":3,"name":"Mumbai"}
     */

    private int id;
    private String address;
    private String address_tag;
    private String lat;
    private String lng;
    private int city;
    private String created_at;
    private int user;
    private int user_type;
    private CityInfoBean city_info;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress_tag() {
        return address_tag;
    }

    public void setAddress_tag(String address_tag) {
        this.address_tag = address_tag;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public CityInfoBean getCity_info() {
        return city_info;
    }

    public void setCity_info(CityInfoBean city_info) {
        this.city_info = city_info;
    }


}