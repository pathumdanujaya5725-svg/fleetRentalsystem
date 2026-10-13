package ijse.fleet;

public abstract class Vehicle implements Reportable {
    private String vehicleId;
    private double dailyRate;
    private FuelType fuelType;
    private RentalStatus status;

    public Vehicle(String vehicleId, double dailyRate, FuelType fuelType) {
        this.vehicleId = vehicleId;
        this.dailyRate = dailyRate;
        this.fuelType = fuelType;
        this.status = RentalStatus.AVAILABLE;
    }

    public abstract double calculateRentalCost(int rentalDays);

    public String getVehicleId() {
        return vehicleId;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public RentalStatus getStatus() {
        return status;
    }

    public void setStatus(RentalStatus status) {
        this.status = status;
    }

    @Override
    public String getReportCategory() {
        return "VEHICLE";
    }

    @Override
    public String getSummary() {
        return vehicleId + " - Status: " + status + " (Rate: " + dailyRate + ")";
    }

    @Override
    public String toString() {
        String vehicleType = this.getClass().getSimpleName().toUpperCase();
        return vehicleId + " [" + vehicleType + " - " + fuelType + "] - Daily Rate: " + dailyRate + " - Status: " + status;
    }
}