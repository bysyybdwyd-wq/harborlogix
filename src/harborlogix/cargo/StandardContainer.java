package harborlogix.cargo;

import harborlogix.clients.Client;
import harborlogix.ops.TariffPolicy;

/**
 * SKELETON.  StandardContainer IS-A CargoUnit.
 *
 * Extra state : volumeM3 (must be positive)
 * Daily fee   : BASE_STORAGE_RATE * volumeM3
 * Category    : "Standard"
 * Briefing    : any sensible one-line message
 */
public class StandardContainer extends CargoUnit {

     private final double volumeM3;

    public StandardContainer(String unitId, Client owner, double weightKg, int daysStored, double volumeM3) {
        super(unitId, owner, weightKg, daysStored);
        if(volumeM3>0){
            this.volumeM3 = volumeM3;
        }else {
            throw  new IllegalArgumentException("Volume M3 needs to be greater than 0");
        }
    }

    public double getVolumeM3() {
        return volumeM3;
    }

    @Override
    public double dailyStorageFee() {
       return TariffPolicy.BASE_STORAGE_RATE * volumeM3;
    }

    @Override
    public String handlingCategory() {
        return "Standard";
    }

    @Override
    public String safetyBriefing() {
        return "Standard container: Check corner castings and twistlocks before lifting.";
    }

    @Override
    public String toString() {
        return super.toString() + ", volume=" + volumeM3;
    }
}
