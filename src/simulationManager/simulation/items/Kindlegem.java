package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class Kindlegem extends Item {
    public static final String name = "Kindlegem";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 800;

    public Kindlegem() {
        super(name, type, cost);
        hp = 200;
        ah = 10;
    }

    @Override
    public Item makeCopy() {
        return new Kindlegem();
    }
}
