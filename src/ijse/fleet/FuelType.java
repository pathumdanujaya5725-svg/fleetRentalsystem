package ijse.fleet;

public enum FuelType {
    PETROL(1.1),
    DIESEL(1.0),
    HYBRID(1.2),
    ELECTRIC(1.3);

    private final double costMultiplier;

    FuelType(double costMultiplier) {
        this.costMultiplier = costMultiplier;
    }

    public double getCostMultiplier() {
        return costMultiplier;
    }
}