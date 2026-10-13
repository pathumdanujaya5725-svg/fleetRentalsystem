package ijse.fleet;

public class RentalAgreement {
    private static int counter = 1000;
    private final String agreementId;
    private final Vehicle vehicle;
    private final Customer customer;
    private final int rentalDays;
    private final double totalCost;
    private int daysElapsed;

    public RentalAgreement(Vehicle vehicle, Customer customer, int rentalDays) {
        this.agreementId = "AGR-" + (++counter);
        this.vehicle = vehicle;
        this.customer = customer;
        this.rentalDays = rentalDays;

        double subtotal = vehicle.calculateRentalCost(rentalDays);
        this.totalCost = customer.applyDiscount(subtotal);
        this.daysElapsed = 0;
    }

    public String getAgreementId() {
        return agreementId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public int getDaysElapsed() {
        return daysElapsed;
    }

    public void incrementDaysElapsed() {
        this.daysElapsed++;
    }

    @Override
    public String toString() {
        return "================================================================\n" +
                "RENTAL AGREEMENT RECEIPT\n" +
                "================================================================\n" +
                "Agreement ID : " + agreementId + "\n" +
                "Vehicle      : " + vehicle.getVehicleId() + " - " + vehicle.getSummary() + "\n" +
                "Customer     : " + customer.getCustomerId() + " - " + customer.getSummary() + "\n" +
                "Rental Days  : " + rentalDays + "\n" +
                "Total Cost   : " + String.format("%.2f", totalCost) + "\n" +
                "================================================================";
    }
}