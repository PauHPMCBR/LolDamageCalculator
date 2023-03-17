package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class RabbadonsDeathcap extends Item {
    public static final String name = "Rabbadon's Deathcap";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 3600;

    public RabbadonsDeathcap() {
        super(name, type, cost);
        ap = 120;
    }

    public void specialStats() {
        owner.AP *= 1.35; //have to check if some extra ap is skipped
    }

    @Override
    public Item makeCopy() {
        return new RabbadonsDeathcap();
    }
}
