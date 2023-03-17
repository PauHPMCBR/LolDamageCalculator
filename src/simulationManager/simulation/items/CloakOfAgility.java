package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class CloakOfAgility extends Item {
    public static final String name = "Cloak of Agility";
    public static final ItemType type = ItemType.basic;
    public static final int cost = 600;

    public CloakOfAgility() {
        super(name, type, cost);
        crit = 15;
    }

    @Override
    public Item makeCopy() {
        return new CloakOfAgility();
    }
}
