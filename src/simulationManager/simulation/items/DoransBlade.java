package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class DoransBlade extends Item {
    public static final String name = "Doran's Blade";
    public static final ItemType type = ItemType.starter;
    public static final int cost = 450;

    public DoransBlade() {
        super(name, type, cost);
        ad = 8;
        hp = 80;
        omnivamp = 2.5f;
    }

    @Override
    public Item makeCopy() {
        return new DoransBlade();
    }
}
