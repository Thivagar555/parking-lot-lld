package model;

import enums.VehicleType;

public class ParkingSpot {
    private String spotId;
    private VehicleType supportedvehicleType;
    private Vehicle parkedVehicle;
    private boolean isFree;
    public ParkingSpot(String spotId, VehicleType supportedvehicleType) {
        this.spotId = spotId;
        this.supportedvehicleType = supportedvehicleType;
        isFree = true;
    }
    public boolean isFree() {
        return isFree;
    }
    public VehicleType getSupportedvehicleType() {
        return supportedvehicleType;
    }
    public String getSpotId() {
        return spotId;
    }
    // Association: ParkingSpot ↔ Vehicle
    public boolean ParkVehicle(Vehicle vehicle) {
        if(!isFree) {
            return false;
        }
        if(vehicle.getVehicleType() != supportedvehicleType) {
            return false;
        }
        this.parkedVehicle = vehicle;
        isFree = false;
        return true;
    }
    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }
    public void unParkVehicle() {
        this.parkedVehicle = null;
        isFree = true;
    }
    public void display() {
        System.out.println(
                "Spot ID: " + spotId +
                        " | Type: " + supportedvehicleType
        );
    }

}
