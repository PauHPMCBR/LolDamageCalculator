package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class NeedlesslyLargeRod extends Item {
    public static final String name = "Needlessly Large Rod";
    public static final ItemType type = ItemType.basic;
    public static final int cost = 1250;

    public NeedlesslyLargeRod() {
        super(name, type, cost);
        ap = 60;
    }

    @Override
    public Item makeCopy() {
        return new NeedlesslyLargeRod();
    }
}
