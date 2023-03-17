package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class SilvermereDawn extends Item {
    public static final String name = "Silvermere Dawn";
    public static final ItemType type = ItemType.legendary;
    public static final int cost = 3000;

    public SilvermereDawn() {
        super(name, type, cost);
        ad = 40;
        hp = 300;
        mr = 40;
    }

    @Override
    public Item makeCopy() {
        return new SilvermereDawn();
    }
}
