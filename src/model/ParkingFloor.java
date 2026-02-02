package model;

import enums.VehicleType;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloor {
    private String floorId;
    private List<ParkingSpot> parkingSpots;

    public ParkingFloor(String floorId) {
        this.floorId = floorId;
        this.parkingSpots = new ArrayList<>();
    }
    public String getFloorId() {
        return floorId;
    }
    // Composition: Floor owns the spots
    public void addParkingSpot(ParkingSpot spot) {
        parkingSpots.add(spot);
    }
    public ParkingSpot getAvailableSpot(VehicleType vehicleType) {
        for (ParkingSpot spot : parkingSpots) {
            if(spot.isFree() && spot.getSupportedvehicleType() == vehicleType) {
                return spot;
            }
        }
        return null;
    }
    public boolean parkVehicle(Vehicle vehicle) {
        ParkingSpot parkingSpot = getAvailableSpot(vehicle.getVehicleType());
        if(parkingSpot == null) {
            return false;
        }
        return parkingSpot.ParkVehicle(vehicle);
    }
    public boolean unParkVehicle(String vehicleNumber) {
        for (ParkingSpot spot : parkingSpots) {
            if (!spot.isFree() &&
                    spot.getParkedVehicle().getVehicleNumber()
                            .equals(vehicleNumber)) {

                spot.unParkVehicle();
                return true;
            }
        }
        return false;
    }
    public void displayAvailableSpots() {

        boolean hasFreeSpots = false;

        for (ParkingSpot spot : parkingSpots) {
            if (spot.isFree()) {
                hasFreeSpots = true;
                spot.display();   // 👈 delegated to ParkingSpot
            }
        }

        if (!hasFreeSpots) {
            System.out.println("No available spots on floor " + floorId);
        }
    }

}
