package simulationManager.simulation.items;

import simulationManager.simulation.Item;
import simulationManager.simulation.ItemType;

public class CaulfieldsWarhammer extends Item {
    public static final String name = "Caulfield's Warhammer";
    public static final ItemType type = ItemType.epic;
    public static final int cost = 1100;

    public CaulfieldsWarhammer() {
        super(name, type, cost);
        ad = 25;
        ah = 10;
    }

    @Override
    public Item makeCopy() {
        return new CaulfieldsWarhammer();
    }
}
