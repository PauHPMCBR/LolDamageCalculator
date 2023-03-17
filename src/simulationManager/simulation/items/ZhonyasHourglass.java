package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class ZhonyasHourglass extends Item {
    public static final String name = "Zhonya's Hourglass";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 3000;

    public ZhonyasHourglass() {
        super(name, type, cost);
        ap = 80;
        ah = 15;
        armor = 45;
    }

    @Override
    public Item makeCopy() {
        return new ZhonyasHourglass();
    }
}
