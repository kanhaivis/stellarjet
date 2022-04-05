package com.ns.model.PaymentResponse;

import java.util.List;

public class PurchaseSeatDetails {
    /**
     * resultcode : 1
     * message : Seat order details.
     * data : {"purchase_id":207,"purchase_status":"pending","user":6,"total_amount":112000,"total_amount_paid":0,"amount_to_be_paid":112000,"ifsc_code":null,"bank_ac":null,"seat_cost":100000,"NEFT_amount_limt":500000,"order_details":{"particulars":{"air_tickets":{"unit":1,"rate":100000,"total_value":100000,"total_amount":112000,"tax_details":[{"tax_id":4,"tax_included":"E","tax_included_text":"Excluded","tax_name":"IGST","tax_rate":"12%","tax_amnt":12000}]}},"total":{"unit":"1","total_value":100000,"total_tax_amnts":12000,"total_amount":112000,"total_amount_in_words":"One Lakh Twelve Thousands Rupees "}}}
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
        /**
         * purchase_id : 207
         * purchase_status : pending
         * user : 6
         * total_amount : 112000
         * total_amount_paid : 0
         * amount_to_be_paid : 112000
         * ifsc_code : null
         * bank_ac : null
         * seat_cost : 100000
         * NEFT_amount_limt : 500000
         * order_details : {"particulars":{"air_tickets":{"unit":1,"rate":100000,"total_value":100000,"total_amount":112000,"tax_details":[{"tax_id":4,"tax_included":"E","tax_included_text":"Excluded","tax_name":"IGST","tax_rate":"12%","tax_amnt":12000}]}},"total":{"unit":"1","total_value":100000,"total_tax_amnts":12000,"total_amount":112000,"total_amount_in_words":"One Lakh Twelve Thousands Rupees "}}
         */

        private int purchase_id;
        private String purchase_status;
        private int user;
        private int total_amount;
        private int total_amount_paid;
        private int amount_to_be_paid;
        private String ifsc_code;
        private String  bank_ac;
        private int seat_cost;
        private int NEFT_amount_limt;
        private OrderDetailsBean order_details;

        public int getPurchase_id() {
            return purchase_id;
        }

        public void setPurchase_id(int purchase_id) {
            this.purchase_id = purchase_id;
        }

        public String getPurchase_status() {
            return purchase_status;
        }

        public void setPurchase_status(String purchase_status) {
            this.purchase_status = purchase_status;
        }

        public int getUser() {
            return user;
        }

        public void setUser(int user) {
            this.user = user;
        }

        public int getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(int total_amount) {
            this.total_amount = total_amount;
        }

        public int getTotal_amount_paid() {
            return total_amount_paid;
        }

        public void setTotal_amount_paid(int total_amount_paid) {
            this.total_amount_paid = total_amount_paid;
        }

        public int getAmount_to_be_paid() {
            return amount_to_be_paid;
        }

        public void setAmount_to_be_paid(int amount_to_be_paid) {
            this.amount_to_be_paid = amount_to_be_paid;
        }

        public String getIfsc_code() {
            return ifsc_code;
        }

        public void setIfsc_code(String ifsc_code) {
            this.ifsc_code = ifsc_code;
        }

        public String  getBank_ac() {
            return bank_ac;
        }

        public void setBank_ac(String  bank_ac) {
            this.bank_ac = bank_ac;
        }

        public int getSeat_cost() {
            return seat_cost;
        }

        public void setSeat_cost(int seat_cost) {
            this.seat_cost = seat_cost;
        }

        public int getNEFT_amount_limt() {
            return NEFT_amount_limt;
        }

        public void setNEFT_amount_limt(int NEFT_amount_limt) {
            this.NEFT_amount_limt = NEFT_amount_limt;
        }

        public OrderDetailsBean getOrder_details() {
            return order_details;
        }

        public void setOrder_details(OrderDetailsBean order_details) {
            this.order_details = order_details;
        }

    }
}
