package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class RubyCrystal extends Item {
    public static final String name = "Ruby Crystal";
    public static final ItemType type = ItemType.basic;
    public static final int cost = 400;

    public RubyCrystal() {
        super(name, type, cost);
        hp = 150;
    }

    @Override
    public Item makeCopy() {
        return new RubyCrystal();
    }
}
