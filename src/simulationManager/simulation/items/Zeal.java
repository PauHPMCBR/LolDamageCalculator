package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class Zeal extends Item {
    public static final String name = "Zeal";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 1050;

    public Zeal() {
        super(name, type, cost);
        as = 18;
        crit = 15;
        ms = 7;
    }

    @Override
    public Item makeCopy() {
        return new Zeal();
    }
}
