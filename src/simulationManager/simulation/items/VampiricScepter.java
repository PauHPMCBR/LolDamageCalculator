package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class VampiricScepter extends Item {
    public static final String name = "Vampiric Scepter";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 900;

    public VampiricScepter() {
        super(name, type, cost);
        ad = 15;
        lifesteal = 7;
    }

    @Override
    public Item makeCopy() {
        return new VampiricScepter();
    }
}
