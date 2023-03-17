package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class YoummuusGhostblade extends Item {
    public static final String name = "Yoummuu's Ghostblade";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 3000;

    public YoummuusGhostblade() {
        super(name, type, cost);
        ad = 55;
        ah = 15;
        lethality = 18;
    }

    @Override
    public Item makeCopy() {
        return new YoummuusGhostblade();
    }
}
