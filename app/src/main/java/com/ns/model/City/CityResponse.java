package com.ns.model.City;

import java.util.List;

public class CityResponse {
    /**
     * resultcode : 1
     * message : City list.
     * data : {"cities":[{"id":1,"name":"Bengaluru","short_name":"BLR","airport":"BLR","terminal":"Null","airport_lat":"12.9677022","airport_long":"77.6367125","airport_address":"HAL Old Airport Rd, ISRO Colony, Domlur, Bengaluru, Karnataka, India","status":1,"status_title":"Enabled"},{"id":2,"name":"Delhi","short_name":"DEL","airport":"DELHI","terminal":"VIP terminal \u2013 T1","airport_lat":"28.55616239999999","airport_long":"77.09995779999997","airport_address":"New Delhi, Delhi 110037, India","status":1,"status_title":"Enabled"},{"id":3,"name":"Mumbai","short_name":"MUM","airport":"BOM","terminal":"null","airport_lat":"19.093555","airport_long":"72.85658969999997","airport_address":"Navpada Chhatrapati Shivaji International Airport Area, Navpada, Vile Parle East, Vile Parle, Mumbai, Maharashtra 400099, India","status":1,"status_title":"Enabled"},{"id":4,"name":"Mysore","short_name":"mYS","airport":"mysore airport","terminal":"1","airport_lat":"12.9677022","airport_long":"77.6367125","airport_address":"undefined","status":1,"status_title":"Enabled"},{"id":5,"name":"Kerala","short_name":"KER","airport":"mysore airport","terminal":"1","airport_lat":"12.9677022","airport_long":"77.6367125","airport_address":"undefined","status":1,"status_title":"Enabled"},{"id":6,"name":"Goa","short_name":"GOa","airport":"Dabolim Airport","terminal":null,"airport_lat":"15.3803485","airport_long":"73.83499519999998","airport_address":"Airport Rd, Dabolim, Goa 403801, India","status":1,"status_title":"Enabled"},{"id":7,"name":"Keral","short_name":"kl","airport":"hal","terminal":"t1","airport_lat":"12.9604757","airport_long":"77.64240399999994","airport_address":"HAL Old Airport Rd, ISRO Colony, Domlur, Bengaluru, Karnataka, India","status":1,"status_title":"Enabled"},{"id":8,"name":"Rajasthan","short_name":"pink city","airport":"rajsthan air","terminal":null,"airport_lat":"27.0238036","airport_long":"74.21793260000004","airport_address":"Rajasthan, India","status":1,"status_title":"Enabled"},{"id":9,"name":"Indore","short_name":"ind","airport":"indore air","terminal":"T4","airport_lat":"22.7113985","airport_long":"75.88319999999999","airport_address":"Indore Bypass Rd, Madhya Pradesh, India","status":1,"status_title":"Enabled"},{"id":10,"name":"Kolkata","short_name":"kol","airport":"kol air","terminal":"t6","airport_lat":"22.6375676","airport_long":"88.43190729999992","airport_address":"kolkata Airport Quarters, Kaikhali, Kolkata, West Bengal 700028, India","status":1,"status_title":"Enabled"},{"id":11,"name":"Ootty","short_name":"oo","airport":"ootty","terminal":"023","airport_lat":"12.9677022","airport_long":"77.6367125","airport_address":"undefined","status":1,"status_title":"Enabled"},{"id":12,"name":"Jay Pur","short_name":"jay","airport":"jay","terminal":"t4","airport_lat":"26.9124336","airport_long":"75.78727090000007","airport_address":"Jaipur, Rajasthan, India","status":1,"status_title":"Enabled"},{"id":13,"name":"Testing","short_name":"test","airport":"tst","terminal":"null","airport_lat":"12.9677022","airport_long":"77.6367125","airport_address":"undefined","status":1,"status_title":"Enabled"},{"id":14,"name":"Abcde","short_name":"abcd","airport":"abcd","terminal":"null","airport_lat":"12.9165757","airport_long":"77.61011630000007","airport_address":"BTM Layout, Bengaluru, Karnataka, India","status":1,"status_title":"Enabled"}]}
     */

    private int resultcode;
    private String message;
    private CityDataBean data;

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

    public CityDataBean getData() {
        return data;
    }

    public void setData(CityDataBean data) {
        this.data = data;
    }

}
