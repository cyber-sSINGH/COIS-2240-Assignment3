import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.io.*;

// Singleton RentalSystem with file-based storage
public class RentalSystem {

    private static RentalSystem instance = null; // Singleton instance

    private List<Vehicle> vehicles = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private RentalHistory rentalHistory = new RentalHistory();

    private final String VEHICLE_FILE = "vehicles.txt";
    private final String CUSTOMER_FILE = "customers.txt";
    private final String RECORD_FILE = "rental_records.txt";

    
    // Private constructor for Singleton
    // Loads previously saved data
    
    private RentalSystem() {
        loadData();
    }

    
    // Public method to get the single instance
    
    public static RentalSystem getInstance() {
        if (instance == null) {
            instance = new RentalSystem();
        }
        return instance;
    }

    
    // Add vehicle with duplicate check and file save
    // Returns true if added, false if duplicate
    
    public boolean addVehicle(Vehicle vehicle) {
        if (vehicle == null) return false;
        if (findVehicleByPlate(vehicle.getLicensePlate()) != null) {
            System.out.println("Duplicate license plate. Vehicle not added.");
            return false;
        }
        vehicles.add(vehicle);
        saveVehicle(vehicle); // persist to file
        return true;
    }

    
    // Add customer with duplicate check and file save
    // Returns true if added, false if duplicate
    
    public boolean addCustomer(Customer customer) {
        if (customer == null) return false;
        if (findCustomerById(customer.getCustomerId()) != null) {
            System.out.println("Duplicate customer ID. Customer not added.");
            return false;
        }
        customers.add(customer);
        saveCustomer(customer); // persist to file
        return true;
    }

    
    // Original rent/return methods 
    
    public boolean rentVehicle(String licensePlate, String customerName) {
        if (licensePlate == null) return false;
        Vehicle v = findVehicleByPlate(licensePlate);
        if (v == null || v.getStatus() != Vehicle.Status.Available) return false;

        if (v instanceof Rentable) ((Rentable) v).rentVehicle();
        else v.setStatus(Vehicle.Status.Rented);

        return true;
    }

    public boolean returnVehicle(String licensePlate) {
        if (licensePlate == null) return false;
        Vehicle v = findVehicleByPlate(licensePlate);
        if (v == null || v.getStatus() != Vehicle.Status.Rented) return false;

        if (v instanceof Rentable) ((Rentable) v).returnVehicle();
        else v.setStatus(Vehicle.Status.Available);

        return true;
    }

    
    // Rent/Return with RentalRecord and RentalHistory (empty bodies for now)
    
    public boolean rentVehicle(Vehicle vehicle, Customer customer, LocalDate rentalDate, double amount) {
        // To be implemented: create rental record & save
        return false;
    }

    public boolean returnVehicle(Vehicle vehicle, Customer customer, LocalDate returnDate, double extraAmount) {
        // To be implemented: create rental record & save
        return false;
    }

    
    // Display available vehicles
    
    public void displayAvailableVehicles() {
        System.out.println("| Type\t| Plate\t| Make\t| Model\t| Year |");
        System.out.println("----------------------------------------------------------------");
        for (Vehicle v : vehicles) {
            if (v.getStatus() == Vehicle.Status.Available) {
                String type = (v instanceof Car) ? "CAR" : (v instanceof Minibus ? "MINIBUS" : "VEHICLE");
                System.out.printf("| %s\t| %s\t| %s\t| %s\t| %d |\n",
                        type, v.getLicensePlate(), v.getMake(), v.getModel(), v.getYear());
            }
        }
    }

    
    // Find vehicle by license plate
    
    public Vehicle findVehicleByPlate(String plate) {
        if (plate == null) return null;
        for (Vehicle v : vehicles) {
            if (v.getLicensePlate().equalsIgnoreCase(plate)) return v;
        }
        return null;
    }

    
    // Find customer by ID
    
    public Customer findCustomerById(String id) {
        if (id == null) return null;
        for (Customer c : customers) {
            if (c.getCustomerId().equalsIgnoreCase(id)) return c;
        }
        return null;
    }

    
    // File-based save methods
    
    public void saveVehicle(Vehicle vehicle) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(VEHICLE_FILE, true))) {
            bw.write(vehicle.getInfo());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error saving vehicle: " + e.getMessage());
        }
    }

    public void saveCustomer(Customer customer) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CUSTOMER_FILE, true))) {
            bw.write(customer.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error saving customer: " + e.getMessage());
        }
    }

    public void saveRecord(RentalRecord record) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RECORD_FILE, true))) {
            bw.write(record.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error saving rental record: " + e.getMessage());
        }
    }

    
    // Load data from files on startup
    
    private void loadData() {
    }
    }
    

