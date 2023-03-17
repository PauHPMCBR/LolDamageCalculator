package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class ChainVest extends Item {
    public static final String name = "Chain Vest";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 800;

    public ChainVest() {
        super(name, type, cost);
        armor = 40;
    }

    @Override
    public Item makeCopy() {
        return new ChainVest();
    }
}
