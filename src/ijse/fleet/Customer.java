package ijse.fleet;

public abstract class Customer implements Reportable {
    private String customerId;
    private String fullName;
    private String contactNumber;

    public Customer(String customerId, String fullName, String contactNumber) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.contactNumber = contactNumber;
    }

    public abstract double applyDiscount(double subtotal);

    public String getCustomerId() {
        return customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    @Override
    public String getReportCategory() {
        return "CUSTOMER";
    }

    @Override
    public String getSummary() {
        return customerId + " - " + fullName + " (" + contactNumber + ")";
    }

    @Override
    public String toString() {
        return customerId + " - " + fullName;
    }
}