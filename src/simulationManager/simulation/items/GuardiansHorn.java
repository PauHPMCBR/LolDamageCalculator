package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class GuardiansHorn extends Item {
    public static final String name = "Guardian's Horn";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 950;

    public GuardiansHorn() {
        super(name, type, cost);
        hp = 150;
    }

    @Override
    public Item makeCopy() {
        return new GuardiansHorn();
    }
}
