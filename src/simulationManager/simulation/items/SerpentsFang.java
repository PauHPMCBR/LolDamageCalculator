package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class SerpentsFang extends Item {
    public static final String name = "Serpent's Fang";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 2600;

    public SerpentsFang() {
        super(name, type, cost);
        ad = 55;
        lethality = 12;
    }

    @Override
    public Item makeCopy() {
        return new SerpentsFang();
    }
}
