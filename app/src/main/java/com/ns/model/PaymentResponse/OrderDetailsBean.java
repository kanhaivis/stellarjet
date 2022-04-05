package com.ns.model.PaymentResponse;

import java.io.Serializable;

public class OrderDetailsBean implements Serializable {
    /**
     * particulars : {"air_tickets":{"unit":1,"rate":100000,"total_value":100000,"total_amount":112000,"tax_details":[{"tax_id":4,"tax_included":"E","tax_included_text":"Excluded","tax_name":"IGST","tax_rate":"12%","tax_amnt":12000}]}}
     * total : {"unit":"1","total_value":100000,"total_tax_amnts":12000,"total_amount":112000,"total_amount_in_words":"One Lakh Twelve Thousands Rupees "}
     */

    private ParticularsBean particulars;
    private TotalBean total;

    public ParticularsBean getParticulars() {
        return particulars;
    }

    public void setParticulars(ParticularsBean particulars) {
        this.particulars = particulars;
    }

    public TotalBean getTotal() {
        return total;
    }

    public void setTotal(TotalBean total) {
        this.total = total;
    }

    public static class ParticularsBean implements Serializable{
        /**
         * air_tickets : {"unit":1,"rate":100000,"total_value":100000,"total_amount":112000,"tax_details":[{"tax_id":4,"tax_included":"E","tax_included_text":"Excluded","tax_name":"IGST","tax_rate":"12%","tax_amnt":12000}]}
         */

        private AirTicketsBean air_tickets;

        public AirTicketsBean getAir_tickets() {
            return air_tickets;
        }

        public void setAir_tickets(AirTicketsBean air_tickets) {
            this.air_tickets = air_tickets;
        }

    }

}
