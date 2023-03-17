package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class Morellonomicon extends Item {
    public static final String name = "Morellonomicon";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 3000;

    public Morellonomicon() {
        super(name, type, cost);
        ap = 90;
        magic_pen = 10;
        hp = 200;
    }

    @Override
    public Item makeCopy() {
        return new Morellonomicon();
    }
}
