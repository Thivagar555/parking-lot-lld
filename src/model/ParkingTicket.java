package model;

import service.ParkingLot;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParkingTicket {
    private final String ticketId;
    private final String vehicleNumber;
    private final LocalDateTime entrytime;
    private LocalDateTime exittime;
    private String parkedSpot;
    ParkingLot instance;

    public ParkingTicket(String ticketId, String vehicleNumber) {
        instance = ParkingLot.getInstance();
        this.ticketId = ticketId;
        this.vehicleNumber = vehicleNumber;
        entrytime = LocalDateTime.now();
        parkedSpot = instance.getParkedSpot(vehicleNumber);
    }

    public void closeTicket() {
        this.exittime = LocalDateTime.now();
        instance.setPricingStrategy(getDurationInHours());
    }
    public long getDurationInHours() {
        if(exittime == null) {
            throw new IllegalStateException("Vehicle has not exited yet");
        }

        long hours= Duration.between(entrytime, exittime).toHours();
        return (hours == 0) ? 1 : hours; //minimum one hour
    }

    public String getTicketId() {
        return ticketId;
    }
    public String getVehicleNumber() {
        return vehicleNumber;
    }
    public String getParkedSpot() {
        return parkedSpot;
    }
    public String getExittime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm: a");
        return exittime.format(dtf);
    }
    public String getEntryTime()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm");
        return entrytime.format(dtf);
    }


}
