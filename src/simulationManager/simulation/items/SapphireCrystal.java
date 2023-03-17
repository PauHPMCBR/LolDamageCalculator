package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class SapphireCrystal extends Item {
    public static final String name = "Sapphire Crystal";
    public static final ItemType type = ItemType.basic;
    public static final int cost = 350;

    public SapphireCrystal() {
        super(name, type, cost);
        mana = 250;
    }

    @Override
    public Item makeCopy() {
        return new SapphireCrystal();
    }
}
