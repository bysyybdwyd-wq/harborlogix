package harborlogix.cargo;

import harborlogix.clients.Client;
import harborlogix.ops.TariffPolicy;

/**
 * SKELETON.  A DIRECT child of CargoUnit - a separate branch from the
 * container family. Do not make it extend StandardContainer.
 *
 * Extra state : capacityLitres (positive), fillPercent (0-100, mutable)
 * currentLitres() : capacityLitres * fillPercent / 100
 * Daily fee   : LIQUID_RATE * currentLitres()   (actual content, not capacity)
 * Category    : "Tank"
 *
 * transferOut() exists ONLY here. It is the method you will use in Part C
 * to demonstrate the one downcast this assignment permits.
 */
public class LiquidTank extends CargoUnit {

     private final double capacityLitres;
     private double fillPercent;

    public LiquidTank(String unitId, Client owner, double weightKg,
                      int daysStored, double capacityLitres, double fillPercent) {
        super(unitId, owner, weightKg, daysStored);

        if (capacityLitres <= 0) {
            throw new IllegalArgumentException("Capacity Litres must be greater than zero.");
        }
        if (fillPercent < 0 || fillPercent > 100) {
            throw new IllegalArgumentException("Fill Percent must be greater than or egual to zero and smaller than 100.");
        }
        this.capacityLitres = capacityLitres;
        this.fillPercent = fillPercent;
    }

    public double getCapacityLitres() {
        return capacityLitres;
    }

    public double getFillPercent() {
        return fillPercent;
    }

    public double currentLitres() {
        return (fillPercent/100.0) * capacityLitres;
    }

    /**
     * Pumps out up to `litres` and returns how much was actually moved
     * (never more than is present). Updates fillPercent accordingly.
     * Reject a non-positive request with IllegalArgumentException.
     */
    public double transferOut(double litres) {
        if (litres <= 0) {
            throw new IllegalArgumentException("Litres to transfer must be positive.");
        }
        double present = currentLitres();
        double actualMoved = Math.min(litres, present);
        double remaining = present - actualMoved;

        this.fillPercent = (remaining / capacityLitres) * 100.0;
        return actualMoved;
    }

    @Override
    public double dailyStorageFee() {
       return currentLitres()* TariffPolicy.LIQUID_RATE;
    }

    @Override
    public String handlingCategory() {
        return  "Tank";
    }

    @Override
    public String safetyBriefing() {
        return "Liquid Tank: Verify pressure relief valves and seal integrity before transfer.";
    }

    @Override
    public String toString() {
        return super.toString() + ", capacityLitres=" + capacityLitres + ", fillPercent=" + fillPercent;
    }
}
