package harborlogix.cargo;

import harborlogix.clients.Client;
import harborlogix.ops.TariffPolicy;

/**
 * OversizedCargo - חלק ד' (הרחבת המערכת).
 * מציג שמירה על עקרון הפתוח/סגור (Open/Closed Principle):
 * הוספת סוג מטען חדש מבלי לשנות אף שורת קוד ב-Yard.java.
 */
public class OversizedCargo extends CargoUnit {

    private final double lengthM;
    private final double widthM;
    private final double heightM;

    public OversizedCargo(String unitId, Client owner, double weightKg, int daysStored,
                          double lengthM, double widthM, double heightM) {
        super(unitId, owner, weightKg, daysStored);

        if (lengthM <= 0 || widthM <= 0 || heightM <= 0) {
            throw new IllegalArgumentException("Dimensions must be positive.");
        }
        this.lengthM = lengthM;
        this.widthM = widthM;
        this.heightM = heightM;
    }

    public double getLengthM() { return lengthM; }
    public double getWidthM() { return widthM; }
    public double getHeightM() { return heightM; }

    @Override
    public double dailyStorageFee() {
        return TariffPolicy.OVERSIZE_DAILY_FLAT;
    }

    @Override
    public String handlingCategory() {
        return "Oversized";
    }

    @Override
    public String safetyBriefing() {
        return "Oversized load: Check route clearances and secure wide load warning flags.";
    }

    @Override
    public String toString() {
        return super.toString() + ", lengthM=" + lengthM + ", widthM=" + widthM + ", heightM=" + heightM;
    }
}