package model;

import enums.VehicleType;

public abstract class Vehicle {
    protected VehicleType vehicleType;
    protected String VehicleNumber;

    //constructor will be called when you create an instance of a subclass that is inherited
    public Vehicle(VehicleType vehicleType, String VehicleNumber) {
        this.vehicleType = vehicleType;
        this.VehicleNumber = VehicleNumber;
    }
    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getVehicleNumber() {
        return VehicleNumber;
    }
}
