package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class VoidStaff extends Item {
    public static final String name = "Void Staff";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 2800;

    public VoidStaff() {
        super(name, type, cost);
        ap = 65;
        percent_magic_pen = 40;
    }

    @Override
    public Item makeCopy() {
        return new VoidStaff();
    }
}
