package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class BlightingJewel extends Item {
    public static final String name = "Blighting Jewel";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 1250;

    public BlightingJewel() {
        super(name, type, cost);
        ap = 25;
        percent_magic_pen = 13;
    }

    @Override
    public Item makeCopy() {
        return new BlightingJewel();
    }
}
