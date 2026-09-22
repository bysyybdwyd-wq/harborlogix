package harborlogix.cargo;

import harborlogix.clients.Client;
import harborlogix.ops.TariffPolicy;

/**
 * SKELETON.  Also a child of StandardContainer - a sibling of the reefer.
 *
 * Extra state : hazardClass (1-9 only), requiresEscort (boolean)
 * Daily fee   : the parent's fee MULTIPLIED by HAZMAT_MULTIPLIER
 * Category    : "Hazmat"
 * Briefing    : must state the hazard class number
 */
public class HazmatContainer extends StandardContainer {

    private final int hazardClass;
    private final boolean requiresEscort;

    public HazmatContainer(String unitId, Client owner, double weightKg,
                           int daysStored, double volumeM3,
                           int hazardClass, boolean requiresEscort) {
        super(unitId, owner, weightKg, daysStored, volumeM3);
        if(hazardClass < 1 || hazardClass > 9) {
            throw new IllegalArgumentException("hazardClass must be between 0 and 6");
        }
        this.hazardClass = hazardClass;
        this.requiresEscort = requiresEscort;
    }

    public int getHazardClass() {
        return hazardClass;
    }

    public boolean isRequiresEscort() {
        return requiresEscort;
    }

    @Override
    public double dailyStorageFee() {
        return super.dailyStorageFee() * TariffPolicy.HAZMAT_MULTIPLIER;
    }

    @Override
    public String  handlingCategory() {
        return "Hazmat";
    }

    @Override
    public String safetyBriefing() {
        return "Hazmat Class:" +hazardClass + "Hazmat container: Ensure hazard safety protocols and protective gear are active.";
    }

    @Override
    public String toString() {
        return super.toString() + ", hazardClass=" + hazardClass + ", requiresEscort=" + requiresEscort;
    }
}
