package com.ns.model.PaymentResponse;

public class MemberShipDataBean {
    /**
     * particulars : {"membership":{"total_value":"300000","total_amount":354000,"total_taxes":54000,"tax_details":[{"tax_id":3,"tax_included":"E","tax_included_text":"Excluded","tax_name":"IGST","tax_rate":"18%","total_value":"300000","tax_amnt":54000}],"unit":1,"rate":300000,"membership_title":"Gold","membership_prepaid":2}}
     * total : {"unit":1,"total_value":300000,"total_tax_amnts":54000,"total_amount":354000,"total_amount_in_words":"Three Lakh Fifty Four Thousands Rupees "}
     * DIRECT_PAYMENT_AMOUNT_LIMIT : 500000
     * MIN_SEAT_PURCHASE_NO : 2
     * MAX_SEAT_PURCHASE_NO : 99
     */

    private ParticularsBean particulars;
    private TotalBean total;
    private int DIRECT_PAYMENT_AMOUNT_LIMIT;
    private int MIN_SEAT_PURCHASE_NO;
    private int MAX_SEAT_PURCHASE_NO;

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

    public int getDIRECT_PAYMENT_AMOUNT_LIMIT() {
        return DIRECT_PAYMENT_AMOUNT_LIMIT;
    }

    public void setDIRECT_PAYMENT_AMOUNT_LIMIT(int DIRECT_PAYMENT_AMOUNT_LIMIT) {
        this.DIRECT_PAYMENT_AMOUNT_LIMIT = DIRECT_PAYMENT_AMOUNT_LIMIT;
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