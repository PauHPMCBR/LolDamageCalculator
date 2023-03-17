package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class RelicShield extends Item {
    public static final String name = "Relic Shield";
    public static final ItemType type = ItemType.starter;
    public static final int cost = 400;

    public RelicShield() {
        super(name, type, cost);
        ap = 5;
        hp = 30;
        hpRegen = 50;
    }

    @Override
    public Item makeCopy() {
        return new RelicShield();
    }
}
