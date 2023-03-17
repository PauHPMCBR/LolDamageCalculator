package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class GiantsBelt extends Item {
    public static final String name = "Giant's Belt";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 900;

    public GiantsBelt() {
        super(name, type, cost);
        hp = 350;
    }

    @Override
    public Item makeCopy() {
        return new GiantsBelt();
    }
}
