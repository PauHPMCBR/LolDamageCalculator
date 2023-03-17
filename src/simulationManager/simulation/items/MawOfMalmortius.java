package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class MawOfMalmortius extends Item {
    public static final String name = "Maw of Malmortius";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 2900;

    public MawOfMalmortius() {
        super(name, type, cost);
        ad = 65;
        mr = 50;
    }

    @Override
    public Item makeCopy() {
        return new MawOfMalmortius();
    }
}
