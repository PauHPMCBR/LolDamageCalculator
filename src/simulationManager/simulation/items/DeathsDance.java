package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class DeathsDance extends Item {
    public static final String name = "Death's Dance";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 3300;

    public DeathsDance() {
        super(name, type, cost);
        ad = 65;
        armor = 50;
    }

    @Override
    public Item makeCopy() {
        return new DeathsDance();
    }
}
