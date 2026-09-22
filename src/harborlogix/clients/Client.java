package harborlogix.clients;

/**
 * SKELETON - implement the TODOs.
 *
 * DESIGN DECISION YOU MUST MAKE AND JUSTIFY IN DESIGN.md:
 * Should Client be abstract, like CargoUnit? Or concrete?
 * Both answers are defensible. Pick one, implement it, and defend it.
 * (The skeleton is concrete; change it if you decide otherwise.)
 */
public class Client {

    private final String name;
    private final String clientId;

    // TODO: private final fields for clientId and name

    public Client(String clientId, String name) {
        // TODO: validate both (non-null, non-blank) and assign
        if(name==null || name.isBlank()){
            throw new IllegalArgumentException("name can not be null  or blank");
        }
        if(clientId==null || clientId.isBlank()){
            throw new IllegalArgumentException("clientId can not be null   or blank");
        }
        this.name = name;
        this.clientId = clientId;
    }

    public String getClientId() {
        return clientId;
    }

    public String getName() {
        return  name;
    }

    /** Walk-in clients get no discount. Subclasses may override. */
    public double discountPercent() {
        return  0.0;
    }

    public String clientTier() {
        return "Standard";
    }

    /** Priority clients are unloaded first. */
    public boolean priorityHandling() {
        return false;
    }

    @Override
    public String toString() {
        return String.format("%s [%s, Tier: %s]", name, clientId, clientTier());
    }
}
