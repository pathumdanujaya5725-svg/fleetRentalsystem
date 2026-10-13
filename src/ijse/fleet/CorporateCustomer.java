package ijse.fleet;

public class CorporateCustomer extends Customer {
    private double creditLimit;
    private double outstandingBalance;

    public CorporateCustomer(String customerId, String fullName, String contactNumber, double creditLimit) {
        super(customerId, fullName, contactNumber);
        this.creditLimit = creditLimit;
        this.outstandingBalance = 0.0;
    }

    @Override
    public double applyDiscount(double subtotal) {
        return subtotal * 0.85; // flat 15% discount
    }

    public boolean hasSufficientCredit(double cost) {
        return (outstandingBalance + cost) <= creditLimit;
    }

    public double getOutstandingBalance() {
        return outstandingBalance;
    }

    public void setOutstandingBalance(double outstandingBalance) {
        this.outstandingBalance = outstandingBalance;
    }

    @Override
    public String getSummary() {
        return super.getSummary() + " [Corporate, Credit Limit: " + creditLimit + "]";
    }
}