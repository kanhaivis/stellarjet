package com.ns.model.FeedBack;

import java.util.List;

public class FeedBackRequest {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private int booking_id;

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    List<FeedBackDetailsRequest> feedback;

    public List<FeedBackDetailsRequest> getFeedbacks() {
        return feedback;
    }

    public void setFeedbacks(List<FeedBackDetailsRequest> feedbacks) {
        this.feedback = feedbacks;
    }
}
