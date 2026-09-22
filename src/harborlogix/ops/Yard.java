package harborlogix.ops;

import harborlogix.cargo.CargoUnit;
import harborlogix.clients.Client;
import java.util.ArrayList;

/**
 * SKELETON - and the most important file in the assignment.
 *
 * THE RULE
 * --------
 * This file must NOT contain:
 *   - the name of any CargoUnit subclass
 *   - the name of any Client subclass
 *   - the word `instanceof`
 *   - any cast to a hierarchy type
 *
 * Verify with:
 *   java harborlogix.tools.OpenClosedCheck src/harborlogix/ops/Yard.java
 *
 * Yard knows there is *a* cargo unit and *a* client. It knows which questions
 * to ask them. It must not know what kind they are.
 */
public class Yard {

     private final String yardName;
     private final int capacity;
     private final ArrayList<CargoUnit> units = new ArrayList<>();

    public Yard(String yardName, int capacity) {
       if(yardName==null || yardName.trim().isEmpty()){
         throw  new IllegalArgumentException("Yard name cannot be null or empty");
       }
       if(capacity<1){
           throw new IllegalArgumentException("Capacity cannot be less than 1");
       }

       this.yardName = yardName;
       this.capacity = capacity;
    }

    public String getYardName() {
        return yardName;
    }

    public int getCapacity()    {
        return capacity;
    }

    public int getUnitCount()   {
        return units.size();
    }

    /**
     * Returns the stored units.
     * THINK: should this return the internal list, or a copy? Your choice
     * affects encapsulation, and you will be asked about it.
     */
    public ArrayList<CargoUnit> getUnits() {
        return new ArrayList<>(units);
    }

    /**
     * Adds a unit.
     * Reject null with IllegalArgumentException.
     * Reject a duplicate unit ID with IllegalArgumentException.
     * Reject exceeding capacity with IllegalStateException.
     * Think about why those two situations deserve different exception types.
     */
    public void receive(CargoUnit unit) {
        if(unit == null){
            throw  new IllegalArgumentException("Unit cannot be null");
        }
        for(CargoUnit cargoUnit : units) {
            if (cargoUnit.getUnitId().equals(unit.getUnitId())) {
                throw new IllegalArgumentException("Unit already received by this Yard");
            }
        }
        if(units.size() >= capacity){
            throw  new IllegalStateException("Cannot receive more than "+capacity+" units");
        }
        units.add(unit);
    }

    /** Sum of every unit's daily fee. */
    public double totalDailyRevenue() {
        double totalDailyRevenue = 0;
        for(CargoUnit unit : units){
            totalDailyRevenue+= unit.dailyStorageFee();
        }
        return totalDailyRevenue;
    }

    /**
     * Total owed by one client: sum of totalStorageCharge() over that client's
     * units, reduced by that client's own discount percentage.
     * Match clients by clientId, not by object identity.
     */
    public double invoiceFor(Client client) {
        double totalPrice = 0;
        for(CargoUnit unit : units){
            if(unit.getOwner().getClientId().equals(client.getClientId())){
                totalPrice += unit.totalStorageCharge();
            }
        }
        return totalPrice-totalPrice*(client.discountPercent()/100);
    }

    /** The heaviest unit in the yard, or null if the yard is empty. */
    public CargoUnit heaviestUnit() {
        if(units.isEmpty()){
            return  null;
        }
        CargoUnit heaviestUnit = units.get(0);
        for(CargoUnit unit : units){
            if(unit.getWeightKg() > heaviestUnit.getWeightKg()){
                heaviestUnit = unit;
            }
        }
        return heaviestUnit;
    }

    /** Prints one line per unit plus its safety briefing, then the daily revenue. */
    public void printManifest() {
        for(CargoUnit unit : units){
            System.out.println(unit);
            System.out.println(unit.safetyBriefing());
        }
        System.out.println(totalDailyRevenue());
    }
}
