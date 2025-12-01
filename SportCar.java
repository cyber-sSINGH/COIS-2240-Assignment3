// SportCar.java
public final class SportCar extends Car {

    private int horsepower;
    private boolean hasTurbo;

    public SportCar(String make, String model, int year, int numSeats, int horsepower, boolean hasTurbo) {
        super(make, model, year, numSeats);
        this.horsepower = horsepower;
        this.hasTurbo = hasTurbo;
    }

    public int getHorsepower() { return horsepower; }
    public boolean hasTurbo() { return hasTurbo; }

    @Override
    public String getInfo() {
        return super.getInfo() + "\t" + horsepower + "HP\tTurbo:" + (hasTurbo ? "Yes" : "No");
    }
}
