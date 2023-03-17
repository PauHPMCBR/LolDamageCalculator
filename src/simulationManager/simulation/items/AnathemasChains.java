package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class AnathemasChains extends Item {
    public static final String name = "Anathema's Chains";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 2500;

    public AnathemasChains() {
        super(name, type, cost);
        ah = 20;
        hp = 650;
    }

    @Override
    public Item makeCopy() {
        return new AnathemasChains();
    }
}
