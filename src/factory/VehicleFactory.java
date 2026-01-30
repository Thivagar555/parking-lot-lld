package factory;

import enums.VehicleType;
import model.Bike;
import model.Car;
import model.Truck;
import model.Vehicle;

public class VehicleFactory {

    public static Vehicle createVehicle(VehicleType vehicleType, String VehicleNumber) {
        switch (vehicleType) {
            case CAR :
                return new Car(VehicleNumber);
                case TRUCK :
                    return new Truck(VehicleNumber);
            case BIKE:
                return new Bike(VehicleNumber);
                default :
                    throw new IllegalArgumentException("Invalid vehicle Type");
        }
    }
}
