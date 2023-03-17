package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class PauldronsOfWhiterock extends Item {
    public static final String name = "Pauldrons of Whiterock";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 400;

    public PauldronsOfWhiterock() {
        super(name, type, cost);
        ad = 15;
        hp = 250;
        hpRegen = 100;
    }

    @Override
    public Item makeCopy() {
        return new PauldronsOfWhiterock();
    }
}
