package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class RunesteelSpaulders extends Item {
    public static final String name = "Runesteel Spaulders";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 400;

    public RunesteelSpaulders() {
        super(name, type, cost);
        ad = 6;
        hp = 100;
        hpRegen = 75;
    }

    @Override
    public Item makeCopy() {
        return new RunesteelSpaulders();
    }
}
