package ijse.fleet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FleetManager {
    private final List<Vehicle> vehicles;
    private final List<Customer> customers;
    private final List<RentalAgreement> agreements;

    public FleetManager() {
        this.vehicles = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.agreements = new ArrayList<>();
        loadSampleData();
    }

    private void loadSampleData() {
        addVehicle(new Car("VEH-1001", 4000.0, FuelType.PETROL, 4));
        addVehicle(new Van("VEH-1002", 6000.0, FuelType.DIESEL, 10));
        addVehicle(new Truck("VEH-1003", 10000.0, FuelType.DIESEL, 5.0));

        addCustomer(new IndividualCustomer("CUS-1001", "Hasith hirudaya", "0712345678", 150));
        addCustomer(new CorporateCustomer("CUS-1002", "mahesh deepal", "0712245445", 50000.0));
    }

    public void addVehicle(Vehicle v) {
        vehicles.add(v);
    }

    public void addCustomer(Customer c) {
        customers.add(c);
    }

    public Customer registerAndGetNewCustomer(String name, String phone) {
        String newId = "CUS-" + (1001 + customers.size());
        Customer newCustomer = new IndividualCustomer(newId, name, phone, 100);
        customers.add(newCustomer);
        return newCustomer;
    }

    public Vehicle findVehicleById(String id) {
        for (Vehicle v : vehicles) {
            if (v.getVehicleId().equalsIgnoreCase(id)) {
                return v;
            }
        }
        return null;
    }

    public Customer findCustomerById(String id) {
        for (Customer c : customers) {
            if (c.getCustomerId().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }

    public List<Vehicle> getAvailableVehicles() {
        List<Vehicle> available = new ArrayList<>();
        for (Vehicle v : vehicles) {
            if (v.getStatus() == RentalStatus.AVAILABLE) {
                available.add(v);
            }
        }
        return available;
    }

    public RentalAgreement createRentalAgreement(String vehicleId, String customerId, int rentalDays)
            throws VehicleNotAvailableException, InsufficientCreditException {

        Vehicle vehicle = findVehicleById(vehicleId);
        if (vehicle == null) {
            System.out.println("Vehicle not found: " + vehicleId);
            return null;
        }
        if (vehicle.getStatus() != RentalStatus.AVAILABLE) {
            throw new VehicleNotAvailableException("Vehicle is not available.");
        }

        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found: " + customerId);
            return null;
        }

        double subtotal = vehicle.calculateRentalCost(rentalDays);
        double finalCost = customer.applyDiscount(subtotal);

        if (customer instanceof CorporateCustomer) {
            CorporateCustomer corp = (CorporateCustomer) customer;
            if (!corp.hasSufficientCredit(finalCost)) {
                throw new InsufficientCreditException("Insufficient credit limit.");
            }
            corp.setOutstandingBalance(corp.getOutstandingBalance() + finalCost);
        }

        RentalAgreement agreement = new RentalAgreement(vehicle, customer, rentalDays);
        vehicle.setStatus(RentalStatus.RENTED);
        agreements.add(agreement);

        return agreement;
    }

    public void returnVehicle(String agreementId) {
        boolean found = false;
        for (RentalAgreement agreement : agreements) {
            if (agreement.getAgreementId().equalsIgnoreCase(agreementId)) {
                agreement.getVehicle().setStatus(RentalStatus.AVAILABLE);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Agreement ID not found: " + agreementId);
        }
    }

    public void printFleetStatus() {
        int availableCount = 0;
        int rentedCount = 0;
        for (Vehicle v : vehicles) {
            if (v.getStatus() == RentalStatus.AVAILABLE) {
                availableCount++;
            } else if (v.getStatus() == RentalStatus.RENTED) {
                rentedCount++;
            }
        }
        System.out.println("[FLEET STATUS] Available: " + availableCount + " | Rented: " + rentedCount);
    }

    public double generateRevenueReport() {
        double total = 0;
        for (RentalAgreement agreement : agreements) {
            total += agreement.getTotalCost();
        }
        return total;
    }

    public void printAllReportables() {
        System.out.println("\n--- Reportables (Vehicles & Customers) ---");
        for (Vehicle v : vehicles) {
            System.out.println(v.getReportCategory() + ": " + v.getSummary());
        }
        for (Customer c : customers) {
            System.out.println(c.getReportCategory() + ": " + c.getSummary());
        }
    }

    public void checkOverdueAgreements() {
    }
}