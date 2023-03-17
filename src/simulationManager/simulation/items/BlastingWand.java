package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class BlastingWand extends Item {
    public static final String name = "Blasting Wand";
    public static final ItemType type = ItemType.basic;
    public static final int cost = 850;

    public BlastingWand() {
        super(name, type, cost);
        ap = 40;
    }

    @Override
    public Item makeCopy() {
        return new BlastingWand();
    }
}
