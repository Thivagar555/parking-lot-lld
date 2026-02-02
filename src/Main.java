import enums.VehicleType;
import factory.VehicleFactory;
import model.ParkingTicket;
import model.Vehicle;
import service.ParkingLot;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Singleton ParkingLot -- bill pugh instantiated
        ParkingLot instance = ParkingLot.getInstance();
        while(true)
        {
            System.out.println(
                    "\n1.VIEW PARKING SLOTS" +
                            "\n2.PARK VEHICLE" +
                            "\n3.EXIT PARKING AND PAY" +
                            "\n4.CLOSE APPLICATION" +
                            "\nEnter your choice : "
            );
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    // Observer already handles display
                    instance.notifyObservers();
                    instance.displayParkingSlots();
                    break;
                case 2:
                    parkVehicleFlow(scanner, instance);
                    break;
                case 3:
                    exitVehicleFlow(scanner, instance);
                    break;
                case 4:
                    System.out.println("Exiting system...");
                    return;
                default:
                    System.out.println("Invalid choice.");

            }
        }
    }

        private static void parkVehicleFlow (Scanner scanner, ParkingLot instance)
        {
            System.out.print("Enter Vehicle number: ");
            String number = scanner.nextLine();
            System.out.print("Enter Vehicle Type:(CAR/BIKE/TRUCK) : ");
            VehicleType vehicleType = VehicleType.valueOf(scanner.next().toUpperCase());
            Vehicle vehicle = VehicleFactory.createVehicle(vehicleType, number);
            //parking vehile
            boolean isParked = instance.parkVehicle(vehicle);
            if (isParked) {
                ParkingTicket ticket = new ParkingTicket("T - " + number, number);
                instance.activeTickets.put(number, ticket);
                System.out.println("\nTicket id: " + ticket.getTicketId() +
                        "\nParked spot : " + ticket.getParkedSpot() +
                        "\nEntered time : " + ticket.getEntryTime()
                );
            } else {
                System.out.println("NO PARKING SLOT AVAILABLE");
            }
        }
        private static void exitVehicleFlow (Scanner scanner, ParkingLot instance)
        {
            System.out.print("Enter Vehicle number: ");
            String number = scanner.nextLine();
            double amt = 0;
            if (instance.unparkVehicle(number)) {
                ParkingTicket ticket = instance.activeTickets.get(number);
                amt = instance.calculateFee(ticket.getDurationInHours());
                long duration = ticket.getDurationInHours();
                System.out.println("\nVehicle Number : " + number);
                System.out.println("Exit time : " + ticket.getExittime());
                if (duration > 24) {
                    System.out.println("\nParked Duration is " + duration / 24 + " Days");
                } else {
                    System.out.println("\nParked Duration is " + duration + " Hours");
                }
                System.out.println("\nTotal Bill Amount in INR : " + amt);
            }
        }
}
