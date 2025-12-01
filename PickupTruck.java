// PickupTruck.java
public class PickupTruck extends Vehicle implements Rentable {

    private double cargoSize;      
    private boolean hasTrailer;    

    public PickupTruck(String make, String model, int year, double cargoSize, boolean hasTrailer) {
        super(make, model, year);
        if (cargoSize <= 0) {
            throw new IllegalArgumentException("cargoSize must be greater than zero.");
        }
        this.cargoSize = cargoSize;
        this.hasTrailer = hasTrailer;
    }

    public double getCargoSize() { return cargoSize; }
    public boolean hasTrailer() { return hasTrailer; }

    @Override
    public String getInfo() {
        return super.getInfo() + "\t" + cargoSize + "ft";
    }
    
    // Implementation of Rentable interface methods
    @Override
    public void rentVehicle() {
        setStatus(Status.Rented);
        System.out.println("PickupTruck " + getLicensePlate() + " has been rented.");
    }

    @Override
    public void returnVehicle() {
        setStatus(Status.Available);
        System.out.println("PickupTruck " + getLicensePlate() + " has been returned.");
    }
}
