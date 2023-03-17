package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class RunaansHurrycane extends Item {
    public static final String name = "Runaans Hurrycane";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 2600;

    public RunaansHurrycane() {
        super(name, type, cost);
        as = 45;
        crit = 20;
        ms = 7;
    }

    @Override
    public Item makeCopy() {
        return new RunaansHurrycane();
    }
}
