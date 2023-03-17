package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class Tiamat extends Item {
    public static final String name = "Tiamat";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 1200;

    public Tiamat() {
        super(name, type, cost);
        ad = 25;
    }

    @Override
    public Item makeCopy() {
        return new Tiamat();
    }
}
