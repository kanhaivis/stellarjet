package com.ns.model.BoardingList;

import com.ns.model.ReUseModel.BookingListBean;

import java.io.Serializable;
import java.util.List;

public class BoardingListResponse implements Serializable{


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

    public static class DataBean implements Serializable {
        private List<BookingListBean> booking_list;

        public List<BookingListBean> getBooking_list() {
            return booking_list;
        }

        public void setBooking_list(List<BookingListBean> booking_list) {
            this.booking_list = booking_list;
        }
    }
}
