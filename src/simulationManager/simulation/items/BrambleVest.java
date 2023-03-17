package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class BrambleVest extends Item {
    public static final String name = "Bramble Vest";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 800;

    public BrambleVest() {
        super(name, type, cost);
        armor = 30;
    }

    @Override
    public Item makeCopy() {
        return new BrambleVest();
    }
}
