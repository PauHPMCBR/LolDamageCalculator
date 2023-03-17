package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class QuicksilverSash extends Item {
    public static final String name = "Quicksilver Sash";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 1300;

    public QuicksilverSash() {
        super(name, type, cost);
        mr = 30;
    }

    @Override
    public Item makeCopy() {
        return new QuicksilverSash();
    }
}
