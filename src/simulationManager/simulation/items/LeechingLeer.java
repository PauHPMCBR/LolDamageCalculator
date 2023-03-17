package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class LeechingLeer extends Item {
    public static final String name = "Leeching Leer";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 1300;

    public LeechingLeer() {
        super(name, type, cost);
        ap = 20;
        hp = 250;
        omnivamp = 5;
    }

    @Override
    public Item makeCopy() {
        return new LeechingLeer();
    }
}
