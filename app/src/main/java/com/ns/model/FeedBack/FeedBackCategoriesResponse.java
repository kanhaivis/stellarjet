package com.ns.model.FeedBack;

import java.util.List;

public class FeedBackCategoriesResponse {
    /**
     * resultcode : 1
     * message : Feedback Categories
     * data : [{"id":1,"feedback_type":"Flight Experience","display_order":1},{"id":2,"feedback_type":"Food Experience","display_order":2},{"id":3,"feedback_type":"Limousine Experience","display_order":3}]
     */

    private int resultcode;
    private String message;
    private List<FeedBackCategoriesDataBean> data;

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

    public List<FeedBackCategoriesDataBean> getData() {
        return data;
    }

    public void setData(List<FeedBackCategoriesDataBean> data) {
        this.data = data;
    }

}
