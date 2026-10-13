package ijse.fleet;

import java.util.Scanner;

public class Main {
    private final FleetManager fleetManager;
    private final UserManager userManager;
    private final Scanner scanner;
    private SystemUser loggedInUser;

    public Main() {
        this.fleetManager = new FleetManager();
        this.userManager = new UserManager();
        this.scanner = new Scanner(System.in);
        this.loggedInUser = null; // null until login succeeds
    }

    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        System.out.println("=== Welcome to Fleet Rental Management System (FRMS) ===");

        while (loggedInUser == null) {
            showAuthMenu();
        }

        boolean running = true;
        while (running) {
            try {
                showMainMenu();
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        handleViewAvailableVehicles();
                        break;
                    case 2:
                        handleCreateRentalAgreement(fleetManager, scanner);
                        break;
                    case 3:
                        handleReturnVehicle();
                        break;
                    case 4:
                        fleetManager.printFleetStatus();
                        break;
                    case 5:
                        System.out.println("Total Revenue: " + fleetManager.generateRevenueReport());
                        break;
                    case 6:
                        fleetManager.printAllReportables();
                        break;
                    case 0:
                        running = false;
                        System.out.println("Exiting system. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public void showAuthMenu() {
        System.out.println("\n--- Authentication Menu ---");
        System.out.println("1. Login");
        System.out.println("2. Register New User");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    loggedInUser = userManager.login(username, password);
                    if (loggedInUser != null) {
                        System.out.println("Login successful! Welcome, " + loggedInUser.getUsername());
                    } else {
                        System.out.println("Invalid credentials. Try again.");
                    }
                    break;
                case 2:
                    System.out.print("Enter new username: ");
                    String newUsername = scanner.nextLine();
                    System.out.print("Enter new password: ");
                    String newPassword = scanner.nextLine();
                    System.out.print("Enter fullName: ");
                    String fullName = scanner.nextLine();
                    userManager.registerUser(newUsername, newPassword, fullName);
                    System.out.println("User registered successfully. You can now login.");
                    break;
                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } catch (Exception e) {
            System.out.println("Error during authentication: " + e.getMessage());
        }
    }

    public void showMainMenu() {
        System.out.println("\n--- FRMS Main Menu ---");
        System.out.println("1. View Available Vehicles");
        System.out.println("2. Create Rental Agreement");
        System.out.println("3. Return Vehicle");
        System.out.println("4. Print Fleet Status (Snapshot)");
        System.out.println("5. Generate Revenue Report");
        System.out.println("6. Print All Reportables");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private void handleViewAvailableVehicles() {
        System.out.println("\n--- Available Vehicles ---");
        for (Vehicle v : fleetManager.getAvailableVehicles()) {
            System.out.println(v);
        }
    }

    private static void handleCreateRentalAgreement(FleetManager fleetManager, Scanner scanner) {
        System.out.print("Enter Vehicle ID: ");
        String vehicleId = scanner.nextLine();

        System.out.print("Enter Customer Name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter Customer Phone Number: ");
        String customerPhone = scanner.nextLine();

        Customer customer = fleetManager.registerAndGetNewCustomer(customerName, customerPhone);
        System.out.println("Customer registered automatically with ID: " + customer.getCustomerId());

        System.out.print("Enter Rental Days: ");
        int rentalDays = Integer.parseInt(scanner.nextLine());

        try {
            RentalAgreement agreement = fleetManager.createRentalAgreement(vehicleId, customer.getCustomerId(), rentalDays);
            if (agreement != null) {
                System.out.println("\nRental agreement created successfully!");
                System.out.println(agreement);
            }
        } catch (VehicleNotAvailableException | InsufficientCreditException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void handleReturnVehicle() {
        System.out.print("Enter Agreement ID to return vehicle: ");
        String aId = scanner.nextLine();
        fleetManager.returnVehicle(aId);
        System.out.println("Vehicle returned successfully!");
    }
}