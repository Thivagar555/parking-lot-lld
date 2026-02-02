package service;

import enums.VehicleType;
import model.ParkingFloor;
import model.ParkingSpot;
import model.ParkingTicket;
import model.Vehicle;
import observer.Observer;
import strategy.DayPricingStrategy;
import strategy.HourlyPricingStrategy;
import strategy.PricingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
        private ParkingLot() {
            this.floors = new ArrayList<>();
            this.observers = new ArrayList<>();
            this.activeTickets = new HashMap<>();
            loadData();
        }

    private void loadData() {
        ParkingFloor floor1 = new ParkingFloor("F1");
        floor1.addParkingSpot(new ParkingSpot("C1", VehicleType.CAR));
        floor1.addParkingSpot(new ParkingSpot("C2", VehicleType.CAR));
        floor1.addParkingSpot(new ParkingSpot("B1", VehicleType.BIKE));
        floor1.addParkingSpot(new ParkingSpot("B2", VehicleType.BIKE));
        floor1.addParkingSpot(new ParkingSpot("T1", VehicleType.TRUCK));
        floor1.addParkingSpot(new ParkingSpot("T2", VehicleType.TRUCK));
        addFloor(floor1);
        ParkingFloor floor2 = new ParkingFloor("F2");
        floor2.addParkingSpot(new ParkingSpot("C1", VehicleType.CAR));
        floor2.addParkingSpot(new ParkingSpot("C2", VehicleType.CAR));
        floor2.addParkingSpot(new ParkingSpot("B1", VehicleType.BIKE));
        floor2.addParkingSpot(new ParkingSpot("B2", VehicleType.BIKE));
        addFloor(floor2);
    }

    //BILL PUGH SINGLETON
        private static class Helper {
            public static final ParkingLot Instance =  new ParkingLot();
        }
        public static ParkingLot getInstance()
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
    public Map<String, ParkingTicket> activeTickets;
        // ===== Composition =====
        private  List<ParkingFloor> floors;

    // ===== Strategy =====
    private PricingStrategy pricingStrategy;

    // ===== Observer =====
    private List<Observer> observers;

    public void addFloor(ParkingFloor floor)
    {
        floors.add(floor);
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
                ParkingTicket ticket = activeTickets.get(vehicleNumber);
                if(ticket != null)
                {
                    ticket.closeTicket();
                }
                notifyObservers();
                return true;
            }
        }
        return false;
    }
    public void setPricingStrategy(long t) {
        if(t>24)
        {
            pricingStrategy = new DayPricingStrategy();
        }
        else if(t>0) {
            pricingStrategy = new HourlyPricingStrategy();
        }
    }
    public double calculateFee(long time) {
        if (pricingStrategy == null) {
            throw new IllegalStateException("Invalid pricingStrategy");
        }
        return pricingStrategy.calculatePrice(time);
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

    public String getParkedSpot(String vehicleNumber)
    {
        for(ParkingFloor floor : floors)
        {
            String spotId = floor.getSpot(vehicleNumber);
            if(spotId != null)
            {
                return floor.getFloorId()+spotId;
            }
        }
        return null;
    }
    public void displayParkingSlots() {
        for (ParkingFloor floor : floors) {
            floor.displayAvailableSpots();
        }
    }


}
