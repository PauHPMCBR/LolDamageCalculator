package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class RanduinsOmen extends Item {
    public static final String name = "Randuin's Omen";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 3000;

    public RanduinsOmen() {
        super(name, type, cost);
        armor = 60;
        hp = 400;
    }

    @Override
    public Item makeCopy() {
        return new RanduinsOmen();
    }
}
