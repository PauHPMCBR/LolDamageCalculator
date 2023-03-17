package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class SteelShoulderguards extends Item {
    public static final String name = "Steel Shoulderguards";
    public static final ItemType type = ItemType.starter;
    public static final int cost = 400;

    public SteelShoulderguards() {
        super(name, type, cost);
        ad = 3;
        hp = 30;
        hpRegen = 50;
    }

    @Override
    public Item makeCopy() {
        return new SteelShoulderguards();
    }
}
