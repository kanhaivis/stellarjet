package com.ns.model.PaymentResponse;

import java.io.Serializable;

public class TotalBean implements Serializable {
    /**
     * unit : 1
     * total_value : 300000
     * total_tax_amnts : 54000
     * total_amount : 354000
     * total_amount_in_words : Three Lakh Fifty Four Thousands Rupees
     */

    private int unit;
    private int total_value;
    private int total_tax_amnts;
    private int total_amount;
    private String total_amount_in_words;

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getTotal_value() {
        return total_value;
    }

    public void setTotal_value(int total_value) {
        this.total_value = total_value;
    }

    public int getTotal_tax_amnts() {
        return total_tax_amnts;
    }

    public void setTotal_tax_amnts(int total_tax_amnts) {
        this.total_tax_amnts = total_tax_amnts;
    }

    public int getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }

    public String getTotal_amount_in_words() {
        return total_amount_in_words;
    }

    public void setTotal_amount_in_words(String total_amount_in_words) {
        this.total_amount_in_words = total_amount_in_words;
    }
}