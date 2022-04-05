package com.ns.model.FeedBack;

public class FeedBackCategoriesDataBean {
    /**
     * id : 1
     * feedback_type : Flight Experience
     * display_order : 1
     */

    private int id;
    private String feedback_type;
    private int display_order;
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFeedback_type() {
        return feedback_type;
    }

    public void setFeedback_type(String feedback_type) {
        this.feedback_type = feedback_type;
    }

    public int getDisplay_order() {
        return display_order;
    }

    public void setDisplay_order(int display_order) {
        this.display_order = display_order;
    }
}
