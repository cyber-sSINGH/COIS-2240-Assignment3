// Vehicle.java
public abstract class Vehicle {
    public enum Status {
        Available, Held, Rented, UnderMaintenance, OutOfService
    }

    private String licensePlate; // stored uppercase
    private String make;         // First char upper, rest lower
    private String model;        // First char upper, rest lower
    private int year;
    private Status status;

    // Constructor: sets make/model/year; licensePlate empty; status Available
    public Vehicle(String make, String model, int year) {
        this.make = formatName(make);
        this.model = formatName(model);
        this.year = year;
        this.status = Status.Available;
        this.licensePlate = "";
    }

    // Default constructor
    public Vehicle() {
        this("Unknown", "Generic", 2000);
    }

    private String formatName(String s) {
        if (s == null) return "";
        s = s.trim();
        if (s.isEmpty()) return "";
        if (s.length() == 1) return s.toUpperCase();
        return s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
    }

    // Setters & getters
    public void setLicensePlate(String plate) {
        if (plate == null) plate = "";
        this.licensePlate = plate.trim().toUpperCase();
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    public String getLicensePlate() { return licensePlate; }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public Status getStatus() { return status; }

    // Returns the vehicle details separated by tabs: Plate\tMake\tModel\tYear\tStatus
    public String getInfo() {
        return getLicensePlate() + "\t" + getMake() + "\t" + getModel() + "\t" + getYear() + "\t" + getStatus();
    }
}
