package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class SerratedDirk extends Item {
    public static final String name = "Serrated Dirk";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 1100;

    public SerratedDirk() {
        super(name, type, cost);
        ad = 30;
        lethality = 10;
    }

    @Override
    public Item makeCopy() {
        return new SerratedDirk();
    }
}
