package com.ns.model.FeedBack;

public class FeedBackDetailsRequest {

    private int RATING;
    private int ID;
    private String DESCRIPTION;

    public int getRATING() {
        return RATING;
    }

    public void setRATING(int RATING) {
        this.RATING = RATING;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public FeedBackDetailsRequest(int RATING, int ID, String DESCRIPTION) {
        this.RATING = RATING;
        this.ID = ID;
        this.DESCRIPTION = DESCRIPTION;
    }
}
