package harborlogix.clients;

/**
 * SKELETON. A state client. The discount is fixed by statute at 25 percent,
 * so it must NOT be a constructor parameter - model it as a constant.
 * Government cargo always gets priority handling.
 */
public class GovernmentClient extends Client {

     public static final double STATUTORY_DISCOUNT = 25.0;
     private final String agencyCode;

    public GovernmentClient(String clientId, String name, String agencyCode) {
        super(clientId, name);
        if (agencyCode == null || agencyCode.trim().isEmpty()) {
            throw new IllegalArgumentException("agencyCode cannot be null or empty");
        }
        this.agencyCode = agencyCode;
    }

    public String getAgencyCode() {
        return agencyCode;
    }

    // TODO: override discountPercent(), clientTier(), priorityHandling(), toString()

    @Override
    public double discountPercent() {
        return STATUTORY_DISCOUNT;
    }

    @Override
    public String clientTier(){
        return "Government";
    }

    @Override
    public boolean priorityHandling(){
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + "\n Agency Code: " + agencyCode;
    }
}
