package com.ns.model;

import java.util.List;

public class OrderResponse {

    /**
     * resultcode : 1
     * message : Seat order details.
     * data : {"no_of_seats":2,"rate":100000,"total_value":200000,"total_amount":200000,"tax_details":[{"tax_id":4,"tax_included":"I","tax_included_text":"Included","tax_name":"IGST","tax_rate":"12%","tax_amnt":21429}],"purchase_id":33,"status":"Processing"}
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
         * no_of_seats : 2
         * rate : 100000
         * total_value : 200000
         * total_amount : 200000
         * tax_details : [{"tax_id":4,"tax_included":"I","tax_included_text":"Included","tax_name":"IGST","tax_rate":"12%","tax_amnt":21429}]
         * purchase_id : 33
         * status : Processing
         */

        private int no_of_seats;
        private int rate;
        private int total_value;
        private int total_amount;
        private int purchase_id;
        private String status;
        private List<TaxDetailsBean> tax_details;

        public int getNo_of_seats() {
            return no_of_seats;
        }

        public void setNo_of_seats(int no_of_seats) {
            this.no_of_seats = no_of_seats;
        }

        public int getRate() {
            return rate;
        }

        public void setRate(int rate) {
            this.rate = rate;
        }

        public int getTotal_value() {
            return total_value;
        }

        public void setTotal_value(int total_value) {
            this.total_value = total_value;
        }

        public int getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(int total_amount) {
            this.total_amount = total_amount;
        }

        public int getPurchase_id() {
            return purchase_id;
        }

        public void setPurchase_id(int purchase_id) {
            this.purchase_id = purchase_id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<TaxDetailsBean> getTax_details() {
            return tax_details;
        }

        public void setTax_details(List<TaxDetailsBean> tax_details) {
            this.tax_details = tax_details;
        }

        public static class TaxDetailsBean {
            /**
             * tax_id : 4
             * tax_included : I
             * tax_included_text : Included
             * tax_name : IGST
             * tax_rate : 12%
             * tax_amnt : 21429
             */

            private int tax_id;
            private String tax_included;
            private String tax_included_text;
            private String tax_name;
            private String tax_rate;
            private int tax_amnt;

            public int getTax_id() {
                return tax_id;
            }

            public void setTax_id(int tax_id) {
                this.tax_id = tax_id;
            }

            public String getTax_included() {
                return tax_included;
            }

            public void setTax_included(String tax_included) {
                this.tax_included = tax_included;
            }

            public String getTax_included_text() {
                return tax_included_text;
            }

            public void setTax_included_text(String tax_included_text) {
                this.tax_included_text = tax_included_text;
            }

            public String getTax_name() {
                return tax_name;
            }

            public void setTax_name(String tax_name) {
                this.tax_name = tax_name;
            }

            public String getTax_rate() {
                return tax_rate;
            }

            public void setTax_rate(String tax_rate) {
                this.tax_rate = tax_rate;
            }

            public int getTax_amnt() {
                return tax_amnt;
            }

            public void setTax_amnt(int tax_amnt) {
                this.tax_amnt = tax_amnt;
            }
        }
    }
}
