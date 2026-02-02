package model;

import java.time.Duration;
import java.time.LocalDateTime;

public class ParkingTicket {
    private final String ticketId;
    private final String vehicleNumber;
    private final LocalDateTime entrytime;
    private LocalDateTime exittime;
    public ParkingTicket(String ticketId, String vehicleNumber) {
        this.ticketId = ticketId;
        this.vehicleNumber = vehicleNumber;
        this.entrytime = LocalDateTime.now();
    }
    public void closeTicket() {
        this.exittime = LocalDateTime.now();
    }
    public long getDurationInHours() {
        if(this.exittime == null) {
            throw new IllegalStateException("Vehicle has not exited yet");
        }

        long hours= Duration.between(this.entrytime, this.exittime).toHours();
        return (hours == 0) ? 1 : hours; //minimum one hour
    }

    public String getTicketId() {
        return ticketId;
    }
    public String getVehicleNumber() {
        return vehicleNumber;
    }

}
