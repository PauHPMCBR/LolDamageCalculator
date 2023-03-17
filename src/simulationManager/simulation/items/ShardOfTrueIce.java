package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class ShardOfTrueIce extends Item {
    public static final String name = "Shard of True Ice";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 400;

    public ShardOfTrueIce() {
        super(name, type, cost);
        ap = 40;
        hp = 75;
        manaRegen = 100;
    }

    @Override
    public Item makeCopy() {
        return new ShardOfTrueIce();
    }
}
