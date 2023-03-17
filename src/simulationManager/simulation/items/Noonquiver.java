package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class Noonquiver extends Item {
    public static final String name = "Noonquiver";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 1300;

    public Noonquiver() {
        super(name, type, cost);
        ad = 30;
        as = 15;
    }

    @Override
    public Item makeCopy() {
        return new Noonquiver();
    }
}
