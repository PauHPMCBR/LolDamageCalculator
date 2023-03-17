package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class BFSword extends Item {
    public static final String name = "B. F. Sword";
    public static final ItemType type = ItemType.basic;
    public static final int cost = 1300;

    public BFSword() {
        super(name, type, cost);
        ad = 40;
    }

    @Override
    public Item makeCopy() {
        return new BFSword();
    }
}
