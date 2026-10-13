package ijse.fleet;

public class Truck extends Vehicle {
    private double cargoCapacityTons;

    public Truck(String vehicleId, double dailyRate, FuelType fuelType, double cargoCapacityTons) {
        super(vehicleId, dailyRate, fuelType);
        this.cargoCapacityTons = cargoCapacityTons;
    }

    @Override
    public double calculateRentalCost(int rentalDays) {
        return (getDailyRate() * rentalDays * getFuelType().getCostMultiplier()) + (cargoCapacityTons * 10.00 * rentalDays);
    }

    @Override
    public String getSummary() {
        return super.getSummary() + " [Truck, Capacity: " + cargoCapacityTons + "T]";
    }
}