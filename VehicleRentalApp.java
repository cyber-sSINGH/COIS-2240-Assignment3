// VehicleRentalApp.java
import java.util.Scanner;

public class VehicleRentalApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RentalSystem rentalSystem = RentalSystem.getInstance();

        while (true) {
            System.out.println();
            System.out.println("Vehicle Rental System - Menu");
            System.out.println("1: Add Vehicle");
            System.out.println("2: Rent Vehicle");
            System.out.println("3: Return Vehicle");
            System.out.println("4: Display Available Vehicles");
            System.out.println("5: Exit");
            System.out.print("Select option (1-5): ");

            String line = scanner.nextLine().trim();
            int choice;
            try {
                choice = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection. Please enter a number 1-5.");
                continue;
            }

            if (choice == 1) {
                System.out.println("Select vehicle type: 1: Car  2: Minibus");
                String typeLine = scanner.nextLine().trim();
                int type;
                try {
                    type = Integer.parseInt(typeLine);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid selection.");
                    continue;
                }

                if (type == 1) { // Car
                    System.out.print("Enter licensePlate: ");
                    String plate = scanner.nextLine().trim();
                    System.out.print("Enter make: ");
                    String make = scanner.nextLine().trim();
                    System.out.print("Enter model: ");
                    String model = scanner.nextLine().trim();
                    System.out.print("Enter year: ");
                    int year;
                    try {
                        year = Integer.parseInt(scanner.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid year.");
                        continue;
                    }
                    System.out.print("Enter numSeats: ");
                    int numSeats;
                    try {
                        numSeats = Integer.parseInt(scanner.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number of seats.");
                        continue;
                    }

                    Vehicle vehicle;
                    try {
                        vehicle = new Car(make, model, year, numSeats);
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Error: " + ex.getMessage());
                        continue;
                    }
                    vehicle.setLicensePlate(plate);
                    boolean added = rentalSystem.addVehicle(vehicle);
                    if (added) System.out.println("Car has been added successfully.");
                    else System.out.println("Add failed: duplicate plate or invalid data.");

                } else if (type == 2) { // Minibus
                    System.out.print("Enter licensePlate: ");
                    String plate = scanner.nextLine().trim();
                    System.out.print("Enter make: ");
                    String make = scanner.nextLine().trim();
                    System.out.print("Enter model: ");
                    String model = scanner.nextLine().trim();
                    System.out.print("Enter year: ");
                    int year;
                    try {
                        year = Integer.parseInt(scanner.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid year.");
                        continue;
                    }
                    System.out.print("Is accessible (yes/no): ");
                    String acc = scanner.nextLine().trim().toLowerCase();
                    boolean isAccessible = acc.equals("yes") || acc.equals("y") || acc.equals("true");

                    Vehicle vehicle = new Minibus(make, model, year, isAccessible);
                    vehicle.setLicensePlate(plate);
                    boolean added = rentalSystem.addVehicle(vehicle);
                    if (added) System.out.println("Minibus has been added successfully.");
                    else System.out.println("Add failed: duplicate plate or invalid data.");

                } else {
                    System.out.println("Invalid vehicle type selection.");
                }

            } else if (choice == 2) { // Rent
                System.out.print("Enter licensePlate to rent: ");
                String plate = scanner.nextLine().trim();
                System.out.print("Enter customerName: ");
                String name = scanner.nextLine().trim();
                boolean ok = rentalSystem.rentVehicle(plate, name);
                if (ok) System.out.println("Renting successful.");
                else System.out.println("Renting failed (not available or not found).");

            } else if (choice == 3) { // Return
                System.out.print("Enter licensePlate to return: ");
                String plate = scanner.nextLine().trim();
                boolean ok = rentalSystem.returnVehicle(plate);
                if (ok) System.out.println("Return successful.");
                else System.out.println("Return failed (not rented or not found).");

            } else if (choice == 4) { // Display available
                rentalSystem.displayAvailableVehicles();

            } else if (choice == 5) { // Exit
                scanner.close();
                System.out.println("Exiting application...");
                System.exit(0);
            } else {
                System.out.println("Invalid option. Please choose 1-5.");
            }
        }
    }
}
