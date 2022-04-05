package com.ns.model.PaymentResponse;

import java.io.Serializable;

public class TaxDetailsBean implements Serializable {
    /**
     * tax_id : 3
     * tax_included : E
     * tax_included_text : Excluded
     * tax_name : IGST
     * tax_rate : 18%
     * total_value : 300000
     * tax_amnt : 54000
     */

    private int tax_id;
    private String tax_included;
    private String tax_included_text;
    private String tax_name;
    private String tax_rate;
    private String total_value;
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

    public String getTotal_value() {
        return total_value;
    }

    public void setTotal_value(String total_value) {
        this.total_value = total_value;
    }

    public int getTax_amnt() {
        return tax_amnt;
    }

    public void setTax_amnt(int tax_amnt) {
        this.tax_amnt = tax_amnt;
    }
}
