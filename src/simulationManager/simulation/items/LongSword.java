package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class LongSword extends Item {
    public static final String name = "Long Sword";
    public static final ItemType type = ItemType.basic;
    public static final int cost = 350;

    public LongSword() {
        super(name, type, cost);
        ad = 10;
    }

    @Override
    public Item makeCopy() {
        return new LongSword();
    }
}
