package ijse.fleet;

public class Van extends Vehicle {
    private int seatingCapacity;

    public Van(String vehicleId, double dailyRate, FuelType fuelType, int seatingCapacity) {
        super(vehicleId, dailyRate, fuelType);
        this.seatingCapacity = seatingCapacity;
    }

    @Override
    public double calculateRentalCost(int rentalDays) {
        return (getDailyRate() * rentalDays * getFuelType().getCostMultiplier()) + (getDailyRate() * 0.20);
    }

    @Override
    public String getSummary() {
        return super.getSummary() + " [Van, Seats: " + seatingCapacity + "]";
    }
}