package com.ns.model;

import java.io.Serializable;

public class SeatSelectionRequest implements Serializable {
    private String seatId;
    private String seatName;
    private boolean isSelected;

    public SeatSelectionRequest() {
    }

    public SeatSelectionRequest(String seatId, String seatName) {
        this.seatId = seatId;
        this.seatName = seatName;
    }

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }
}
