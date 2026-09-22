package harborlogix.clients;

/**
 * SKELETON. A client with a negotiated discount between 0 and 40 percent.
 * Reject anything outside that range with IllegalArgumentException.
 */
public class ContractClient extends Client {

    private final double contractDiscount;

    public ContractClient(String clientId, String name, double contractDiscount) {
        super(clientId, name);
       if (contractDiscount <0 || contractDiscount >40){
       throw  new IllegalArgumentException("Contract discount must be between 0 and 40.");
       }
       this.contractDiscount = contractDiscount;
    }

    @Override
    public double discountPercent(){
        return contractDiscount;
    }

    @Override
    public String clientTier() {
        return "Contract";
    }

    @Override
    public String toString() {
        return super.toString() + "\n Discount percent: " + discountPercent();
    }
}
