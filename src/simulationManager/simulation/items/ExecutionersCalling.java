package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class ExecutionersCalling extends Item {
    public static final String name = "Executioner's Calling";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 800;

    public ExecutionersCalling() {
        super(name, type, cost);
        ad = 15;
    }

    @Override
    public Item makeCopy() {
        return new ExecutionersCalling();
    }
}
