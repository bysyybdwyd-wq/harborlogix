package harborlogix.cargo;

import harborlogix.clients.Client;
import harborlogix.ops.TariffPolicy;

/**
 * SKELETON.  Level 3 of the hierarchy.
 *
 * Extra state : targetTempC (must be 8.0 or below), powerDrawKw (positive)
 * Daily fee   : the parent's fee PLUS (POWER_RATE * powerDrawKw)
 *               You must EXTEND the parent's fee, not recompute it.
 * Category    : "Reefer"
 */
public class RefrigeratedContainer extends StandardContainer {

    private final double targetTempC;
    private final double powerDrawKw;

    public RefrigeratedContainer(String unitId, Client owner, double weightKg, int daysStored, double volumeM3,
                                 double targetTempC, double powerDrawKw) {
        super(unitId, owner, weightKg, daysStored, volumeM3);

        if(targetTempC <= 8.0) {
       this.targetTempC = targetTempC;}
        else {
            throw new IllegalArgumentException("targetTempC must be smaller than or equal to 8.0");
        }
       if (powerDrawKw > 0.0) {
           this.powerDrawKw = powerDrawKw;
       }else {
           throw new IllegalArgumentException("powerDrawKw must be greater than 0.");
       }

    }

    public double getTargetTempC() {
        return targetTempC;
    }

    public double getPowerDrawKw() {
        return powerDrawKw;
    }

    @Override
    public double dailyStorageFee(){
        return super.dailyStorageFee() + (TariffPolicy.POWER_RATE * powerDrawKw);
    }
    @Override
    public String handlingCategory() {
        return "Reefer";
    }
    @Override
    public String safetyBriefing() {
        return "Reefer container: Ensure continuous power supply and check target temp settings.";
    }

    @Override
    public String toString() {
        return super.toString() + ", temp=" + targetTempC + ", powerDrawKw=" + powerDrawKw;
    }
}
