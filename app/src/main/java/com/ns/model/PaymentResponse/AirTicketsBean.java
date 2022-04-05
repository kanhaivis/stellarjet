package com.ns.model.PaymentResponse;

import java.io.Serializable;
import java.util.List;

public class AirTicketsBean implements Serializable {
    /**
     * unit : 1
     * rate : 100000
     * total_value : 100000
     * total_amount : 112000
     * tax_details : [{"tax_id":4,"tax_included":"E","tax_included_text":"Excluded","tax_name":"IGST","tax_rate":"12%","tax_amnt":12000}]
     */

    private int unit;
    private int rate;
    private int total_value;
    private int total_amount;
    private List<TaxDetailsBean> tax_details;

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
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

    public List<TaxDetailsBean> getTax_details() {
        return tax_details;
    }

    public void setTax_details(List<TaxDetailsBean> tax_details) {
        this.tax_details = tax_details;
    }

}
