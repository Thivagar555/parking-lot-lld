package service;

import model.ParkingFloor;
import model.Vehicle;
import observer.Observer;
import strategy.PricingStrategy;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
        private ParkingLot() {
            this.floors = new ArrayList<>();
            this.observers = new ArrayList<>();
        }
//BILL PUGH SINGLETON
        private static class Helper {
            public static final ParkingLot Instance =  new ParkingLot();
        }
        public ParkingLot getInstance()
        {
            return Helper.Instance;
        }
        //DOUBLE CHECKED LOCKING SINGLETON
//        private static volatile ParkingLot instance;
//        public ParkingLot getInstance()
//        {
//            if(instance == null)
//            {
//                synchronized (ParkingLot.class)
//                {
//                    if(instance == null)
//                    {
//                        instance = new ParkingLot();
//                    }
//                }
//            }
//            return instance;
//        }
        // ===== Composition =====
        private List<ParkingFloor> floors;

    // ===== Strategy =====
    private PricingStrategy pricingStrategy;

    // ===== Observer =====
    private List<Observer> observers;

    public void addFloor(ParkingFloor floor)
    {
        this.floors.add(floor);
    }
    public boolean parkVehicle(Vehicle vehicle)
    {
        for(ParkingFloor floor : this.floors)
        {
            if(floor.parkVehicle(vehicle))
            {
                notifyObservers();
                return true;
            }
        }
        return false;
    }
    public boolean unparkVehicle(String vehicleNumber)
    {
        for(ParkingFloor floor : this.floors)
        {
            if(floor.unParkVehicle(vehicleNumber))
            {
                notifyObservers();
                return true;
            }
        }
        return false;
    }
    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }
    public double calculateFee(long time) {
        if (pricingStrategy == null) {
            throw new IllegalStateException("Invalid pricingStrategy");
        }
        return pricingStrategy.calculateFee(time);
    }
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }
    public void notifyObservers() {
        for (Observer observer : this.observers) {
            observer.update();
        }
    }
}
