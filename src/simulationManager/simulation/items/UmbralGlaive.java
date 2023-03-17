package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class UmbralGlaive extends Item {
    public static final String name = "Umbral Glaive";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 2300;

    public UmbralGlaive() {
        super(name, type, cost);
        ad = 50;
        ah = 15;
        lethality = 10;
    }

    @Override
    public Item makeCopy() {
        return new UmbralGlaive();
    }
}
