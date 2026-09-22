package harborlogix.app;

import harborlogix.cargo.*;
import harborlogix.clients.*;
import harborlogix.ops.*;


public class TerminalApp {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("HarborLogix Terminal - Student ID: " + TariffPolicy.STUDENT_ID);
        System.out.println("==================================================\n");


        Yard yard = new Yard("Main Yard", TariffPolicy.YARD_CAPACITY);


        Client stdClient = new Client("C-100", "Standard Shipping Co");
        ContractClient conClient = new ContractClient("C-200", "Global Trade Corp", 20.0);
        GovernmentClient govClient = new GovernmentClient("C-300", "Port Authority", "GOV-77");


        System.out.println("=== SECTION 1: YARD MANIFEST ===");
        StandardContainer std = new StandardContainer("U-STD-01", stdClient, 5000, 4, 30.0);
        RefrigeratedContainer reefer = new RefrigeratedContainer("U-REF-01", conClient, 6000, 3, 25.0, -18.0, 4.5);
        HazmatContainer hazmat = new HazmatContainer("U-HAZ-01", stdClient, 4500, 2, 20.0, 3, true);
        LiquidTank tank = new LiquidTank("U-TNK-01", govClient, 8000, 5, 10000.0, 80.0);

        yard.receive(std);
        yard.receive(reefer);
        yard.receive(hazmat);
        yard.receive(tank);


        for (CargoUnit unit : yard.getUnits()) {
            System.out.println(" - " + unit);
        }
        System.out.printf("Total Daily Revenue: $%.2f%n%n", yard.totalDailyRevenue());


        System.out.println("=== SECTION 2: CLIENT INVOICES ===");
        printInvoice(yard, stdClient);
        printInvoice(yard, conClient);
        printInvoice(yard, govClient);
        System.out.println();


        System.out.println("=== SECTION 3: DRAINAGE ROUND ===");
        for (CargoUnit unit : yard.getUnits()) {
            if (unit instanceof LiquidTank liquidTank) {
                System.out.println("Found liquid tank: " + liquidTank.getUnitId());
                double moved = liquidTank.transferOut(3000.0);
                System.out.printf("Pumped out %.1f Litres. Updated fill: %.1f%%%n", moved, liquidTank.getFillPercent());
            }
        }
        System.out.println();


        System.out.println("=== SECTION 4: PART D - OVERSIZED CARGO ===");
        OversizedCargo oversize = new OversizedCargo("U-OVS-01", govClient, 12000, 3, 12.0, 4.0, 3.5);
        yard.receive(oversize);

        System.out.println("Updated Yard Manifest after adding OversizedCargo:");
        for (CargoUnit unit : yard.getUnits()) {
            System.out.println(" - " + unit);
        }
        System.out.printf("Updated Total Daily Revenue: $%.2f%n", yard.totalDailyRevenue());
    }

    private static void printInvoice(Yard yard, Client client) {
        double invoiceTotal = yard.invoiceFor(client);
        System.out.printf("Client: %-20s | Tier: %-10s | Discount: %4.1f%% | Total Invoice: $%.2f%n",
                client.getName(), client.clientTier(), client.discountPercent(), invoiceTotal);
    }
}