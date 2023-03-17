package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class NullMagicMantle extends Item {
    public static final String name = "Null-Magic Mantle";
    public static final ItemType type = ItemType.basic;
    public static final int cost = 450;

    public NullMagicMantle() {
        super(name, type, cost);
        mr = 25;
    }

    @Override
    public Item makeCopy() {
        return new NullMagicMantle();
    }
}
