package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class AegisOfTheLegion extends Item {
    public static final String name = "Aegis of the Legion";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 1200;

    public AegisOfTheLegion() {
        super(name, type, cost);
        ah = 10;
        armor = 30;
        mr = 30;
    }

    @Override
    public Item makeCopy() {
        return new AegisOfTheLegion();
    }
}
