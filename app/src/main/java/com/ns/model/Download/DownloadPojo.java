package com.ns.model.Download;

import java.io.Serializable;

public class DownloadPojo implements Serializable {
    private String SeatName;
    private String Username;
    private String TicketUrlLinked;

    public DownloadPojo(String seatName, String username, String ticketUrlLinked) {
        SeatName = seatName;
        Username = username;
        TicketUrlLinked = ticketUrlLinked;
    }

    public String getSeatName() {
        return SeatName;
    }

    public String getUsername() {
        return Username;
    }

    public String getTicketUrlLinked() {
        return TicketUrlLinked;
    }
}
