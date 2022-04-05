package com.ns.model;

import com.ns.model.LoginResponse.AddressBean;

import java.util.List;

public class AddressDetailsPojo {
    /**
     * resultcode : 1
     * message : Addresses list.
     * data : {"addresses":[{"id":11,"address":"304 , Civil Lines, Ring Road, New Delhi, Delhi, India ","address_tag":"also","lat":"28.686273815669907","lng":null,"city":2,"created_at":"2019-03-29 10:43:06","user":6,"user_type":1,"city_info":{"id":2,"name":"Delhi"}},{"id":12,"address":"2 , Kurla West, CST Road, Mumbai, Maharashtra, India ","address_tag":"emerald ","lat":"19.075983832120244","lng":null,"city":3,"created_at":"2019-03-29 10:43:38","user":6,"user_type":1,"city_info":{"id":3,"name":"Mumbai"}}]}
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

    public static class DataBean {
        private List<AddressBean> addresses;

        public List<AddressBean> getAddresses() {
            return addresses;
        }

        public void setAddresses(List<AddressBean> addresses) {
            this.addresses = addresses;
        }
    }
}
