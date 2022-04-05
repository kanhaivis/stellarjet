package com.ns.model.PaymentResponse;

import java.io.Serializable;
import java.util.List;

public class PaymentLastOrderDetails implements Serializable {

    /**
     * resultcode : 1
     * message : Previous order.
     * data : {"purchaseData":{"purchase_id":208,"purchase_status":"pending","user":6,"total_amount":112000,"total_amount_paid":0,"amount_to_be_paid":112000,"ifsc_code":null,"bank_ac":null,"seat_cost":100000,"NEFT_amount_limt":500000,"order_details":{"particulars":{"air_tickets":{"unit":1,"rate":100000,"total_value":100000,"total_amount":112000,"tax_details":[{"tax_id":4,"tax_included":"E","tax_included_text":"Excluded","tax_name":"IGST","tax_rate":"12%","tax_amnt":12000}]}},"total":{"unit":"1","total_value":100000,"total_tax_amnts":12000,"total_amount":112000,"total_amount_in_words":"One Lakh Twelve Thousands Rupees "}}},"MIN_SEAT_PURCHASE_NO":2,"MAX_SEAT_PURCHASE_NO":99}
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

    public static class DataBean implements Serializable{
        /**
         * purchaseData : {"purchase_id":208,"purchase_status":"pending","user":6,"total_amount":112000,"total_amount_paid":0,"amount_to_be_paid":112000,"ifsc_code":null,"bank_ac":null,"seat_cost":100000,"NEFT_amount_limt":500000,"order_details":{"particulars":{"air_tickets":{"unit":1,"rate":100000,"total_value":100000,"total_amount":112000,"tax_details":[{"tax_id":4,"tax_included":"E","tax_included_text":"Excluded","tax_name":"IGST","tax_rate":"12%","tax_amnt":12000}]}},"total":{"unit":"1","total_value":100000,"total_tax_amnts":12000,"total_amount":112000,"total_amount_in_words":"One Lakh Twelve Thousands Rupees "}}}
         * MIN_SEAT_PURCHASE_NO : 2
         * MAX_SEAT_PURCHASE_NO : 99
         */

        private PurchaseDataBean purchaseData;
        private int MIN_SEAT_PURCHASE_NO;
        private int MAX_SEAT_PURCHASE_NO;

        public PurchaseDataBean getPurchaseData() {
            return purchaseData;
        }

        public void setPurchaseData(PurchaseDataBean purchaseData) {
            this.purchaseData = purchaseData;
        }

        public int getMIN_SEAT_PURCHASE_NO() {
            return MIN_SEAT_PURCHASE_NO;
        }

        public void setMIN_SEAT_PURCHASE_NO(int MIN_SEAT_PURCHASE_NO) {
            this.MIN_SEAT_PURCHASE_NO = MIN_SEAT_PURCHASE_NO;
        }

        public int getMAX_SEAT_PURCHASE_NO() {
            return MAX_SEAT_PURCHASE_NO;
        }

        public void setMAX_SEAT_PURCHASE_NO(int MAX_SEAT_PURCHASE_NO) {
            this.MAX_SEAT_PURCHASE_NO = MAX_SEAT_PURCHASE_NO;
        }

    }
}
