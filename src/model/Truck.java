package model;

import enums.VehicleType;

public class Truck extends Vehicle {

    public Truck(String VehicleNumber) {
        super(VehicleType.TRUCK, VehicleNumber);
    }
}
