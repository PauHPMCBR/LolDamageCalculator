package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class DoransRing extends Item {
    public static final String name = "Doran's Ring";
    public static final ItemType type = ItemType.starter;
    public static final int cost = 400;

    public DoransRing() {
        super(name, type, cost);
        ap = 15;
        hp = 70;
    }

    @Override
    public Item makeCopy() {
        return new DoransRing();
    }
}
