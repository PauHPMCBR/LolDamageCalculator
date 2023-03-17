package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class Bloodthister extends Item {
    public static final String name = "Bloodthister";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 3200;

    public Bloodthister() {
        super(name, type, cost);
        ad = 55;
        crit = 20;
        lifesteal = 15;
    }

    @Override
    public Item makeCopy() {
        return new Bloodthister();
    }
}
