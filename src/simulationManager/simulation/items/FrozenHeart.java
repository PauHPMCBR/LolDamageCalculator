package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class FrozenHeart extends Item {
    public static final String name = "Frozen Heart";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 2700;

    public FrozenHeart() {
        super(name, type, cost);
        armor = 90;
        ah = 20;
        mana = 400;
    }

    @Override
    public Item makeCopy() {
        return new FrozenHeart();
    }
}
