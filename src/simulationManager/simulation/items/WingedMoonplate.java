package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class WingedMoonplate extends Item {
    public static final String name = "Winged Moonplate";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 800;

    public WingedMoonplate() {
        super(name, type, cost);
        hp = 150;
        ms = 5;
    }

    @Override
    public Item makeCopy() {
        return new WingedMoonplate();
    }
}
