package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class TargonsBuckler extends Item {
    public static final String name = "Targon's Buckler";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 400;

    public TargonsBuckler() {
        super(name, type, cost);
        ap = 10;
        hp = 100;
        hpRegen = 75;
    }

    @Override
    public Item makeCopy() {
        return new TargonsBuckler();
    }
}
