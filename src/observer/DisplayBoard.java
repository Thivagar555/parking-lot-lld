package observer;

import model.ParkingFloor;
import model.ParkingSpot;

import java.util.List;

public class DisplayBoard implements Observer {
    private String floorId;
    private List<ParkingFloor> floors;
    public DisplayBoard(String floorId,  List<ParkingFloor> floors) {
        this.floorId = floorId;
        this.floors = floors;
    }
    @Override
    public void update() {
        System.out.println("\nDisplay updated for floor: " + floorId);

        for(ParkingFloor floor : floors) {
            if(floorId.equals(floor.getFloorId())) {
                floor.displayAvailableSpots();
                break;
            }
        }
    }
}
