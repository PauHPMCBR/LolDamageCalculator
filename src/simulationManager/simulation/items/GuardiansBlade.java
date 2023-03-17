package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class GuardiansBlade extends Item {
    public static final String name = "Guardian's Blade";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 950;

    public GuardiansBlade() {
        super(name, type, cost);
        ad = 30;
        ah = 15;
        hp = 150;
    }

    @Override
    public Item makeCopy() {
        return new GuardiansBlade();
    }
}
