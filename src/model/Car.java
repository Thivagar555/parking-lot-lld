package model;

import enums.VehicleType;

public class Car extends Vehicle {

    public Car( String VehicleNumber) {
        super(VehicleType.CAR, VehicleNumber);
    }

}
