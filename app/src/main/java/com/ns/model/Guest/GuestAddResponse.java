package com.ns.model.Guest;

import java.util.List;

public class GuestAddResponse {
    /**
     * resultcode : 1
     * message : New Contact list.
     * data : {"new_contacts":[{"guest_id":96},{"guest_id":97}]}
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
        private List<NewContactsBean> new_contacts;

        public List<NewContactsBean> getNew_contacts() {
            return new_contacts;
        }

        public void setNew_contacts(List<NewContactsBean> new_contacts) {
            this.new_contacts = new_contacts;
        }

        public static class NewContactsBean {
            /**
             * guest_id : 96
             */

            private int guest_id;

            public int getGuest_id() {
                return guest_id;
            }

            public void setGuest_id(int guest_id) {
                this.guest_id = guest_id;
            }
        }
    }
}
