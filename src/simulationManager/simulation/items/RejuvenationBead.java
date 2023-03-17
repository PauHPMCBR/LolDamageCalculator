package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class RejuvenationBead extends Item {
    public static final String name = "Rejuvenation Bead";
    public static final ItemType type = ItemType.basic;
    public static final int cost = 300;

    public RejuvenationBead() {
        super(name, type, cost);
        hpRegen = 100;
    }

    @Override
    public Item makeCopy() {
        return new RejuvenationBead();
    }
}
