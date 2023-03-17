package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class BandleglassMirror extends Item {
    public static final String name = "Bandleglass Mirror";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 950;

    public BandleglassMirror() {
        super(name, type, cost);
        ap = 20;
        ah = 10;
        manaRegen = 50;
    }

    @Override
    public Item makeCopy() {
        return new BandleglassMirror();
    }
}
