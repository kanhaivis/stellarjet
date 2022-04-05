package com.ns.model.PaymentResponse;

import java.util.List;

public class MembershipBean {
    /**
     * total_value : 300000
     * total_amount : 354000
     * total_taxes : 54000
     * tax_details : [{"tax_id":3,"tax_included":"E","tax_included_text":"Excluded","tax_name":"IGST","tax_rate":"18%","total_value":"300000","tax_amnt":54000}]
     * unit : 1
     * rate : 300000
     * membership_title : Gold
     * membership_prepaid : 2
     */

    private String total_value;
    private int total_amount;
    private int total_taxes;
    private int unit;
    private int rate;
    private String membership_title;
    private int membership_prepaid;
    private List<TaxDetailsBean> tax_details;

    public String getTotal_value() {
        return total_value;
    }

    public void setTotal_value(String total_value) {
        this.total_value = total_value;
    }

    public int getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(int total_amount) {
        this.total_amount = total_amount;
    }

    public int getTotal_taxes() {
        return total_taxes;
    }

    public void setTotal_taxes(int total_taxes) {
        this.total_taxes = total_taxes;
    }

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

    public String getMembership_title() {
        return membership_title;
    }

    public void setMembership_title(String membership_title) {
        this.membership_title = membership_title;
    }

    public int getMembership_prepaid() {
        return membership_prepaid;
    }

    public void setMembership_prepaid(int membership_prepaid) {
        this.membership_prepaid = membership_prepaid;
    }

    public List<TaxDetailsBean> getTax_details() {
        return tax_details;
    }

    public void setTax_details(List<TaxDetailsBean> tax_details) {
        this.tax_details = tax_details;
    }

}
