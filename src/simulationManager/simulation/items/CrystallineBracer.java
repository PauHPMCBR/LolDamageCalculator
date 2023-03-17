package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class CrystallineBracer extends Item {
    public static final String name = "Crystaline Bracer";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 800;

    public CrystallineBracer() {
        super(name, type, cost);
        hp = 200;
        hpRegen = 100;
    }

    @Override
    public Item makeCopy() {
        return new CrystallineBracer();
    }
}
