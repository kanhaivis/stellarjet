package com.ns.model.PaymentResponse;

import java.util.List;

public class ParticularsBean {
    /**
     * membership : {"total_value":"300000","total_amount":354000,"total_taxes":54000,"tax_details":[{"tax_id":3,"tax_included":"E","tax_included_text":"Excluded","tax_name":"IGST","tax_rate":"18%","total_value":"300000","tax_amnt":54000}],"unit":1,"rate":300000,"membership_title":"Gold","membership_prepaid":2}
     */

    private MembershipBean membership;

    public MembershipBean getMembership() {
        return membership;
    }

    public void setMembership(MembershipBean membership) {
        this.membership = membership;
    }

}
