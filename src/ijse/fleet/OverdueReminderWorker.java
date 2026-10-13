package ijse.fleet;

public class OverdueReminderWorker implements Runnable {
    private final FleetManager fleetManager;

    public OverdueReminderWorker(FleetManager fleetManager) {
        this.fleetManager = fleetManager;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5000); // Sleep for 5 seconds
                fleetManager.checkOverdueAgreements();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("OverdueReminderWorker interrupted.");
                break;
            }
        }
    }
}