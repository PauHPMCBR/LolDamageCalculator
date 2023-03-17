package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class BansheesVeil extends Item {
    public static final String name = "Banshee's Veil";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 2600;

    public BansheesVeil() {
        super(name, type, cost);
        ap = 80;
        ah = 10;
        mr = 45;
    }

    @Override
    public Item makeCopy() {
        return new BansheesVeil();
    }
}
