package ijse.fleet;

public class IndividualCustomer extends Customer {
    private int loyaltyPoints;

    public IndividualCustomer(String customerId, String fullName, String contactNumber, int loyaltyPoints) {
        super(customerId, fullName, contactNumber);
        this.loyaltyPoints = loyaltyPoints;
    }

    @Override
    public double applyDiscount(double subtotal) {
        double discountRate = 0.00;
        if (loyaltyPoints >= 300) {
            discountRate = 0.10;
        } else if (loyaltyPoints >= 100) {
            discountRate = 0.05;
        }
        return subtotal * (1 - discountRate);
    }

    @Override
    public String getSummary() {
        return super.getSummary() + " [Individual, Points: " + loyaltyPoints + "]";
    }
}