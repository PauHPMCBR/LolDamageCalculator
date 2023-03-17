package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class GuardianAngel extends Item {
    public static final String name = "Guardian Angel";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 0;

    public GuardianAngel() {
        super(name, type, cost);
        ad = 45;
        armor = 40;
    }

    @Override
    public Item makeCopy() {
        return new GuardianAngel();
    }
}
