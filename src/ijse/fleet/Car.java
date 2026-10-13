package ijse.fleet;

public class Car extends Vehicle {
    private int passengerCapacity;

    public Car(String vehicleId, double dailyRate, FuelType fuelType, int passengerCapacity) {
        super(vehicleId, dailyRate, fuelType);
        this.passengerCapacity = passengerCapacity;
    }

    @Override
    public double calculateRentalCost(int rentalDays) {
        return getDailyRate() * rentalDays * getFuelType().getCostMultiplier();
    }

    @Override
    public String getSummary() {
        return super.getSummary() + " [Car, Seats: " + passengerCapacity + "]";
    }
}