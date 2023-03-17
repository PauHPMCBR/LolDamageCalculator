package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class InfinityEdge extends Item {
    public static final String name = "Infinity Edge";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 3400;

    public InfinityEdge() {
        super(name, type, cost);
        ad = 70;
        crit = 20;
    }

    public void specialStats() {
        if (owner.CRIT_CHANCE >= 40) //not tested
            owner.crit_damage += 0.35f;
    }

    @Override
    public Item makeCopy() {
        return new InfinityEdge();
    }
}
