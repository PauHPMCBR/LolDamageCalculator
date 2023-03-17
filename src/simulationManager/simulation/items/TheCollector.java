package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class TheCollector extends Item {
    public static final String name = "The Collector";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 3000;

    public TheCollector() {
        super(name, type, cost);
        ad = 55;
        crit = 20;
        lethality = 12;
    }

    public void extraDmg() {
        if (owner.getEnemy().getRelativeMissingHP() > 0.95)
            cs.damage.execute();
    }

    @Override
    public Item makeCopy() {
        return new TheCollector();
    }
}
