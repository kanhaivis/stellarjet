package com.ns.model.PaymentResponse;

import java.util.List;

public class MembershipCostdetailsResponse {
    /**
     * resultcode : 1
     * message : Membership Cost details
     * data : {"particulars":{"membership":{"total_value":"300000","total_amount":354000,"total_taxes":54000,"tax_details":[{"tax_id":3,"tax_included":"E","tax_included_text":"Excluded","tax_name":"IGST","tax_rate":"18%","total_value":"300000","tax_amnt":54000}],"unit":1,"rate":300000,"membership_title":"Gold","membership_prepaid":2}},"total":{"unit":1,"total_value":300000,"total_tax_amnts":54000,"total_amount":354000,"total_amount_in_words":"Three Lakh Fifty Four Thousands Rupees "},"DIRECT_PAYMENT_AMOUNT_LIMIT":500000,"MIN_SEAT_PURCHASE_NO":2,"MAX_SEAT_PURCHASE_NO":99}
     */

    private int resultcode;
    private String message;
    private MemberShipDataBean data;

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

    public MemberShipDataBean getData() {
        return data;
    }

    public void setData(MemberShipDataBean data) {
        this.data = data;
    }

}
