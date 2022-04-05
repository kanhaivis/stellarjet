package com.ns.model.CancelBooking;

import java.io.Serializable;

public class CancelBookingInfo implements Serializable {

    private String SeatName;
    private String GuestName;
    private int seatId;

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    private boolean checkStatus ;

    public CancelBookingInfo(String seatName, String guestName, int seatId, boolean checkStatus) {
        SeatName = seatName;
        GuestName = guestName;
        this.seatId = seatId;
        this.checkStatus = checkStatus;
    }

    public String getSeatName() {
        return SeatName;
    }

    public String getGuestName() {
        return GuestName;
    }

    public boolean isCheckStatus() {
        return checkStatus;
    }

    public void setSeatName(String seatName) {
        SeatName = seatName;
    }

    public void setGuestName(String guestName) {
        GuestName = guestName;
    }

    public void setCheckStatus(boolean checkStatus) {
        this.checkStatus = checkStatus;
    }
}
