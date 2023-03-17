package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class NegatronCloak extends Item {
    public static final String name = "Negatron Cloak";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 900;

    public NegatronCloak() {
        super(name, type, cost);
        mr = 50;
    }

    @Override
    public Item makeCopy() {
        return new NegatronCloak();
    }
}
