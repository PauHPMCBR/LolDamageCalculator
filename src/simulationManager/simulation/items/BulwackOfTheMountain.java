package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class BulwackOfTheMountain extends Item {
    public static final String name = "Bulwack of the Mountain";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 400; //

    public BulwackOfTheMountain() {
        super(name, type, cost);
        ap = 20;
        hp = 250;
        hpRegen = 100;
    }

    @Override
    public Item makeCopy() {
        return new BulwackOfTheMountain();
    }
}
