// Car.java
public class Car extends Vehicle implements Rentable {
    private int numSeats;

    public Car(String make, String model, int year, int numSeats) {
        super(make, model, year);
        if (numSeats <= 0) {
            throw new IllegalArgumentException("numSeats must be greater than zero.");
        }
        this.numSeats = numSeats;
    }

    public int getNumSeats() {
        return numSeats;
    }

    // Override getInfo to include number of seats at the end
    @Override
    public String getInfo() {
        return super.getInfo() + "\t" + numSeats;
    }

    @Override
    public void rentVehicle() {
        setStatus(Status.Rented);
        System.out.println("Car " + getLicensePlate() + " has been rented.");
    }

    @Override
    public void returnVehicle() {
        setStatus(Status.Available);
        System.out.println("Car " + getLicensePlate() + " has been returned.");
    }
}
