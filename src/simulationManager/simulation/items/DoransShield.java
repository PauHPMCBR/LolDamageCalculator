package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class DoransShield extends Item {
    public static final String name = "Doran's Shield";
    public static final ItemType type = ItemType.starter;
    public static final int cost = 450;

    public DoransShield() {
        super(name, type, cost);
        hp = 80;
    }

    @Override
    public Item makeCopy() {
        return new DoransShield();
    }
}
