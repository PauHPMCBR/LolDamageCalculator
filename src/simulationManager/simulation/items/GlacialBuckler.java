package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class GlacialBuckler extends Item {
    public static final String name = "Glacial Buckler";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 900;

    public GlacialBuckler() {
        super(name, type, cost);
        ah = 10;
        armor = 20;
        mana = 250;
    }

    @Override
    public Item makeCopy() {
        return new GlacialBuckler();
    }
}
