// Minibus.java
public class Minibus extends Vehicle implements Rentable {
    private boolean isAccessible;

    public Minibus(String make, String model, int year, boolean isAccessible) {
        super(make, model, year);
        this.isAccessible = isAccessible;
    }

    public boolean isAccessible() {
        return isAccessible;
    }

    // Override getInfo to include accessibility status (Yes/No)
    @Override
    public String getInfo() {
        String access = isAccessible ? "Yes" : "No";
        return super.getInfo() + "\t" + access;
    }

    @Override
    public void rentVehicle() {
        setStatus(Status.Rented);
        System.out.println("Minibus " + getLicensePlate() + " has been rented.");
    }

    @Override
    public void returnVehicle() {
        setStatus(Status.Available);
        System.out.println("Minibus " + getLicensePlate() + " has been returned.");
    }
}
