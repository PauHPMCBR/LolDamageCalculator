package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class Pickaxe extends Item {
    public static final String name = "Pickaxe";
    public static final ItemType type = ItemType.basic;
    public static final int cost = 875;

    public Pickaxe() {
        super(name, type, cost);
        ad = 25;
    }

    @Override
    public Item makeCopy() {
        return new Pickaxe();
    }
}
