package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class SpectresCowl extends Item {
    public static final String name = "Spectre's Cowl";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 1250;

    public SpectresCowl() {
        super(name, type, cost);
        hp = 250;
        mr = 25;
    }

    @Override
    public Item makeCopy() {
        return new SpectresCowl();
    }
}
