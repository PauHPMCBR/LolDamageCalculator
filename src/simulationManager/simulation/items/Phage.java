package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class Phage extends Item {
    public static final String name = "Phage";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 1100;

    public Phage() {
        super(name, type, cost);
        ad = 15;
        hp = 200;
    }

    @Override
    public Item makeCopy() {
        return new Phage();
    }
}
